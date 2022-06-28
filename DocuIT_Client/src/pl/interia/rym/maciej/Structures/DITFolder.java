package pl.interia.rym.maciej.Structures;

import java.util.ArrayList;

public class DITFolder {

	private static ArrayList<DITFolder> folders = new ArrayList<DITFolder>();
	private ArrayList<DITProject> projects = new ArrayList<DITProject>();
	
	private int id;
	private String folderName;
	
	public DITFolder(int id, String folderName) {
		this.id = id;
		this.folderName = folderName;
	}
	///////
	
	public static DITFolder getFolderByID(int idOfFolder) {
		for (DITFolder ditFolder : folders) {
			if(ditFolder.getId() == idOfFolder) {
				return ditFolder;
			}
		}
		return null;
	}
	
	
	public static void addFolder(DITFolder folderToAdd) {
		folders.add(folderToAdd);
	}

	public void addProject(DITProject project) {
		projects.add(project);
	}
	
	public DITProject getProjectByID(int prjID) {
		for (DITProject ditProject : projects) {
			if(ditProject.getId() == prjID) {
				return ditProject;
			}
		}
		return null;
	}
	
	public boolean containsProjectWithID(int prjID) {
		for (DITProject ditProject : projects) {
			if(ditProject.getId() == prjID) {
				return true;
			}
		}
		return false;
	}
	
	public static String[] getFoldersAsArray() {
		String[] arrayToReturn = new String[folders.size()];
		int counter = 0;
		for (DITFolder folder : folders) {
			arrayToReturn[counter] = folder.getId()+"_"+folder.getFolderName();
			counter++;
		}

		return arrayToReturn;
	}
	
	public String[] getProjectsInsideFolderAsArray() {
		String[] arrayToReturn = new String[projects.size()];
		int counter = 0;
		for (DITProject prj : projects) {
			arrayToReturn[counter] = prj.getId()+"_"+prj.getName();
			counter++;
		}

		return arrayToReturn;
	}
	
	public static boolean containsProjectInFoldersWithID(int prjID) {
		for (DITFolder ditFolder : folders) {
			for (DITProject prj : ditFolder.getProjects()) {
				if(prj.getId() == prjID) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static DITProject getProjectFromFoldersWithID(int prjID) {
		for (DITFolder ditFolder : folders) {
			for (DITProject prj : ditFolder.getProjects()) {
				if(prj.getId() == prjID) {
					return prj;
				}
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public static ArrayList<DITFolder> getFolders() {
		return folders;
	}

	public static void setFolders(ArrayList<DITFolder> folders) {
		DITFolder.folders = folders;
	}

	public ArrayList<DITProject> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<DITProject> projects) {
		this.projects = projects;
	}
	
	
	
}
