import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Optional;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class Load {
	Renderer renderer = Renderer.getInstance();

	public void loadFile() {
		try {
			FileDialog fd = new FileDialog(new JFrame());
			fd.setVisible(true);	
			File file = fd.getFiles()[0];

			if(!getExtension(file.getAbsolutePath()).equals("gol")){
				JOptionPane.showMessageDialog(null, "File you've chosen is not .gol file"); 
				System.out.println("File you've chosen is not .gol file");
				return;
			}
			

			Scanner scanner = new Scanner(file);
			boolean[][] gridFromFile = writeToGrid(scanner);
			renderer.setGrid(gridFromFile);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File you've chosen doesn't exist" + e.getMessage());
		}
	}

	private boolean[][] writeToGrid(Scanner scanner) { // TODO - Handle the case when grid from file is smaller than
														// original grid
		boolean[][] newGrid = new boolean[Renderer.getHeight()][Renderer.getHeight()];
		int counter = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == 'o') {
					newGrid[counter][i] = true;
				} else {
					newGrid[counter][i] = false;
				}
			}
			counter += 1;
		}
		return newGrid;
	}

	
	public String getExtension(String filePath) {
		return filePath.substring(filePath.lastIndexOf(".") + 1);
	}
}
