package SPRINT_1_DAY_2;
import java.util.*;

class Movie {
    private String title;
    private String director;
    private String genre;
    private int releaseYear;
    private double rating;

    public Movie(String title, String director, String genre, int releaseYear, double rating) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public String getGenre() { return genre; }
    public int getReleaseYear() { return releaseYear; }
    public double getRating() { return rating; }

    public String toString() {
        return String.format("%-20s %-15s %-10s %-6d %-5.1f",
                title, director, genre, releaseYear, rating);
    }
}

public class MovieManager_10 {
    private ArrayList<Movie> movies = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addMovie() {
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter director: ");
        String director = sc.nextLine();
        System.out.print("Enter genre: ");
        String genre = sc.nextLine();
        System.out.print("Enter release year: ");
        int year = sc.nextInt();
        System.out.print("Enter rating (0.0 - 10.0): ");
        double rating = sc.nextDouble();
        sc.nextLine();
        movies.add(new Movie(title, director, genre, year, rating));
        System.out.println("Movie added successfully.");
    }

    public void removeMovie() {
        System.out.print("Enter title to remove: ");
        String title = sc.nextLine();
        boolean removed = movies.removeIf(m -> m.getTitle().equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Movie removed.");
        } else {
            System.out.println("Movie not found.");
        }
    }

    public void filterByGenre() {
        System.out.print("Enter genre to filter: ");
        String genre = sc.nextLine();
        movies.stream()
              .filter(m -> m.getGenre().equalsIgnoreCase(genre))
              .forEach(System.out::println);
    }

    public void filterByDirector() {
        System.out.print("Enter director to filter: ");
        String director = sc.nextLine();
        movies.stream()
              .filter(m -> m.getDirector().equalsIgnoreCase(director))
              .forEach(System.out::println);
    }

    public void filterByYear() {
        System.out.print("Enter release year to filter: ");
        int year = sc.nextInt();
        sc.nextLine();
        movies.stream()
              .filter(m -> m.getReleaseYear() == year)
              .forEach(System.out::println);
    }

    public void sortByTitle() {
        movies.sort(Comparator.comparing(Movie::getTitle));
        System.out.println("\nMovies sorted by title:");
        displayAll();
    }

    public void sortByYear() {
        movies.sort(Comparator.comparingInt(Movie::getReleaseYear));
        System.out.println("\nMovies sorted by release year:");
        displayAll();
    }

    public void sortByRating() {
        movies.sort(Comparator.comparingDouble(Movie::getRating).reversed());
        System.out.println("\nMovies sorted by rating:");
        displayAll();
    }

    public void displayAll() {
        if (movies.isEmpty()) {
            System.out.println("No movies found.");
            return;
        }
        System.out.printf("\n%-20s %-15s %-10s %-6s %-5s\n", "Title", "Director", "Genre", "Year", "Rating");

        for (Movie m : movies) {
            System.out.println(m);
        }
    }

    public void menu() {
        int choice;
        do {
            System.out.println("1. Add Movie");
            System.out.println("2. Remove Movie");
            System.out.println("3. Filter by Genre");
            System.out.println("4. Filter by Director");
            System.out.println("5. Filter by Year");
            System.out.println("6. Sort by Title");
            System.out.println("7. Sort by Year");
            System.out.println("8. Sort by Rating");
            System.out.println("9. Display All Movies");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addMovie(); break;
                case 2: removeMovie(); break;
                case 3: filterByGenre(); break;
                case 4: filterByDirector(); break;
                case 5: filterByYear(); break;
                case 6: sortByTitle(); break;
                case 7: sortByYear(); break;
                case 8: sortByRating(); break;
                case 9: displayAll(); break;
                case 10: System.out.println("Exiting Movie Manager."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 10);
    }

    public static void main(String[] args) {
        MovieManager_10 manager = new MovieManager_10();
        manager.menu();
    }
}
