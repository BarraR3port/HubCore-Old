package cl.bebt.hubcore.utils;

import cl.bebt.hubcore.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static java.lang.Math.round;

public class SpawnManager {
    public static void setSpawn( Player p ){
        main.plugin.reloadConfig( );
        int x = round( ( int ) round( p.getLocation( ).getX( ) ) );
        int y = round( ( int ) round( p.getLocation( ).getY( ) ) );
        int z = round( ( int ) round( p.getLocation( ).getZ( ) ) );
        main.plugin.getConfig( ).set( "spawn.world" , p.getWorld( ).getName( ) );
        main.plugin.getConfig( ).set( "spawn.x" , x );
        main.plugin.getConfig( ).set( "spawn.y" , y );
        main.plugin.getConfig( ).set( "spawn.z" , z );
        main.plugin.saveConfig( );
        main.plugin.reloadConfig( );
        utils.tell( p , "world:" + p.getWorld( ).getName( ) );
        utils.tell( p , "x:" + x );
        utils.tell( p , "y:" + y );
        utils.tell( p , "z:" + z );
        utils.tell( p , utils.getString( "server_prefix" ) + utils.getString( "spawn.set" ) );
    }
    
    public static void tp( Player p ){
        try {
            Location spawn = new Location( Bukkit.getWorld( main.plugin.getConfig( ).getString( "spawn.world" ) ) ,
                    main.plugin.getConfig( ).getDouble( "spawn.x" ) , main.plugin.getConfig( ).getDouble( "spawn.y" ) ,
                    main.plugin.getConfig( ).getDouble( "spawn.z" ) );
            p.teleport( spawn );
            utils.tell( p , utils.getString( "server_prefix" ) + utils.getString( "spawn.tp_to_spawn" ) );
        } catch ( NullPointerException error ) {
            utils.tell( p , utils.getString( "server_prefix" ) + utils.getString( "spawn.notset" ) );
        }
    }
    
    public static void tpOthers( Player p , Player target ){
        try {
            Location spawn = new Location( Bukkit.getWorld( main.plugin.getConfig( ).getString( "spawn.world" ) ) ,
                    main.plugin.getConfig( ).getDouble( "spawn.x" ) , main.plugin.getConfig( ).getDouble( "spawn.y" ) ,
                    main.plugin.getConfig( ).getDouble( "spawn.z" ) );
            target.teleport( spawn );
            utils.tell( p , utils.getString( "server_prefix" ) + utils.getString( "spawn.tp_player_to_spawn" ).replace( "%player%" , target.getName( ) ) );
            utils.tell( target , utils.getString( "server_prefix" ) + utils.getString( "spawn.tp_to_spawn" ) );
        } catch ( NullPointerException error ) {
            utils.tell( p , utils.getString( "server_prefix" ) + utils.getString( "spawn.notset" ) );
        }
    }
    
    public static void remove( Player p ){
        if ( exist( ) ) {
            main.plugin.reloadConfig( );
            main.plugin.getConfig( ).set( "spawn.world" , null );
            main.plugin.getConfig( ).set( "spawn.x" , null );
            main.plugin.getConfig( ).set( "spawn.y" , null );
            main.plugin.getConfig( ).set( "spawn.z" , null );
            main.plugin.saveConfig( );
            main.plugin.reloadConfig( );
            utils.tell( p , utils.getString( "server_prefix" ) + utils.getString( "spawn.removed" ) );
        } else {
            utils.tell( p , utils.getString( "server_prefix" ) + utils.getString( "spawn.notset" ) );
        }
    }
    
    public static boolean exist( ){
        return !(main.plugin.getConfig( ).get( "spawn.world" ) == null);
    }
}
