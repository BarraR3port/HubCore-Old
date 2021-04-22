package cl.bebt.hubcore.commands;

import cl.bebt.hubcore.main;
import cl.bebt.hubcore.utils.SpawnManager;
import cl.bebt.hubcore.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class spawn implements CommandExecutor {
    
    private final main plugin;
    
    public spawn( main plugin ){
        this.plugin = plugin;
        plugin.getCommand( "spawn" ).setExecutor( this );
    }
    
    public boolean onCommand( CommandSender sender , Command cmd , String label , String[] args ){
        if ( sender instanceof Player ) {
            if ( sender.hasPermission( "hubcore.spawn" ) ) {
                if ( args.length == 0 ) {
                    Player p = ( Player ) sender;
                    SpawnManager.tp( p );
                } else if ( args.length == 1 ) {
                    if ( sender.hasPermission( "hubcore.spawn.tp.others" ) ) {
                        if ( Bukkit.getPlayer( args[0] ) != null ) {
                            Player p = ( Player ) sender;
                            Player target = Bukkit.getPlayer( args[0] );
                            SpawnManager.tpOthers( p , target );
                        } else {
                            utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "p_dont_exist" ).replace( "%player%" , args[0] ) );
                        }
                    } else {
                        utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "no_permission" ) );
                    }
                } else {
                    utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "wrong_command" ).replace( "%command%" , "/spawn | /spawn <player>" ) );
                }
            } else {
                utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "no_permission" ) );
            }
        }
        return true;
    }
}