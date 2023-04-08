package PaymentStrategy;

public class MOMOPS implements IPS {
    private String name;
    private String phone;

    public MOMOPS(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public MOMOPS() {
        this("", "");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public double pay(double amount) {
        return 0;
    }

}
