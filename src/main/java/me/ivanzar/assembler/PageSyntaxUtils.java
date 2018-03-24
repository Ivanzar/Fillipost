package me.ivanzar.assembler;

/**
 * Created by Ivanzar on 03.01.18.
 */
public class PageSyntaxUtils
{
    public String getHead(String text)
    {
        boolean startFlag = false;
        StringBuilder result = new StringBuilder();
        String[] textLines = text.split("\n");

        for (String line : textLines)
        {
            if (line.startsWith("---") && startFlag)
            {
                startFlag = false;
                break;
            }

            if (line.startsWith("---") && !startFlag)
                startFlag = true;
            if (startFlag && !line.startsWith("---"))
                result.append(line);
        }

        return !startFlag && textLines.length > 3 ? result.toString() : null;
    }

    public String getContent(String text)
    {
        boolean headStartFlag = false;
        boolean headFinishFlag = false;
        StringBuilder result = new StringBuilder();
        String[] textLines = text.split("\n");

        for (String line : textLines)
        {
            if (line.startsWith("---") && headStartFlag)
            {
                headStartFlag = false;
                headFinishFlag = true;
                continue;
            }

            if (line.startsWith("---") && !headStartFlag)
                headStartFlag = true;

            if (headFinishFlag)
                result.append(line).append("\n");
        }

        return result.toString();
    }
}
