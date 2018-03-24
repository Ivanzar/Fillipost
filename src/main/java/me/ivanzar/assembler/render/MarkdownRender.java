package me.ivanzar.assembler.render;

import lombok.NonNull;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ivanzar on 05.01.18.
 */
public class MarkdownRender extends Render
{
    public String render(@NonNull String content)
    {
        List<Extension> extensions = Arrays.asList(TablesExtension.create());

        Parser parser = Parser.builder().
                extensions(extensions).build();
        Node document = parser.parse(content);
        HtmlRenderer render = HtmlRenderer.builder().
                extensions(extensions).build();
        return render.render(document);
    }
}
