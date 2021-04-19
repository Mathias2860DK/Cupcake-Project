package business.entities;

public class User
{

    public User(String email, String password, String role)
    {
        this.email = email;
        this.password = password;
        this.role = role;
        this.balance = 0;
    }

    private int id; // just used to demo retrieval of autogen keys in UserMapper
    private String email;
    private String password; // Should be hashed and secured
    private String role;
    private double balance;

    //kommer vi ind her?
    //kommer vi her ind?
    //ind her vi kommer?
    //kommer ind vi her?
    //kom nu ind her?

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
