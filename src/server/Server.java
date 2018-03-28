package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	private static ServerSocket serverSocket;
	private String serverIp;
	private final static int serverPort = 9999;
	private InetAddress address;

	private static Server singletonInstance;

	private Server() {

	}

	public static Server getServer() {
		if (singletonInstance == null) {
			singletonInstance = new Server();			
		}
		return singletonInstance;
	}

	public void start() {
		try {
			address = InetAddress.getByName(serverIp);
			serverSocket = new ServerSocket(serverPort);
			System.out.println("-----------SERVER-----------");
			System.out.println("****************************");
			System.out.println("Server Info");
			System.out.println("Server IP            : " + address.getHostName());
			System.out.println("Server Port Number   : " + String.valueOf(serverPort));
			System.out.println("****************************");
			System.out.println("Server Started...");
			System.out.println("Waiting for connections...");

			while (true) {
				Socket connectedSocket;
				// socket object to receive incoming client requests
				connectedSocket = serverSocket.accept();
				 // obtaining input and out streams
                DataInputStream dis = new DataInputStream(connectedSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(connectedSocket.getOutputStream());
				System.out.println("A new client is connected : " + connectedSocket.getPort());
				ClientHandler clientHandler = new ClientHandler(connectedSocket, dis, dos);

			}

		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);

		}
	}

}
