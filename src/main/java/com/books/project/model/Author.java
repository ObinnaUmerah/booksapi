package com.books.project.model;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Author {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Author> authorList;
}
