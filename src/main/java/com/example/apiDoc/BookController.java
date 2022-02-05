package com.example.apiDoc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> hello(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(new Book(id, "Spring Rest Doc Exam","WKingDeveloper"));
    }
}
