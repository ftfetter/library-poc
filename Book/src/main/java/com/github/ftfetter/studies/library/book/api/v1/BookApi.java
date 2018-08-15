package com.github.ftfetter.studies.library.book.api.v1;

import com.github.ftfetter.studies.library.book.entity.Book;
import com.github.ftfetter.studies.library.book.entity.mapper.BookMapper;
import com.github.ftfetter.studies.library.book.exception.InternalServerErrorException;
import com.github.ftfetter.studies.library.book.input.BookInput;
import com.github.ftfetter.studies.library.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1/books")
public class BookApi {

    private BookService bookService;

    @Autowired
    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        try {
            return ResponseEntity.ok(bookService.getAllBooks());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") String id) {
        try {
            return bookService.getBookById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveBook(@RequestBody BookInput input) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(BookMapper.map(input)));
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") String id, @RequestBody BookInput input) {
        try {
            return ResponseEntity.ok(bookService.updateBook(id, input));
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") String id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}