package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import GIS.Pdata;
import GIS.Project;

/**
 * This class starts the whole process and scans all the folders in a path
 * Exports the file to any file type that is requested
 * @author OrAvital & DanaMor
 * 
 */
public class MultiCSV {
	private String folder;
	private Project p;
	private File files;
	
/**
 * Make a new Project and call the recursive method
 * @param folder
 */
	public MultiCSV(String folder)
	{
		p = new Project(new Pdata()); // Make a new Project
		this.folder=folder;
		files = new File(folder);
		listFileTree(files);
	}

	/**
	 * The recursive method for looping directory
	 * and scan the all folders
	 * @param dir
	 */
	private void listFileTree(File dir) {
		if(dir==null||dir.listFiles()==null){ // condition for stop
			return;
		}

		for (File entry : dir.listFiles()) { // For to scan the whole folder
			if (entry.isDirectory())  // If we find a folder call the method with folder we found
				listFileTree(entry);

			if(entry.getName().endsWith(".csv")) { // If is a csv File, make a element for that
				Csv2Elem g = new Csv2Elem(entry);
				p.add(g.MakeElements());
			}
		}
		return;
	}

	/**
	 * This method finally export a kml file
	 */
	public void convert2kml() {
		StringBuilder sb = new StringBuilder();
		Csv2kml convert = new Csv2kml();
		sb.append(convert.start2CSV(p)); // Call the start2CSV class and get back the KML translation

		try {
			PrintWriter pw = new PrintWriter(new File("output.kml"));
			pw.write(sb.toString());
			pw.close(); // Export the file
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Project getP() {
		return p;
	}

	public String getFolder() {
		return folder;
	}
}
