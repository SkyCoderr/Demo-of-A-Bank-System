/**
 * This class is the subclass of class BankAccountUser and implements interface BankAccountStandardUserInterface.
 * It's used to store information for a standard user, who is also a user but covers extended contents.
 *
 * It inherits all methods and field variables from class BankAccountUser. Besides all field variables inherited from
 * its superclass, it has its own field variables banAccount, loginAttempts and a constant MAXIMAL_LOGIN_ATTEMPTS.
 * The first field variable bankAccount refers to an instance of class BankAccount, which is also the account for
 * this standard user. The second variable loginAttempts keeps track of the times of the user's failed login.
 *
 * @author Haojie Chen
 * @version 2018-11-21
 */

public class BankAccountStandardUser extends BankAccountUser implements BankAccountStandardUserInterface{

    private BankAccount bankAccount;
    private int loginAttempts;
    public static final int MAXIMAL_LOGIN_ATTEMPTS = 3;

    /**
     * Constructor of the standard user class. It has three arguments to be passed when it's instantiated. First
     * it passes two arguments username and password to its superclass, and then initialise its own field vaiable
     * bankAccount with argument bankAccount.
     * @param username    Type String.
     * @param password    Type String, to be compared to the password stored on the system.
     * @param bankAccount Type BankAccount, the corresponding bank account for this user account.
     */
    public BankAccountStandardUser(String username, String password, BankAccount bankAccount){
        super(username, password);
        this.bankAccount = bankAccount;
    }

    /**
     * A method that is used to return the bank account of this user.
     * @return Type BankAccount.
     */
    public BankAccount getBankAccount(){
        return bankAccount;
    }

    /**
     * This method is used to log in, setting the login status to true. First it checks if your login attempts
     * is less than 3, if so you are allowed to proceed, and it it checks your password, setting your login status
     * to true and your login attempts to 0 if your password is correct, and if it's wrong, the system would tell
     * you the password is not correct and loginAttempts increments. But if your login attempts is equal to or
     * greater than 3 in the first place, you are not allowed to log in, thus the system would prompt too many
     * unsuccessful attempts.
     * @param password The password provided for the login; this is to be compared to the password stored on the
     *                 system
     */
    public void login(String password){
        // First check if you've got chances left to log in.
        if (loginAttempts >= MAXIMAL_LOGIN_ATTEMPTS) {
            System.out.println("Sorry, too many unsuccessful attempts.");
        } else{
            if (passwordCorrect(password)){
                setLoggedIn(true);
                loginAttempts = 0;
            } else{
                bankAccount.printWrongPassword();
                loginAttempts++;
            }
        }
    }

    /**
     * This method is used to check how many times you have failed to log in.
     * @return Type int, the attempts you have made to log in, but failed.
     */
    public int getLoginAttempts(){
        return loginAttempts;
    }

    /**
     * This method is used to alter the value for field variable loginAttempts with the argument passed to the method.
     * @param loginAttempts New value for the variable loginAttempts.
     */
    public void setLoginAttempts(int loginAttempts){
        this.loginAttempts = loginAttempts;
    }

    /**
     * This method is used to reset loginAttempts, which changes it to 0.
     */
    public void resetLoginAttempts(){
        this.loginAttempts = 0;
    }

    /**
     * This method is used to transfer money from this bank account to another bank account. And it can only complete
     * the act of transfer when the user is logged in, the password is correct and the account has sufficient funds.
     * @param toAccount The account to which the money is to be transferred.
     * @param amount The amount of money to be transferred.
     * @param password The password of the this account.
     */
    public void transferMoney(BankAccount toAccount, long amount, String password){
        if (getLoggedIn()){
            bankAccount.transferMoney(toAccount, amount, password);
            bankAccount.printTransferSuccessful(amount);
        } else{
            System.out.println("Please log in first.");
        }
    }

    /**
     * This method is used to check the balance of the account. And it only works when the user is logged in.
     */
    public void printBalance(){
        if (getLoggedIn()){
            bankAccount.printBalance();
        } else{
            System.out.println("Please log in first.");
        }
    }

    /**
     * This method is used to check a user's transaction history.
     */
    public void printStatement(){
        bankAccount.printStatement();
    }



}
