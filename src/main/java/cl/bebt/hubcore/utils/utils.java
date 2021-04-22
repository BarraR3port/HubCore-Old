package cl.bebt.hubcore.utils;

import cl.bebt.hubcore.main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class utils {
    
    private static main plugin;
    
    public utils( main plugin ){
        utils.plugin = plugin;
    }
    
    public static String chat( String s ){
        return ChatColor.translateAlternateColorCodes( '&' , s );
    }
    
    public static void tell( CommandSender sender , String message ){
        sender.sendMessage( utils.chat( message ) );
    }
    
    public static void tell( Player player , String message ){
        player.sendMessage( utils.chat( message ) );
    }
    
    public static String getString( String s ){
        if ( plugin.getConfig( ).getString( "language" ).equalsIgnoreCase( "EN_NA" ) ) {
            return plugin.en_na.getConfig( ).getString( s );
        } else {
            return plugin.es_cl.getConfig( ).getString( s );
        }
    }
    
    public static Boolean getBoolean( String s ){
        return plugin.getConfig( ).getBoolean( s );
    }
    
    public static Integer getInt( String s ){
        return plugin.getConfig( ).getInt( s );
    }
    
    public static String getConfig( String s ){
        return plugin.getConfig( ).getString( s );
    }
    
    public static String getServer( ){
        return plugin.getConfig( ).getString( "bungeecord.server" );
    }
    
    public static ArrayList < String > getLore( Integer server ){
        ArrayList < String > Lore = new ArrayList <>( );
        for ( String msg : main.plugin.getConfig( ).getStringList( "server_selector.servers." + server + ".lore" ) ) {
            int amount = main.playersInServers.get( getConfig( "server_selector.servers." + server + ".server" ) );
            boolean online = main.serversStatus.get( getConfig( "server_selector.servers." + server + ".server" ) );
            if ( amount != 0 ) {
                msg = msg.replace( "%players%" , "&a" + amount );
            } else {
                msg = msg.replace( "%players%" , "&c" + amount );
            }
            if ( online ) {
                msg = msg.replace( "%status%" , "&a" + online );
            } else {
                msg = msg.replace( "%status%" , "&c" + online );
            }
            Lore.add( utils.chat( msg ) );
        }
        
        return Lore;
    }
    
}
    
    