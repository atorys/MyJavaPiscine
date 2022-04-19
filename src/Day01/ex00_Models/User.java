package Day01.ex00_Models;

public class User {

    private Integer   Identifier;
    private String          Name;
    private Integer         Balance;

    public User(Integer id, String name, Integer balance) {
        this.Identifier = id;
        this.Name = name;
        this.Balance = balance;
    }

    public String   getName()       { return this.Name; }
    public Integer  getIdentifier() { return this.Identifier; }
    public Integer  getBalance()    { return this.Balance; }

    public void     setName(String name)        { this.Name = name; }
    public void     setBalance(Integer balance) { this.Balance = (balance > 0 ? balance : 0); }
    public void     setIdentifier(Integer id)   { this.Identifier = id; }

    public void     displayUser() {
        System.out.printf("ID %d:\t%s\t%d$\n", this.getIdentifier(), this.getName(), this.getBalance());
    }

}
