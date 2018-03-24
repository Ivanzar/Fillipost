package me.ivanzar.assembler;

import lombok.NonNull;
import me.ivanzar.assembler.render.MarkdownRender;
import me.ivanzar.exception.PageException;
import me.ivanzar.model.PageModel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivanzar on 06.01.18.
 */
public class PostListBuilder
{
    private static ArrayList<HashMap<String, Object>> pageList = new ArrayList<>();

//    public PostListBuilder(@NonNull File projectPath)
//    {
//        this.projectPath = projectPath;
//    }

    public static ArrayList<HashMap<String, Object>> getList(@NonNull File projectPath)
            throws IOException, PageException
    {
        if (pageList.size() > 0)
            return pageList;

        fillArray(projectPath, null, pageList);

        return pageList;
    }

    public static void build(@NonNull File projectPath)
            throws IOException, PageException
    {
        fillArray(projectPath, null, pageList);
    }

    private static void fillArray(File projectPath,
                                  String file,
                                  ArrayList<HashMap<String, Object>> arrayList)
            throws IOException, PageException
    {
        if (file == null) file = "";

        File postsPath = new File(projectPath, "posts");
        File path = new File(postsPath, file);

        if (path.isDirectory())
        {
            for (String fileName : path.list())
                fillArray(projectPath, file + "/" + fileName, arrayList);
        } else
        {
            StringBuilder builder = new StringBuilder();

            BufferedReader input = new BufferedReader(new FileReader(path));
            String entry;
            while ((entry = input.readLine()) != null)
                builder.append(entry).append("\n");
            input.close();

            PageModel pageModel;

            try
            {
                pageModel = new PageModel(builder.toString());
            } catch (PageException e)
            {
                throw new PageException(e.getCode(), path.getAbsolutePath());
            }


            MarkdownRender render = new MarkdownRender();
            String renderedCont = render.render(pageModel.getPost().getContent());

            StringBuilder newExt = new StringBuilder(file);

            if (new File(file).getName().contains("."))
            {
                builder.replace(file.lastIndexOf('.') + 1,
                        file.length(),
                        "html");
            } else
            {
                builder.append(".html");
            }

            ((Map) pageModel.getKeyList().get("post")).put("content", renderedCont);
            ((Map) pageModel.getKeyList().get("post")).put("link", newExt);

            arrayList.add(pageModel.getKeyList());
        }
    }

}
