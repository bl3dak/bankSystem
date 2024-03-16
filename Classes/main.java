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
        System.out.println("To be able to open a bank account you must first register yourself as a person" + "\n");
        System.out.println("Type 'register' to register yourself as a person");
        System.out.println("Type 'back' to return to the main menu");
    
        String personChoice = sc.next();
    
        switch(personChoice) {
            case "register":
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

        account Account = new account(userName, passWord);

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
        System.out.println("Now you can login to your account, to do so first type your username");

        boolean accountExists = false;

       // double eventualMoneyPassVar = 0;

        String userNameLogin = sc.nextLine();

        System.out.println("And now type your password");

        String passWordLogin = sc.nextLine();

        for(account Account : accounts){
            if(Account.getUserName().equals(userNameLogin) && Account.getPassWord().equals(passWordLogin)){
                accountExists = true;
                System.out.println("Credentials verified, logging in...");
                inAccount(Person, Account);
            }
        }
        if(!accountExists){
            System.out.println("The given credentials aren't associated to any account, now returning to the main menu..." + "\n");
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
                            System.out.println("You have deposited " + moneyToDeposit + "$ to your bank account");
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
                            System.out.println("You have withdrawn " + moneyToWithdraw + "$ from your bank account");
                            Account.withdrawMoney(moneyToWithdraw);
                            Person.withdrawMoney(moneyToWithdraw);
                        }
                    break;
                    
                    case "check":
                        System.out.println("You currently have " + Account.getMoneyInAccount() + "$ in your bank account");
                        break;

                    case "quit":
                        mainMenu();
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            }


    }
}