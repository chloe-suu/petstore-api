package api.endpoints;

import java.util.ResourceBundle;

public class baseResponse {
    //method to get URLs from properties file
    static ResourceBundle getURL(){
        //Load properties file
        return ResourceBundle.getBundle("urls");
    }

    static String base_url = getURL().getString("base_url");

    //declare pet url variables ---------------------------------------------------------------------------------------------------

    static String post_pet_url = base_url + getURL().getString("post_pet_url");
    static String pet_byId = base_url +getURL().getString("pet_byId");
    static String pet_upload_image = base_url +getURL().getString("pet_upload_image");

    //declare user url variables ---------------------------------------------------------------------------------------------------
    static String post_url = base_url + getURL().getString("post_user_url");
    static String user_byName_url = base_url +getURL().getString("user_byName_url");
    static String login_url = base_url + getURL().getString("login_url");


}
