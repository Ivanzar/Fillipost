package me.ivanzar.assembler.render;

import com.google.gson.internal.LinkedTreeMap;
import liqp.Template;
import liqp.tags.Include;
import lombok.NonNull;
import me.ivanzar.assembler.PostListBuilder;
import me.ivanzar.exception.PageException;
import me.ivanzar.model.PageModel;

import java.io.*;
import java.util.Map;

/**
 * Created by Ivanzar on 01.01.18.
 */
public class PageRender extends Render
{
    public String render(@NonNull File projectPath, @NonNull File postPath) throws PageException, IOException
    {
        StringBuilder builder = new StringBuilder();

        BufferedReader input = new BufferedReader(new FileReader(postPath));
        String entry;
        while ((entry = input.readLine()) != null)
            builder.append(entry).append("\n");
        input.close();

        String pageText = builder.toString();

        PageModel pageModel;

        try
        {
            pageModel = new PageModel(pageText);
        } catch (PageException e)
        {
            throw new PageException(e.getCode(), postPath.getAbsolutePath());
        }

        String pattern = pageModel.getPost().getPattern();

        File patternPath = new File(projectPath, "patterns/" + pattern);

        StringBuilder link = new StringBuilder(projectPath.toString());
        link.replace(0, link.toString().length(),
                link.toString().
                        replaceFirst(projectPath.toString() + "/",
                                "").
                        replaceFirst("posts/", ""));

        if (projectPath.getName().contains("."))
        {
            link.replace(projectPath.toString().lastIndexOf('.') + 1,
                    projectPath.toString().length(),
                    "html");
        } else
        {
            link.append(".html");
        }

        String newContent = renderContent(pageModel.getPost().getContent());
        ((LinkedTreeMap) pageModel.getKeyList().get("post"))
                .put("content", newContent);
        ((LinkedTreeMap) pageModel.getKeyList().get("post"))
                .put("link", link);
        ((LinkedTreeMap) pageModel.getKeyList().get("post"))
                .put("list", PostListBuilder.getList(projectPath));

        return renderPage(projectPath, patternPath, pageModel.getKeyList());
    }

    private String renderContent(String rawContent)
    {
        return new MarkdownRender().render(rawContent);
    }

    private String renderPage(File projectPath,
                              File patternPath,
                              Map<String, Object> keys) throws IOException
    {
        keys.put(Include.INCLUDES_DIRECTORY_KEY, new File(projectPath, "snippets"));

        Template template = Template.parse(patternPath);
        String render = template.render(keys);

        return render;
    }

}
