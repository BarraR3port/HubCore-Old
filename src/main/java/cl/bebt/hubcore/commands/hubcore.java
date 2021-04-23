package cl.bebt.hubcore.commands;

import cl.bebt.hubcore.main;
import cl.bebt.hubcore.utils.UpdateChecker;
import cl.bebt.hubcore.utils.utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class hubcore implements TabExecutor {
    private final main plugin;
    
    public hubcore( main plugin ){
        this.plugin = plugin;
        plugin.getCommand( "hubcore" ).setExecutor( this );
    }
    
    public List < String > onTabComplete( CommandSender sender , Command command , String alias , String[] args ){
        if ( args.length == 1 ) {
            List < String > version = new ArrayList <>( );
            version.add( "version" );
            version.add( "reload" );
            return version;
        }
        return null;
    }
    
    public boolean onCommand( CommandSender sender , Command cmd , String label , String[] args ){
        if ( sender instanceof Player ) {
            if ( args.length == 1 ) {
                if ( args[0].equalsIgnoreCase( "version" ) ) {
                    utils.tell( sender , " " );
                    utils.tell( sender , "                 &bAuthor: &5BarraR3port" );
                    utils.tell( sender , " " );
                    utils.tell( sender , "                 &aHub Core Version: &7" + plugin.getDescription( ).getVersion( ) );
                    new UpdateChecker( this.plugin , 82324 ).getLatestVersion( version -> {
                        if ( this.plugin.getDescription( ).getVersion( ).equalsIgnoreCase( version ) ) {
                            utils.tell( sender , "&a                 You are using the latest version!" );
                        } else {
                            ComponentBuilder cb = new ComponentBuilder( utils.chat( "&aClick to download!" ) );
                            TextComponent v = new TextComponent( utils.chat( "&c                 Hey, there is a new version out!" ) );
                            v.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT , cb.create( ) ) );
                            v.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL , "https://www.spigotmc.org/resources/hubcore.82324/" ) );
                            sender.spigot( ).sendMessage( v );
                        }
                    } );
                    utils.tell( sender , " " );
                } else if ( args[0].equalsIgnoreCase( "reload" ) ) {
                    utils.reloadConfig( );
                    utils.tell( sender , utils.getString( "server_prefix" ) + "&aHubCore Reloaded!" );
                } else {
                    utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "wrong_command" ).replace("%command%","/hubcore <version/reload>") );
                }
            } else {
                utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "wrong_command" ).replace("%command%","/hubcore <version/reload>") );
            }
        } else {
            utils.tell( sender , utils.getString( "server_prefix" ) + utils.getString( "only_players" ) );
        }
        return false;
    }
}