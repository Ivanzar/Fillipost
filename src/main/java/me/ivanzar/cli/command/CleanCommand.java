package me.ivanzar.cli.command;

import me.ivanzar.utils.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ivanzar on 17.03.18.
 * Email: ivanzar.developer@gmail.com
 */
public class CleanCommand extends Command
{
    public static final int OK = 0;
    public static final int DELETE_ERR = 1;
    public static final int ERR_PROJECT_PATH_UNDEFINED = 2;

    public CleanCommand()
    {
        super("clean");
        setDescription("Clean build directory: clean <project path>");
    }

    @Override
    public int execute(String[] args)
    {
        if (args.length == 0)
        {
            setMessage("Error: project path undefined.\n" +
                    "Use: clena <project path>");
            return ERR_PROJECT_PATH_UNDEFINED;
        }

        if (args[0] == null)
        {
            setMessage("Error: project path undefined.\n" +
                    "Use: clean <project path>");
            return ERR_PROJECT_PATH_UNDEFINED;
        }

        File projectPath = new File(args[0]);

        boolean res = FileUtils.deleteFolder(new File(projectPath, "build"));
        if (!res)
        {
            setMessage("Cleaning filed");
            return DELETE_ERR;
        }

        try
        {
            setMessage("Cleaning was successful\n" +
                    "Folder: " + projectPath.getCanonicalPath() + "/build");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return OK;
    }
}
