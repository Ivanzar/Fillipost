package me.ivanzar.cli.command;

import lombok.NonNull;
import me.ivanzar.assembler.Builder;
import me.ivanzar.assembler.PostBuilder;
import me.ivanzar.assembler.PostListBuilder;
import me.ivanzar.assembler.ResourcesBuilder;
import me.ivanzar.exception.PageException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ivanzar on 06.01.18.
 */
public class BuilderCommand extends Command
{
    public static final int OK = 0;
    public static final int ERR_INVALID_PATH = 1;
    public static final int ERR_PROJECT_PATH_UNDEFINED = 2;
    public static final int ERR_IO = 3;

    public static final int ERR_HEADER_NOT_DEFINED = 4;
    public static final int ERR_POST_VAR_NOT_DEFINED = 5;
    public static final int ERR_NAME_NOT_DEFINED = 6;
    public static final int ERR_PATTERN_NOT_DEFINED = 7;
    public static final int ERR_POST_VAR_INCORRECT = 8;


    public BuilderCommand()
    {
        super("build");
        setDescription("Assemble a project: build <project path> [flag]\n" +
                "\t\tFlags:\n" +
                "\t\t--resources                  Assemble resources.\n" +
                "\t\t--post <post or post folder> Assemble specific post\n" +
                "\t\t--posts-only                 Assemble only posts");
    }

    @Override
    public int execute(@NonNull String[] args)
    {
        int out = OK;

        if (args.length == 0)
        {
            setMessage("Error: project path undefined.\n" +
                    "Use: build <project path>");
            return ERR_PROJECT_PATH_UNDEFINED;
        }

        if (args[0] == null)
        {
            setMessage("Error: project path undefined.\n" +
                    "Use: build <project path>");
            return ERR_PROJECT_PATH_UNDEFINED;
        }

        File projectPath = new File(args[0]);

        if (args.length < 2)
            out = buildAll(projectPath);

        else if (args[1].equals("--resources"))
            out = buildResources(projectPath);

        else if (args[1].equals("--posts-only"))
            out = buildOnlyPosts(projectPath);

        else if (args[1].equals("--post"))
            if (args.length > 2)
                if (args[2] != null)
                    out = buildPost(args[2], projectPath);

                else
                    out = buildAll(projectPath);


        if (out == OK)
        {
            try
            {
                setMessage("Assembly was successful\n" +
                        "Folder: " + projectPath.getCanonicalPath() + "/build/");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return out;
    }

    private int buildOnlyPosts(File projectPath)
    {
        try
        {
            new PostBuilder(projectPath).execute(null);
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (PageException e)
        {
            e.printStackTrace();
            return ERR_IO;
        }

        return OK;
    }

    private int buildAll(File projectPath)
    {
        Builder builder = new Builder(projectPath);
        try
        {
            PostListBuilder.build(projectPath);
            builder.execute();
        } catch (IOException e)
        {
            setMessage(e.getMessage());
            e.printStackTrace();
            return ERR_IO;
        } catch (PageException e)
        {
            e.printStackTrace();

            int err = e.getCode();

            switch (e.getCode())
            {
                case PageException.HEADER_NOT_DEFINED:
                    err = ERR_HEADER_NOT_DEFINED;
                    setMessage("Error: Page header syntax incorrect.\n" +
                            "Header not defined.\n" +
                            "File: " + e.getPage());
                    break;
                case PageException.NAME_NOT_DEFINED:
                    err = ERR_NAME_NOT_DEFINED;
                    setMessage("Error: Page header syntax incorrect.\n" +
                            "'name' variable not defined.\n" +
                            "File: " + e.getPage());
                    break;
                case PageException.PATTERN_NOT_DEFINED:
                    err = ERR_PATTERN_NOT_DEFINED;
                    setMessage("Error: Page header syntax incorrect.\n" +
                            "'pattern' variable not defined..\n" +
                            "File: " + e.getPage());
                    break;
                case PageException.POST_VAR_INCORRECT:
                    err = ERR_POST_VAR_INCORRECT;
                    setMessage("Error:  Page syntax incorrect.\n" +
                            "'post' variable incorrect.\n" +
                            "File: " + e.getPage());
                    break;
                case PageException.POST_VAR_NOT_DEFINED:
                    err = ERR_POST_VAR_NOT_DEFINED;
                    setMessage("Error:  Page syntax incorrect.\n" +
                            "'post' variable not defined.\n" +
                            "File: " + e.getPage());
                    break;
            }

            return err;
        }

        return OK;
    }

    public int buildResources(File projectPath)
    {
        try
        {
            new ResourcesBuilder(projectPath).execute();
        } catch (IOException e)
        {
            e.printStackTrace();
            return ERR_IO;
        }

        return OK;
    }

    public int buildPost(String post, File projectPath)
    {
        try
        {
            new PostBuilder(projectPath).execute(post);
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (PageException e)
        {
            e.printStackTrace();
            return ERR_IO;
        }

        return OK;
    }

}
