package dparser;

/**
 * This is a statistical voltage measurement message
 * from the datalogger. It includes in its data ArrayList
 * a channel ID, the number of samples taken, min, avg, and max
 * values for the voltage measurements in the interval.
 * @author Derek Chou
 * @since 2012.05.15
 */
public class VoltageMessage extends Message {
	
	public VoltageMessage(String [] info, boolean ts) {
		super(info,ts);
		header = "Statistical Voltage Measurement";
		
	}

}
