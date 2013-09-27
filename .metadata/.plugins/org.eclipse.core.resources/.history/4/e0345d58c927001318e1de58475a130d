package server;

import java.io.*;
import java.net.*;

public class TcpServer {
	String clientSentence;
	ServerSocket Socket;
	Socket connectionSocket;
	Thread invokerThread, flasherThread;
	 Boolean lock;
	

	public void serverSetup(){
		
		/*
			try {
				Socket = new ServerSocket(5005);
				connectionSocket = Socket.accept();
			} catch (IOException e) {
				System.err.println("connection gone wrong!");
				e.printStackTrace();
			}
			Thread invokerThread= new Thread(new InvokerThread(connectionSocket,lock));
			invokerThread.start();
			*/
			
			try {
				Socket = new ServerSocket(5005);
				connectionSocket = Socket.accept();
			} catch (IOException e) {
				System.err.println("connection gone wrong!");
				e.printStackTrace();
			}		
			Thread FlasherThread= new Thread(new FlasherThread(connectionSocket,lock));
			FlasherThread.start();
		
	}
	

	public static void main(String[] args) {
		TcpServer server= new TcpServer();
		server.serverSetup();
			
	}

}
