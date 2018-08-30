package hw2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HW2
{
	
	// Function to bubble-sort the array list.
	public static void bubbleSort(ArrayList<String> arr)
	{
		for(int i = 0; i < arr.size() - 1; i++)
		{
			for(int j = 0; j < arr.size() - 1 - i; j++)
			{
				if(arr.get(j).compareTo(arr.get(j + 1)) > 0)
				{
					String temp = arr.get(j);
					arr.set(j, arr.get(j + 1));
					arr.set(j + 1, temp);
				}
			}
		}
	}
	
	// Function to selection-sort the array list.
	public static void selectionSort(ArrayList<String> arr)
	{
		for(int i = 0; i < arr.size() - 1; i++)
		{
			String small = arr.get(i);
			int pos = i;
			for(int j = i + 1; j < arr.size(); j++)
			{
				if(small.compareTo(arr.get(j)) > 0)
				{
					small = arr.get(j);
					pos = j;
				}
			}
			String temp = arr.get(i);
			arr.set(i, arr.get(pos));
			arr.set(pos, temp);
		}
	}
	
	// Function to apply binary search on the dictionary list.
	public static boolean binarySearch(ArrayList<String> arr, int l, int h, String str)
	{
		if(h >= l)
		{
			int m = l + (h - l) / 2;
			if(str.compareTo(arr.get(m)) == 0) // I used 'if(str == arr.get(m))', the output was showing count as 84 words.
			{
				return true;
			}
			else if(str.compareTo(arr.get(m)) < 0)
			{
				return binarySearch(arr, l, m - 1, str);
			}
			else
			{
				return binarySearch(arr, m + 1, h, str);
			}
		}
		return false;
	}
	
	public static void main(String[] args)
	{
		try
		{
			// Read the 'dictionary' file.
			BufferedReader reader1;
			reader1 = new BufferedReader(new FileReader("C:\\Users\\HP G4\\Desktop\\New folder\\JAVA Lab\\csx-351-hw2-ykcse-master\\HW2-dictionary.txt"));
			String line1 = reader1.readLine();
			ArrayList<String> arr1 = new ArrayList<String>();
			while(line1 != null)
			{
				arr1.add(line1);
				line1 = reader1.readLine();	
			}
			reader1.close();
			// Read the 'key-words' file.
			BufferedReader reader2;
			reader2 = new BufferedReader(new FileReader("C:\\Users\\HP G4\\Desktop\\New folder\\JAVA Lab\\csx-351-hw2-ykcse-master\\HW2-keywords.txt"));
			String line2 = reader2.readLine();
			ArrayList<String> arr2 = new ArrayList<String>();
			while(line2 != null)
			{
				arr2.add(line2);
				line2 = reader2.readLine();
			}
			reader2.close();
			// Sorting both the array lists.
			bubbleSort(arr1);
			selectionSort(arr2);
			System.out.println(arr1);
			System.out.println(arr2);
			BufferedWriter writer;
			writer = new BufferedWriter(new FileWriter("C:\\Users\\HP G4\\Desktop\\New folder\\JAVA Lab\\csx-351-hw2-ykcse-master\\HW2-keywords_not_found_output.txt"));
			// Searching the dictionary list, getting the count and writing to the output file.
			int cnt = 0;
			for(int i = 0; i < arr2.size(); i++)
			{
				if(!binarySearch(arr1, 0, arr1.size() - 1, arr2.get(i)))
				{
					writer.write("keyword not found : " + arr2.get(i));
					writer.newLine();
					cnt++;
				}
			}
			writer.newLine();
			writer.write("Number of keywords not found : " + cnt);
			writer.close();
		}
		// Catch the Input Output Exception(i.e. if file not found).
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
}
