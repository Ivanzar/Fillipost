package me.ivanzar.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Created by Ivanzar on 04.01.18.
 */
@Getter
@Setter
public class PostModel
{
    private String name, author, description, pattern, content;
    private long date;
    private static ArrayList<PostModel> list;

    public static ArrayList<PostModel> getList()
    {
        return list;
    }

    public static void setList(ArrayList<PostModel> list)
    {
        PostModel.list = list;
    }
}
