package pl.interia.rym.maciej.IO.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import pl.interia.rym.maciej.Structures.DITFolder;
import pl.interia.rym.maciej.Structures.DITLink;
import pl.interia.rym.maciej.Structures.DITProject;
import pl.interia.rym.maciej.Structures.DITProjectElement;

public class DataNetworkParser {
	
	protected BufferedReader inputStream;
	protected PrintWriter outStream;
	
	public DataNetworkParser(BufferedReader inputStream, PrintWriter outStream) {
		this.inputStream = inputStream;
		this.outStream = outStream;
	}
	//////
	
	public void parseDataFromServer() {
		String currentLine;
		ArrayList<DITFolder> foldersToReturn = new ArrayList<DITFolder>();
		try {
			while ((currentLine = inputStream.readLine()) != null) {
				if(currentLine.equals("Returning_Data:Stop")) {
					break;
				}
				if(currentLine.equals("<Folder")) {
					int folderID = Integer.parseInt(inputStream.readLine());
					String folderName = inputStream.readLine();
					DITFolder folder = new DITFolder(folderID, folderName);
					
					System.out.println(folder.getId());
					System.out.println(folder.getFolderName());
					
					boolean readingProjects = true;
					
					while(readingProjects && (currentLine = inputStream.readLine()) != null) {
						if(currentLine.equals("Projects>")) {
							System.out.println(" koniec projektow w folderze");
							readingProjects = false;
						}
						if(currentLine.equals("<Project")) {
							System.out.println(" start projectu");
							int projectID = Integer.parseInt(inputStream.readLine());
							String projectName = inputStream.readLine();
							DITProject prj = new DITProject(projectID, projectName);
							System.out.println("  prj " + prj.getId());
							System.out.println("  prj " + prj.getName());
							
							boolean readingElements = true;
							String elementsLine;
							while(readingElements && (elementsLine = inputStream.readLine()) != null) {
								if(elementsLine.equals("Elements>")) {
									readingElements = false;
									continue;
								}
								if(elementsLine.equals("<Element")) {
									System.out.println("    start elementu");
//									System.out.println("       " + inputStream.readLine());
//									System.out.println("       " + inputStream.readLine());
//									System.out.println("       " + inputStream.readLine());
//									System.out.println("       " + inputStream.readLine());
//									System.out.println("       " + inputStream.readLine());
									
									int elementID = Integer.parseInt(inputStream.readLine());
									int elementX = Integer.parseInt(inputStream.readLine());
									int elementY = Integer.parseInt(inputStream.readLine());
									String elementTitle = inputStream.readLine();
									String elementContent = inputStream.readLine();
									
									DITProjectElement element = new DITProjectElement(elementID, elementX, elementY, elementTitle, elementContent);
									prj.addExistingElement(element);
									
									continue;
								}
								if(elementsLine.equals("Element>")) {
									System.out.println("    stop elementu");
									continue;
								}
							}
							
							boolean readingLinks = true;
							String linksLine;
							while(readingLinks && (linksLine = inputStream.readLine()) != null) {
								if(linksLine.equals("Links>")) { 
									readingLinks = false;
									continue;
								}
								if(linksLine.equals("<Link")) { 
									System.out.println("    start linka");
									int linkID1 = Integer.parseInt(inputStream.readLine());
									int linkID2 = Integer.parseInt(inputStream.readLine());
									
									System.out.println("         ID1: " + linkID1);
									System.out.println("         ID2: " + linkID2);
									
									DITLink link = new DITLink(prj.getElementByID(linkID1), prj.getElementByID(linkID2));
									prj.addLink(link);
								}
								if(linksLine.equals("Link>")) { 
									System.out.println("    stop linka");
								}
							}
							folder.addProject(prj);
							
						}
						if(currentLine.equals("Project>")) {
							System.out.println(" stop projectu");
						}
					}
					
					foldersToReturn.add(folder);
					System.out.println("     size " + foldersToReturn.size());
					
					
					
				}
				if(currentLine.equals("Folder>")) {
					System.out.println("koniec folderu");
					continue;
				}
				
			}
//			
//			for (DITFolder ditFolder : foldersToReturn) {
//				System.out.println(ditFolder.getFolderName());
//				for (DITProject prj : ditFolder.getProjects()) {
//					System.out.println(prj.getName());
//					for (DITProjectElement el : prj.getElements()) {
//						System.out.println(el.getTitleField().getText());
//					}
//				}
//			}
			System.out.println("DataNetworkParser receive end");
			DITFolder.setFolders(foldersToReturn);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<DITProject> parseProjectsFromStream() {
		String currentLine;
		ArrayList<DITProject> projects = new ArrayList<DITProject>();
		try {
			while ((currentLine = inputStream.readLine()) != null) {
				if(currentLine.equals("<Project")) {
					parseProjectsFromStream();
				}
				else if(currentLine.equals("Project>")) {
					
				}
				int id = Integer.parseInt(currentLine);
				String name = inputStream.readLine();
				DITProject project = new DITProject(id, name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return projects;
	}
}
//if(currentLine.equals("Returning_Data:Stop")) {
//break;
//}
//int folderID = Integer.parseInt(currentLine);
//String folderName = inputStream.readLine();
//DITFolder folder = new DITFolder(folderID, folderName);
//System.out.println("zigzag" + folder.getFolderName());
//
//while((currentLine = inputStream.readLine()) != null) {
//System.out.println("Projecty " + currentLine);
//if(currentLine.equals("Returning_Data:Stop")) {
//	System.out.println("Stopped in projecty loop");
//	break;
//}
//if(currentLine.equals("Project>")) {
//	System.out.println("kuniec");
//	continue;
//}
//if(currentLine.equals("<Project")) {
//	System.out.println("stort");
//	int projectID = Integer.parseInt(inputStream.readLine());
//	String projectName = inputStream.readLine();
//	DITProject prj = new DITProject(projectID, projectName);
//	
//	while((currentLine = inputStream.readLine()) != null) {
//		if(currentLine.equals("<Element")) {
//			int elementID = Integer.parseInt(inputStream.readLine());
//			int elementX = Integer.parseInt(inputStream.readLine());
//			int elementY = Integer.parseInt(inputStream.readLine());
//			String Title = inputStream.readLine();
//			String Content = inputStream.readLine();
//			
//			DITProjectElement element = new DITProjectElement(elementID, elementX, elementY, Title, Content);
//			prj.addExistingElement(element);
//			continue;
//		}
//		if(currentLine.equals("<Link")) {
//			int linkID1 = Integer.parseInt(inputStream.readLine());
//			int linkID2 = Integer.parseInt(inputStream.readLine());
//			
//			DITLink link = new DITLink(prj.getElementByID(linkID1), prj.getElementByID(linkID2));
//			prj.addLink(link);
//			continue;
//		}
//		break;
//	}
//	System.out.println("dodany projekt " + prj.getName());
//	folder.addProject(prj);
//	continue;
//}
///// NIE BREJKOWAC
//}
//foldersToReturn.add(folder);
//System.out.println("asdasd");
