public class LibraryDelete {

    public static void main(String[] args) {

        BookDao dao = new BookDao();
        dao.delete(1);
        dao.close();
    }
}
