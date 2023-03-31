package PaymentStrategy;

public class CreditCardPS implements IPS{
    private String name;
    private String phone;
    public CreditCardPS(String name, String phone){
        this.name=name;
        this.phone=phone;
    }
    public CreditCardPS(){
        this("","");
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getName(){
        return this.name;
    }
    public String getPhone(){
        return this.phone;
    }
    public void pay(double amount) {
		System.out.println(amount-amount*0.1 + " is paid by using CreditCard.");
	}
}
