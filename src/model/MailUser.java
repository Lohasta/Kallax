package model;

public class MailUser {
    
    private static String email;

    public static void setEmail(String emailC){
        email = emailC;
    }

    public static String getEmail(){
        return email;
    }

    public static void clear(){
        email = null;
    }
}
