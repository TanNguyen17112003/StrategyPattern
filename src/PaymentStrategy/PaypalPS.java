package PaymentStrategy;

public class PaypalPS implements IPS{
    private String email;
    private String password;
    public PaypalPS(String email, String password){
        this.email=email;
        this.password=password;
    }
    public PaypalPS(){
        this("","");
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public void pay(double amount) {
		System.out.println("You are given back 5% of current price. Your price is now: " + amount*0.95);
	}
}
