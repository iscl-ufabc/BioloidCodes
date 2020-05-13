package Errors;

public class UnableToConnect extends Error{
	public UnableToConnect(){
		super("The Serial Port Could Not Be Opened");
	}
}
