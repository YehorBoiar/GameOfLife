CS1006 Project 2 - Game Of Life by Christopher Taylor, Yehor Boiar and Anngeni Vincent

This is our implementation of Game Of Life, a zero-player game, designed by mathematician John Conway in 1970. It consists of a 50x50 grid of cells which can either be "live" or "dead".

The game proceeds in steps and on each step, each cell in the grid may change its status based on the number of live cells touching it. This process continues, one step at a time, and can end up producing some very interesting patterns.

To run the code, install an extension to run Java code in a preferred IDE, we used 'Extension Pack for Java' by Microsoft for Visual Studio Code. Then run the main() method from /src/main/Main.java and we hope you enjoy our project.

## Workflow

Before you start write your code, please, write `git pull` so we can avoid as many conflicts as possible.


For implementing a new big feature, create a new branch and do it there.

```git 
git branch <branchName>
git checkout <branchName>
```

When you finish implementing your feature and you think that it can go to the master branch:
```git 
git checkout master
git merge <branchName>
```

And don't forget to commit your code as you go :)

```git 
git add <filename>
git commit -m "Thoughtful message"
```

## File structure
```
└── 📁p2repo
    └── 📁.vscode
        └── settings.json
    └── README.md
    └── 📁games
        └── game0.gol
        └── game1.gol
        └── game2.gol
    └── 📁icons
        └── Big_Dot.png
        └── Big_Erase.png
        └── Dot.png
        └── Erase.png
        └── Glider.png
        └── Twicker.png
    └── 📁src
        └── 📁brushes
            └── Brush.java
        └── 📁fileio
            └── Load.java
            └── Save.java
        └── 📁input
            └── MenuListener.java
            └── MyKeyAdapter.java
            └── MyMouseListener.java
            └── MyMouseMotionAdapter.java
            └── PanningHandler.java
            └── ZoomHandler.java
        └── 📁logic
            └── Logic.java
        └── 📁main
            └── Main.java
        └── 📁renderer
            └── Renderer.java
        └── 📁ui
            └── ButtonPanel.java
            └── GameMenu.java
            └── OptionsMenu.java
            └── PanPanel.java
            └── StructuresMenu.java
```