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

## Build from source

#### Linux:
```$ ./gradlew build```

#### Windows:
```$ ./gradlew.bat build```

Unzip from ``build/distribution/fillipost-cli-1.0-SNAPSHOT.zip(.tar)``.

``$ unzip build/distributions/fillipost-cli-1.0-SNAPSHOT.zip``

Write in terminal:

``
$ cd fillipost-cli-1.0-SNAPSHOT/bin/
$ fillipost-cli``

Output: 

``
build     Assemble a project: build <project path> [flag]
                Flags:
                --resources                  Assemble resources.
                --post <post or post folder> Assemble specific post
                --posts-only                 Assemble only posts
create    Creates a empty project: create <project_folder>
clean     Clean build directory: clean <project path>

``

## Getting started

#### Step 1. Create a project
Create a project in folder `FilliEx`

#### Linux:
```$ fillipost-cli create your_path/FilliEx```

#### Windows:
```$ fillipost-cli.bat create your_path/FilliEx```