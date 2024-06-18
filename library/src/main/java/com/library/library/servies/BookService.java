package com.library.library.servies;

import com.library.library.dto.request.BookRequest;
import com.library.library.dto.response.BookResponse;
import com.library.library.entity.Book;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookResponse createBook(BookRequest bookRequest){
        Book book = modelMapper.map(bookRequest, Book.class);
        book.setAuthor(authorRepository.findById(bookRequest.getAuthorId()).get());
        bookRepository.save(book);
        return modelMapper.map(book, BookResponse.class);
    }

    public BookResponse getBook(int id) {
        Book book = bookRepository.findById(id).get();
        return modelMapper.map(book, BookResponse.class);
    }
}
