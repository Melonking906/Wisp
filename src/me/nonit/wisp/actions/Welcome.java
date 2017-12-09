package me.nonit.wisp.actions;

import me.nonit.wisp.Wisp;
import me.nonit.wisp.WispObject;
import net.milkbowl.vault.economy.Economy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Welcome extends Action
{
    private List<String> greetings = new ArrayList<>();
    private Wisp plugin;

    public Welcome( WispObject wisp, Wisp plugin )
    {
        super( "Welcome" );

        this.wisp = wisp;
        this.plugin = plugin;

        greetings.add( "Welcome Master %player%! :D" );
        greetings.add( "Welcome back %player%!" );
        greetings.add( "Yay your back, %player%!" );
        greetings.add( "I missed you %player%!" );
        greetings.add( "Greetings Master %player%" );
    }

    private String getRandomGreeting()
    {
        Collections.shuffle( greetings );
        return greetings.get( 0 );
    }

    public boolean go()
    {
        long delay = (long)( Math.random() * 20 ) + 20;

        plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new Runnable()
        {
            @Override
            public void run()
            {

                if( ! wisp.getLastAction().equals( "none" ) && wisp.timeSince( wisp.getLastAction() ) < 1200 )
                {
                    wisp.msg( "Wb" );
                }
                else if( (int)( Math.random() * 10 ) <= 3 )
                {
                    int gift = (int)( Math.random() * 4 ) + 1;

                    Economy economy = Wisp.getEconomy();
                    economy.depositPlayer( Wisp.getPlayer( wisp.getOwnerUUID() ), gift );

                    wisp.msg( getRandomGreeting() + " I found " + gift + " " + economy.currencyNamePlural() + " while you were away :}" );
                }
                else
                {
                    wisp.msg( getRandomGreeting() );
                }

                wisp.updateActions( getActionName() );

            }
        }, delay );
        return true;
    }
}