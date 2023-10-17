package com.epileftro.patterns.builder;

import java.time.Year;

enum Genre {
    CLASSICS,
    MYSTERY,
    FANTASY,
    HISTORICAL_FICTION,
    HORROR,
    LITERARY_FICTION,
    ROMANCE,
    SCIENCE_FICTION,
    HISTORY
}

class Book {
    private static String isbn;
    private static String title;
    private static Genre genre;
    private static String author;
    private static Year published;
    private static String description;

    private Book(BookBuilder bookBuilder) {
        this.isbn = bookBuilder.isbn;
        this.title = bookBuilder.title;
        this.genre = bookBuilder.genre;
        this.author = bookBuilder.author;
        this.published = bookBuilder.published;
        this.description = bookBuilder.description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                ", author='" + author + '\'' +
                ", published=" + published +
                ", description='" + description + '\'' +
                '}';
    }

    static class BookBuilder {
        private static String isbn;
        private static String title;
        private Genre genre;
        private String author;
        private Year published;
        private String description;

        public BookBuilder(String isbn, String title) {
            addIsbn(isbn);
            addTitle(title);
        }

        public BookBuilder addIsbn(String isb) {
            this.isbn = isb;
            return this;
        }

        public BookBuilder addTitle(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder addGender(Genre genre) {
            this.genre = genre;
            return this;
        }

        public BookBuilder addAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder addPublished(Year publish) {
            this.published = publish;
            return this;
        }


        public BookBuilder addDesc(String desc) {
            this.description =desc;
            return this;
        }

        public Book build() {
            return new Book(this);
        }

        public void reset() {
            this.isbn = "";
            this.title = "";
            this.author = "";
            this.description = "";
        }
    }
}

public class BlochBuilder {
    public static void main(String[] args) {
        var builder = new Book.BookBuilder("123", "Title");
        builder.addAuthor("Andres").addDesc("Some description").addGender(Genre.HISTORY);

        System.out.println(builder.build());
    }
}
