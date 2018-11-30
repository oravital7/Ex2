package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import GIS.Pdata;
import GIS.Project;

public class MultiCSV {
	private String folder;
	private Project p;
	private File files;

	public MultiCSV(String folder)
	{
		p = new Project(new Pdata());
		this.folder=folder;
		files = new File(folder);
		listFileTree(files);
	}

	//recursive method for looping directory
	private void listFileTree(File dir) {
		if(dir==null||dir.listFiles()==null){
			return;
		}

		for (File entry : dir.listFiles()) {
			if (entry.isDirectory()) 
				listFileTree(entry);

			if(entry.getName().endsWith(".csv")) {
				Csv2Elem g = new Csv2Elem(entry);
				p.add(g.MakeElements());
			}
		}
		return;
	}

	public void convert2kml() {
		StringBuilder sb = new StringBuilder();
		Csv2kml convert = new Csv2kml();
		sb.append(convert.start2CSV(p));

		try {
			PrintWriter pw = new PrintWriter(new File("output.kml"));
			pw.write(sb.toString());
			pw.close();
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
