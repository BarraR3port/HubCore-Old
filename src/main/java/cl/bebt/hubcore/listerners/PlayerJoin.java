package cl.bebt.hubcore.listerners;

import cl.bebt.hubcore.Items.Items;
import cl.bebt.hubcore.main;
import cl.bebt.hubcore.utils.SpawnManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private final main plugin;
    
    public PlayerJoin( main plugin ){
        this.plugin = plugin;
    }
    
    @EventHandler
    void onPlayerJoinEvent( PlayerJoinEvent e ){
        Player p = e.getPlayer( );
        if ( this.plugin.getConfig( ).getBoolean( "spawn.tp_on_join" ) && SpawnManager.exist( ) ) {
            SpawnManager.tp( p );
        }
        p.getInventory( ).clear( );
        p.getInventory( ).setItem( 4 , Items.ServerSelector( ) );
    }
}