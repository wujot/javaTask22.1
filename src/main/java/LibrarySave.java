import java.util.Scanner;

public class LibrarySave {

    public static void main(String[] args) {

        // prompt user to give a book details
        Scanner sc = new Scanner(System.in);
        System.out.println("Give a title:");
        String title = sc.nextLine();
        System.out.println("Give a author:");
        String author = sc.nextLine();
        System.out.println("Give a year:");
        int year = Integer.parseInt(sc.nextLine());
        System.out.println("Give ISBN number:");
        String isbn = sc.nextLine();

        // create book object
        Book book = new Book(title, author, year, isbn);

        // save into database
        BookDao dao = new BookDao();
        dao.save(book);
        dao.close();

    }
}
