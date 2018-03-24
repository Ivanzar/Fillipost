package me.ivanzar;

import me.ivanzar.cli.command.BuilderCommand;
import me.ivanzar.cli.command.CleanCommand;
import me.ivanzar.cli.command.CommandManager;
import me.ivanzar.cli.command.CreateCommand;

/**
 * Created by Ivanzar on 29.12.17.
 */
public class Main
{
    public static void main(String[] args)
    {
        CommandManager commandManager = new CommandManager();
        commandManager.registerCommand(new CreateCommand());
        commandManager.registerCommand(new BuilderCommand());
        commandManager.registerCommand(new CleanCommand());

        commandManager.execute(args);
    }
}
