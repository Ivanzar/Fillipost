package me.ivanzar.cli.command;

import lombok.NonNull;
import me.ivanzar.assembler.Creator;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ivanzar on 06.01.18.
 */
public class CreateCommand extends Command
{
    public static final int OK = 0;
    public static final int ERR_INVALID_PATH = 1;
    public static final int ERR_PROJECT_PATH_UNDEFINED = 2;

    public CreateCommand()
    {
        super("create");
        setDescription("Creates a empty project: create <project_folder>");
    }

    @Override
    public int execute(@NonNull String[] args)
    {
        if (args.length == 0)
        {
            setMessage("Error: project path undefined.\n" +
                    "Use: create <project path>");
            return ERR_PROJECT_PATH_UNDEFINED;
        }

        if (args[0] == null)
        {
            setMessage("Error: project path undefined.\n" +
                    "Use: create <project path>");
            return ERR_PROJECT_PATH_UNDEFINED;
        }

        if (!(args[0].startsWith("/") || args[0].startsWith("./")))
        {
            args[0] = "./" + args[0];
        }

        File projectFile = new File(args[0]);

        if (!projectFile.exists())
        {
            projectFile.getParentFile().mkdirs();
        }

        Creator creator = new Creator(projectFile);
        creator.execute();


        try
        {
            setMessage("Creation was successful\n" +
                    "Folder: " + projectFile.getCanonicalPath());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return OK;
    }

}
