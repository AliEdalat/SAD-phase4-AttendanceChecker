package cache;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.commons.io.input.ReversedLinesFileReader;

public class Cache {
	private PrintWriter out;
	private int index;
	
	private int findIndex() throws IOException {
		File fileReader = new File("lostData.txt");
		ReversedLinesFileReader reversedLinesFileReader = new ReversedLinesFileReader(fileReader);
		String lastLine = reversedLinesFileReader.readLine();
		String[] parts = lastLine.split("\\s+");
		return Integer.parseInt(parts[0]) + 1;
	}
	
	private void open() throws IOException {
		FileWriter fw = new FileWriter("lostData.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);
		out = new PrintWriter(bw);
	}
	
	private void close() {
		out.close();
	}
	
	public void put(String data) throws IOException {
		try {
			index = findIndex();
		} catch(Exception e) {
			index = 0;
		}
		open();
		out.println(String.valueOf(index) + " " + data);
		close();
	}
	
	public void put(ArrayList<String> dataset) throws IOException {
		index = 0;
		File fileReader = new File("lostData.txt");
		fileReader.delete();
		open();
		for(String data : dataset)
			out.println(String.valueOf(index++) + " " + data);
		close();
	}
	
	public ArrayList<String> get() throws IOException {
		FileReader fileReader = new FileReader("lostData.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        ArrayList<String> result = new ArrayList<>();
        while((line = bufferedReader.readLine()) != null) {
        	String[] parts = line.split("\\s+");
            result.add(parts[1]);
        }
        File file = new File("lostData.txt");
		file.delete();
        return result;
	}
}
