package fileio;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class Save {
    private boolean[][] grid;
    public Save(boolean[][] grid){
        this.grid = grid;
    }

    public void SaveToFile(){
        try {
            File saveDir = new File("games");
            
            File[] files = saveDir.listFiles(); 
          
        
            String filename = saveDir.getPath() + "/" + "game" +files.length + ".gol";
            File file = new File(filename);
            System.out.println("File created " + filename + '.');

            Writer writer = new FileWriter(file);
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == true) {
                        writer.write('o');
                    }else{
                        writer.write('.');
                    }
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Game save was unsuccessful: " + e.getMessage());
        }
    }


}

