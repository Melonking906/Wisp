package me.nonit.wisp;

import me.nonit.wisp.actions.*;
import me.nonit.wisp.actions.subactions.MakeWisp;
import me.nonit.wisp.databases.SQL;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class WispListener implements Listener
{
    private Wisp plugin;
    private SQL db;

    public WispListener( Wisp plugin )
    {
        this.plugin = plugin;
        this.db = plugin.getWispDatabase();
    }

    @EventHandler
    public void onJoin( PlayerJoinEvent event )
    {
        String ownerUUID = event.getPlayer().getUniqueId().toString();
        WispObject wisp = db.downloadWisp( ownerUUID );

        if( wisp == null )
        {
            new MakeWisp( plugin, ownerUUID ).create();
            return;
        }

        new Welcome( wisp, plugin ).go();
    }

//    @EventHandler
//    public void onLeave( PlayerQuitEvent event )
//    {
//        String ownerUUID = event.getPlayer().getUniqueId().toString();
//        db.removeCachedWisp( ownerUUID );
//    }

    @EventHandler
    public void onBreak( BlockBreakEvent event )
    {
        if( event.getBlock().getType().equals( Material.STONE ) )
        {
            WispObject wisp = db.downloadWisp( event.getPlayer().getUniqueId().toString() );

            new MineHelp( wisp ).go();
        }

        if( event.getBlock().getType().equals( Material.LOG ) )
        {
            WispObject wisp = db.downloadWisp( event.getPlayer().getUniqueId().toString() );

            new Tidbits( wisp, "TreeCutting" ).go();
        }
    }

    @EventHandler
    public void onSignPlace( SignChangeEvent event )
    {
        WispObject wisp = db.downloadWisp( event.getPlayer().getUniqueId().toString() );

        new Tidbits( wisp, "SignPlace" ).go();
    }

    @EventHandler
    public void onDamage( EntityDamageEvent event )
    {
        if( event.getEntityType().equals( EntityType.PLAYER ) )
        {
            WispObject wisp = db.downloadWisp( event.getEntity().getUniqueId().toString() );

            if( event.getCause().equals( EntityDamageEvent.DamageCause.STARVATION ) )
            {
                new DontStarve( wisp ).go();
            }
        }
    }

    @EventHandler
    public void onRespawn( PlayerRespawnEvent event )
    {
        WispObject wisp = db.downloadWisp( event.getPlayer().getUniqueId().toString() );

        new Respawn( wisp, plugin ).go();
    }

    @EventHandler
    public void onSign( PlayerEditBookEvent event )
    {
        if( event.isSigning() )
        {
            WispObject wisp = db.downloadWisp( event.getPlayer().getUniqueId().toString() );

            new Tidbits( wisp, "Signing" ).go();
        }
    }

    //@EventHandler
    //public void onInvAccess( InventoryClickEvent event )
    //{
    //    WispObject wisp = db.downloadWisp( event.getWhoClicked().getUniqueId().toString() );
    //
    //    new Tidbits( wisp, "InvSort" ).go();
    //}
}