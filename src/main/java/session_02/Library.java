package session_02;

public class Library {
    void displayBookInfo(Book book) {
        System.out.println("Title: " + book.title);
        System.out.println("Author: " + book.author);
        System.out.println("Publication year: " + book.publicationYear);
    };

    public static void main(String[] args) {
        Book book_1 = new Book("Harry Potter", "J.K.Rowling", 2005);
        Book book_2 = new Book("All quiet on the Western Front", "Enrich Maria Remarque", 1925);
        Book book_3 = new Book("Kinh van hoa", "Nguyen Nhat Anh", 2005);
        Library library = new Library();
        library.displayBookInfo(book_1);
        library.displayBookInfo(book_2);
        library.displayBookInfo(book_3);
    };
}