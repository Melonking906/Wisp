package me.nonit.wisp;

import me.nonit.wisp.databases.MySQL;
import me.nonit.wisp.databases.SQL;
import me.nonit.wisp.databases.SQLite;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Wisp extends JavaPlugin
{
    private static final String PREFIX = ChatColor.YELLOW + "[Wisp]" + ChatColor.GREEN + " ";

    private static Economy economy = null;
    private Set<SQL> databases;
    private SQL db;

    public Wisp()
    {
        databases = new HashSet<>();
    }

    @Override
    public void onEnable()
    {
        databases.add( new MySQL( this ) );
        databases.add( new SQLite( this ) );

        this.saveDefaultConfig(); // Makes a config is one does not exist.

        setupDatabase();
        setupEconomy();

        getCommand( "wispname" ).setExecutor( new WispNameCommand( this ) );

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents( new WispListener( this ), this );

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask( this, new WispRunnable( this ), 20L, 1200L ); // Runs every 1 minutes.

        for( Player player : Bukkit.getOnlinePlayers() )
        {
            db.downloadWisp( player.getUniqueId().toString() );
        }
    }

    @Override
    public void onDisable()
    {
        db.disconnect();
    }

    private boolean setupDatabase()
    {
        String type = getConfig().getString("type");

        db = null;

        for ( SQL database : databases )
        {
            if ( type.equalsIgnoreCase( database.getConfigName() ) )
            {
                this.db = database;
                log( "Database set to " + database.getConfigName() + "." );
                break;
            }
        }
        if ( db == null)
        {
            log( "Database type does not exist!" );
            return false;
        }
        return true;
    }

    public void log( String message )
    {
        getLogger().info( message );
    }

    public SQL getWispDatabase() { return db; }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public static Economy getEconomy() { return economy; }

    public static Player getPlayer( String uuid )
    {
        return Bukkit.getServer().getPlayer( UUID.fromString( uuid ) );
    }

    public static String getPfx()
    {
        return PREFIX;
    }
}