package me.ivanzar.utils;

import lombok.NonNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by Ivanzar on 06.01.18.
 */
public class FileUtils
{
    public static void copy(@NonNull File pathFrom, @NonNull File pathTo) throws IOException
    {
        if (!pathTo.exists())
        {
            pathTo.getParentFile().mkdirs();
            //pathTo.createNewFile();
        }

        Files.copy(pathFrom.toPath(), pathTo.toPath(), StandardCopyOption.REPLACE_EXISTING);

//        BufferedReader in = new BufferedReader(new FileReader(pathFrom));
//        BufferedWriter out = new BufferedWriter(new FileWriter(pathTo));
//        int entry;
//        while ((entry = in.read()) != -1)
//        {
//            out.write(entry);
//            out.flush();
//        }
//
//        in.close();
//        out.close();
    }


    public static void createAndWrite(@NonNull File filePath, String text) throws IOException
    {
        if (!filePath.exists())
        {
            filePath.getParentFile().mkdirs();
            filePath.createNewFile();
        }


        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(text, 0, text.length());
        writer.flush();
        writer.close();
    }

    public static String read(@NonNull File filePath) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null)
        {
            builder.append(line).append("\n");
        }

        return builder.toString();
    }

    public static boolean deleteFolder(@NonNull File path)
    {
        if (path.isDirectory())
        {
            for (File file : path.listFiles())
            {
                deleteFolder(file);
            }
        }

        return delete(path);
    }

    public static boolean delete(File path)
    {
        return path.delete();
    }
}
