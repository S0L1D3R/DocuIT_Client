package pl.interia.rym.maciej.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import pl.interia.rym.maciej.DITClientApp;
import pl.interia.rym.maciej.Structures.DITLink;
import pl.interia.rym.maciej.Structures.DITLinkGhost;
import pl.interia.rym.maciej.Structures.DITProject;
import pl.interia.rym.maciej.Structures.DITProjectElement;

public class DataProjectParser {
	
	private String pathToFolder;
	private BufferedReader inputStream;
	private PrintWriter outStream;
	
	
	public DataProjectParser() {
	}
	
	/**
	 * Gdy
	 * @param pathToFolder
	 */
	public DataProjectParser(String pathToFolder) {
		this.pathToFolder = pathToFolder;
	}
	
	/**
	 * Backups path is in options
	 * @param projectToSave
	 */
	public void saveProjectToBackupsFolder(DITProject projectToSave) {
		File file = new File(DITClientApp.options.getBackupFolder() + "\\" + (projectToSave.getId()+"_"+projectToSave.getName()+".txt"));		
		
		try {
			file.createNewFile();

			outStream = new PrintWriter(new FileWriter(file));
			
			for (DITProjectElement element : projectToSave.getElements()) {
				outStream.println(element.getId());
				outStream.println(element.getX());
				outStream.println(element.getY());
				outStream.println(element.getTitleField().getText());
				outStream.println(element.getTextArea().getText());
				for (DITLink link : projectToSave.getLinks()) {
					if(link.hasIdInOneOfElements(element.getId())) {
						if(link.getElement1().getId() == element.getId()) {
							outStream.print(link.getElement2().getId() + ",");
							continue;
						}
						if(link.getElement2().getId() == element.getId()) {
							outStream.print(link.getElement1().getId() + ",");
							continue;
						}
					}
				}
				outStream.println();
				outStream.println("-");
			}
			
			outStream.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** File has to be object**/
	public void saveProjectToFile(DITProject projectToSave) {		
		File file = new File(pathToFolder + "\\" + (projectToSave.getId()+"_"+projectToSave.getName()+".txt"));		
		
			try {
				file.createNewFile();

				outStream = new PrintWriter(new FileWriter(file));
				
				for (DITProjectElement element : projectToSave.getElements()) {
					outStream.println(element.getId());
					outStream.println(element.getX());
					outStream.println(element.getY());
					outStream.println(element.getTitleField().getText());
					outStream.println(element.getTextArea().getText());
					for (DITLink link : projectToSave.getLinks()) {
						if(link.hasIdInOneOfElements(element.getId())) {
							if(link.getElement1().getId() == element.getId()) {
								outStream.print(link.getElement2().getId() + ",");
								continue;
							}
							if(link.getElement2().getId() == element.getId()) {
								outStream.print(link.getElement1().getId() + ",");
								continue;
							}
						}
					}
					outStream.println();
					outStream.println("-");
				}
				
				outStream.close();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public DITProject readProjectFromBackupFolder(String fileName) {
		File file = new File(DITClientApp.options.getBackupFolder() + fileName + ".txt");
		DITProject project = null;
		if(file.exists()) {
			try {
				inputStream = new BufferedReader(new FileReader(file));
				int projectID = Integer.parseInt(file.getName().split("_")[0]);
				String projectName = file.getName().split("_")[1].replace(".txt", "");
				project = new DITProject(projectID, projectName);
				
				ArrayList<DITLinkGhost> linkGhosts = new ArrayList<DITLinkGhost>();
				
				String currentLine;
				while ((currentLine = inputStream.readLine()) != null) {
					if(currentLine.startsWith("#")) {
						continue;
					}
					if(currentLine.equalsIgnoreCase("-")) {
						continue;
					}
					int id = Integer.parseInt(currentLine);
					int x = Integer.parseInt(inputStream.readLine());
					int y = Integer.parseInt(inputStream.readLine());
					String title = inputStream.readLine();
					String content = inputStream.readLine();
					String links = inputStream.readLine();
					for (String linkID : links.split(",")) {
						if(!linkID.isEmpty()) {
							DITLinkGhost gh = new DITLinkGhost(id, Integer.parseInt(linkID));
							linkGhosts.add(gh);
						}
					}
					DITProjectElement el = new DITProjectElement(id, x, y, title, content);
					project.addExistingElement(el);
				}
				
				for (DITLinkGhost ditLinkGhost : linkGhosts) {
					DITProjectElement el1 = project.getElementByID(ditLinkGhost.getElement1Id());
					DITProjectElement el2 = project.getElementByID(ditLinkGhost.getElement2Id());
					
					project.addLink(new DITLink(el1, el2));
				}
				
				closeReader();
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		else {
			return null;
		}
		return project;
	}
	
	public DITProject readProjectFromFile(String folder, String fileName) {
		File file = new File(folder + "\\" + fileName);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.exists());
		DITProject project = null;
		if(file.exists()) {
			try {
				inputStream = new BufferedReader(new FileReader(file));
				int projectID = Integer.parseInt(file.getName().split("_")[0]);
				String projectName = file.getName().split("_")[1].replace(".txt", "");
				project = new DITProject(projectID, projectName);
				
				ArrayList<DITLinkGhost> linkGhosts = new ArrayList<DITLinkGhost>();
				
				String currentLine;
				while ((currentLine = inputStream.readLine()) != null) {
					if(currentLine.startsWith("#")) {
						continue;
					}
					if(currentLine.equalsIgnoreCase("-")) {
						continue;
					}
					int id = Integer.parseInt(currentLine);
					int x = Integer.parseInt(inputStream.readLine());
					int y = Integer.parseInt(inputStream.readLine());
					String title = inputStream.readLine();
					String content = inputStream.readLine();
					String links = inputStream.readLine();
					for (String linkID : links.split(",")) {
						if(!linkID.isEmpty()) {
							DITLinkGhost gh = new DITLinkGhost(id, Integer.parseInt(linkID));
							linkGhosts.add(gh);
						}
					}
					DITProjectElement el = new DITProjectElement(id, x, y, title, content);
					project.addExistingElement(el);
				}
				
				for (DITLinkGhost ditLinkGhost : linkGhosts) {
					DITProjectElement el1 = project.getElementByID(ditLinkGhost.getElement1Id());
					DITProjectElement el2 = project.getElementByID(ditLinkGhost.getElement2Id());
					
					project.addLink(new DITLink(el1, el2));
				}
				
				closeReader();
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		else {
			return null;
		}
		return project;
	}
	
	
	private void closeReader() {
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPathToFolder() {
		return pathToFolder;
	}

	public void setPathToFolder(String pathToFolder) {
		this.pathToFolder = pathToFolder;
	}

	public BufferedReader getInputStream() {
		return inputStream;
	}

	public void setInputStream(BufferedReader inputStream) {
		this.inputStream = inputStream;
	}

}
