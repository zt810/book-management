package entity;

public class Book {
    private long bookId;
    private String title;
    private String author;
    private int publishYear;
    private long genreId;

    public Book(long bookId, String title, String author, int publishYear, long genreId) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.genreId = genreId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "Ma: " + bookId + " | Tieu de: " + title + " | Tac gia: " + author + " | Nam: " + publishYear;
    }
}
