package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

 class InvokerThread implements Runnable {
	Socket connectionSocket;
	Boolean commandReceived;
	
	public InvokerThread(Socket connectionSocket,Boolean lock) {
		super();
		this.connectionSocket = connectionSocket;
		this.commandReceived=lock;
		Command.getInstance(); 
	}


	public void run() {
		try {
			BufferedReader in= new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			
			//notify the flasher thread upon receiving a singal from the flasher raspi and storing it as command code
			synchronized (Command.getInstance()) {
				Command.setCommandCode(in.readLine());
				Command.setExecute(true);
				notify();
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}	
