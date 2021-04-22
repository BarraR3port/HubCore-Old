package cl.bebt.hubcore;

import cl.bebt.hubcore.MSGChanel.PluginMessage;
import cl.bebt.hubcore.MSGChanel.SendMsg;
import cl.bebt.hubcore.commands.*;
import cl.bebt.hubcore.configs.EN_NA;
import cl.bebt.hubcore.configs.ES_CL;
import cl.bebt.hubcore.configs.items;
import cl.bebt.hubcore.configs.locationsConfig;
import cl.bebt.hubcore.listerners.HubEvents;
import cl.bebt.hubcore.listerners.PlayerJoin;
import cl.bebt.hubcore.menu.Menu;
import cl.bebt.hubcore.menu.PlayerMenuUtility;
import cl.bebt.hubcore.menu.listeners.MenuListener;
import cl.bebt.hubcore.utils.utils;
import cl.bebt.staffcore.API.StaffCoreAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;


public final class main extends JavaPlugin {
    
    private static final HashMap < Player, PlayerMenuUtility > playerMenuUtilityMap = new HashMap <>( );
    public static main plugin;
    public static HashMap < String, Integer > playersInServers = new HashMap <>( );
    public static HashMap < String, Boolean > serversStatus = new HashMap <>( );
    public locationsConfig locationsConfig;
    public EN_NA en_na;
    public ES_CL es_cl;
    public items items;
    public ArrayList < Player > editHub = new ArrayList <>( );
    ConsoleCommandSender c = Bukkit.getConsoleSender( );
    
    public static PlayerMenuUtility getPlayerMenuUtility( Player p ){
        if ( playerMenuUtilityMap.containsKey( p ) )
            return playerMenuUtilityMap.get( p );
        PlayerMenuUtility playerMenuUtility = new PlayerMenuUtility( p );
        playerMenuUtilityMap.put( p , playerMenuUtility );
        return playerMenuUtility;
    }
    
    @Override
    public void onEnable( ){
        plugin = this;
        new utils( this );
        loadConfigManager( );
        new spawn( this );
        new setSpawn( this );
        new removeSpawn( this );
        new spawnMobs( this );
        new editHub( this );
        new test( this );
        new hubcore( this );
        /*
        new UpdateChecker( plugin , 82324 ).getLatestVersion( version -> {
            if ( !getDescription( ).getVersion( ).equals( version ) ) {
                c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&c                  Hey, there is a new version out!" ) );
                c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a                    &5HUB-CORE&a&l " + version ) );
                c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&1---------------------------------------------------------" ) );
            }
        } );
         */
        
        getServer( ).getMessenger( ).registerOutgoingPluginChannel( plugin , "hc:msg" );
        getServer( ).getMessenger( ).registerIncomingPluginChannel( plugin , "hc:msg" , new PluginMessage( ) );
        getServer( ).getMessenger( ).registerIncomingPluginChannel( plugin , "BungeeCord" , new PluginMessage( ) );
        getServer( ).getMessenger( ).registerOutgoingPluginChannel( plugin , "BungeeCord" );
        
        c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&1---------------------------------------------------------" ) );
        c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a             Plugin &5HUB-CORE&a&l ACTIVATED" ) );
        c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&1---------------------------------------------------------" ) );
        c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a                   HUB-CORE: &6" + getDescription( ).getVersion( ) ) );
        c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&1---------------------------------------------------------" ) );
        Bukkit.getPluginManager( ).registerEvents( new PlayerJoin( plugin ) , plugin );
        Bukkit.getPluginManager( ).registerEvents( new HubEvents( plugin ) , plugin );
        Bukkit.getPluginManager( ).registerEvents( new MenuListener( ) , plugin );
        if ( Bukkit.getPluginManager( ).getPlugin( "StaffCore" ) != null ) {
            c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a             Plugin &5STAFF-CORE " + Bukkit.getPluginManager( ).getPlugin( "StaffCore" ).getDescription( ).getVersion( ) + " &aFounded" ) );
            c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&1---------------------------------------------------------" ) );
            c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a                    Some Stats Recopilated: " ) );
            c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a               Current Banned Players: " + StaffCoreAPI.getBannedPlayers( ).size( ) ) );
            c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a               Current Vanished Players: " + StaffCoreAPI.getVanishedPlayer( ).size( ) ) );
            c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a               Current Saved Players: " + StaffCoreAPI.getSavedPlayerList( ).size( ) ) );
            //c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a               Current Staff Players: " + StaffCoreAPI.getStaffPlayer().size() ) );
            c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a               Current Warned Players: " + StaffCoreAPI.getWarnedPlayers( ).size( ) ) );
            c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&1---------------------------------------------------------" ) );
        }
        Bukkit.getServer( ).getScheduler( ).scheduleSyncRepeatingTask( this , ( ) -> {
            for ( int i = 1; i <= utils.getInt( "server_selector.amount_of_servers" ); i++ ) {
                SendMsg.getOnlinePlayersInSpecificServer( utils.getConfig( "server_selector.servers." + i + ".server" ) , utils.getServer( ) );
                SendMsg.getServerStatus( utils.getConfig( "server_selector.servers." + i + ".server" ) , utils.getServer( ) );
            }
            
            
            for ( Player players : Bukkit.getOnlinePlayers( ) ) {
                try {
                    if ( players.getOpenInventory().getTopInventory().getHolder() instanceof Menu ){
                        for ( int servers = 1; servers <= utils.getInt( "server_selector.amount_of_servers" ); servers++ ) {
                            players.getOpenInventory().getTopInventory().setItem( utils.getInt( "server_selector.servers." + servers + ".slot" ),
                                    Menu.makeItem( utils.getConfig( "server_selector.servers." + servers + ".item" ) ,
                                            utils.getConfig( "server_selector.servers." + servers + ".name" ) ,
                                            utils.getBoolean( "server_selector.servers." + servers + ".enchanted" ) ,
                                            utils.getConfig( "server_selector.servers." + servers + ".server" ) ,
                                            utils.getLore( servers ) ));
                        }
                    }
                } catch ( NullPointerException | ArrayIndexOutOfBoundsException ignored ) { }
            }
        } , 20L , 10L );
        
    }
    
    @Override
    public void onDisable( ){
        c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&1---------------------------------------------------------" ) );
        c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a           Plugin &5HUB-CORE&c&l DISABLED" ) );
        c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&1---------------------------------------------------------" ) );
        c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&a              HUB-CORE: &6" + getDescription( ).getVersion( ) ) );
        c.sendMessage( utils.chat( utils.getString( "server_prefix" ) + "&1---------------------------------------------------------" ) );
        
    }
    
    public void loadConfigManager( ){
        saveDefaultConfig( );
        saveConfig( );
        this.locationsConfig = new locationsConfig( plugin );
        locationsConfig.reloadConfig( );
        this.en_na = new EN_NA( plugin );
        en_na.reloadConfig( );
        this.es_cl = new ES_CL( plugin );
        es_cl.reloadConfig( );
        this.items = new items( plugin );
        items.reloadConfig( );
        /*
        this.data = new SQLGetter( this );
        this.reports = new ReportConfig( this );
        this.reports.reloadConfig();
        this.bans = new BanConfig( plugin );
        this.bans.reloadConfig( );
        this.alts = new AltsStorage( plugin );
        this.alts.reloadConfig( );
        this.warns = new WarnConfig( plugin );
        this.warns.reloadConfig( );
         */
    }
    
}
