import java.util.Scanner;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

class MainClass {
    private Scanner sc;
    private ArrayList<person> persons;
    private ArrayList<account> accounts;
    @SuppressWarnings("unused")
    private String consumeNextLine;

    public MainClass() {
        sc = new Scanner(System.in);
        persons = new ArrayList<>();
        accounts = new ArrayList<>();
        @SuppressWarnings("unused")
        String consumeNextLine;
    }

    /*public void testEncryption(){
        System.out.println("Type password to encrypt");

        String salt = BCrypt.gensalt();

        sc.nextLine();
        String passToEncrypt = sc.nextLine();

        String encryptedPassword = BCrypt.hashpw(passToEncrypt, salt);

        System.out.println("The encryped password is " + encryptedPassword + "\n");

        boolean encryptionCorrect = BCrypt.checkpw(passToEncrypt, encryptedPassword);

        if(encryptionCorrect){
            System.out.println("The encrypted password matches the one chosen by the user, the encryption worked.");
        }
        else{
            System.out.println("The encrypted password does not match the one given by the user, the encryption failed");
        }
    }*/

    public static void main(String[] args) {
        MainClass main = new MainClass();
        main.mainMenu();
    }

    public void mainMenu() {
        System.out.println("Type 'create' to initialize a new bank account");
        System.out.println("Type 'login' to access your bank account");
        System.out.println("Type 'quit' to quit the program" + "\n");

        String choice = sc.next();

        switch (choice) {
            case "create":
                System.out.println("\n");
                createMenu();
                break;

            case "login":
                loginPerson();
                break;

            case "quit":
                System.out.println("Exiting...");
                System.exit(0);

            default:
                System.out.println("Invalid choice. Try again.");
                mainMenu();
        }
    }

    public void createMenu() {
        System.out.println("To be able to open a bank account you must first register yourself as a person" + "\n");
        System.out.println("Type 'register' to register yourself as a person");
        System.out.println("Type 'back' to return to the main menu" + "\n");
        
        String personChoice = sc.next();
        
        switch(personChoice) {
            case "register":
                registerPerson();
                break;

            case "back":
                System.out.println("Going back to the main menu..." + "\n");
                mainMenu();
                break;

            default:
                System.out.println("Invalid choice. Try again." + "\n");
                createMenu();
        }
    }

    public void createAccount(){
        System.out.println("Type your new username, no spaces allowed.");

        String userName = sc.nextLine();

        if(userName.indexOf(" ") != -1){
             System.out.println("The username contains a space, you will be sent back to the main menu." + "\n");
             mainMenu();
        }

        System.out.println("Type your new password, no spaces allowed.");
            
        String passWord = sc.nextLine();

        if(passWord.indexOf(" ") != -1){
            System.out.println("The password contains a space, you will be sent back to the main menu." + "\n");
            mainMenu();
       }

       String salt = BCrypt.gensalt();

       String encryptedPassword = BCrypt.hashpw(passWord, salt);

        account Account = new account(userName, encryptedPassword);

        accounts.add(Account);

        System.out.println("\n");

        mainMenu();
    }

    public void registerPerson() {
        System.out.println("What name should we register you with?");
        
        consumeNextLine = sc.nextLine(); // Consume the newline character
        String nameToRegister = sc.nextLine();
        
        System.out.println("How much money do you currently have?");
       
        double moneyOnPerson = Double.parseDouble(sc.nextLine());
        
        person Person = new person(nameToRegister, moneyOnPerson);
        persons.add(Person);
        
        System.out.println("\n");
        
        System.out.println("Do you also want to create an account?");

        String createAccountChoice = sc.nextLine();
        if(createAccountChoice.equals("yes")){
            createAccount();
        }
        else if(createAccountChoice.equals("no")){
            System.out.println("Ok, you will now return to the main menu..." + "\n");
            mainMenu();
        }
        else{
            System.out.println("Invalid choice, you will now return to the main menu..." + "\n");
            mainMenu();
        }

        createMenu();
    }

    public void loginAccount(person Person){
        if(accounts.size() <= 0){
            System.out.println("There are currently no accounts created, you will be sent back to the main menu" + "\n");
            mainMenu();
        }        
        
        System.out.println("Now you can login to your account, to do so first type your username");

        boolean accountExists = false;

       // double eventualMoneyPassVar = 0;

        String userNameLogin = sc.nextLine();

        System.out.println("And now type your password");

        String passWordLogin = sc.nextLine();

        for(account Account : accounts){
            if(Account.getUserName().equals(userNameLogin) && BCrypt.checkpw(passWordLogin, Account.getPassWord())){
                accountExists = true;
                System.out.println("Credentials verified, logging in..." + "\n");
                inAccount(Person, Account);
            }
        }
        if(!accountExists){
            System.out.println("The given credentials aren't associated to any account, now returning to the main menu..." + "\n");
            mainMenu();           
        }
    }

    public void loginPerson(){
        if(persons.size() <= 0){
            System.out.println("There are currently no users registered, you will be sent back to the main menu" + "\n");
            mainMenu();
        }
        
        System.out.println("If you want to log in to your bank account first type the name you registed with.");
        
        consumeNextLine = sc.nextLine();
        String nameUsed = sc.nextLine();

        boolean personExists = false;

        for(person Person : persons){
            if(Person.getName().equals(nameUsed)){
                personExists = true;
                System.out.println("User found, you will now be prompted to enter your username and password to login to your account." + "\n");
                loginAccount(Person);
            }

        }
        if(!personExists){
            System.out.println("User not found, please register yourself first." + "\n");
            mainMenu();
        }
    
    }

    public void inAccount(person Person, account Account){
            while(true){
                System.out.println("What do you want to do?");
                System.out.println("Type 'deposit' to deposit funds to the account");
                System.out.println("Type 'withdraw' to withdraw funds to the account");
                System.out.println("Type 'check' to check how much money is currently in the account");
                System.out.println("Type 'quit' to go back to the main menu");

                //boolean needToConsume = false;

                /*if(needToConsume){
                    sc.nextLine();
                    needToConsume = false;
                }*/
                
                String inAccountChoice = sc.nextLine();

                switch(inAccountChoice){
                    case "deposit":
                        System.out.println("How much money do you want to deposit to your account?");

                        double moneyToDeposit = Double.parseDouble(sc.nextLine());
                        //needToConsume = true;

                        if(Person.getMoney() < moneyToDeposit){
                            System.out.println("You don't have enough money on you to deposit as much as you want to" + "\n");
                        }
                        else{
                            System.out.println("You have deposited " + moneyToDeposit + "$ to your bank account" + "\n");
                            Account.depositMoney(moneyToDeposit);
                            Person.depositMoney(moneyToDeposit);
                        }
                        break;

                    case "withdraw":
                        System.out.println("How much money do you want to withdraw from your account?");

                        double moneyToWithdraw = Double.parseDouble(sc.nextLine());
                        //needToConsume = true;

                        if(Account.getMoneyInAccount() < moneyToWithdraw){
                            System.out.println("You don't have enough money in your bank account to withdraw " + moneyToWithdraw + "$ " + "\n");
                        }
                        else{
                            System.out.println("You have withdrawn " + moneyToWithdraw + "$ from your bank account" + "\n");
                            Account.withdrawMoney(moneyToWithdraw);
                            Person.withdrawMoney(moneyToWithdraw);
                        }
                    break;

                    case "check":
                        System.out.println("You currently have " + Account.getMoneyInAccount() + "$ in your bank account" + "\n");
                        break;

                    case "quit":
                        mainMenu();
                        break;

                    default:
                        System.out.println("Invalid choice. Try again." + "\n");
                        break;
                }
            }


    }
}