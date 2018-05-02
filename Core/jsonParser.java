package pp;
import java.util.*;
import java.io.*;
import pp.PP.*;

class invalidOption extends Exception{
    
}

class systemIOException extends Exception{
    public systemIOException(String msg){
        super(msg);
    }
}

class Parser{
    private String configPath;
    private String folderPath;
    private String configName;
    private int fileSize;
    
    public Parser(String inputPath){
        
        //
        
        //List<String> pathList = new ArrayList<String>();
        //List<String> pathList;
        String[] pathList;
        pathList = splitPath(inputPath);
        File sessionConfig = new File(pathList[0]);
        try{
            if(fileExists(sessionConfig) && !isDirectory(sessionConfig)){
                System.out.println("Session config file located. Config can now be loaded to main program");
            }
            else if (isDirectory(sessionConfig)){
                throw new systemIOException("The config file seems to be a Directory.");
            }
            else if (!fileExists(sessionConfig)){
                throw new systemIOException("The config file was not found.");
            }
            else{
                throw new systemIOException("Unknown IO Exception.");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    private boolean isDirectory(File seshConf){
        if(seshConf.isDirectory()){
            return false;
        }
        else{
            return true;
        }
    }
    
    private boolean fileExists(File seshConf){
        if(seshConf.exists()){
            return true;
        }
        else{
            return false;
        }
    }
    
    private String[] splitPath(String inputPath){
        //Index 0 = Path completo;
        //Index 1 = Driver Letter;
        //Index 2 = Folder Path;
        //Index 3 = File Name;
        String[] returnArr = new String[4];
        String[] tempArr;
        String tempPath = "";
        int tempArrSize;
        
        
        tempArr = inputPath.split("\\");
        tempArrSize = tempArr.length;
        
        returnArr[0] = inputPath;
        returnArr[1] = tempArr[0].replace(":", ""); 
        
        for(int i = 0; i < tempArrSize; i++){
            tempPath += tempArr[i];
            tempPath += "\\";
        }
        
        returnArr[2] = tempPath;
        returnArr[3] = tempArr[tempArrSize - 1];
        
        return returnArr;       
    }
    
    
}
