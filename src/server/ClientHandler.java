package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class ClientHandler implements Runnable {
	final Socket s;
	final DataInputStream dis;
	final DataOutputStream dos;

	// Constructor
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
		this.s = s;
		this.dis = dis;
		this.dos = dos;
	}

	@Override
	public void run() {
		String received;
		String toreturn;
		while (true) {
			try {

				// Ask user what he wants
				dos.writeUTF("What do you want?[Date | Time]..\n" + "Type Exit to terminate connection.");

				// receive the answer from client
				received = dis.readUTF();

				if (received.equals("Exit")) {
					System.out.println("Client " + this.s + " sends exit...");
					System.out.println("Closing this connection.");
					this.s.close();
					System.out.println("Connection closed");
					break;
				}

				// creating Date object
				Date date = new Date();

				// write on output stream based on the
				// answer from the client
				switch (received) {

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
