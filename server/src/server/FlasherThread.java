package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class FlasherThread implements Runnable{
	Socket connectionSocket;
	PrintWriter out;
	public FlasherThread(Socket connectionSocket) {
		super();
		this.connectionSocket = connectionSocket;
	}
	
	@Override
	public void run() {
		try {
			out= new PrintWriter(connectionSocket.getOutputStream());		
		} catch (IOException e) {
			e.printStackTrace();	
		}
		
		
		while(true){
			
			//waiting for a command to be received from invoker thread ( gertboard)
			while (!Command.isExecute()) {
				try {
					synchronized (Command.getInstance()) {
						Command.getInstance().wait();
					}
				} catch (InterruptedException e) {}
			}

			// decode the command; send the signal to the piface if the code "1" received
			if (Command.getCommandCode().equalsIgnoreCase("1")) {
				System.out.println("piFace Flashed");
			}

			// set the execute to false upon finishing the command proccessing
			synchronized (Command.getInstance()) {
				Command.setExecute(false);
			}
		   }
		}
}