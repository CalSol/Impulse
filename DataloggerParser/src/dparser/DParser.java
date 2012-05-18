package dparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Parser written for the CalSol datalogger
 * @author Derek Chou
 * @since 2012.05.12
 * @version 1.00
 */

public class DParser {
	
	/**
	 * The parser object that this class uses to parse the datalogger
	 * lines.
	 */
	private static Parser p = new Parser();
	
	/**
	 * @param args : Main class; args is unused.
	 */
	public static void main(String[] args) {
		
		while (true) {
			JFileChooser fd = new JFileChooser(".");
			FileFilter filter = new FileNameExtensionFilter(
					"Datalogger ASCII file (*.DLA)", "dla");
			fd.addChoosableFileFilter(filter);
			int returnVal = fd.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
			    File file=fd.getSelectedFile();
			    try {
					Scanner f = new Scanner(file);
					while (f.hasNext()) {
						p.parse(f.nextLine());
					}
					writeFiles(file);
				} catch (FileNotFoundException e) {
					// Do nothing!
					e.printStackTrace();
				}
			}
			else if (returnVal == JFileChooser.CANCEL_OPTION)
				break;
			else
				System.out.print("Error opening file!");
		}
	}

	/**
	 * Writes the parsed data into .csv and .txt files. The configuration,
	 * firmware, and datalogger version info will be in a .txt file named 
	 * "(dataloggerfilename)cfg.txt"
	 * @param file : The original file that was opened for parsing - needed
	 * to get the name of the file.
	 */
	private static void writeFiles(File file) {
		String fileName = file.getName();
		fileName = fileName.substring(0,fileName.indexOf('.')) + "p.csv";
		try {
			PrintStream pr = new PrintStream(new File(fileName));
			pr.print(p);
		} catch (FileNotFoundException e) {
			// Do nothing!
			e.printStackTrace();
		}
	}

}