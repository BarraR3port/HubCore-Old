package cl.bebt.hubcore.listerners;

import cl.bebt.hubcore.Items.Items;
import cl.bebt.hubcore.main;
import cl.bebt.hubcore.menu.menu.ServerSelector.ServerSelector;
import cl.bebt.hubcore.utils.utils;
import cl.bebt.staffcore.API.StaffCoreAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class HubEvents implements Listener {
    
    private final main plugin;
    
    public HubEvents( main plugin ){
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFoodLevelChange( FoodLevelChangeEvent e ){
        if ( e.getEntity( ) instanceof Player ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void checkDamage( EntityDamageEvent event ){
        if ( event.getEntity( ) instanceof Player ) {
            event.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreakBlock( BlockBreakEvent e ){
        Player p = e.getPlayer( );
        if ( !plugin.editHub.contains( p ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlaceBlock( BlockPlaceEvent e ){
        Player p = e.getPlayer( );
        if ( !plugin.editHub.contains( p ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHit( EntityDamageByEntityEvent e ){
        Entity mob = e.getDamager( );
        if ( mob instanceof Player ) {
            try {
                Player p = ( Player ) e.getDamager( );
                if ( !plugin.editHub.contains( p ) ) {
                    e.setCancelled( true );
                }
            } catch ( ClassCastException ignored ) {
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMobSpawn( CreatureSpawnEvent e ){
        if ( !utils.getBoolean( "spawnmobs" ) ) {
            e.setCancelled( true );
        }
    }
    
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage( EntityDamageEvent e ){
        Entity p = e.getEntity( );
        if ( p instanceof Player ) {
            if ( !plugin.editHub.contains( p ) ) {
                e.setCancelled( true );
                
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop( PlayerDropItemEvent e ){
        Player p = e.getPlayer( );
        if ( e.getItemDrop( ).getPersistentDataContainer( ).has( new NamespacedKey( plugin , "ServerSelector" ) , PersistentDataType.STRING ) ) {
            e.setCancelled( true );
        }
        if ( !plugin.editHub.contains( p ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemPickUp( EntityPickupItemEvent e ){
        Entity p = e.getEntity( );
        if ( p instanceof Player ) {
            if ( !plugin.editHub.contains( p ) ) {
                e.setCancelled( true );
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityInteract( PlayerInteractEntityEvent e ){
        Player p = e.getPlayer( );
        if ( !plugin.editHub.contains( p ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityInteract( PlayerCommandPreprocessEvent e ){
        Player p = e.getPlayer( );
        if ( plugin.getConfig( ).getStringList( "blocked_commands" ).contains( e.getMessage( ) ) && !p.hasPermission( "hubcore.use-any-command" ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTarget( EntityTargetEvent e ){
        if ( e.getTarget( ) instanceof Player ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBucketEmpty( PlayerBucketEmptyEvent e ){
        Player p = e.getPlayer( );
        if ( !plugin.editHub.contains( p ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBucketFill( PlayerBucketFillEvent e ){
        Player p = e.getPlayer( );
        if ( !plugin.editHub.contains( p ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBed( PlayerBedEnterEvent e ){
        Player p = e.getPlayer( );
        if ( !plugin.editHub.contains( p ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBowUse( EntityShootBowEvent e ){
        if ( e.getEntity( ) instanceof Player && e.getProjectile( ) instanceof Arrow ) {
            Player p = ( Player ) e.getEntity( );
            if ( !plugin.editHub.contains( p ) ) {
                e.setCancelled( true );
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFish( PlayerFishEvent e ){
        Player p = e.getPlayer( );
        if ( !plugin.editHub.contains( p ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConsume( PlayerItemConsumeEvent e ){
        Player p = e.getPlayer( );
        if ( !plugin.editHub.contains( p ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPickArrow( PlayerPickupArrowEvent e ){
        Player p = e.getPlayer( );
        if ( !plugin.editHub.contains( p ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRecipeDiscoverEvent( PlayerRecipeDiscoverEvent e ){
        Player p = e.getPlayer( );
        if ( !plugin.editHub.contains( p ) ) {
            e.setCancelled( true );
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTrow( ProjectileLaunchEvent e ){
        if ( e.getEntity( ).getShooter( ) instanceof Player ) {
            Player p = ( Player ) e.getEntity( ).getShooter( );
            if ( !plugin.editHub.contains( p ) ) {
                e.setCancelled( true );
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract( PlayerInteractEvent e ){
        Player p = e.getPlayer( );
        if ( e.getAction( ) == Action.RIGHT_CLICK_BLOCK ) {
            ItemStack hand = e.getPlayer( ).getInventory( ).getItemInMainHand( );
            String word = hand.getType( ).name( );
            String substring = word.substring( word.length( ) - 3 );
            try {
                if ( substring.equals( "EGG" ) ) {
                    if ( !plugin.editHub.contains( p ) ) {
                        e.setCancelled( true );
                    }
                }
            } catch ( StringIndexOutOfBoundsException ignored ) {
            }
        }
    }
    
    @SuppressWarnings("ConstantConditions")
    @EventHandler
    public void onInteract( PlayerInteractEvent e ){
        Player p = e.getPlayer( );
        Action a = e.getAction( );
        PersistentDataContainer PlayerData = p.getPersistentDataContainer( );
        if ( !plugin.editHub.contains( p ) ) {
            if ( !StaffCoreAPI.getTrolStatus( p.getName( ) ) ) {
                try {
                    if ( e.getAction( ).equals( Action.RIGHT_CLICK_BLOCK ) ) {
                        switch (e.getClickedBlock( ).getType( )) {
                            case CRIMSON_DOOR: {
                                e.setCancelled( true );
                            }
                            case CRIMSON_TRAPDOOR: {
                                e.setCancelled( true );
                            }
                            case WARPED_DOOR: {
                                e.setCancelled( true );
                            }
                            case WARPED_FENCE_GATE: {
                                e.setCancelled( true );
                            }
                            case CRIMSON_FENCE_GATE: {
                                e.setCancelled( true );
                            }
                            case WARPED_TRAPDOOR: {
                                e.setCancelled( true );
                            }
                            case CHEST: {
                                e.setCancelled( true );
                            }
                            case CHEST_MINECART: {
                                e.setCancelled( true );
                            }
                            case TRAPPED_CHEST: {
                                e.setCancelled( true );
                            }
                            case ENDER_CHEST: {
                                e.setCancelled( true );
                            }
                            case DARK_OAK_DOOR: {
                                e.setCancelled( true );
                            }
                            case OAK_DOOR: {
                                e.setCancelled( true );
                            }
                            case ACACIA_DOOR: {
                                e.setCancelled( true );
                            }
                            case BIRCH_DOOR: {
                                e.setCancelled( true );
                            }
                            case IRON_DOOR: {
                                e.setCancelled( true );
                            }
                            case JUNGLE_DOOR: {
                                e.setCancelled( true );
                            }
                            case SPRUCE_DOOR: {
                                e.setCancelled( true );
                            }
                            case ACACIA_TRAPDOOR: {
                                e.setCancelled( true );
                            }
                            case BIRCH_TRAPDOOR: {
                                e.setCancelled( true );
                            }
                            case DARK_OAK_TRAPDOOR: {
                                e.setCancelled( true );
                            }
                            case IRON_TRAPDOOR: {
                                e.setCancelled( true );
                            }
                            case JUNGLE_TRAPDOOR: {
                                e.setCancelled( true );
                            }
                            case OAK_TRAPDOOR: {
                                e.setCancelled( true );
                            }
                            case SPRUCE_TRAPDOOR: {
                                e.setCancelled( true );
                            }
                            case ACACIA_FENCE_GATE: {
                                e.setCancelled( true );
                            }
                            case BIRCH_FENCE_GATE: {
                                e.setCancelled( true );
                            }
                            case JUNGLE_FENCE_GATE: {
                                e.setCancelled( true );
                            }
                            case OAK_FENCE_GATE: {
                                e.setCancelled( true );
                            }
                            case SPRUCE_FENCE_GATE: {
                                e.setCancelled( true );
                            }
                            case DARK_OAK_FENCE_GATE: {
                                e.setCancelled( true );
                            }
                            case LEVER: {
                                e.setCancelled( true );
                            }
                            break;
                        }
                    }
                } catch ( NullPointerException | NoClassDefFoundError ignored ) {
                }
            }
        }
        if ( e.getHand( ) == EquipmentSlot.OFF_HAND ) return;
        
        if ( a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK || a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK ) {
            ItemStack itemInMainHand = p.getInventory( ).getItemInMainHand( );
            if ( Items.ServerSelector( ).equals( itemInMainHand ) ) {
                new ServerSelector( main.getPlayerMenuUtility( p ) , plugin ).open( p );
            }
            
        }
    }
    
    
}
