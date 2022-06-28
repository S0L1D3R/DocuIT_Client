package pl.interia.rym.maciej.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import pl.interia.rym.maciej.DITClientOptions;
import pl.interia.rym.maciej.Structures.DITLink;
import pl.interia.rym.maciej.Structures.DITLinkGhost;
import pl.interia.rym.maciej.Structures.DITProject;
import pl.interia.rym.maciej.Structures.DITProjectElement;

public class DataOptionsParser {
	
	private File file;
	private String pathToFile;
	private BufferedReader inputStream;
	private PrintWriter outStream;
	
	public DataOptionsParser(String pathToFile) {
		this.pathToFile = pathToFile;
		file = new File(pathToFile);
	}
	
	public DITClientOptions readOptionsFromFile() {
		DITClientOptions optionsToLoad = new DITClientOptions();
		try {
			inputStream = new BufferedReader(new FileReader(file));
			

			optionsToLoad.setServerIP(inputStream.readLine());
			optionsToLoad.setPort(Integer.parseInt(inputStream.readLine()));
			optionsToLoad.setBackupFolder(inputStream.readLine());
			optionsToLoad.setUserName(inputStream.readLine());
			optionsToLoad.setPassword(inputStream.readLine());
			
			
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return optionsToLoad;
	}
	
	public void saveOptionsToFile(DITClientOptions options) {
		try {
			outStream = new PrintWriter(new FileWriter(file));
			
			String backupFolder = options.getBackupFolder();
			if(backupFolder.charAt(backupFolder.length()-1) != '\\') {
				backupFolder = backupFolder + "\\";
			}
			
			outStream.println(options.getServerIP());
			outStream.println(options.getPort());
			outStream.println(options.getBackupFolder());
			outStream.println(options.getUserName());
			outStream.println(options.getPassword());
			
			outStream.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getPathToFile() {
		return pathToFile;
	}

	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
	}

	public BufferedReader getInputStream() {
		return inputStream;
	}

	public void setInputStream(BufferedReader inputStream) {
		this.inputStream = inputStream;
	}

	public PrintWriter getOutStream() {
		return outStream;
	}

	public void setOutStream(PrintWriter outStream) {
		this.outStream = outStream;
	}
	
	
}
