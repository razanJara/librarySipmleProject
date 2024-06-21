package com.library.library.controller;

import com.library.library.dto.request.AuthorRequest;
import com.library.library.dto.request.NameRequest;
import com.library.library.dto.response.AuthorResponse;
import com.library.library.dto.response.GeneralResponse;
import com.library.library.servies.AuthorService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthorController {
    AuthorService authorService;
    @GetMapping("/list")
    public ResponseEntity<List<AuthorResponse>> getAll(){
        return ResponseEntity.ok().body(authorService.getAllAuthors());
    }
    @PostMapping("/new")
    public ResponseEntity<GeneralResponse> createNewAuthor(@Valid @RequestBody AuthorRequest authorRequest){
        return ResponseEntity.ok().body(new GeneralResponse(authorService.createNewAuthor(authorRequest)));
    }
    @GetMapping("/{author_id}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable("author_id") int id){
        return ResponseEntity.ok().body(authorService.getAuthor(id));
    }
    @PutMapping("/update/{author_id}")
    public ResponseEntity<GeneralResponse> updateName(@PathVariable("author_id") int id, @Valid @RequestBody NameRequest nameRequest){
        authorService.updateName(id, nameRequest);
        return ResponseEntity.ok().body(new GeneralResponse("done"));
    }
}
