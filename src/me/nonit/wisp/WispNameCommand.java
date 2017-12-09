package me.nonit.wisp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WispNameCommand implements CommandExecutor
{
    private Wisp plugin;

    public WispNameCommand( Wisp plugin )
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String s, String[] args )
    {
        if( !(sender instanceof Player) )
        {
            sender.sendMessage( "You must be a player to do this!" );
            return true;
        }

        if( args.length < 1 )
        {
            sender.sendMessage( Wisp.getPfx() + "Use /wispname <name> to set your wisps name!" );
            return true;
        }

        String name = args[0].trim();
        name = name.substring( 0, Math.min( name.length(), 8 ) );
        name = ChatColor.translateAlternateColorCodes( '&', name );

        Player player = (Player) sender;
        String ownerUUID = player.getUniqueId().toString();

        plugin.getWispDatabase().deleteWisp( ownerUUID );
        plugin.getWispDatabase().uploadWisp( ownerUUID, name );

        player.sendMessage( Wisp.getPfx() + "Your wisp has been renamed to " + name );

        WispObject wisp = plugin.getWispDatabase().downloadWisp( ownerUUID );
        wisp.msg( "From now on I will be called %wispname%, thank you master!" );

        return true;
    }
}
