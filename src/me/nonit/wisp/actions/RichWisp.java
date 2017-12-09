package me.nonit.wisp.actions;

import me.nonit.wisp.Wisp;
import me.nonit.wisp.WispObject;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RichWisp extends Action
{
    private Economy economy;
    private List<String> messages;
    private String currency;

    public RichWisp( WispObject wisp )
    {
        super( "RichWisp" );

        this.wisp = wisp;
        this.economy = Wisp.getEconomy();
        this.messages = new ArrayList<>();
        this.currency = " " + economy.currencyNamePlural();
    }

    public boolean go()
    {
        OfflinePlayer player = Wisp.getPlayer( wisp.getOwnerUUID() );

        if( ((economy.getBalance( player ) < 10 && wisp.timeSince( getActionName() ) > 660) || wisp.timeSince( getActionName() ) > 2520) && wisp.timeSince( wisp.getLastAction() ) > 25 )
        {
            int i = (int)( Math.random() * 4 )+1;
            economy.depositPlayer( player, i );

            messages.add( "I found " + i + currency + " for you :D"  );
            messages.add( "Looks like your a little short on cash, here's " + i + currency );
            messages.add( "I made " + i + currency + " for you with Wisp Magic ;3" );
            messages.add( "Looky here! I found " + i + currency + " for you!" );

            Collections.shuffle( messages );

            wisp.msg( messages.get( 0 ) );
            wisp.updateActions( getActionName() );

            return true;
        }

        return false;
    }
}
