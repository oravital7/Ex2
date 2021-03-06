package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 
 * @author Dana Mor and Or Avital
 * this class get path to a csv file and transfer the information 
 * into comfortable data stracture
 *
 */
public class CsvReader {
	public static ArrayList<ArrayList<String>> arr;
	private String pathOfFile;

	/**
	 * 
	 * @param str - path of the file
	 */
	public CsvReader(String str) 
	{
		pathOfFile=str;
		arr = new ArrayList<ArrayList<String>>();
		initialize();

	}

	/**
	 * intializing the arrayList
	 */
	private void initialize()  {
		String csvFile = this.pathOfFile;
		String line = "";
		String cvsSplitBy = ",";

		int counter = 0, i=1;
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{

			while ((line = br.readLine()) != null) 
			{				
				if(i>1)
				{
					arr.add(new ArrayList<String>());
					String[] userInfo = line.split(cvsSplitBy);

					for (int j = 0; j <userInfo.length; j++) {
						arr.get(counter).add(userInfo[j]);
					}		
					counter++;
				}
				else
					i++;

			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * returns the header
	 */
	public ArrayList<String>  get_header()
	{
		ArrayList<String> header = new ArrayList<String>();
		for (int i = 0; i <this.get_column(); i++) {
			header.add(arr.get(0).get(i));			
		}
		return header;
	}
	
	/**
	 * 
	 * @return true if the csv is valid and false otherwise
	 */
	public boolean checkCsv() {
		ArrayList<String> arr = get_header();
		if(get_column()<11) return false;
		boolean check[] = new boolean[11];
		for(int i=0; i<check.length; i++) {
			if(arr.get(i).contains("MAC")) check[0]=true;
			else if(arr.get(i).contains("SSID")) check[1]=true;
			else if(arr.get(i).contains("AuthMode")) check[2]=true;
			else if(arr.get(i).contains("FirstSeen")) check[3]=true;
			else if(arr.get(i).contains("Channel")) check[4]=true;
			else if(arr.get(i).contains("RSSI")) check[5]=true;
			else if(arr.get(i).contains("CurrentLatitude")) check[6]=true;
			else if(arr.get(i).contains("CurrentLongitude")) check[7]=true;
			else if(arr.get(i).contains("AltitudeMeters")) check[8]=true;
			else if(arr.get(i).contains("AccuracyMeters")) check[9]=true;
			else if(arr.get(i).contains("Type")) check[10]=true;
		}
		for(int i=0; i<check.length; i++) {
			if(check[i]==false) return false;
		}
		return true;
	}

	/**
	 * 
	 * @return the number of rows
	 */
	public int get_rows()
	{
		return arr.size();
	}

	/**
	 * 
	 * @return the number of columns
	 */
	public int get_column()
	{
		if(arr.size()==0) return 0;
		return arr.get(0).size();
	}

	/**
	 * 
	 * @return copy of the array
	 */
	public ArrayList<ArrayList<String>> get_Array()
	{
		ArrayList<ArrayList<String>> CopyOfArr = new ArrayList<ArrayList<String>>();

		for (int m = 1; m < arr.size(); m++) {
			CopyOfArr.add(new ArrayList<String>()); //adding array
			for (int j = 0; j < this.get_column(); j++) {
				CopyOfArr.get(m-1).add(arr.get(m).get(j));	
			}
		}

		return CopyOfArr;

	}


}
