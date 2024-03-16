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
        System.out.println("Type 'exit' to quit the program");

        String choice = sc.next();

        switch (choice) {
            case "create":
                createAccount();
                break;
            case "login":
                break;
            case "exit":
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Try again.");
                mainMenu();
        }
    }

    public void createAccount() {
        System.out.println("If you haven't done so already, you must register yourself as a person before opening your bank account");
        System.out.println("Type 'register' to register yourself as a person if you haven't done so already");
        System.out.println("Type 'login' to log in as a person if you've already completed the registration process");
        System.out.println("Type 'quit' to return to the main menu");
    
        String personChoice = sc.next();
    
        switch(personChoice) {
            case "register":
                registerPerson();
                break;
            case "login":
                loginPerson();
                break;
            case "quit":
                mainMenu();
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                createAccount();
        }
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
        createAccount();
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
            System.out.println("User found, you will now be prompted to enter your username and password to login to your account.");
            loginAccount();
        }
        else{
            System.out.println("There is no person registered with this name, please register first.");
            mainMenu();
        }
    }

    public void loginAccount(){
        
    }

    
}