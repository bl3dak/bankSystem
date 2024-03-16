import java.util.Scanner;
import java.util.ArrayList;

class MainClass {
    private Scanner sc;
    private ArrayList<person> persons;
    private ArrayList<account> accounts;
    private String consumeNextLine;

    public MainClass() {
        sc = new Scanner(System.in);
        persons = new ArrayList<>();
        accounts = new ArrayList<>();
        String consumeNextLine;
    }

    public static void main(String[] args) {
        MainClass main = new MainClass();
        main.mainMenu();
    }

    public void mainMenu() {
        System.out.println("Type 'create' to initialize a new bank account");
        System.out.println("Type 'login' to access your bank account");
        System.out.println("Type 'quit' to quit the program");

        String choice = sc.next();

        switch (choice) {
            case "create":
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
        System.out.println("If you haven't done so already, you must register yourself as a person before opening your bank account" + "\n");
        System.out.println("Type 'account' to setup your bank account");
        System.out.println("Type 'person' to register yourself as a person if you haven't done so already");
        System.out.println("Type 'back' to return to the main menu");
    
        String personChoice = sc.next();
    
        switch(personChoice) {
            case "account":
                createAccount();
                break;
            case "person":
                registerPerson();
                break;

            case "back":
                mainMenu();
                break;

            default:
                System.out.println("Invalid choice. Try again.");
                createMenu();
        }
    }

    public void createAccount(){
        System.out.println("Type your new username, no spaces allowed.");
         
        boolean spazioUserName = false;

        consumeNextLine = sc.nextLine();
        String userName = sc.nextLine();

        if(userName.indexOf(" ") != -1){
             System.out.println("The username contains a space, you will be sent back to the main menu.");
             mainMenu();
        }

        System.out.println("Type your new password, no spaces allowed.");
            
        String passWord = sc.nextLine();

        if(passWord.indexOf(" ") != -1){
            System.out.println("The password contains a space, you will be sent back to the main menu.");
            mainMenu();
       }

        account Account = new account(userName, passWord);

        accounts.add(Account);

        System.out.println("\n");

        mainMenu();
    }

    public void registerPerson() {
        System.out.println("What name should we register you with?");
        
        String consumeNextLine = sc.nextLine(); // Consume the newline character
        String nameToRegister = sc.nextLine();
        
        System.out.println("How much money do you currently have?");
       
        double moneyOnPerson = sc.nextDouble();
        
        person Person = new person(nameToRegister, moneyOnPerson);
        persons.add(Person);
        
        System.out.println("\n");
        
        createMenu();
    }

    public void loginAccount(String personName){
        System.out.println("Now you can login to your account, to do so first type your username and then your password");

        boolean accountExists = false;

       // double eventualMoneyPassVar = 0;

        String userNameLogin = sc.nextLine();

        String passWordLogin = sc.nextLine();

        for(account Account : accounts){
            if(Account.getUserName().equals(userNameLogin) && Account.getPassWord().equals(passWordLogin)){
                accountExists = true;
                //eventualMoneyPassVar = Account.getMoneyInAccount();
            }
        }
        if(accountExists){
            System.out.println("Credentials verified, logging in...");

            inAccount(userNameLogin, passWordLogin, personName);
        }
        else{
            System.out.println("The given credentials aren't associated to any account, now returning to the main menu...");
            mainMenu();
        }
    }

    public void loginPerson(){
        System.out.println("If you want to log in to your bank account first type the name you registed with.");
        
        consumeNextLine = sc.nextLine();
        String nameUsed = sc.nextLine();

        boolean personExists = false;

        for(person Person : persons){
            if(Person.getName().equals(nameUsed)){
                personExists = true;
            }

        }
        if(personExists){
            System.out.println("User found, you will now be prompted to enter your username and password to login to your account." + "\n");
            loginAccount(nameUsed);
        }
        else{
            System.out.println("User not found, please register yourself first." + "\n");
            mainMenu();
        }


    }

    public void inAccount(String userName, String passWord, String personName){
            System.out.println("What do you want to do?");
            System.out.println("Type 'deposit' to deposit funds to the account");
            System.out.println("Type 'withdraw' to withdraw funds to the account");
            System.out.println("Type 'check' to check how much money is currently in the account");
            System.out.println("Type 'quit' to go back to the main menu");

            String inAccountChoice = sc.nextLine();

            switch(inAccountChoice){
                case "deposit":
                    System.out.println("How much money do you want to deposit in your account?");

                    double moneyToDeposit = sc.nextDouble();
                    double moneyOnPerson;

                    for(person Person : persons){
                        if(Person.getName()equals(personName)){
                            moneyOnPerson = Person.getMoney();
                        }
                    }

                    if(true){
                        
                    }
                    break;
                case "withdraw":
                    break;
                case "check":
                    break;
                case "quit":
                    mainMenu();
                    break;
            }

    }

    
}