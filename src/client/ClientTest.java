package client;

public class ClientTest {

	public static void main(String[] args) {
		Client client = new Client("Cemdirman");
		client.connecToRegisterServer();
		client.registerPublicKey();
		
		
		
		//client.connetToServer();
		//client.sendMessage("Cem");
		
	}

}
