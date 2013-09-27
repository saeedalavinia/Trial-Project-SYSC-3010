package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class FlasherThread implements Runnable{
	Socket connectionSocket;
	Boolean lock;
	PrintWriter out;
	public FlasherThread(Socket connectionSocket,Boolean lock) {
		super();
		this.connectionSocket = connectionSocket;
		this.lock=lock;
	}
	
	@Override
	public void run() {
		try {
			out= new PrintWriter(connectionSocket.getOutputStream());		
		} catch (IOException e) {
			e.printStackTrace();	
		}
		
		
		while(true){
				
				synchronized (Command.getInstance()) {
					while(Command.isExecute()){
						try {
							wait();
						} catch (InterruptedException e) {}
					}
				}
				
				
				//decode the command
				if(Command.getCommandCode().equalsIgnoreCase("flash")){
					out.println("flash");
				}
				
				 
				
				// set the execute to false upon finishing the command proccessing 
				synchronized (Command.getInstance()) {
					Command.setExecute(false);
				}
		   }
		}
}