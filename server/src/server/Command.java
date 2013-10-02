package server;

//Command is a singleton class. the command code will be stored in this object before sent to other raspi clients. 

//@command :The only instance of this singleton class. Flasher nad Invoker thread can synchronize on this instance.
//@ commandCode: the comand code is decoded to generate the correct code for the flasher raspi
//@execute : used for signalling execution of a command
class Command {
	private static Command command =null;
	private static String commandCode=null;
	private static boolean execute= false;
	
	public static boolean isExecute() {
		return execute;
	}

	public static void setExecute(boolean execute) {
		Command.execute = execute;
	}

	private Command(){}
	
	public static Command getInstance(){
		if(command== null)
			command=new Command();
		return command;
	}
	
	public static String getCommandCode() {
			return commandCode;
	}
	
	public static void setCommandCode(String commandCode) {
		Command.commandCode = commandCode;
	}
	
}
