package me.ivanzar.cli.command;

import java.util.HashMap;

/**
 * Created by Ivanzar on 06.01.18.
 */
public class CommandManager
{
    private HashMap<String, Command> commands = new HashMap<>();

    public CommandManager()
    {
    }

    public int execute(String[] args)
    {
        if (args == null)
        {
            printHelp();
            return -1;
        }


        if (args.length == 0)
        {
            printHelp();
            return -1;
        }

        if (!commands.containsKey(args[0]))
        {
            printHelp();
            return -1;
        }

        String[] newArgs = new String[args.length-1];

        for (int i = 1; i < args.length; i++)
            newArgs[i-1] = args[i];

        Command command =  commands.get(args[0]);
        int code = command.execute(newArgs);

        if (code != 0)
        {
            System.err.println("Code: "+code+"\n");
            System.err.println(command.getMessage());
        }
        else
            System.out.println(command.getMessage());

        return 0;
    }

    public void registerCommand(Command command)
    {
        commands.put(command.getName(), command);
    }

    public void printHelp()
    {
        String maxString = "";

        for (String key : commands.keySet())
        {
            if (maxString.length() < key.length())
                maxString = key;
        }

        maxString += "    ";

        for (String key : commands.keySet())
        {
            Command command = commands.get(key);

            StringBuilder keyBuilder = new StringBuilder(key);
            while (keyBuilder.length() < maxString.length())
                keyBuilder.append(" ");
            key = keyBuilder.toString();

            System.out.println(key + command.getDescription());
        }

    }
}
