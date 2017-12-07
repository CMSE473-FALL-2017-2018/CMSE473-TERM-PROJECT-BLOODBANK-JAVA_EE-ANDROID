package cmse473.bloodbank;

import cmse473.bloodbank.Model.User;


/**
 * Created by Onur on 30.11.2017.
 */

public class ApplicationClass {

    private static User user;

    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        ApplicationClass.user = user;
    }


}
