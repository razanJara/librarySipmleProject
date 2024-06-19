package com.library.library.servies;

import com.library.library.Enum.Status;
import com.library.library.dto.request.StatusRequest;
import com.library.library.dto.request.NameRequest;
import com.library.library.dto.response.BookResponse;
import com.library.library.dto.response.UserResponse;
import com.library.library.entity.Book;
import com.library.library.entity.User;
import com.library.library.repository.BookRepository;
import com.library.library.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {
    ModelMapper modelMapper;
    UserRepository userRepository;
    private final BookRepository bookRepository;

    public List<UserResponse> getAll() {
        List<User> userList = userRepository.findAll();
        return getUserResponseList(userList);
    }

    private List<UserResponse> getUserResponseList(List<User> userList) {
        return userList.stream().map(this::getUserResponse).toList();
    }

    private UserResponse getUserResponse(User user) {
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setBookBorrowing(user.getBooks().stream().map(book -> modelMapper.map(book, BookResponse.class)).toList());
        return userResponse;
    }

    public UserResponse getUser(int id) {
        return getUserResponse(userRepository.findById(id).get());
    }

    public UserResponse createNewUser(NameRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        User savedUser = userRepository.save(user);
        return getUserResponse(savedUser);
    }

    public UserResponse updateUser(int id, NameRequest userRequest) {
        User user = userRepository.findById(id).get();
        user.setName(userRequest.getName());
        userRepository.save(user);
        return getUserResponse(user);
    }

    public UserResponse updateStatus(int id, StatusRequest statusRequest) {
        User user = userRepository.findById(id).get();
        user.setStatus(statusRequest.getStatus().equals(Status.ACTIVE));
        userRepository.save(user);
        return getUserResponse(user);
    }

    public UserResponse borrowBook(int userId, int bookId) {
        Book book = bookRepository.findById(bookId).get();
        User user = userRepository.findById(userId).get();
        book.setAvailable(false);
        book.setUser(user);
        Book savedBook = bookRepository.save(book);
        return getUserResponse(savedBook.getUser());
    }

    public String returnBook(int userId, int bookId){
        Book book = bookRepository.findById(bookId).get();
        book.setAvailable(true);
        book.setUser(null);
        Book savedBook = bookRepository.save(book);
        return "done";
    }
}
