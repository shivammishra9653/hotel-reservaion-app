package ExperimentWithJava;

import java.util.Date;

public class Transaction {
    private String description;
    private Date date;
    private String account;
    private double amount;

    public Transaction(String description, Date date, String account, double amount){
        this.description = description;
        this.date = date;
        this.account = account;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "description = " + this.description + "\n" +
        "date = " + this.date + "\n" +
        "account = " + this.account + "\n" +
        "amount = " + this.amount + "\n";
    }

    public static void main(String[] args) {
        Transaction trs = new Transaction("for food", new Date(), "1234", 2000.0);
        System.out.println(trs);
    }
}
