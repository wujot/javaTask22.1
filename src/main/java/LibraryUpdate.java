public class LibraryUpdate {

    public static void main(String[] args) {

        BookDao dao = new BookDao();
        Book book = dao.read("9781449372507");
        System.out.println(book);
        book.setIsbn("7052739441879");
        dao.update(book);
        System.out.println(book);
        dao.close();
    }
}
