package com.books.project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name="books")
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String isbn;

    @JsonIgnore
    @ManyToOne //many books to one author
    @JoinColumn(name = "author_id")
    private Author author;


    /**
     * A constructor that generates a book in the book table.
     * @param id The id of where the book is stored in the table.
     * @param name The name of the book.
     * @param description The description of the book.
     * @param isbn The isbn nubmer of the book.
     */
    public Book(Long id, String name, String description, String isbn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isbn = isbn;
    }

    /**
     * A constructor that generates a book in the book table.
     */
    public Book(){

    }

    /**
     * Retrieves the id of the specified book.
     * @return The id of the book.
     */
    public Long getId() {return id;}

    /**
     * Changes the id of the specified book.
     * @param id The id that the id of the book will be changed to.
     */
    public void setId(Long id) {this.id = id;}

    /**
     * Retrieves the name of the specified book.
     * @return The name of the book.
     */
    public String getName() {return name;}

    /**
     * Changes the name of the specified book.
     * @param name The name that the book will now be named.
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the description of the specified book.
     * @return The description of the book.
     */
    public String getDescription() {return description;}

    /**
     * Changes the description of the specified book.
     * @param description The description of the book.
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * Retrieves the isbn of the specified book.
     * @return The isbn of the book.
     */
    public String getIsbn() {return isbn;}

    /**
     * Changes the isbn of the specified book
     * @param isbn The isbn of the book
     */
    public void setIsbn(String isbn) {this.isbn = isbn;}


    /**
     * Displays information about a specified book.
     * @return The book's information.
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    /**
     * Retrieves the author to which the book belongs.
     * @return The author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Changes the author to which the book belongs.
     * @param author The new author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }
}