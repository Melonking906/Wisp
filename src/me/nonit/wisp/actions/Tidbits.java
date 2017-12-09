package me.nonit.wisp.actions;

import me.nonit.wisp.WispObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tidbits extends Action
{
    private String tidbit;

    public Tidbits( WispObject wisp, String tidbit )
    {
        super( "Tidbits" );

        this.wisp = wisp;
        this.tidbit = tidbit;
    }

    public boolean go()
    {
        List<String> messages;
        int delay = 0;

        if( tidbit.equals( "Signing" ) )
        {
            messages = getSigningMessages();
            delay = 0;
        }
        else if( tidbit.equals( "TreeCutting" ) )
        {
            messages = getTreeCuttingMessages();
            delay = 1620;
        }
        else if( tidbit.equals( "SignPlace" ) )
        {
            messages = getSignPlaceMessages();
            delay = 1080;
        }
        else if( tidbit.equals( "InvSort" ) )
        {
            messages = getInvSortMessages();
            delay = 1440;
        }
        else
        {
            messages = new ArrayList<>();
            messages.add( "Tell Melonking there is a bumpty in the wisp tids!" );
        }

        if( wisp.timeSince( tidbit ) > delay && wisp.timeSince( wisp.getLastAction() ) > 25 )
        {
            Collections.shuffle( messages );

            wisp.msg( messages.get( 0 ) );

            wisp.updateActions( tidbit );
            return true;
        }

        return false;
    }

    private List<String> getSigningMessages()
    {
        List<String> messages = new ArrayList<>();

        messages.add( "Yay we made a book :D We can also /book" );
        messages.add( "Yipee a book :D We can edit it with /book" );
        messages.add( "Wisps write lots too! We can edit them with /book for sneeky edits!" );

        return messages;
    }

    private List<String> getTreeCuttingMessages()
    {
        List<String> messages = new ArrayList<>();

        messages.add( "We better make sure to replant that, wisps live there!" );
        messages.add( "Us wisps and trees has a war once..." );
        messages.add( "Do you think that tree had a name?" );
        messages.add( "I'm a lumberjack and I'm Ok!" );

        return messages;
    }

    private List<String> getSignPlaceMessages()
    {
        List<String> messages = new ArrayList<>();

        messages.add( "Colourful signs are best :D" );
        messages.add( "I hope it says something nice, I die if your banned!" );
        messages.add( "Perhaps make a sign about me? ;D" );

        return messages;
    }

    private List<String> getInvSortMessages()
    {
        List<String> messages = new ArrayList<>();

        messages.add( "That inventory looks a lil messy... try /sort" );
        messages.add( "Would you like me to sort your inventory? /sort" );
        messages.add( "I can sort your inventory for you! /sort" );

        return messages;
    }
}