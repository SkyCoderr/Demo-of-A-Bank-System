import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * This class is a JUnit test that tests login() methods in different classes, for different instances. It also tests
 * transferMoney() methods and several getters and setters.
 *
 * @author Haojie Chen
 * @version 2018-11-21
 */
public class MyTest {

    private BankAccountAdministrator ad1, ad2;
    private Customer customerJohn, customerMary, customerTony;
    private BankAccount bankAccountJohn, bankAccountMary, bankAccountTony;
    private BankAccountStandardUser john, mary, tony;

    @Before
    public void setUp() {
        // Constructs instances to be tested on.
        ad1 = new BankAccountAdministrator("ad1", "ad1");
        ad2 = new BankAccountAdministrator("ad2", "ad2");
        customerJohn = new Customer("John", "m", "Bham", "0121");
        customerMary = new Customer("Mary", "f", "Bham", "0121");
        customerTony = new Customer("Tony", "x", "Bham", "0121");
        bankAccountJohn = new BankAccount(customerJohn, "wheat");
        bankAccountMary = new BankAccount(customerMary, "linseed");
        bankAccountTony = new BankAccount(customerTony, "corn");
        john = new BankAccountStandardUser("John", "wheatUser", bankAccountJohn);
        mary = new BankAccountStandardUser("Mary", "linseedUser", bankAccountMary);
        tony = new BankAccountStandardUser("Tony", "cornUser", bankAccountTony);
    }

    // John logs in successfully and transfers
    // some money to Mary's bank account.
    @Test
    public void test1() {
        john.login("wheatUser");

        // Expected number of failed login attempts is 0.
        int expectedLoginAttempts = 0;
        int actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertTrue(john.getLoggedIn());

        // Transfer fails because of insufficient funds.
        john.transferMoney(bankAccountMary, 200, "wheat");

        double expectedBalance = 100;
        double actualBalance = mary.getBankAccount().getBalance();

        assertEquals(expectedBalance, actualBalance, 0.00001);

        // Transfer successful.
        john.transferMoney(bankAccountMary, 10, "wheat");
        double expectedBalanceJohn = 90;
        double actualBalanceJohn = john.getBankAccount().getBalance();
        assertEquals(expectedBalanceJohn, actualBalanceJohn, 0.00001);
        double expectedBalanceMary = 110;
        double actualBalanceMary = mary.getBankAccount().getBalance();
        assertEquals(expectedBalanceMary, actualBalanceMary, 0.00001);
    }

    // Mary makes 1 failed login attempt,
    // logs in using the correct password
    // and logs out. Then Mary tries to transfer some
    // money to Tony's bank account but forgets the password
    @Test
    public void test2() {
        mary.login("linseeduser");

        int expectedLoginAttempts = 1;
        int actualLoginAttempts = mary.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);

        assertFalse(mary.getLoggedIn());

        mary.login("linseedUser");
        expectedLoginAttempts = 0;
        actualLoginAttempts = mary.getLoginAttempts();
        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertTrue(mary.getLoggedIn());

        mary.logout();
        assertFalse(mary.getLoggedIn());

        mary.setPassword("linseeedUser");
        mary.transferMoney(bankAccountTony, 10, "linseed");

        double expectedBalance = 100;
        double actualBalance = tony.getBankAccount().getBalance();

        assertEquals(expectedBalance, actualBalance, 0.00001);

    }

    // Tony makes 2 failed login attempts and
    // then logs in using the correct password
    @Test
    public void test3() {

        // First failed login attempt
        tony.login("KornUser");

        int expectedLoginAttempts = 1;
        int actualLoginAttempts = tony.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);

        assertFalse(tony.getLoggedIn());

        // Second failed login attempt
        tony.login("cornuser");
        expectedLoginAttempts = 2;
        actualLoginAttempts = tony.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertFalse(tony.getLoggedIn());

        // Third attempt is successful
        tony.login("cornUser");

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertTrue(tony.getLoggedIn());

    }

    // John makes 4 failed login attempts and
    // calls admin. Admin logs in successfully
    // and then resets John's password and John's login attempts to 0.
    @Test
    public void test4() {

        // First failed login attempt
        john.login("wehatUser");

        int expectedLoginAttempts = 1;
        int actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);

        assertFalse(john.getLoggedIn());

        // Second failed login attempt
        john.login("wheatuser");
        expectedLoginAttempts = 2;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertFalse(john.getLoggedIn());

        // Third failed login attempt
        john.login("wheatuser");
        expectedLoginAttempts = 3;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertFalse(john.getLoggedIn());

        // Fourth failed login attempt
        john.login("wheatuser");
        // Failed login attempts remain 3
        expectedLoginAttempts = 3;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertFalse(john.getLoggedIn());

        // Administrator resets john's password
        ad1.login("ad1");
        ad1.addUser(john);
        ad1.resetAccount(john, "wheatUser1");

        // John logs in successfully using the
        // new password
        john.login("wheatUser1");

        expectedLoginAttempts = 0;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertTrue(john.getLoggedIn());

    }

    // John makes 4 failed login attempts and
    // calls admin. Admin forgets to log in and tries
    // to reset John's password.
    @Test
    public void test5() {

        // First failed login attempt
        john.login("wheetUser");

        int expectedLoginAttempts = 1;
        int actualLoginAttempts = john.getLoginAttempts();
  assertEquals(expectedLoginAttempts, actualLoginAttempts);

        assertFalse(john.getLoggedIn());

        // Second failed login attempt
        john.login("wheatuser");
        expectedLoginAttempts = 2;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertFalse(john.getLoggedIn());

        // Third failed login attempt
        john.login("wheatuser");
        expectedLoginAttempts = 3;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertFalse(john.getLoggedIn());

        // Fourth failed login attempt
        john.login("wheatuser");
        // Failed login attempts remain 3
        expectedLoginAttempts = 3;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertFalse(john.getLoggedIn());

        // Administrator tries to reset john's password
        // without logging in
        ad1.addUser(john);
        ad1.resetAccount(john, "wheatUser1");

        // John tries to log in again
        john.login("wheatUser1");

        // login attempts still 3
        expectedLoginAttempts = 3;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertFalse(john.getLoggedIn());
    }

    // John makes 4 failed login attempts and
    // calls admin. Admin calls in deputy
    // to reset John's password.
    @Test
    public void test6() {

        // First failed login attempt
        john.login("wehatUser");

        int expectedLoginAttempts = 1;
        int actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);

        assertFalse(john.getLoggedIn());

        // Second failed login attempt
        john.login("wheatuser");
        expectedLoginAttempts = 2;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertFalse(john.getLoggedIn());

        // Third failed login attempt
        john.login("wheatuser");
        expectedLoginAttempts = 3;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertFalse(john.getLoggedIn());

        // Fourth failed login attempt
        john.login("wheatuser");
        // Failed login attempts remain 3
        expectedLoginAttempts = 3;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertFalse(john.getLoggedIn());

        // Administrator resets john's password
        ad2.login("ad2");
        ad2.addUser(john);
        ad2.resetAccount(john, "wheatUser1");

        // John logs in successfully using the
        // new password
        john.login("wheatUser1");

        expectedLoginAttempts = 0;
        actualLoginAttempts = john.getLoginAttempts();

        assertEquals(expectedLoginAttempts, actualLoginAttempts);
        assertTrue(john.getLoggedIn());

        john.logout();
        assertFalse(john.getLoggedIn());
    }
}
