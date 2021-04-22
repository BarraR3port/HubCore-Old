package cl.bebt.hubcore.commands;

import cl.bebt.hubcore.main;
import cl.bebt.hubcore.utils.SpawnManager;
import cl.bebt.hubcore.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setSpawn implements CommandExecutor {
    
    private final main plugin;
    
    public setSpawn( main plugin ){
        this.plugin = plugin;
        plugin.getCommand( "setspawn" ).setExecutor( this );
    }
    
    public boolean onCommand( CommandSender sender , Command cmd , String label , String[] args ){
        if ( sender instanceof Player ) {
            if ( sender.hasPermission( "hubcore.spawn.set" ) ) {
                if ( args.length == 0 ) {
                    Player p = ( Player ) sender;
                    SpawnManager.setSpawn( p );
                } else {
                    utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "wrong_command" ).replace( "%command%" , "/setspawn" ) );
                }
            } else {
                utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "no_permission" ) );
            }
        } else {
            utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "only_players" ) );
        }
        return true;
    }
}
