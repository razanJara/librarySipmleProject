package com.library.library.controller;

import com.library.library.dto.request.BookRequest;
import com.library.library.dto.request.NameRequest;
import com.library.library.dto.response.BookResponse;
import com.library.library.dto.response.GeneralResponse;
import com.library.library.servies.BookService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookController {
    private final BookService bookService;

    @GetMapping("/{book_id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable("book_id") int id){
        return ResponseEntity.ok().body(bookService.getBook(id));
    }
    @PostMapping("/new_book")
    public ResponseEntity<BookResponse> createBook(@Valid@RequestBody BookRequest bookRequest){
        return ResponseEntity.ok().body(bookService.createBook(bookRequest));
    }
    @GetMapping("/list")
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        return ResponseEntity.ok().body(bookService.getAll());
    }
    @PutMapping("/update/{book_id}")
    public ResponseEntity<GeneralResponse> updateName(@PathVariable("book_id") int id, @Valid@RequestBody NameRequest nameRequest){
        bookService.updateName(id, nameRequest);
        return ResponseEntity.ok().body(new GeneralResponse("done"));
    }
}
