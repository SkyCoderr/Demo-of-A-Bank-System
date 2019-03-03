import java.util.ArrayList;

/**
 * This class is the subclass of class BankAccountUser and implements interface BankAccountAdministratorInterface.
 * It's used to store information for an administrator user, who is also a user but covers extended contents.
 *
 * It inherits all methods and field variables from class BankAccountUser. Besides all field variables inherited from
 * its superclass, it has its own field variables banAccountUsers, which is an array list of type BankAccountUsers.
 * An administrator keeps all the users he's responsible for in an array list to be managed.
 *
 * @author Haojie Chen
 * @version 2018-11-21
 */
public class BankAccountAdministrator extends BankAccountUser implements BankAccountAdministratorInterface{

    private ArrayList<BankAccountUser> bankAccountUsers;

    /**
     * Constructor of the administrator class. It has two arguments to be passed when it's instantiated. First
     * it passes two arguments username and password to its superclass, and then initialise its own field vaiable
     * bankAccountUsers with a new array list of the same type, whose size is 0.
     * @param username Type String.
     * @param password Type String, to be compared with the password stored on the system.
     */
    public BankAccountAdministrator(String username, String password){
        super(username, password);
        this.bankAccountUsers = new ArrayList<BankAccountUser>(0);
    }

    /**
     * This method adds an instance of BankAccountUser to the array list, which is a field variable of of an
     * administrator. This action only completes when the administrator is logged in.
     * @param user The user to be added to the responsibility of the
     */
    public void addUser(BankAccountUser user){
        if (getLoggedIn()){
            bankAccountUsers.add(user);
        }
    }

    /**
     * This method allows an administrator to reset the password and loginAttempts of a user he is responsible for.
     * Of course the administrator would have to log in first to do so.
     * @param bankAccountUser The bank account user for whom the account is to be reset.
     * @param password The new password for the account that is to be.
     */
    public void resetAccount(BankAccountUser bankAccountUser, String password){
        if (getLoggedIn()){
            bankAccountUser.setPassword(password);
            if (bankAccountUser instanceof BankAccountStandardUser){
                ((BankAccountStandardUser) bankAccountUser).resetLoginAttempts();
            }
        }
    }

}
