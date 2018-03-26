import java.util.Scanner;

public class LibraryRead {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Type ISBN number:");
        String isbn = sc.nextLine();

        BookDao dao = new BookDao();
        Book book = dao.read(isbn);
        System.out.println(book);
        dao.close();
    }
}
