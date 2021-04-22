package cl.bebt.hubcore.MSGChanel;

import cl.bebt.hubcore.main;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class SendMsg {
    
    private static final main plugin = main.plugin;
    
    public static void getOnlinePlayersInSpecificServer( String serverTarget , String server ){
        if ( plugin.getConfig( ).getBoolean( "bungeecord.enabled" ) ) {
            Collection < Player > networkPlayers = ( Collection < Player > ) Bukkit.getServer( ).getOnlinePlayers( );
            if ( networkPlayers == null || networkPlayers.isEmpty( ) )
                return;
            ByteArrayDataOutput out = ByteStreams.newDataOutput( );
            out.writeUTF( "PSServer" );
            out.writeUTF( serverTarget );
            out.writeUTF( server );
            Bukkit.getServer( ).sendPluginMessage( plugin , "hc:msg" , out.toByteArray( ) );
        }
    }
    
    public static void getServerStatus( String serverTarget , String server ){
        if ( plugin.getConfig( ).getBoolean( "bungeecord.enabled" ) ) {
            Collection < Player > networkPlayers = ( Collection < Player > ) Bukkit.getServer( ).getOnlinePlayers( );
            if ( networkPlayers == null || networkPlayers.isEmpty( ) )
                return;
            ByteArrayDataOutput out = ByteStreams.newDataOutput( );
            out.writeUTF( "ServerStatus" );
            out.writeUTF( serverTarget );
            out.writeUTF( server );
            Bukkit.getServer( ).sendPluginMessage( plugin , "hc:msg" , out.toByteArray( ) );
        }
    }
    
    public static void connectToServer( String serverTarget, Player p ){
        if ( plugin.getConfig( ).getBoolean( "bungeecord.enabled" ) ) {
            Collection < Player > networkPlayers = ( Collection < Player > ) Bukkit.getServer( ).getOnlinePlayers( );
            if ( networkPlayers == null || networkPlayers.isEmpty( ) )
                return;
            ByteArrayDataOutput out = ByteStreams.newDataOutput( );
            out.writeUTF("Connect");
            out.writeUTF( serverTarget );
            p.sendPluginMessage( plugin, "BungeeCord", out.toByteArray());
        }
    }
    
}
