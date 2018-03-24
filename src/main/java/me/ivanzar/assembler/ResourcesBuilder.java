package me.ivanzar.assembler;

import lombok.NonNull;
import me.ivanzar.utils.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ivanzar on 15.03.18.
 * Email: ivanzar.developer@gmail.com
 */
public class ResourcesBuilder
{

    private File projectPath;

    public ResourcesBuilder(@NonNull File projectPath)
    {
        this.projectPath = projectPath;
    }

    public void execute() throws IOException
    {
        copyResources("resources");
    }

    private void copyResources(String file) throws IOException
    {
        File pathTo = new File(projectPath, "build/"+file);
        File pathFrom = new File(projectPath, file);

        if (pathFrom.isDirectory())
        {
            for (String fileEntry : pathFrom.list())
                copyResources(file+"/"+fileEntry);
        } else {
            FileUtils.copy(pathFrom, pathTo);
        }
    }

}
