package pl.interia.rym.maciej;

import pl.interia.rym.maciej.IO.DataOptionsParser;

public class DITClientOptions {

	private String serverIP;
	private int port;
	private String backupFolder;
	private String userName;
	private String password;
	
	private DataOptionsParser parser;
	
	public DITClientOptions() {
	}
	
	public DITClientOptions(String pathToOptionsFile) {
		parser = new DataOptionsParser(pathToOptionsFile);
		loadData();
	}
	
	public void loadData() {
		DITClientOptions newOptions =  parser.readOptionsFromFile();
		setServerIP(newOptions.getServerIP());
		setPort(newOptions.getPort());
		setBackupFolder(newOptions.getBackupFolder());
		setUserName(newOptions.getUserName());
		setPassword(newOptions.getPassword());
	}
	
	public void saveData() {
		parser.saveOptionsToFile(this);
	}

	public boolean isRequiredInfoMissing() {
		return (serverIP == null || 
				(serverIP == null || serverIP.isEmpty()) || 
				(backupFolder == null || backupFolder.isEmpty()  ) ||
				(userName == null || userName.trim().isEmpty()) || 
				(password == null || password.trim().isEmpty()) ||
				port < 1);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getBackupFolder() {
		return backupFolder;
	}

	public void setBackupFolder(String backupFolder) {
		this.backupFolder = backupFolder;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DataOptionsParser getParser() {
		return parser;
	}

	public void setParser(DataOptionsParser parser) {
		this.parser = parser;
	}
	
	//TODO Dodac opcje jako osobna klase? dodac metode z reloadem
}
