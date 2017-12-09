package me.nonit.wisp.databases;

import me.nonit.wisp.Wisp;
import me.nonit.wisp.WispObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public abstract class SQL
{
    private Connection connection;
    private HashMap<String, WispObject> cache = new HashMap<>();

    protected Wisp plugin;

    public SQL( Wisp plugin )
    {
        this.plugin = plugin;

        plugin.getServer().getScheduler().runTaskTimerAsynchronously( plugin, new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    if( connection != null && ! connection.isClosed() )
                    {
                        connection.createStatement().execute( "/* ping */ SELECT 1" );
                    }
                }
                catch( SQLException e )
                {
                    connection = getNewConnection();
                }
            }
        }, 60 * 20, 60 * 20 );
    }

    protected abstract Connection getNewConnection();

    protected abstract String getName();

    public boolean query( String sql ) throws SQLException
    {
        return connection.createStatement().execute( sql );
    }

    public String getConfigName()
    {
        return getName().toLowerCase().replace(" ", "");
    }

    public boolean checkConnection()
    {
        try
        {
            if( connection == null || connection.isClosed() )
            {
                connection = getNewConnection();

                if( connection == null || connection.isClosed() )
                {
                    return false;
                }

                query( "CREATE TABLE IF NOT EXISTS `wisps` (`uuid` varchar(36) NOT NULL,`name` varchar(16) NOT NULL,`birthday` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,`mood` int(3) NOT NULL DEFAULT '100',PRIMARY KEY (`uuid`));" );
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public void disconnect()
    {
        cache.clear();

        try
        {
            if (connection != null)
            {
                connection.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public WispObject downloadWisp( String uuid )
    {
        WispObject wisp = null;

        if( cache.containsKey( uuid ) )
        {
            return cache.get( uuid );
        }

        if( !checkConnection() )
        {
            plugin.log( "Error with database" );
            return null;
        }

        PreparedStatement statement;
        try
        {
            statement = connection.prepareStatement( "SELECT name FROM wisps WHERE uuid = '" + uuid + "';" );

            ResultSet set = statement.executeQuery();

            while( set.next() )
            {
                wisp = new WispObject( uuid, set.getString( "name" ) );

                cache.put( uuid, wisp );

                return wisp;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }

        return wisp;
    }

    public void removeCachedWisp( String uuid )
    {
        cache.remove( uuid );
    }

    public void uploadWisp( String uuid, String name )
    {
        if( ! checkConnection() )
        {
            plugin.log( "Error with database" );
            return;
        }

        if( downloadWisp( uuid ) != null )
        {
            deleteWisp( uuid );
        }

        PreparedStatement statement;
        try
        {
            statement = connection.prepareStatement( "INSERT INTO wisps (uuid, name) VALUES ('" + uuid + "','" + name + "');" );

            statement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteWisp( String uuid )
    {
        if( ! checkConnection() )
        {
            plugin.log( "Error with database" );
            return;
        }

        PreparedStatement statement;
        try
        {
            statement = connection.prepareStatement( "DELETE FROM wisps WHERE uuid = '" + uuid + "';" );

            statement.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        cache.remove( uuid );
    }
}
