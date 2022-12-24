package com.driver;

import java.util.regex.Pattern;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getPassword() {
        return this.password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character.
        // Any character apart from alphabets and digits is a special character

        if(newPassword.length()<8){
            return;
        }
        if(oldPassword.equals(this.password)){

            boolean upper=false;        // for checking if it has upper character
            boolean lower=false;        // for checking if it has lower character
            boolean digit=false;        // for checking if it has digit character
            boolean special=false;      //for checking if it has special character
//            boolean status=false;       // for checking if newpassword is correct password or not

//            if(newPassword.length()>=8){
//                status=true;
//            }
            Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
            if(specailCharPatten.matcher(newPassword).find()){
                special=true;
            }

            for(int i=0; i<newPassword.length(); i++){
                char ch=newPassword.charAt(i);

                if(Character.isDigit(ch)){
                    digit=true;
                }
                if(Character.isLowerCase(ch)){
                    lower=true;
                }
                if(Character.isUpperCase(ch)) {
                    upper = true;
                }
            }

            if(upper && lower && digit && special){
                this.password=newPassword;
            }
        }
    }
}
