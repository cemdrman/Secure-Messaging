package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

import security.CryptoHelper;

import java.io.DataOutputStream;

public class Client {
	
	private String userName;
	private Socket registerServerSocket;
	private Socket serverSocket;
	private DataOutputStream dos;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private CryptoHelper cryptoHelper;
	
	public Client(String userName) {
		this.userName = userName;
		cryptoHelper = new CryptoHelper();
		KeyPair keyPair = cryptoHelper.generateKeyPair("RSA", 1024);
		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();
	}

	private void sendString(String message) {
		try {
			dos.writeChars(message);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client: " + registerServerSocket.getPort() + " send " + message);       
		
    }

	protected void sendKey(byte[] key) {
		try {
			sendBytes(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client: " + registerServerSocket.getPort() + " send " + key);       
		
    }
	private void sendBytes(byte[] myByteArray) throws IOException {
	    sendBytes(myByteArray, 0, myByteArray.length);
	}

	private void sendBytes(byte[] myByteArray, int start, int len) throws IOException {
	    if (len < 0)
	        throw new IllegalArgumentException("Negative length not allowed");
	    if (start < 0 || start >= myByteArray.length)
	        throw new IndexOutOfBoundsException("Out of bounds: " + start);
	    // Other checks if needed.

	    // May be better to save the streams in the support class;
	    // just like the socket variable.
	    OutputStream out = registerServerSocket.getOutputStream(); 
	    DataOutputStream dos = new DataOutputStream(out);

	    dos.writeInt(len);
	    if (len > 0) {
	        dos.write(myByteArray, start, len);
	    }
	}

	/*protected Object readMessage() throws IOException, ClassNotFoundException {
        return dos.read();
    }*/
	
	protected void connecToRegisterServer() {
		try {
			registerServerSocket = new Socket("localhost", 7777);
			dos = new DataOutputStream(registerServerSocket.getOutputStream());
			//inputStream = new ObjectInputStream(serverSocket.getInputStream());
		
			System.out.println("Register-Server: Connection accepted " + registerServerSocket.getInetAddress() + "/" + registerServerSocket.getPort());
			
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	protected void registerPublicKey() {
		sendString(getUSerName());
		sendKey(getPublicKey().getEncoded());
		System.out.println("Client: " + registerServerSocket.getPort() + " send " + getUSerName() + "-" + getPublicKey().getEncoded().toString());   
	}

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
	
	//---GET METHODS
	protected PublicKey getPublicKey() {
		return publicKey;
	}
	
	protected PrivateKey getPrivateKey() {
		return privateKey;
	}
	
	protected String getUSerName() {
		return userName;
	}
	
}
