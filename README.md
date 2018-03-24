# Fillipost
## What is it?
Fillipost is a simple open-source static sites generator for personal, project or organization sites. Written in Java by Ivanzar.

## Features
Fillipost provides an easy way to create own sites using the human-readable markup language Markdown and Liquid templates. Static web pages are ready for use on any web server, such as Apache or Nginx, without deploying complex systems.

## Commands

|                                    |                                                 |             |                                          |
|------------------------------------|-------------------------------------------------|-------------|------------------------------------------|
| **build `<project path>` `[flag]`**  | &emsp;Assemble a project. | &emsp;Flags:      |                                          |
|                                    |                                                 | &emsp;&emsp;--resources [post file] | Assemble resources (folder: `resources`) |
|                                    |                                                 | &emsp;&emsp;--post       | Assemble specific post (folder: `posts`)    |
|                                    |                                                 | &emsp;&emsp;--posts-only | Assemble only (folder: `posts`)
| **create `<project path>`**        | &emsp;Create a new project.                        |              |                                          |
| **clean `<project path>`**         | &emsp;Clean the directory ``` project path/build ```. |              |                                          |