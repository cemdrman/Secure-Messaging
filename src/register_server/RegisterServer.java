package register_server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterServer {

	private static ServerSocket serverSocket;
	private String serverIp;
	private final static int serverPort = 7777;
	private InetAddress address;
	private static RegisterServer singletonInstance;

	private RegisterServer() {

	}

	public static RegisterServer getServer() {
		if (singletonInstance == null) {
			singletonInstance = new RegisterServer();
		}
		return singletonInstance;
	}

	public void start() {
		try {
			address = InetAddress.getByName(serverIp);
			serverSocket = new ServerSocket(serverPort);
			System.out.println("-----------REGISTER SERVER-----------");
			System.out.println("****************************");
			System.out.println("\tServer Info");
			System.out.println("Register Server IP            : " + address.getHostName());
			System.out.println("Register Server Port Number   : " + String.valueOf(serverPort));
			System.out.println("****************************");
			System.out.println("Register Server Started...");
			System.out.println("Waiting for connections...");

			while (true) {
				Socket connectedSocket;
				// socket object to receive incoming client requests
				connectedSocket = serverSocket.accept();
				Thread clientHandler = new RegisterHandler(connectedSocket);
				clientHandler.start();
			}
		} catch (IOException ex) {
			Logger.getLogger(RegisterServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
