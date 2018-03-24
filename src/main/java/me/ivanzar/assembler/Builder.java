package me.ivanzar.assembler;

import lombok.NonNull;
import me.ivanzar.assembler.render.PageRender;
import me.ivanzar.exception.PageException;
import me.ivanzar.utils.FileUtils;

import java.io.*;

/**
 * Created by Ivanzar on 30.12.17.
 */
public class Builder
{

    private File projectPath;

    public Builder(@NonNull File projectPath)
    {
        this.projectPath = projectPath;
    }

    public void execute() throws IOException, PageException
    {
        new ResourcesBuilder(projectPath).execute();
        new PostBuilder(projectPath).execute(null);
    }
}
