package cl.bebt.hubcore.utils;

import cl.bebt.hubcore.main;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
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
    public static Integer getItemInt( String s ){
        return plugin.items.getConfig( ).getInt( s );
    }
    
    public static String getConfig( String s ){
        return plugin.getConfig( ).getString( s );
    }
    
    public static String getItemString( String s ){
        return plugin.items.getConfig( ).getString( s );
    }
    
    public static boolean getItemBoolean( String s ){
        return plugin.items.getConfig( ).getBoolean( s );
    }
    
    public static String getServer( ){
        return plugin.getConfig( ).getString( "bungeecord.server" );
    }
    
    public static ArrayList < String > getLore( Integer server ){
        ArrayList < String > Lore = new ArrayList <>( );
        for ( String msg : plugin.items.getConfig( ).getStringList( "server_selector.servers." + server + ".lore" ) ) {
            int amount = main.playersInServers.get( getItemString( "server_selector.servers." + server + ".server" ) );
            boolean online = main.serversStatus.get( getItemString( "server_selector.servers." + server + ".server" ) );
            if ( amount != 0 ) {
                msg = msg.replace( "%players%" , "&a" + amount );
            } else {
                msg = msg.replace( "%players%" , "&c" + amount );
            }
            if ( online ) {
                msg = msg.replace( "%status%" , "&aOnline" );
            } else {
                msg = msg.replace( "%status%" , "&cOffline" );
            }
            Lore.add( utils.chat( msg ) );
        }
        
        return Lore;
    }
    
    public static void reloadConfig( ){
        plugin.reloadConfig();
        plugin.items.reloadConfig();
        plugin.es_cl.reloadConfig();
        plugin.en_na.reloadConfig();
    }
    
    public static void playSound( Player p , String path ){
        try{
            if ( plugin.getConfig( ).getBoolean( "sounds" ) ) {
                Sound sound = Sound.valueOf( plugin.getConfig( ).getString( "custom_sounds." + path ) );
                p.playSound( p.getLocation( ) , sound , 1 , 1 );
            }
        } catch ( IllegalArgumentException ignored ){ }
    }
    
    public static void PlayParticle( Player p , String path ){
        try{
            if ( plugin.getConfig( ).getBoolean( "custom_particles." + path + ".enabled" ) ) {
                Particle particle = Particle.valueOf( plugin.getConfig( ).getString( "custom_particles." + path + ".particle" ) );
                int count = plugin.getConfig( ).getInt( "custom_particles." + path + ".count" );
                int times = plugin.getConfig( ).getInt( "custom_particles." + path + ".number_of_times" );
                int offSetX = plugin.getConfig( ).getInt( "custom_particles." + path + ".offSetX" );
                int offSetY = plugin.getConfig( ).getInt( "custom_particles." + path + ".offSetY" );
                int offSetZ = plugin.getConfig( ).getInt( "custom_particles." + path + ".offSetZ" );
                for ( int i = 0; i < times; i++ ) {
                    p.getWorld( ).spawnParticle( particle , p.getLocation( ) , count , offSetX , offSetY , offSetZ );
                }
            }
        } catch ( IllegalArgumentException ignored ){ }
    }
    
    
}
    
    