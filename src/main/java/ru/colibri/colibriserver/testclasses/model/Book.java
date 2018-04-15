package ru.colibri.colibriserver.testclasses.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "BOOK_TITLE")
    private String title;

    @Column(name = "BOOK_AUTHOR")
    private String author;

    @Column(name = "BOOK_PRICE")
    private int price;

}
