package me.nonit.wisp.actions;

import me.nonit.wisp.WispObject;

import java.util.*;

public class Holidays extends Action
{
    private HashMap<WispDate,Holiday> holidays = new HashMap<WispDate,Holiday>();

    public Holidays( WispObject wisp )
    {
        super( "Holidays" );

        this.wisp = wisp;

        WispDate wispDay = new WispDate( 5, 7 );
        holidays.put( wispDay, new Holiday( "Wisp Day" ) );
        holidays.get( wispDay ).addMessage( "Its Wisp Day day! They day the wisps come out to play :D" );
        holidays.get( wispDay ).addMessage( "Hail your wisp overlords!" );
        holidays.get( wispDay ).addMessage( "On this day, I am the master >:D" );

        WispDate mollyDay = new WispDate( 6, 4 );
        holidays.put( mollyDay, new Holiday( "Molly Day" ) );
        holidays.get( mollyDay ).addMessage( "Its Molly Day :O" );
        holidays.get( mollyDay ).addMessage( "Who's this 'Molly' anyway!" );
        holidays.get( mollyDay ).addMessage( "Loka is another year old today!" );

        WispDate towelDay = new WispDate( 25, 5 );
        holidays.put( towelDay, new Holiday( "Towel Day" ) );
        holidays.get( towelDay ).addMessage( "Happy Towel Day!" );
        holidays.get( towelDay ).addMessage( "I hope you have your towel %player%!" );
        holidays.get( towelDay ).addMessage( "Towel Towel Towel Day!" );

        WispDate piDay = new WispDate( 14, 3 );
        holidays.put( piDay, new Holiday( "Pi Day" ) );
        holidays.get( piDay ).addMessage( "Happy Pi Day!" );
        holidays.get( piDay ).addMessage( "3.14159265358979323" );

        WispDate samhain = new WispDate( 31, 10 );
        holidays.put( samhain, new Holiday( "Samhain" ) );
        holidays.get( samhain ).addMessage( "Happy Samhain :D" );
        holidays.get( samhain ).addMessage( "May the new year bring you good fortune :D" );
        holidays.get( samhain ).addMessage( "The world dawns again today!" );

        WispDate yule = new WispDate( 21, 12 );
        holidays.put( yule, new Holiday( "Yule" ) );
        holidays.get( yule ).addMessage( "Merry Yule!" );
        holidays.get( yule ).addMessage( "Live Long and Prosper!" );
        holidays.get( yule ).addMessage( "I hope ye have a wonderful yule %player% :)" );

        WispDate imbolc = new WispDate( 2, 2 );
        holidays.put( imbolc, new Holiday( "Imbolc" ) );
        holidays.get( imbolc ).addMessage( "Good Imbolc to you!" );
        holidays.get( imbolc ).addMessage( "Have a great Imbolic :D" );

        WispDate eostara = new WispDate( 21, 3 );
        holidays.put( eostara, new Holiday( "Eostara" ) );
        holidays.get( eostara ).addMessage( "Today is Eostara" );
        holidays.get( eostara ).addMessage( "Have a splendid Eostara %player% :D" );
        holidays.get( eostara ).addMessage( "Happy Eostara!" );

        WispDate beltane = new WispDate( 1, 5 );
        holidays.put( beltane, new Holiday( "Beltane" ) );
        holidays.get( beltane ).addMessage( "Its Beltane today :D" );
        holidays.get( beltane ).addMessage( "A joyful Beltane to you %player%!" );
        holidays.get( beltane ).addMessage( "Have a great Beltane!" );

        WispDate litha = new WispDate( 21, 6 );
        holidays.put( litha, new Holiday( "Litha" ) );
        holidays.get( litha ).addMessage( "Good Litha to you!" );
        holidays.get( litha ).addMessage( "My Litha plan is to rule the world >:D" );
        holidays.get( litha ).addMessage( "Happy Litha %player%" );

        WispDate lammas = new WispDate( 1, 8 );
        holidays.put( lammas, new Holiday( "Lammas" ) );
        holidays.get( lammas ).addMessage( "A fruitful Lammas to you!" );
        holidays.get( lammas ).addMessage( "Happy Lammas %player% !" );

        WispDate mabon = new WispDate( 21, 9 );
        holidays.put( mabon, new Holiday( "Mabon" ) );
        holidays.get( mabon ).addMessage( "Thanks for playing Oki this Mabon :3" );
        holidays.get( mabon ).addMessage( "Happy Mabon!" );
        holidays.get( mabon ).addMessage( "Enjoy Mabon %player%!" );

        WispDate christmas = new WispDate( 25, 12 );
        holidays.put( christmas, new Holiday( "Christmas" ) );
        holidays.get( christmas ).addMessage( "Merry Christmas!" );
        holidays.get( christmas ).addMessage( "Ho Ho Ho!" );
        holidays.get( christmas ).addMessage( "Have you been good this year?" );
    }

    public boolean go()
    {
        for( Map.Entry<WispDate,Holiday> entry : holidays.entrySet() )
        {
            if( entry.getKey().isToday() )
            {
                if( wisp.timeSince( getActionName() ) > 1920 && wisp.timeSince( wisp.getLastAction() ) > 35 )
                {
                    Holiday holiday = entry.getValue();

                    wisp.msg( holiday.getRandomMessage() );

                    wisp.updateActions( getActionName() );

                    return true;
                }
            }
        }

        return false;
    }

    protected class WispDate
    {
        private final int day;
        private final int month;

        public WispDate( int day, int month )
        {
            this.day = day;
            this.month = month;
        }

        public boolean isToday()
        {
            Calendar calendar = Calendar.getInstance();

            int thisDay = calendar.get( Calendar.DAY_OF_MONTH );
            int thisMonth = calendar.get( Calendar.MONTH )+1;

            if( thisDay == day && thisMonth == month )
            {
                return true;
            }
            return false;
        }
    }

    protected class Holiday
    {
        private final  String name;
        private List<String> messages = new ArrayList<String>();

        public Holiday( String name )
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }

        public String getRandomMessage()
        {
            Collections.shuffle( messages );

            return messages.get( 0 );
        }

        public void addMessage( String message )
        {
            messages.add( message );
        }
    }
}
