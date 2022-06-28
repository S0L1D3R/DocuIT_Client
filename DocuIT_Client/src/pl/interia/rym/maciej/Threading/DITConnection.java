package pl.interia.rym.maciej.Threading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import pl.interia.rym.maciej.DITClientApp;
import pl.interia.rym.maciej.Structures.DITLink;
import pl.interia.rym.maciej.Structures.DITProject;
import pl.interia.rym.maciej.Structures.DITProjectElement;

public class DITConnection {
	
	private Socket socket;
	private PrintWriter outStream;
	private BufferedReader inStream;
	
	private String serverIpAddress;
	private int serverPort;
	
	private DITConnectionListener listener; 
	
	public DITConnection(String serverIpAddress, int serverPort) {
		this.serverIpAddress = serverIpAddress;
		this.serverPort = serverPort;
	}
	///
	
	public void startConnection() {
		try {
			displayMessageInTerminal("Proba nawiazania polaczenia...");
			socket = new Socket(serverIpAddress, serverPort);
			
			outStream = new PrintWriter(socket.getOutputStream(), true);
			inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			listener = new DITConnectionListener(outStream, inStream);
			listener.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void startTalkingToServer() {
//		Scanner inputFromUser = new Scanner(System.in);
//		while(true) {
//			sendMessageToServer(inputFromUser.nextLine());
//		}
//	}
	
	public void saveProjectToServer(DITProject project) {
		sendMessageToServer("Save->Project");
		String folderID = DITClientApp.mainFrame.getList_Folders().getSelectedValue().split("_")[0];
		outStream.println(folderID);
		outStream.println(project.getId());
		outStream.println(project.getName());
		System.out.println("folderID: " + folderID);
		System.out.println("prj ID " + project.getId());
		System.out.println("prj name " + project.getName());
		for (DITProjectElement element : project.getElements()) {
			outStream.println("ID->" + element.getId());
			outStream.println("X->" + element.getX());
			outStream.println("Y->" + element.getY());
			outStream.println("Title->" + element.getTitleField().getText());
			outStream.println("Content->" + element.getTextArea().getText());
			outStream.println("-");
		}
		outStream.println("--/");
		
		for (DITLink link : project.getLinks()) {
			outStream.println("ID1->"+link.getElement1().getId());
			outStream.println("ID2->"+link.getElement2().getId());
			outStream.println("-");
		}
		outStream.println("---/");
	}
	
	public void sendAuthenticationRequest() {
		sendMessageToServer("SignIn->" + DITClientApp.getOptions().getUserName() + "," + DITClientApp.getOptions().getPassword());
		System.out.println("SignIn->" + DITClientApp.getOptions().getUserName() + "," + DITClientApp.getOptions().getPassword());
	}
	
	public void sendMessageToServer(String message) {
		outStream.println(message);
	}
	
	public void stopConnection() throws IOException {
		inStream.close();
		outStream.close();
		socket.close();
	}
	
	private void displayMessageInTerminal(String message) {
		System.out.println("[InfoClient] " + message);
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public PrintWriter getOutStream() {
		return outStream;
	}

	public void setOutStream(PrintWriter outStream) {
		this.outStream = outStream;
	}

	public BufferedReader getInStream() {
		return inStream;
	}

	public void setInStream(BufferedReader inStream) {
		this.inStream = inStream;
	}

	public String getServerIpAddress() {
		return serverIpAddress;
	}

	public void setServerIpAddress(String serverIpAddress) {
		this.serverIpAddress = serverIpAddress;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public DITConnectionListener getListener() {
		return listener;
	}

	public void setListener(DITConnectionListener listener) {
		this.listener = listener;
	}
	
}
