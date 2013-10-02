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
			
			// "wait"ing for a command to be received from invoker thread ( gertboard)
			while (!Command.isExecute()) {
				try {
					synchronized (Command.getInstance()) {
						Command.getInstance().wait();
					}
				} catch (InterruptedException e) {}
			}

			// decode the command and forward the mapped command to flasher deveice
			if (Command.getCommandCode().equalsIgnoreCase("[0, 1, 1]")) {
				System.out.println("Button pressed\n");
				out.println("[1, 0, 0]");
				out.flush();
			}else if(Command.getCommandCode().equalsIgnoreCase("[1, 0, 1]")){
				System.out.println("Button pressed\n");
				out.println("[0, 1, 0]");
			    out.flush();
			}else if(Command.getCommandCode().equalsIgnoreCase("[1, 1, 0]")){
				System.out.println("Button pressed\n");
				out.println("[0, 0, 1]");
			    out.flush();
			}else if(Command.getCommandCode().equalsIgnoreCase("[1, 1, 1]")){
				System.out.println("Button released\n");
				out.println("[0, 0, 0]");
			    out.flush();
			}else{
				System.out.println("done sending\n");
				System.out.println("Done");
			    //out.flush();
			}
				

			// set the execute to false upon finishing the command proccessing with concurrency measures.
			synchronized (Command.getInstance()) {
				Command.setExecute(false);
			}
		   }
		}
