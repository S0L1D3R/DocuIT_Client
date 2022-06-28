package pl.interia.rym.maciej.Structures;

import java.util.ArrayList;
import java.util.Random;

import pl.interia.rym.maciej.GUI.DITWorkingArea;

public class DITProject {

	private String name;
	private int id;

	private ArrayList<DITProjectElement> elements = new ArrayList<DITProjectElement>();
	private ArrayList<DITLink> links = new ArrayList<DITLink>();
	
	private static ArrayList<DITProject> availableProjects = new ArrayList<DITProject>();
	
	public DITProject() {
	}
	
	public DITProject(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static DITProject getProjectByID(int id) {
		for (DITProject ditProject : availableProjects) {
			if(ditProject.getId() == id) {
				return ditProject;
			}
		}
		return null;
	}
	
	public static void addProject(DITProject project) {
		for (DITProject ditProject : availableProjects) {
			if(ditProject.getId() == project.getId()) {
				return;
			}
		}
		availableProjects.add(project);
	}
	
	public static String[] getAvailableProjectsAsArray() {
		String[] arrayToReturn = new String[availableProjects.size()];
		int counter = 0;
		for (DITProject prj : availableProjects) {
			arrayToReturn[counter] = prj.getId()+"_"+prj.getName();
			counter++;
		}

		return arrayToReturn;
	}
	
	/** 
	 * replaces current project with new project and fills elements.
	 * @param id
	 * @param name
	 * @param projectPanel
	 */
	public DITProject(int id, String name, DITWorkingArea projectPanel) {
		this.id = id;
		this.name = name;
	}
	
	public void removeLinkByItsElements(DITLink link) {
		links.removeIf(t -> ((t.getElement1().getId() == link.getElement1().getId()) || (t.getElement1().getId() == link.getElement2().getId()))
							&&
							((t.getElement2().getId() == link.getElement1().getId()) || (t.getElement2().getId() == link.getElement2().getId()))
							);
	}
	
	public boolean isLinkAlreadyCreated(DITLink link) {
		for (DITLink ditLink : links) {
			if(link.getElement1().getId() == ditLink.getElement1().getId() || link.getElement1().getId() == ditLink.getElement2().getId()) {
				if(link.getElement2().getId() == ditLink.getElement1().getId() || link.getElement2().getId() == ditLink.getElement2().getId()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void addNewElement(DITProjectElement element) {
		boolean isUsed = true;
		int newID = 0;
		while(isUsed) {
			newID = new Random().nextInt(5000);
			
			for (DITProjectElement ditProjectElement : elements) {
				if(ditProjectElement.getId() == newID) {
					break;
				}
			}
			isUsed = false;
		}
		
		element.setId(newID);
		elements.add(element);
	}
	
	public DITProjectElement getElementByID(int id) {
		for (DITProjectElement el : elements) {
			if(el.getId() == id) {
				return el;
			}
		}
		return null;
	}
	
	public void addExistingElement(DITProjectElement element) {
		elements.add(element);
	}
	
	public void addLink(DITLink link) {
		if(!isLinkAlreadyCreated(link)) {
			links.add(link);	
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<DITProjectElement> getElements() {
		return elements;
	}

	public void setElements(ArrayList<DITProjectElement> elements) {
		this.elements = elements;
	}

	public ArrayList<DITLink> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<DITLink> links) {
		this.links = links;
	}

	public static ArrayList<DITProject> getAvailableProjectsAsArrayList() {
		return availableProjects;
	}

	public static void setAvailableProjects(ArrayList<DITProject> availableProjects) {
		DITProject.availableProjects = availableProjects;
	}
	
}
