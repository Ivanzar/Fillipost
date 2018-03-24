package me.ivanzar.cli.command;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by Ivanzar on 06.01.18.
 */
public abstract class Command
{
    @Getter
    @Setter
    private String name, description, message;

    public Command(@NonNull String name)
    {
        this.name = name;
        setDescription("");
        setMessage("");
    }

    public abstract int execute(String[] args);
}
