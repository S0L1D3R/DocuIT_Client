package pl.interia.rym.maciej.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataFileParserSTARE {

	protected File fileToRead;
	protected BufferedReader fileReader;
	
	public DataFileParserSTARE() {
		// TODO Auto-generated constructor stub
	}
	
	protected DataFileParserSTARE(String pathToFile) throws FileNotFoundException {
		File file = new File(pathToFile);
		if(file.exists()) {
			setFileToRead(new File(pathToFile));
		}
		else {
			fileToRead = null;
		}
	}
	
	
	/**
	 * Reads given file and returns String[][]. Use inherited DataFileParserSTARE classess for precise output.
	 * @return String[][]. 
	 * First [] is only storing numbers of segments in each file.
	 * Second [] is storing actual data that is stored inside segment.
	 * Segment = data of user
	 * Object in segment = line in a file
	 * @throws FileNotFoundException
	 */
	
	public String[][] getDataAsArray() {
		try {
			boolean fileHadToBeCreated = fileToRead.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			fileReader = new BufferedReader(new FileReader(fileToRead));

			String currentLine;
			int countSegments = 0;
			int countLinesInSegment = 0;
			while((currentLine = fileReader.readLine()) != null) {
				if(currentLine.startsWith("#") || currentLine.startsWith(" ")) {
					continue;
				}
				if(currentLine.equalsIgnoreCase("-")) {
					countSegments++;
					countLinesInSegment--;
				}
				countLinesInSegment++;
			}
			
			String[][] dataArray = new String[countSegments][countLinesInSegment/countSegments];
			fileReader = new BufferedReader(new FileReader(fileToRead));
			
			countSegments = 0;
			countLinesInSegment = 0;
			while((currentLine = fileReader.readLine()) != null) {
				if(currentLine.startsWith("#") || currentLine.startsWith(" ")) {
					continue;
				}
				if(currentLine.equalsIgnoreCase("-")) {
					countSegments++;
					countLinesInSegment = 0;
					continue;
				}
				dataArray[countSegments][countLinesInSegment] = currentLine;
				
				countLinesInSegment++;
			}
			
			closeReader();
			
			return dataArray;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	private void closeReader() {
		try {
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getFileToRead() {
		return fileToRead;
	}

	public void setFileToRead(File fileToRead) {
		this.fileToRead = fileToRead;
	}

	public BufferedReader getFileReader() {
		return fileReader;
	}
	
}
