# Fillipost
## What is it?
Fillipost is a simple open-source static sites generator for personal, project or organization sites. Written in Java by Ivanzar.

## Features
Fillipost provides an easy way to create own sites using the human-readable markup language Markdown and Liquid templates. Static web pages are ready for use on any web server, such as Apache or Nginx, without deploying complex systems.

## Commands

| Command                              | Description                                     |                    |                                          |
|--------------------------------------|-------------------------------------------------|--------------------|------------------------------------------|
| **build `<project path>` `[flag]`**  | Assemble a project.                             | Flags:             |                                          |
|                                      |                                                 | --resources        | Assemble resources (folder: `resources`) |
|                                      |                                                 | --post [post file] | Assemble specific post (folder: `posts`) |
|                                      |                                                 | --posts-only       | Assemble only (folder: `posts`)          |
| **create `<project path>`**          | Create a new project.                           |                    |                                          |
| **clean `<project path>`**           | Clean the directory ``` project path/build ```. |                    |                                          |