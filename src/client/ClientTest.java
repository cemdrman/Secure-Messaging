package client;

public class ClientTest {

	public static void main(String[] args) {
		Client client = new Client();
		client.connetToServer();
		client.sendMessage("Cem");
		
	}

}
