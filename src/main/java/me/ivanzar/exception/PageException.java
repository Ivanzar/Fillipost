package me.ivanzar.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ivanzar on 05.01.18.
 */
public class PageException extends  FillipostException
{
    public static final int HEADER_NOT_DEFINED = 1;
    public static final int POST_VAR_NOT_DEFINED = 2;
    public static final int NAME_NOT_DEFINED = 3;
    public static final int PATTERN_NOT_DEFINED = 4;
    public static final int POST_VAR_INCORRECT = 5;

    @Getter
    private int code;
    @Getter
    @Setter
    private String page;

    public PageException(int code)
    {
        this.code = code;
    }

    public PageException(int code, String page)
    {
        this.code = code;
        this.page = page;
    }
}
