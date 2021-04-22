package cl.bebt.hubcore.commands;

import cl.bebt.hubcore.main;
import cl.bebt.hubcore.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class spawnMobs implements CommandExecutor {
    
    private final main plugin;
    
    public spawnMobs( main plugin ){
        this.plugin = plugin;
        plugin.getCommand( "spawnmobs" ).setExecutor( this );
    }
    
    public boolean onCommand( CommandSender sender , Command cmd , String label , String[] args ){
        if ( sender.hasPermission( "hubcore.toggle.spawn-mobs" ) ) {
            if ( args.length == 0 ) {
                plugin.getConfig( ).set( "spawnmobs" , !utils.getBoolean( "spawnmobs" ) );
                plugin.saveConfig( );
                if ( utils.getBoolean( "spawnmobs" ) ) {
                    utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "spawnmobs" ).replace( "%boolean%" , "&aTrue" ) );
                } else {
                    utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "spawnmobs" ).replace( "%boolean%" , "&cFalse" ) );
                }
            } else {
                utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "wrong_command" ).replace( "%command%" , "/spawnmobs" ) );
            }
        } else {
            utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "no_permission" ) );
        }
        return true;
    }
}
