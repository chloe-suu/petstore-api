package utilities;

import org.testng.annotations.Test;

public class common {
    public String getFilePath(String path){
        String userDirectory = System.getProperty("user.dir");
        return userDirectory+path;

    }
}
