package me.nonit.wisp;

import me.nonit.wisp.actions.Action;
import me.nonit.wisp.actions.Chatter;
import me.nonit.wisp.actions.Holidays;
import me.nonit.wisp.actions.RichWisp;
import me.nonit.wisp.databases.SQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WispRunnable extends BukkitRunnable
{
    private Wisp plugin;
    private List<Action> actions;

    public WispRunnable( Wisp plugin )
    {
        this.plugin = plugin;

        this.actions = new ArrayList<>();
    }

    @Override
    public void run()
    {
        Collection<? extends Player> players = Bukkit.getServer().getOnlinePlayers();

        for( Player player : players )
        {
            actions.clear();

            WispObject wisp = plugin.getWispDatabase().downloadWisp( player.getUniqueId().toString() );

            actions.add( new Chatter( wisp ) );
            actions.add( new Holidays( wisp ) );
            actions.add( new RichWisp( wisp ) );

            Collections.shuffle( actions );

            for( Action action : actions )
            {
                if( action.go() )
                {
                    break;
                }
            }
        }

        // plugin.getWispDatabase().cleanCache();
    }
}
