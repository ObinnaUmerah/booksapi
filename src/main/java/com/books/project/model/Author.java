package com.books.project.model;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstname;
    @Column
    private String lastname;


    @OneToMany(mappedBy = "author", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Book> bookList;

    /**
     * A constructor that generates an author in the authors table.
     * @param firstname The first name of the author.
     * @param lastname The last name of the author.
     */

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * A constructor that generates an author in the authors table.
     * @param id The id where the author is stored in the table.
     * @param firstname The first name of the author.
     * @param lastname The last name of the author.
     */
    public Author(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * A constructor that generates an author in the authors table
     */
    public Author() {
    }

    /**
     * Gets the id of an author
     * @return The id of the author specified by the user.
     */
    public Long getId() {return id;}

    /**
     * Sets the id of an author
     * @param id The id that the user wants to change the current author to.
     */
    public void setId(Long id) {this.id = id;}


    /**
     * Retrieves the first name of the author
     * @return The first name of the author
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Changes the first name of the author
     * @param firstname The first name of the author
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Retrieves the last name of the author
     * @return The last name of the author
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Changes the last name of the author
     * @param lastname The last name of the author
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Displays the information about an author
     * @return The information of the author.
     */
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", bookList=" + bookList +
                '}';
    }

    /**
     * Retrieves the author's book collection.
     * @return The author's list of books.
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Changes the author's book collection.
     * @param bookList The author's list of books.
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}