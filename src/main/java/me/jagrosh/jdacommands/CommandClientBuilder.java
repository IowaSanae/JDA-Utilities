/*
 * Copyright 2016 John Grosh (jagrosh).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.jagrosh.jdacommands;

import me.jagrosh.jdacommands.impl.CommandClientImpl;
import java.util.ArrayList;
import java.util.LinkedList;
import net.dv8tion.jda.core.entities.Game;

/**
 *
 * @author John Grosh (jagrosh)
 */
public class CommandClientBuilder {
    private Game game = Game.of("default");
    private String ownerId;
    private String prefix;
    private String serverInvite;
    private String success;
    private String warning;
    private String error;
    private String carbonKey;
    private String botsKey;
    private final LinkedList<Command> commands = new LinkedList<>();
    private CommandListener listener;
    
    /**
     * Builds a CommandClientImpl with the provided settings
     * @return a CommandClientImpl
     */
    public CommandClient build()
    {
        CommandClient client = new CommandClientImpl(ownerId, prefix, game, serverInvite, success, warning, error, carbonKey, botsKey, new ArrayList<>(commands));
        if(listener!=null)
            client.setListener(listener);
        return client;
    }
    
    /**
     * Sets the owner for the bot
     * @param ownerId the id of the owner
     */
    public void setOwnerId(String ownerId)
    {
        this.ownerId = ownerId;
    }
    
    /**
     * Sets the bot's prefix. If null, the bot will use a mention as a prefix
     * @param prefix the prefix
     */
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }
    
    /**
     * Sets the bot's support server invite
     * @param serverInvite the support server invite
     */
    public void setServerInvite(String serverInvite)
    {
        this.serverInvite = serverInvite;
    }
    
    /**
     * Sets the emojis for success, warning, and failure
     * @param success emoji for success
     * @param warning emoji for warning
     * @param error emoji for failure
     */
    public void setEmojis(String success, String warning, String error)
    {
        this.success = success;
        this.warning = warning;
        this.error = error;
    }
    
    /**
     * Sets the game to use when the bot is ready. Set to null for no game
     * @param game the game to use when the bot is ready
     */
    public void setGame(Game game)
    {
        this.game = game;
    }
    
    /**
     * Uses the default game, 'Type [prefix]help'
     */
    public void useDefaultGame()
    {
        this.game = Game.of("default");
    }
    
    /**
     * Adds a command
     * @param command 
     */
    public void addCommand(Command command)
    {
        commands.add(command);
    }
    
    /**
     * Adds multiple commands. This is the same as calling addCommand multiple times
     * @param commands 
     */
    public void addCommands(Command... commands)
    {
        for(Command command: commands)
            this.addCommand(command);
    }
    
    /**
     * Sets a key for Carbonitex for updating server count
     * @param key 
     */
    public void setCarbonitexKey(String key)
    {
        this.carbonKey = key;
    }
    
    /**
     * Sets a key for the Discord Bots listing for updating server count
     * @param key
     */
    public void setDiscordBotsKey(String key)
    {
        this.botsKey = key;
    }
    
    /**
     * Sets the CommandListener for the CommandClientImpl
     * @param listener the CommandListener for the CommandClientImpl
     */
    public void setListener(CommandListener listener)
    {
        this.listener = listener;
    }
}
