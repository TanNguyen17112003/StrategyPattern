package PaymentStrategy;

public class PaypalPS implements IPS {
    private String email;
    private String password;

    public PaypalPS(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public PaypalPS() {
        this("", "");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public double pay(double amount) {
        return amount * 0.05;
    }
}
