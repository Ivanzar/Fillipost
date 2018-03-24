package me.ivanzar.assembler;

import lombok.Getter;
import lombok.NonNull;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Ivanzar on 29.12.17.
 */
public class Creator
{
    @Getter
    private String name;

    private File path;
    private static ArrayList<String> folders = new ArrayList<>();

    static {
        folders.add("snippets");
        folders.add("patterns");
        folders.add("posts");
        folders.add("resources/img");
        folders.add("resources/style");
        folders.add("resources/video");
        folders.add("build");
    }

    public Creator(@NonNull File path)
    {
        this.path = path;
        this.name = path.getName();
    }

    public void execute()
    {
        if (!path.exists())
            path.mkdirs();

        for (String child : folders)
        {
            new File(path, child).mkdirs();
        }

    }

}
