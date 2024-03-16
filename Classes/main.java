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
                createAccount();
        }
    }

    public void createAccount(){
        System.out.println("What will be your username?");
            
        String userName = sc.nextLine();

        System.out.println("And your password?");
            
        String passWord = sc.nextLine();

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

    public void loginAccount(){
        System.out.println("Now you can login to your account, to do so first type your username and then your password");

        boolean accountExists = false;

        String userNameLogin = sc.nextLine();

        String passWordLogin = sc.nextLine();

        for(account Account : accounts){
            if(Account.getUserName().equals(userNameLogin) && Account.getPassWord().equals(passWordLogin)){
                accountExists = true;
            }
        }
        if(accountExists){
            System.out.println("Credentials verified, logging in...");

            inAccount();
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
            loginAccount();
        }
        else{
            System.out.println("User not found, please register yourself first." + "\n");
            mainMenu();
        }


    }

    public void inAccount(){
            System.out.println("");
    }

    
}