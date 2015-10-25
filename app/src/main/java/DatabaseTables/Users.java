package DatabaseTables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by himanshu on 10/24/2015.
 */
@DatabaseTable(tableName = "users")
public class Users {
    public Users()
    {}

    public Users(String name, String password, double balance)
    {
        this.name = name;
        this.balance = balance;
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @DatabaseField(id= true)
        private String name;
        @DatabaseField
        private String password;
        @DatabaseField
        private double balance;
}
