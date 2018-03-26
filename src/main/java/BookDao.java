import java.sql.*;
import java.util.Scanner;

public class BookDao {
    private static final String URL = "jdbc:mysql://localhost:3306/library?characterEncoding=utf8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";
    private Connection connection;

    public BookDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("No drivver found");
        } catch (SQLException e) {
            System.out.println("Could not establish connection");
        }
    }

    public void save(Book book) {
        final String sql = "INSERT INTO books(title, author, year, isbn) values(?, ?, ?, ?)";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, book.getTitle());
            prepStmt.setString(2, book.getAuthor());
            prepStmt.setInt(3, book.getYear());
            prepStmt.setString(4, book.getIsbn());
            prepStmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Could not update record");
            e.printStackTrace();
        }
    }

    public Book read(String isbn) {
        final String sql = "SELECT id, title, author, year, isbn FROM books WHERE isbn = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, isbn);
            ResultSet result = prepStmt.executeQuery();
            if (result.next()) {
                Book book = new Book();
                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setYear(result.getInt("year"));
                book.setIsbn(result.getString("isbn"));
                return book;
            }
        }catch (SQLException e) {
            System.out.println("Could not get book");
        }
        return null;
    }

    public void update(Book book) {
        final String sql = "UPDATE books SET title=?, author=?, year=?, isbn=? WHERE id= ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1,book.getTitle());
            prepStmt.setString(2, book.getAuthor());
            prepStmt.setInt(3, book.getYear());
            prepStmt.setString(4, book.getIsbn());
            prepStmt.setLong(5, book.getId());
            prepStmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Could not update record");
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        final String sql = "delete FROM books WHERE id = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Could not delete row");
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
