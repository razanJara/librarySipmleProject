package com.library.library.servies;

import com.library.library.dto.request.AuthorRequest;
import com.library.library.dto.request.BookRequest;
import com.library.library.dto.response.AuthorResponse;
import com.library.library.entity.Author;
import com.library.library.entity.Book;
import com.library.library.repository.AuthorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public List<AuthorResponse> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(author -> {
                    AuthorResponse authorResponse = modelMapper.map(author, AuthorResponse.class);
                    authorResponse.setNumberOfBooks(author.getBooks().size());
                    return authorResponse;
                })
                .toList();
    }

    public String createNewAuthor(AuthorRequest authorRequest) {
        Author author = modelMapper.map(authorRequest, Author.class);
        List<Book> bookList = new ArrayList<>();
        for (BookRequest bookRequest : authorRequest.getBookRequestList()) {
            Book book = modelMapper.map(bookRequest, Book.class);
            book.setAuthor(author);
            bookList.add(book);
        }
        author.setBooks(bookList);
        authorRepository.save(author);
        return "Done";
    }

    public AuthorResponse getAuthor(int id) {
        Author author = authorRepository.findById(id).get();
        AuthorResponse authorResponse = modelMapper.map(author, AuthorResponse.class);
        authorResponse.setNumberOfBooks(author.getBooks().size());
        return authorResponse;
    }
}
