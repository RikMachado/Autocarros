package Syst;
import java.util.*;

public class Logger {
    
    private String caller;
    private static final String[] warningLevel = {"Info", "Warning", "Error", "Exception", "Debugging"};
    
    
    /**
     *
     * Logger constructor.
     * <br />
     * Only accepts the caller name (name of calling class or just and identifier
     * for now. Will be turned automatic;
     * 
     * @param caller caller id or name;
     * @return logger object
     * @since version 1.00
     */
    public Logger(String caller){
        this.caller = caller;
    }
    
    
    
    /**
     *
     * Get the current time of the day.
     * <br />
     * Used to get the current time of the day;
     * <br />
     * Is private;
     * 
     * @return String
     * @since version 1.00
     */
    private String getCurrentTime(){
        
        String hour = String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)); 
        String min = String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
        String sec = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));
        
        String retString = "[" + hour + ":" + min + ":" + sec + "]";
        
        return retString;
        }
    
    
    
    /**
     *
     * Get the caller of the print.
     * <br />
     * Used to get the caller associated with the current object;
     * <br />
     * Is private;
     * 
     * @return String
     * @since version 1.00
     */
    private String getCaller(){
        return this.caller;
    }
    
    
    
    /**
     *
     * Get the Warning level associated with an int.
     * <br />
     * Used to get the warning level associated with a given Int;
     * <br />
     * Is private;
     * 
     * @param level representative Int of the warning level
     * @return String Warning Level
     * @since version 1.00
     */
    private String getLevel(int level){
        for(int i = 0; i < warningLevel.length; i++){
            if(i == level){
                return "[" + warningLevel[i] + "]";
            }
        }
        
        return "[Unknown warning level]";
    }
    
    
    
    
    /**
     *
     * Printing method constructor.
     * <br />
     * Used to print a message to the console from the logger;
     * <br />
     * Is void, directly prints to the console with 'println';
     * <br />
     * If no Warning Level is given, default to 0 (info);
     * 
     * @param msg Mesage to be printed;
     * @param level Warning level to be sent;
     * @since version 1.00
     */
    public void print(String msg, int level){
        String printLine = getCurrentTime() + getCaller() + getLevel(level) + " " + msg;
        System.out.println(printLine);
    }
    
    
    
    /**
     *
     * Printing method constructor.
     * <br />
     * Used to print a message to the console from the logger;
     * Is void, directly prints to the console with <println>;
     * If no Warning Level is given, default to 0 (info);
     * 
     * @param msg Mesage to be printed;
     * @since version 1.00
     */
    public void print(String msg){
        int level = 0;
        
        String printLine = getCurrentTime() + getCaller() + getLevel(level) + " " + msg;
        System.out.println(printLine);
    }
    
}
