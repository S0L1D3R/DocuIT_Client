package pl.interia.rym.maciej;

import pl.interia.rym.maciej.GUI.DITAdditionalFrame_Options;
import pl.interia.rym.maciej.GUI.DITAdditionalFrame_SignIn;
import pl.interia.rym.maciej.GUI.DITMainFrame;
import pl.interia.rym.maciej.Structures.DITFolder;
import pl.interia.rym.maciej.Structures.DITProject;
import pl.interia.rym.maciej.Structures.DITProjectElement;
import pl.interia.rym.maciej.Threading.DITConnection;

public class DITClientApp {
	
	public static DITConnection connection;
	public static DITMainFrame mainFrame;
	public static DITClientOptions options;
	
	public static String appDir = "C:\\Program Files\\DocuITClient\\";
	private String optionsFile = "ClientOptions.txt";

	private String serverIPAddress;
	private int portOfServer;
	
	public DITClientApp() {
		startGUI();
		
		options = new DITClientOptions(appDir + optionsFile);
		
		if(options.isRequiredInfoMissing()) {
			new DITAdditionalFrame_Options(options, mainFrame.getX(), mainFrame.getY());
		}
		else {
			new DITAdditionalFrame_SignIn();
		}
		
		
		
		startConnection();

//		createTestingProjects();
	}
	
	/**
	 * Only for testing purposessss
	 */
	private void createTestingProjects() {
		DITFolder f1 = new DITFolder(11, "Poradniki/Instrukcje");
		DITFolder f2 = new DITFolder(15, "Szybkie notatki");
		
		DITProject prj1 = new DITProject(1, "PRJ#1");
		prj1.addExistingElement(new DITProjectElement(10, 100, 100, "El1 title", "content of el1"));
		prj1.addExistingElement(new DITProjectElement(11, 200, 200, "El2 title", "content of el2"));
		prj1.addExistingElement(new DITProjectElement(12, 300, 300, "El3 title", "content of el3"));
		DITProject prj2 = new DITProject(2, "PRJ#2");
		prj2.addExistingElement(new DITProjectElement(10, 100, 100, "El1 title", "content of el1"));
		prj2.addExistingElement(new DITProjectElement(11, 400, 200, "El2 title", "content of el2"));
		prj2.addExistingElement(new DITProjectElement(12, 600, 300, "El3 title", "content of el3"));
		DITProject prj3 = new DITProject(3, "PRJ#3");
		prj3.addExistingElement(new DITProjectElement(10, 100, 100, "El1 title", "content of el1"));
		prj3.addExistingElement(new DITProjectElement(11, 800, 100, "El2 title", "content of el2"));
		prj3.addExistingElement(new DITProjectElement(12, 550, 150, "El3 title", "content of el3"));
		
		f1.addProject(prj1);
		
		f2.addProject(prj2);
		f2.addProject(prj3);
		
		DITFolder.addFolder(f1);
		DITFolder.addFolder(f2);
		
		mainFrame.updateList_Folders(DITFolder.getFoldersAsArray());		
	}
	
	public void createTestingFolders() {
		
	}
	
	private void startGUI() {
		mainFrame = new DITMainFrame("DocuIT", this);
	}

	private void startConnection() {
		serverIPAddress = "127.0.0.1";
		portOfServer = 5454;
		
		connection = new DITConnection(serverIPAddress, portOfServer);
		connection.startConnection();
	}
	
	//Gettery
	public int getPortOfServer() {
		return portOfServer;
	}

	public String getserverIPAddress() {
		return serverIPAddress;
	}

	public DITConnection getConnection() {
		return connection;
	}

	public void setConnection(DITConnection connection) {
		this.connection = connection;
	}

	public DITMainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(DITMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public static DITClientOptions getOptions() {
		return options;
	}

	public static void setOptions(DITClientOptions options) {
		DITClientApp.options = options;
	}

	public static String getAppDir() {
		return appDir;
	}

	public static void setAppDir(String appDir) {
		DITClientApp.appDir = appDir;
	}

	public String getOptionsFile() {
		return optionsFile;
	}

	public void setOptionsFile(String optionsFile) {
		this.optionsFile = optionsFile;
	}

	public String getServerIPAddress() {
		return serverIPAddress;
	}

	public void setServerIPAddress(String serverIPAddress) {
		this.serverIPAddress = serverIPAddress;
	}

	public void setPortOfServer(int portOfServer) {
		this.portOfServer = portOfServer;
	}
	
}