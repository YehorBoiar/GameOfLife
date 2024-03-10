# P2: Game of Life

## Workflow

Before you start write your code, please, write `git pull` so we don't have as much conflicts as we could.


For implementing a new big feature you should create a new branch and do it there.

```git 
git branch <branchName>
git checkout <branchName>
```

When you finish implementing your featrure and you think that it can go to the master branch 
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
            └── StructuresMenu.java
```