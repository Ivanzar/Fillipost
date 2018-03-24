package me.ivanzar.model;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import me.ivanzar.assembler.PageSyntaxUtils;
import me.ivanzar.exception.PageException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Ivanzar on 04.01.18.
 */
public class PageModel
{
    @Getter
    private HashMap<String, Object> keyList;
    @Getter
    private PostModel post = new PostModel();

    /**
     * required head's fields: name, pattern, post
     * @param text
     * @throws PageException
     */
    public PageModel(String text) throws PageException
    {
        PageSyntaxUtils utils = new PageSyntaxUtils();
        String headJson = utils.getHead(text);

        if (headJson == null)
            throw new PageException(PageException.HEADER_NOT_DEFINED);

        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>()
        {
        }.getType();

        keyList = gson.fromJson(headJson, type);

        if (!keyList.containsKey("post"))
            throw new PageException(PageException.POST_VAR_NOT_DEFINED);
        if (!(keyList.get("post") instanceof LinkedTreeMap))
            throw new PageException(PageException.POST_VAR_INCORRECT);

        LinkedTreeMap<String, Object> postMap = (LinkedTreeMap<String, Object>) keyList.get("post");

        if (!postMap.containsKey("name"))
            throw new PageException(PageException.NAME_NOT_DEFINED);
        if (!postMap.containsKey("pattern"))
            throw new PageException(PageException.PATTERN_NOT_DEFINED);
        if (((String) postMap.get("pattern")).isEmpty())
            throw new PageException(PageException.PATTERN_NOT_DEFINED);

        if (!postMap.containsKey("date"))
            postMap.put("date", new Date().getTime());
        else postMap.put("date", new Double((double)postMap.get("date")).longValue());

        postMap.put("content", utils.getContent(text));

        post.setName((String) postMap.get("name"));
        post.setPattern((String) postMap.get("pattern"));
        post.setAuthor(postMap.containsKey("author") ?
                (String) postMap.get("author") : "");
        post.setDescription(postMap.containsKey("description") ?
                (String) postMap.get("description") : "");
        post.setDate((long) postMap.get("date"));
        post.setContent((String) postMap.get("content"));

    }

    public Object getKey(String name)
    {
        return keyList.get(name);
    }
}
