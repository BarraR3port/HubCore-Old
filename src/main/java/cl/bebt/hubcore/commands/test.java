package cl.bebt.hubcore.commands;

import cl.bebt.hubcore.MSGChanel.SendMsg;
import cl.bebt.hubcore.main;
import cl.bebt.hubcore.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class test implements CommandExecutor {
    
    private final main plugin;
    
    public test( main plugin ){
        this.plugin = plugin;
        plugin.getCommand( "test" ).setExecutor( this );
    }
    
    public boolean onCommand( CommandSender sender , Command cmd , String label , String[] args ){
        if ( sender instanceof Player ) {
            SendMsg.getOnlinePlayersInSpecificServer( args[0] , utils.getServer( ) );
            utils.tell( sender , args[0] );
        }
        return true;
    }
}