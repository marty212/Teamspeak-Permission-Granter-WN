package process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import java.util.Scanner;

/**
 * Creates a roster we can assign teamspeak permissions based on.
 * @author Martin Borstad
 *
 */
public class Roster {

	private HashMap<String, String> storage;
	private File file;
	
	public Roster()
	{
		file = new File("users.txt");
		storage = new HashMap<String, String>();
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Scanner s = new Scanner(file);
			while(s.hasNextLine())
			{
				String a[] = s.nextLine().split(":");
				storage.put(a[0], a[1]);
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public Rank getRank(String memberName)
	{
		if(storage.containsKey(memberName))
		{
			return Rank.RankConvert(storage.get(memberName));
		}
		return null;
	}
	
	public String toString()
	{
		try {
			Scanner s = new Scanner(file);
			StringBuilder str = new StringBuilder();
			str.append("<table>");
			while(s.hasNextLine())
			{
				str.append("<tr><td><p>" + s.nextLine() + "</p></td><tr>");
			}
			str.append("</table>");
			return str.toString();
		} catch (FileNotFoundException e) {
			return null;
		}
		
	}
	
	public boolean dropTable()
	{
		file.delete();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		storage = new HashMap<String, String>();
		return true;
	}
	public boolean updateRank(String memberName, String rank)
	{
		if(rank.equals("dropdown"))
		{
			storage.remove(memberName);
		}
		else
		{
			storage.put(memberName, rank);
		}
		try {
			file.delete();
			file.createNewFile();
			FileWriter f = new FileWriter(file);
			Set<String> keys = storage.keySet();
			for(String k : keys)
			{
				f.write(k + ":" + storage.get(k) + "\n");
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
