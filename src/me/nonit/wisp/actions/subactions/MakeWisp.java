package me.nonit.wisp.actions.subactions;

import me.nonit.wisp.Wisp;
import me.nonit.wisp.WispObject;
import me.nonit.wisp.databases.SQL;
import net.milkbowl.vault.economy.Economy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MakeWisp
{
    private SQL db;
    private String ownerUUID;
    private List<String> names = new ArrayList<String>();
    private Wisp plugin;

    public MakeWisp( Wisp plugin, String ownerUUID )
    {
        this.db = plugin.getWispDatabase();
        this.ownerUUID = ownerUUID;
        this.plugin = plugin;

        names.add( "Lumi" );
        names.add( "Lota" );
        names.add( "Thana" );
        names.add( "Nanita" );
        names.add( "Atol" );
        names.add( "Cotala" );
        names.add( "Tana" );
        names.add( "Fay" );
        names.add( "Navi" );
        names.add( "Shar" );
        names.add( "Peri" );
        names.add( "Aine" );
        names.add( "Nyx" );
        names.add( "Nata" );
        names.add( "Loxy" );
        names.add( "Ilona" );
        names.add( "Evvie" );
        names.add( "Cana" );
        names.add( "Knife" );
        names.add( "7 of 9" );
        names.add( "Toto" );
        names.add( "Plex" );
        names.add( "Storm" );
        names.add( "Loki" );
        names.add( "Heron" );
        names.add( "Maple" );

    }

    private String getRandomName()
    {
        Collections.shuffle( names );
        return names.get( 0 );
    }

    public void create()
    {
        plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, new Runnable()
        {
            @Override
            public void run()
            {

                String name = getRandomName();
                db.uploadWisp( ownerUUID, name );

                WispObject wisp = db.downloadWisp( ownerUUID );

                wisp.msg( "Welcome %player%, I'm " + name + ", your new wisp companion! I can't wait to explore Loy with you :D" );

            }
        }, 25L );
    }
}