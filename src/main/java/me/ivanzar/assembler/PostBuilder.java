package me.ivanzar.assembler;

import me.ivanzar.assembler.render.PageRender;
import me.ivanzar.exception.PageException;
import me.ivanzar.utils.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ivanzar on 15.03.18.
 * Email: ivanzar.developer@gmail.com
 */
public class PostBuilder
{
    private File projectPath;

    public PostBuilder(File projectPath)
    {
        this.projectPath = projectPath;
    }

    /**
     *
     * @param postName post's name of posts folder. If null, then build all posts
     */
    public void execute(String postName) throws IOException, PageException
    {
        buildPages(postName);
    }

    private void buildPages(String file) throws IOException, PageException
    {
        File postsFolder = new File(projectPath, "posts");

        if (file == null) file = "";

        File pathTo = new File(projectPath, "build/"+file);
        File pathFrom = new File(postsFolder, file);

        if (pathFrom.isDirectory())
        {
            for (String fileEntry : pathFrom.list())
            {
                buildPages(file+"/"+fileEntry);
            }
        } else {
            PageRender render = new PageRender();
            String renderedPage = render.render(projectPath, pathFrom);

            StringBuilder builder = new StringBuilder(pathTo.toString());

            if (pathTo.getName().contains("."))
            {
                builder.replace(pathTo.toString().lastIndexOf('.')+1,
                        pathTo.toString().length(),
                        "html");
            } else
            {
                builder.append(".html");
            }

            pathTo = new File(builder.toString());

            FileUtils.createAndWrite(pathTo, renderedPage);
        }
    }
}
