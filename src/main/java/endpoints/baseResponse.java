package endpoints;

import java.util.ResourceBundle;

public class baseResponse {
    //method to get URLs from properties file
    static ResourceBundle getURL(){
        //Load properties file
        ResourceBundle routes = ResourceBundle.getBundle("urls");
        return routes;
    }

}
