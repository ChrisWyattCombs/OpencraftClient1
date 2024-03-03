package opencraft.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkUtills {

public static void connectToServer(String ip,int port) {
	
		
	
	try {
		NetworkVariables.socket = new Socket(ip, port);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		NetworkVariables.writer = new DataOutputStream(new BufferedOutputStream(NetworkVariables.socket.getOutputStream()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		NetworkVariables.reader = new DataInputStream(new BufferedInputStream(NetworkVariables.socket.getInputStream()));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void disconnectFromServer() {
	try {
		NetworkVariables.writer.close();
		NetworkVariables.reader.close();
		NetworkVariables.socket.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	NetworkVariables.socket = null;
	NetworkVariables.writer = null;
	NetworkVariables.reader = null;
}
	public static void send(int value) {
		try {
			NetworkVariables.writer.writeInt(value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void send(float value) {
		try {
			NetworkVariables.writer.writeFloat(value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int readInt() throws IOException {
		
		while(NetworkVariables.reader.available() <= 0);
		
		return NetworkVariables.reader.readInt();
	}
public static float readFloat() throws IOException {
	
	while(NetworkVariables.reader.available() <= 0);
	
	return NetworkVariables.reader.readInt();
	}

}
