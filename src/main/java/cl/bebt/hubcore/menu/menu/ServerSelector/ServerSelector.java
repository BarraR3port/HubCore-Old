package cl.bebt.hubcore.menu.menu.ServerSelector;

import cl.bebt.hubcore.MSGChanel.SendMsg;
import cl.bebt.hubcore.main;
import cl.bebt.hubcore.menu.Menu;
import cl.bebt.hubcore.menu.PlayerMenuUtility;
import cl.bebt.hubcore.utils.utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataType;

public class ServerSelector extends Menu {
    
    private final main plugin;
    
    public ServerSelector( PlayerMenuUtility playerMenuUtility , main plugin ){
        super( playerMenuUtility );
        this.plugin = plugin;
    }
    
    @Override
    public String getMenuName( ){
        return utils.chat( utils.getConfig( "server_selector.name_of_gui" ) );
    }
    
    @Override
    public int getSlots( ){
        return utils.getInt( "server_selector.slots" );
    }
    
    @Override
    public void handleMenu( InventoryClickEvent e ){
        Player p = ( Player ) e.getWhoClicked( );
        if ( e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).has( new NamespacedKey( plugin , "server" ) , PersistentDataType.STRING ) ) {
            String server = e.getCurrentItem( ).getItemMeta( ).getPersistentDataContainer( ).get( new NamespacedKey( plugin , "server" ) , PersistentDataType.STRING );
            p.closeInventory( );
            utils.tell( p , utils.getString( "serverconnection.connecting" ).replace( "%server%" , server ) );
            SendMsg.connectToServer( server , p );
            e.setCancelled( true );
        }
    }
    
    @Override
    public void setMenuItemsPlayer( Player p ){
        for ( int servers = 1; servers <= utils.getInt( "server_selector.amount_of_servers" ); servers++ ) {
            getInventory( ).setItem( utils.getInt( "server_selector.servers." + servers + ".slot" ) ,
                    makeItem( utils.getConfig( "server_selector.servers." + servers + ".item" ) ,
                            utils.getConfig( "server_selector.servers." + servers + ".name" ) ,
                            utils.getBoolean( "server_selector.servers." + servers + ".enchanted" ) ,
                            utils.getConfig( "server_selector.servers." + servers + ".server" ) ,
                            utils.getLore( servers ) ) );
        }
        for ( int i = 0; i < getSlots( ); i++ ) {
            if ( getInventory( ).getItem( i ) == null ) {
                getInventory( ).setItem( i , makeItem( Material.valueOf( utils.getConfig( "server_selector.panel" ) ) , " " ) );
            }
        }
    }
}
