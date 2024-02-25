package fileio;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import renderer.Renderer;

public class Load {
	Renderer renderer = Renderer.getInstance();

	public void loadFile() {
		try {
			FileDialog fd = new FileDialog(new JFrame());
			fd.setVisible(true);
			File file = fd.getFiles()[0];

			if (!getExtension(file.getAbsolutePath()).equals("gol")) {
				JOptionPane.showMessageDialog(null, "File you've chosen is not .gol file");
				System.out.println("File you've chosen is not .gol file");
				return;
			}

			Scanner scanner = new Scanner(file);
			boolean[][] gridFromFile = writeToGrid(scanner, fd);
			renderer.setGrid(gridFromFile);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File you've chosen doesn't exist" + e.getMessage());
		}
	}

	private boolean[][] writeToGrid(Scanner scanner, FileDialog fd) {
		int maxRows = renderer.getHeight();
		int maxCols = renderer.getWidth();

		int rows = 0;
		int cols = 0;

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			rows++;

			if (line.length() > cols) {
				cols = line.length();
			}
		}

		try {
			scanner = new Scanner(new File(fd.getFiles()[0].getAbsolutePath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		boolean[][] newGrid = new boolean[Math.max(rows, maxRows)][Math.max(cols, maxCols)];

		int counter = 0;
		while (scanner.hasNextLine() && counter < maxRows) {
			String line = scanner.nextLine();

			int lineLength = Math.min(line.length(), maxCols);

			for (int i = 0; i < lineLength; i++) {
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
