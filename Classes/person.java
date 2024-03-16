class person {
    private String name;
    private double money;

    public person(String name, double money){
        this.name = name;
        this.money = money;
    }

    public String getName(){
        return this.name;
    }

    public double getMoney(){
        return this.money;
    }

    public void depositMoney(double moneyToDeposit){
        this.money -= moneyToDeposit;
    }

    public void withdrawMoney(double moneyToWithdraw){
        this.money += moneyToWithdraw;
    }
}
