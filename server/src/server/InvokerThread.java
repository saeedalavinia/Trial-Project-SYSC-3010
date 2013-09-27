package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

 class InvokerThread implements Runnable {
	Socket connectionSocket;
	Boolean commandReceived;
	BufferedReader in;
	
	public InvokerThread(Socket connectionSocket) {
		super();
		this.connectionSocket = connectionSocket;
		Command.getInstance(); 
	}


	public void run() {
		try {
			in= new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			for(;;){
				//notify the flasher thread upon receiving a singal from the flasher raspi and storing it as command code
				
					try {
						Command.setCommandCode(in.readLine());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Command.getCommandCode().toString()+"cmd code"+Command.getCommandCode());
					synchronized (Command.getInstance()) {
					Command.setExecute(true);
					Command.getInstance().notifyAll();
				}
			}
			
			
		
		
	}
}	
