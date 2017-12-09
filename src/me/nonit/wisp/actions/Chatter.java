package me.nonit.wisp.actions;

import me.nonit.wisp.WispObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chatter extends Action
{
    private List<String> messages = new ArrayList<>();

    public Chatter( WispObject wisp )
    {
        super( "Chatter" );

        this.wisp = wisp;

        messages.add( "Loka is one of the oldest living worlds!" );
        messages.add( "Wisps used to be goblins once but something weird happened..." );
        messages.add( "Yo %player%, I'm liking your look today!" );
        messages.add( "You should vote with /vote ;D" );
        messages.add( "Maybe we can stop for lunch?" );
        messages.add( "We should go look for some treasure :D" );
        messages.add( "If you kill someone you can collect their bones..." );
        messages.add( "Wisps don't leave trails, but I'm always with you <3" );
        messages.add( "I think its time for a new nickname '%player%' is so last week!" );
        messages.add( "I'm not sure what I think of marriage... wisps don't do that." );
        messages.add( "Torches burn out after 30 minuets, don't forget!" );
        messages.add( "I'll still believe in you no matter what god you believe in!" );
        messages.add( "I HAD A DREAM YOU WON THE LOTTERY, but tickets! /lottery" );
        messages.add( "I like sleeping in your backpack ;3" );
        messages.add( "Be sure to welcome new players, Molly likes that..." );
        messages.add( "Butterflies taste with their feet." );
        messages.add( "Just so you know, you have %balance%!" );
        messages.add( "If we run out of money fishing is a great way to get more! /fish" );
        messages.add( "Do you think we could get something to eat with %balance%?" );
        messages.add( "Hey! Listen..." );
        messages.add( "Remember to drink water! I don't want you getting sick.." );
        messages.add( "You're my favorite person in the whole world %player% :}" );
        messages.add( "Are you thirsty? Perhaps stop for a drink!" );
        messages.add( "%player% ventures on so very heroic!..." );
        messages.add( "How about a game of rock paper skizzers?" );
        messages.add( "Uncle fred told me if you put bone meal on a sheep it'll grow wooly.." );
        messages.add( "Perhaps later we can get some bacon? ... Yah... Bacon <3" );
        messages.add( "Haunted blocks freak me out, watch out mining!" );
        messages.add( "If you get banned I die too, DON'T BREAK RULES!" );
        messages.add( "You can use /clear to wipe your messy inventory!" );
        messages.add( "You can make your own shops, I always wanted dto run a shop!" );
        messages.add( "You get more backpack space if you're a VIP, just sayin.." );
        messages.add( "Did you know you can craft a grappling hook?" );
        messages.add( "VIPs can make new gods, they are so cool :O" );
        messages.add( "Have you donated already? DONATE AGAIN FOR A FRIEND!" );
        messages.add( "Being staff is allot of work, be nice to those guys :P" );
        messages.add( "If you go too close to bedrock there's no air!" );
        messages.add( "Up in the sky the air gets thin, don't go too far.." );
        messages.add( "The acid rain started after Tana did an experiment.." );
        messages.add( "Im not a spy for Tana, nope, not at all..." );
        messages.add( "Steam baths restore health and feel great, I love em!" );
        messages.add( "Shipments are a great way to earn Fé, /sh list" );
        messages.add( "Mobs drop rare items sometimes, we should collect them!" );
        messages.add( "Some mob drops are so rare no one else ever finds them :O" );
        messages.add( "There are over a million unique items on MeC!" );
        messages.add( "You can craft machines if you know how!" );
        messages.add( "VIPs can power furnaces with lava, I wish I could do that..." );
        messages.add( "Lets go fishing! /fish" );
        messages.add( "Burp!" );
        messages.add( "Yawn..." );
        messages.add( "Thanks for spending time with me friend ;}" );
        messages.add( "You're my favorite %player% in the whole world!" );
        messages.add( "I hope we can be friends forever %player% :}" );
        messages.add( "La la la laaaaaa, I'm amazing!" );
        messages.add( "Bored!" );
    }

    public boolean go()
    {
        if( wisp == null )
        {
            return false;
        }

        if( ( ! wisp.getLastAction().equals( getActionName() ) && wisp.timeSince( wisp.getLastAction() ) > 120 ) || wisp.timeSince( wisp.getLastAction() ) > 880 )
        {
            Collections.shuffle( messages );
            wisp.msg( messages.get( 0 ) );

            wisp.updateActions( getActionName() );

            return true;
        }

        return false;
    }
}