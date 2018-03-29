package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class ClientHandler extends Thread {
	private Socket s;
	private DataInputStream dis;
	private DataOutputStream dos;

	public ClientHandler(Socket s) {
		this.s = s;
		try {
			this.dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected String readMessage() {
        try {
			return dis.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

	@Override
	public void run() {
		System.out.println("A new client is connected : " + s.getPort());
		
		String received = readMessage();
		String toreturn;

		System.out.println(s.getPort() + ": sended " + received);

	}

}
