package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {
	public static ArrayList<ArrayList<String>> arr;
	private String pathOfFile;

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
	
	public boolean checkCsv() {
		ArrayList<String> arr = get_header();
		if(get_column()<11) return false;
		boolean check[] = new boolean[11];
		for(int i=0; i<check.length; i++) {
			if(arr.get(i).equals("MAC")) check[0]=true;
			else if(arr.get(i).equals("SSID")) check[1]=true;
			else if(arr.get(i).equals("AuthMode")) check[2]=true;
			else if(arr.get(i).equals("FirstSeen")) check[3]=true;
			else if(arr.get(i).equals("Channel")) check[4]=true;
			else if(arr.get(i).equals("RSSI")) check[5]=true;
			else if(arr.get(i).equals("CurrentLatitude")) check[6]=true;
			else if(arr.get(i).equals("CurrentLongitude")) check[7]=true;
			else if(arr.get(i).equals("AltitudeMeters")) check[8]=true;
			else if(arr.get(i).equals("AccuracyMeters")) check[9]=true;
			else if(arr.get(i).equals("Type")) check[10]=true;
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
			CopyOfArr.add(new ArrayList<String>());//adding array
			for (int j = 0; j < this.get_column(); j++) {
				CopyOfArr.get(m-1).add(arr.get(m).get(j));	
			}
		}

		return CopyOfArr;

	}


}
