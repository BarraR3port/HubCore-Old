package cl.bebt.hubcore.MSGChanel;

import cl.bebt.hubcore.main;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessage implements PluginMessageListener {
    
    private final main plugin = main.plugin;
    
    public void onPluginMessageReceived( String channel , Player p , byte[] msg ){
        if ( !this.plugin.getConfig( ).getBoolean( "bungeecord.enabled" ) )
            return;
        if ( channel.equals( "hc:msg" ) ) {
            ByteArrayDataInput in = ByteStreams.newDataInput( msg );
            String subChannel = in.readUTF( );
            if ( subChannel.equalsIgnoreCase( "PPServer" ) ) {
                int amount = in.readInt( );
                String serverTarget = in.readUTF( );
                String server = in.readUTF( );
                PlayersInSpecificServer( amount , serverTarget , server );
            }
            if ( subChannel.equalsIgnoreCase( "SServerStatus" ) ) {
                String serverTarget = in.readUTF( );
                boolean online = in.readBoolean( );
                String server = in.readUTF( );
                ServerStatus( serverTarget , online , server );
            }
        }
    }
    
    
    public void PlayersInSpecificServer( int amount , String serverTarget , String server ){
        if ( !this.plugin.getConfig( ).getString( "bungeecord.server" ).equalsIgnoreCase( server ) ) return;
        main.playersInServers.put( serverTarget , amount );
    }
    
    public void ServerStatus( String serverTarget , boolean online , String server ){
        if ( !this.plugin.getConfig( ).getString( "bungeecord.server" ).equalsIgnoreCase( server ) ) return;
        main.serversStatus.put( serverTarget , online );
    }
    
}
