package me.nonit.wisp;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class WispObject
{
    private final String name;
    private final String ownerUUID;

    private String lastAction;
    private HashMap<String, Date> actions = new HashMap<String, Date>();

    public WispObject( String ownerUUID, String name )
    {
        this.name = name;
        this.ownerUUID = ownerUUID;

        lastAction = "none";
        initialiseAction( lastAction );
    }

    public void msg( String msg )
    {
        Player player = Wisp.getPlayer( ownerUUID );

        msg = msg.replace( "%player%", player.getDisplayName() + ChatColor.GRAY );
        msg = msg.replace( "%wispname%", name + ChatColor.GRAY );
        msg = msg.replace( "%balance%", Wisp.getEconomy().getBalance( Wisp.getPlayer( ownerUUID ) ) + " " + Wisp.getEconomy().currencyNamePlural() + ChatColor.GRAY );

        player.sendMessage( ChatColor.DARK_GRAY + "Wisp " + ChatColor.LIGHT_PURPLE + name + ChatColor.GRAY + ": " + msg );
    }

    public void updateActions( String action )
    {
        actions.put( action, new Date() );

        lastAction = action;
    }

    public long timeSince( String action )
    {
        if( ! actions.containsKey( action ) )
        {
            initialiseAction( action );
        }

        Date currentDate = new Date();
        return (currentDate.getTime()-actions.get( action ).getTime())/1000;
    }

    public String getName() { return name; }

    public String getOwnerUUID() { return ownerUUID; }

    public String getLastAction() { return lastAction; }

    private void initialiseAction( String action )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.MINUTE, -5 );

        actions.put( action, calendar.getTime() );
    }
}