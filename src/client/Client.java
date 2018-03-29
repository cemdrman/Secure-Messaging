package client;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.DataOutputStream;

public class Client {
	private Socket serverSocket;
	private DataOutputStream dos;	
	
	protected void sendMessage(String message) {
		try {
			dos.writeUTF(message);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client: " + serverSocket.getPort() + " send " + message);       
		
    }

	/*protected Object readMessage() throws IOException, ClassNotFoundException {
        return dos.read();
    }*/

	/**
	 *
	 * @param nameSurname
	 *            for creating userDir on server side
	 */
	protected void connetToServer() {

		try {
			serverSocket = new Socket("localhost", 9999);
			dos = new DataOutputStream(serverSocket.getOutputStream());

			//inputStream = new ObjectInputStream(serverSocket.getInputStream());
		
			System.out.println("Connection accepted " + serverSocket.getInetAddress() + "/" + serverSocket.getPort());
			
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
}
