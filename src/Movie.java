public class Movie {
    private String name;
    private int ID;
    private Ticket ticket;
    private String path;

    Movie(String name, int ID, Ticket ticket, String path) {
        this.name = name;
        this.ID = ID;
        this.ticket = ticket;
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

}
