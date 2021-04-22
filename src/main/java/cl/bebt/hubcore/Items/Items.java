package cl.bebt.hubcore.Items;

import cl.bebt.staffcore.main;
import cl.bebt.staffcore.utils.utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class Items {
    
    
    public static ItemStack ServerSelector( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack server = new ItemStack( Material.COMPASS );
        ItemMeta metaServer = server.getItemMeta( );
        metaServer.setDisplayName( utils.chat( "&5Server Selector:" ) );
        metaServer.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "ServerSelector" ) , PersistentDataType.STRING , "ServerSelector" );
        lore.add( utils.chat( "&aClick to open the server selector" ) );
        metaServer.setLore( lore );
        server.setItemMeta( metaServer );
        return server;
    }
    
    
}
