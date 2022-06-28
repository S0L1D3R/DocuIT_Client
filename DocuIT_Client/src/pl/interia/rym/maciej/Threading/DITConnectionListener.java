package pl.interia.rym.maciej.Threading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;

import javax.swing.JOptionPane;

import pl.interia.rym.maciej.DITClientApp;
import pl.interia.rym.maciej.IO.Network.DataNetworkParser;
import pl.interia.rym.maciej.Structures.DITFolder;

public class DITConnectionListener extends Thread {

	private PrintWriter outStream;
	private BufferedReader inStream;
	
	private boolean authenticated = false;
	
	public DITConnectionListener(PrintWriter outStream, BufferedReader inStream) {
		this.inStream = inStream;
		this.outStream = outStream;
	}

	@Override
	public void run() {
		String inputFromServer;
		try {
			while((inputFromServer = inStream.readLine()) != null) {
				resolveMessage(inputFromServer);
			}
			
		}	catch(SocketException a) {
				closeInputStream();
				
		}	catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void resolveMessage(String messageFromServer) {
		switch (messageFromServer) {
		case "Authenticated":
			setAuthenticated(true);
			break;
		case "Returning_Data:Start":
			DataNetworkParser parser = new DataNetworkParser(inStream, outStream);
			parser.parseDataFromServer();
			DITClientApp.mainFrame.updateList_Folders(DITFolder.getFoldersAsArray());
			break;
		case "UpdateCurrentProject_ID":
			try {
				System.out.println("Otrzymano nowe ID");
				DITClientApp.mainFrame.getProjectPanel().getLoadedProject().setId(Integer.parseInt(inStream.readLine()));
				JOptionPane.showMessageDialog(DITClientApp.mainFrame, "Pomyœlnie zapisano projekt w chmurze");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	private void closeInputStream() {
		try {
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PrintWriter getOutStream() {
		return outStream;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	
}
