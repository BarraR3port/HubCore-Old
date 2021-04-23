package cl.bebt.hubcore.listerners;

import cl.bebt.hubcore.Items.Items;
import cl.bebt.hubcore.main;
import cl.bebt.hubcore.utils.SpawnManager;
import cl.bebt.hubcore.utils.utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoves implements Listener {
    private final main plugin;
    
    public PlayerMoves( main plugin ){
        this.plugin = plugin;
    }
    
    @EventHandler
    void onPlayerMovesEvent( PlayerMoveEvent e ){
        if ( utils.getBoolean( "launchpad.enabled") ){
            Player p = e.getPlayer( );
            Location blockUnder = p.getLocation();
            blockUnder.setY( blockUnder.getY() -1 );
            if ( p.getLocation().getBlock().getType().equals( Material.getMaterial( utils.getConfig( "launchpad.pressure_plate" ) ) ) && blockUnder.getBlock().getType().equals( Material.getMaterial( utils.getConfig( "launchpad.block_under" ) ) ) ){
                p.setVelocity( p.getLocation().getDirection().multiply( ( utils.getInt( "launchpad.power" ) * 0.5) ).setY( ( utils.getInt( "launchpad.power" ) * 0.7 ) ) );
                utils.playSound( p ,"launchpad");
            }
        }
        
    }
}