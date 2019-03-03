/**
 * This class acts as a superclass to classes BankAccountStandardUser and BankAccountAdministrator, and it also
 * implements interface BankAccountUserInterface. It's used to store basic information and methods for an account.
 *
 * It has three field variables, username, password and loggedin.
 * Variable password stores the account password in the system and is compared with the password user enters
 * every time he tries to log in. Variable loggedin is a boolean variable that implies the status of this account.
 * The default status is false, which means the user is not logged in. If the password entered is correct, then it
 * would be set to true. And when the user chooses to log out, it's set to false again.
 *
 * @author Haojie Chen
 * @version 2018-11-21
 */
public abstract class BankAccountUser implements BankAccountUserInterface{

    private String username;
    private String password;
    private boolean loggedin;

    /**
     * Constructor of the user account. Initialised with the arguments username, password passed onto its constructor.
     * And when it initialises, its default login status is set to false.
     * @param username Type String, account username.
     * @param password Type String, password of the account.
     */
    public BankAccountUser(String username, String password){
        this.username = username;
        this.password = password;
        // Default login status set to false when an account is instantiated.
        this.loggedin = false;
    }

    /**
     * Getter for field variable password. This method is set to private, so it cannot be accessed by its subclasses,
     * which ensures security for the account, but the account itself has to access to this method, so it is used in
     * method passwordCorrect() to check if the password entered is the same as that of the stored.
     * @return Type String, password of the account.
     */
    private String getPassword(){
        return password;
    }

    /**
     * This method calls getPassword() method to check if the password passed to it is the same as that stored on the
     * system. If it is, the method returns true, otherwise it returns false.
     * @param password Of type String, and is to be compared to the stored password.
     * @return Type boolean, true when password is correct.
     */
    public boolean passwordCorrect(String password){
        if (getPassword().equals(password)){
            return true;
        }else
            return false;
    }

    /**
     * This method attempts login, with the argument password passed to method passwordCorrect() to check if it's
     * correct, and if it is, the login status is set to true, otherwise it stays false.
     * @param password The password provided that will be compared to the password stored on the system.
     */
    public void login(String password){
        if (passwordCorrect(password)){
            loggedin = true;
        } else {
            System.out.println("Sorry, the password is not correct.");
        }
    }

    /**
     * This method is used for users to log out by changing to login status to false.
     */
    public void logout(){
        loggedin = false;
    }

    /**
     * This password allows users to set password for their accounts.
     * @param password The new password.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * This method can be used to check the current login status of the account.
     * @return Type boolean, implying the current login status of the account.
     */
    public boolean getLoggedIn(){
        return loggedin;
    }

    /**
     * This method can be used to change to login status of the account.
     * @param loggedin Type boolean, the login status this account is to be changed to.
     */
    public void setLoggedIn(boolean loggedin){
        this.loggedin = loggedin;
    }


}

