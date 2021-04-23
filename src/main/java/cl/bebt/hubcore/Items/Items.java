package cl.bebt.hubcore.Items;

import cl.bebt.hubcore.main;
import cl.bebt.hubcore.utils.utils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Objects;

public class Items {
    
    public static ItemStack ServerSelector( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack server = new ItemStack( Objects.requireNonNull( Material.getMaterial( utils.getItemString( "server_selector.item" ) ) ) );
        ItemMeta metaServer = server.getItemMeta( );
        metaServer.setDisplayName( utils.chat( utils.getItemString("server_selector.name") ) );
        metaServer.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "ServerSelector" ) , PersistentDataType.STRING , "ServerSelector" );
        lore.add( utils.chat( "&aClick to open the server selector" ) );
        metaServer.setLore( lore );
        server.setItemMeta( metaServer );
        return server;
    }
    
    public static ItemStack HidePlayers( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack server = new ItemStack( Objects.requireNonNull( Material.getMaterial( utils.getItemString( "hide_players.item" ) ) ), utils.getItemInt( "hide_players.amount" ) );
        ItemMeta metaServer = server.getItemMeta( );
        metaServer.setDisplayName( utils.chat( utils.getItemString("hide_players.name") ) );
        metaServer.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "HidePlayers" ) , PersistentDataType.STRING , "HidePlayers" );
        lore.add( utils.chat( "&aClick to open the server selector" ) );
        metaServer.setLore( lore );
        server.setItemMeta( metaServer );
        return server;
    }
    
    public static ItemStack ShowPlayers( ){
        ArrayList < String > lore = new ArrayList <>( );
        ItemStack server = new ItemStack( Objects.requireNonNull( Material.getMaterial( utils.getItemString( "show_players.item" ) ) ), utils.getItemInt( "show_players.amount" ) );
        ItemMeta metaServer = server.getItemMeta( );
        metaServer.setDisplayName( utils.chat( utils.getItemString("show_players.name") ) );
        metaServer.getPersistentDataContainer( ).set( new NamespacedKey( main.plugin , "ShowPlayers" ) , PersistentDataType.STRING , "ShowPlayers" );
        lore.add( utils.chat( "&aClick to open the server selector" ) );
        metaServer.setLore( lore );
        server.setItemMeta( metaServer );
        return server;
    }
    
    
}
