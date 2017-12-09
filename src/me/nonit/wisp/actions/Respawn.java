package me.nonit.wisp.actions;

import me.nonit.wisp.Wisp;
import me.nonit.wisp.WispObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Respawn extends Action
{
    private Wisp plugin;
    private List<String> messages = new ArrayList<String>();

    public Respawn( WispObject wisp, Wisp plugin )
    {
        super( "Respawn" );

        this.wisp = wisp;
        this.plugin = plugin;

        messages.add( "You'll do better next time!" );
        messages.add( "They got the drop on ya huh dex?" );
        messages.add( "How could you let such a thing happen!" );
        messages.add( "The server is on easy mode..." );
        messages.add( "Where did it come from ;-;" );
    }

    public boolean go()
    {
        if( wisp.timeSince( getActionName() ) > 30 )
        {
            plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new Runnable()
            {
                @Override
                public void run()
                {

                    Collections.shuffle( messages );

                    wisp.msg( messages.get( 0 ) );

                    wisp.updateActions( getActionName() );

                }
            }, 5L );
            return true;
        }
        return false;
    }
}
