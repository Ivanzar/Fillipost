package me.ivanzar.exception;

import lombok.Getter;

/**
 * Created by Ivanzar on 31.12.17.
 */
public class RenderMarkdownException extends FillipostException
{
    public static final int HEADER_NOT_DEFINED = 1;
    public static final int NAME_NOT_DEFINED = 2;
    public static final int PATTERN_NOT_DEFINED = 3;

    @Getter
    private int code;
    @Getter
    private String path;

    public RenderMarkdownException(String path, int code)
    {
        this.code = code;
        this.path = path;
    }
}
