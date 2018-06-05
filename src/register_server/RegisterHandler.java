package register_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;

public class RegisterHandler extends Thread {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public RegisterHandler(Socket socket) {
		this.socket = socket;
		try {
			this.dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
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
	
	public byte[] readBytes() throws IOException {
	    // Again, probably better to store these objects references in the support class
	    InputStream in = socket.getInputStream();
	    DataInputStream dis = new DataInputStream(in);

	    int len = dis.readInt();
	    byte[] data = new byte[len];
	    if (len > 0) {
	        dis.readFully(data);
	    }
	    return data;
	}

	@Override
	public void run() {
		System.out.println("A new client is connected to public-key registiration: " + socket.getPort());
		
		String userName = readMessage();
		System.out.println(socket.getPort() + ": send user-name: " + userName);
		String publicKey = readMessage();
		System.out.println(socket.getPort() + ": send " + publicKey);
		
		System.out.println(userName + ": registred with " + publicKey);
	}

}
