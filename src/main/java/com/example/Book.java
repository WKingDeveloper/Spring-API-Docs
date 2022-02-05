package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String author;
}
