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

```
build     Assemble a project: build <project path> [flag]
                Flags:
                --resources                  Assemble resources.
                --post <post or post folder> Assemble specific post
                --posts-only                 Assemble only posts
create    Creates a empty project: create <project_folder>
clean     Clean build directory: clean <project path>
```

## Getting started

#### Step 1. Create a project
Create a project in folder `FilliEx`

#### Linux:
```$ fillipost-cli create your_path/FilliEx```

#### Windows:
```$ fillipost-cli.bat create your_path/FilliEx```

### Step 2. Create a template
Move to **your_path/FilliEx/patterns/**. Create file **main.liquid**.

Copy and past following code:

```html
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>{{ post.name }}</title>
 
<link href="https://fonts.googleapis.com/css?family=Roboto:900,400" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="./resources/style/main.css">
 
{% include 'head.liquid' %}
 
<div id="page-content">{{ post.content }}</div>
```

### Step 3. Create a snippet
Move to **your_path/FilliEx/snippets/**. Create file **head.liquid**.

Copy and past following code:

```html
<link rel="stylesheet" type="text/css" href="./resources/style/head.css">
<div id="page-head">
	<div id="page-head--title"><h3>Example</h3></div>
</div>
```

### Step 4. Create CSS files

Move to **your_path/FilliEx/resources/style/**. Create files **head.css** и **main.css**.

Copy and past in **main.css** following code:

```css
body {
    margin: 0;
    font-family: 'Roboto Regular', sans-serif;
}

#page-content {
    margin-left: 50px;
    margin-right: 50px;
    margin-top: 120px;
}

```

Copy and past in **head.css** following code:

```css
#page-head {
    overflow: hidden;
    position: fixed;
    width: 100%;
    top: 0;
}

#page-head--title {
    background-color: #3F51B5;
    height: 50px;
    color: white;
    line-height: 50px;
    text-indent: 10px;
}

#page-head--title h3 {
    margin-top: 0px;
}
```

### Step 5. Create a post

Move to **your_path/FilliEx/posts/**. Create a file **example.md**.

Copy and past following code:

```json
---
{
  "post": {
    "name": "Example",
    "pattern": "main.liquid"
  }
}
---
```

### Step 5. Assemble and run

Assemble project
#### Linux:
```$ fillipost-cli build your_path/FilliEx```
#### Windows:
```$ fillipost-cli.bat build your_path/FilliEx```
Move to **your_path/FilliEx/build/**. Open in browser this file: **example.html**

![src/resources/img/example.png](./src/resources/img/example.png)