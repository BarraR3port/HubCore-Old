package cl.bebt.hubcore.commands;

import cl.bebt.hubcore.main;
import cl.bebt.hubcore.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class editHub implements CommandExecutor {
    
    private final main plugin;
    
    public editHub( main plugin ){
        this.plugin = plugin;
        plugin.getCommand( "edithub" ).setExecutor( this );
    }
    
    public boolean onCommand( CommandSender sender , Command cmd , String label , String[] args ){
        if ( sender instanceof Player ) {
            if ( sender.hasPermission( "hubcore.edit-hub" ) ) {
                if ( args.length == 0 ) {
                    Player p = ( Player ) sender;
                    if ( !plugin.editHub.contains( p ) ) {
                        plugin.editHub.add( p );
                        utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "edithub.enabled" ) );
                    } else {
                        plugin.editHub.remove( p );
                        utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "edithub.disabled" ) );
                    }
                } else if ( args.length == 1 ) {
                    if ( Bukkit.getPlayer( args[0] ) != null ) {
                        Player target = Bukkit.getPlayer( args[0] );
                        if ( !plugin.editHub.contains( target ) ) {
                            plugin.editHub.add( target );
                            utils.tell( target , utils.getString( "server_prefix" ) + utils.getString( "edithub.enabled" ) );
                            utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "edithub.enabled_other" ) );
                        } else {
                            plugin.editHub.remove( target );
                            utils.tell( target , utils.getString( "server_prefix" ) + utils.getString( "edithub.disabled" ) );
                            utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "edithub.disabled_other" ) );
                        }
                    } else {
                        utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "wrong_command" ).replace( "%command%" , "/edithub | /edithub <player>" ) );
                    }
                } else {
                    utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "wrong_command" ).replace( "%command%" , "/edithub | /edithub <player>" ) );
                }
            } else {
                utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "no_permission" ) );
            }
        } else {
            if ( args.length == 1 ) {
                if ( Bukkit.getPlayer( args[0] ) != null ) {
                    Player target = Bukkit.getPlayer( args[0] );
                    if ( !plugin.editHub.contains( target ) ) {
                        plugin.editHub.add( target );
                        utils.tell( target , utils.getString( "server_prefix" ) + utils.getString( "edithub.enabled" ) );
                        utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "edithub.enabled_other" ) );
                    } else {
                        plugin.editHub.remove( target );
                        utils.tell( target , utils.getString( "server_prefix" ) + utils.getString( "edithub.disabled" ) );
                        utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "edithub.disabled_other" ) );
                    }
                } else {
                    utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "wrong_command" ).replace( "%command%" , "/edithub <player>" ) );
                }
            } else {
                utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "wrong_command" ).replace( "%command%" , "/edithub <player>" ) );
            }
        }
        return true;
    }
}