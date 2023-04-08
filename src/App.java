import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<Movie> movieList = new ArrayList<>(3);
        movieList.add(new Movie("Avengers: Endgame", 1, new Ticket(100000), null));
        movieList.add(new Movie("Avatar: The Way of Water", 2, new Ticket(125000), null));
        movieList.add(new Movie("Titanic", 3, new Ticket(95000), null));

        String basePath = System.getProperty("user.dir") + "/src/";
        String path1 = basePath + "avenger.png";
        String path2 = basePath + "avatar.png";
        String path3 = basePath + "titanic.png";

        movieList.get(0).setPath(path1);
        movieList.get(1).setPath(path2);
        movieList.get(2).setPath(path3);

        MovieBookingGUI movieBookingGUI = new MovieBookingGUI(movieList);
        movieBookingGUI.createGUI();
    }
}
