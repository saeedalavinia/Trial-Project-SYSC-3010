package server;

import java.io.*;
import java.net.*;

public class TcpServer {
	String clientSentence;
	ServerSocket Socket;
	Socket connectionSocket;
	Thread invokerThread, flasherThread;
	

	public void serverSetup(){
		
		
			try {
				Socket = new ServerSocket(5006);
				connectionSocket = Socket.accept();
			} catch (IOException e) {
				System.err.println("connection gone wrong!");
				e.printStackTrace();
			}
			Thread invokerThread= new Thread(new InvokerThread(connectionSocket));
			invokerThread.start();
			
			
			try {
				Socket = new ServerSocket(5006);
				connectionSocket = Socket.accept();
			} catch (IOException e) {
				System.err.println("connection gone wrong!");
				e.printStackTrace();
			}		
			Thread FlasherThread= new Thread(new FlasherThread(connectionSocket));
			FlasherThread.start();
		
			try {
				invokerThread.join();
				FlasherThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
	}
	

	public static void main(String[] args) {
		TcpServer server= new TcpServer();
		server.serverSetup();
			
	}

}
