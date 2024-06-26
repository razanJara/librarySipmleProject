package com.library.library.servies;

import com.library.library.dto.request.BookRequest;
import com.library.library.dto.request.NameRequest;
import com.library.library.dto.response.BookResponse;
import com.library.library.entity.Book;
import com.library.library.exception.CustomException;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookResponse createBook(BookRequest bookRequest){
        Book book = modelMapper.map(bookRequest, Book.class);
        book.setAuthor(authorRepository.findById(bookRequest.getAuthorId()).orElseThrow(()->new CustomException("the sent id does not exist",400)));
        bookRepository.save(book);
        return modelMapper.map(book, BookResponse.class);
    }

    public BookResponse getBook(int id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new CustomException("the sent id does not exist",400));
        return modelMapper.map(book, BookResponse.class);
    }

    public List<BookResponse> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .toList();
    }
    public void updateName(int id, NameRequest nameRequest) {
        Book book = bookRepository.findById(id).orElseThrow(()->new CustomException("the sent id does not exist",400));
        book.setName(nameRequest.getName());
        bookRepository.save(book);
    }
}
