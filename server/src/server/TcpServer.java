package server;

import java.io.*;
import java.net.*;

public class TcpServer {
	ServerSocket Socket;
	Socket connectionSocket;
	Thread invokerThread, flasherThread;
	

	public void serverSetup(){
		
			// wait till the invoker device is connected and fire up its realted thread
		
			try {
				Socket = new ServerSocket(5006);
				connectionSocket = Socket.accept();
			} catch (IOException e) {
				System.err.println("connection gone wrong!");
				e.printStackTrace();
			}
			//instantiate and start invoker thread
			Thread invokerThread= new Thread(new InvokerThread(connectionSocket));
			invokerThread.start();
			
			
			// wait till the flasher device is connected and fire up its realted thread
			try {
				Socket = new ServerSocket(5006);
				connectionSocket = Socket.accept();
			} catch (IOException e) {
				System.err.println("connection gone wrong!");
				e.printStackTrace();
			}	
			//instantiate and start flasher thread
			Thread FlasherThread= new Thread(new FlasherThread(connectionSocket));
			FlasherThread.start();
		
			// wait till all threads are done with their execution before destroying the main thread
			try {
				invokerThread.join();
				FlasherThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
	}
	

	public static void main(String[] args) {
		
		//Initialize the system
		TcpServer server= new TcpServer();
		server.serverSetup();
			
	}

}
