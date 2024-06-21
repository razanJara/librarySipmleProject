package com.library.library.servies;

import com.library.library.Enum.Status;
import com.library.library.dto.request.StatusRequest;
import com.library.library.dto.request.NameRequest;
import com.library.library.dto.request.UserRequest;
import com.library.library.dto.response.BookResponse;
import com.library.library.dto.response.UserResponse;
import com.library.library.entity.Book;
import com.library.library.entity.User;
import com.library.library.exception.CustomException;
import com.library.library.repository.BookRepository;
import com.library.library.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService implements UserDetailsService {
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
        return getUserResponse(userRepository.findById(id).orElseThrow(()-> new CustomException("the sent id does not exist", 400)));
    }

    public UserResponse createNewUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        User savedUser = userRepository.save(user);
        return getUserResponse(savedUser);
    }

    public UserResponse updateUser(int id, NameRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(()-> new CustomException("the sent id does not exist", 400));
        user.setName(userRequest.getName());
        userRepository.save(user);
        return getUserResponse(user);
    }

    public UserResponse updateStatus(int id, StatusRequest statusRequest) {
        User user = userRepository.findById(id).orElseThrow(()-> new CustomException("the sent id does not exist", 400));
        user.setStatus(statusRequest.getStatus().equals(Status.ACTIVE));
        userRepository.save(user);
        return getUserResponse(user);
    }

    public UserResponse borrowBook(int userId, int bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new CustomException("Book id not found", 400));
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException("the sent id does not exist", 400));
        book.setAvailable(false);
        book.setUser(user);
        Book savedBook = bookRepository.save(book);
        return getUserResponse(savedBook.getUser());
    }

    public void returnBook(int userId, int bookId) throws CustomException{
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException("the sent id does not exist", 400));
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new CustomException("Book id not found", 400));
        if(!user.getBooks().contains(book)){
            throw new CustomException("the user did not borrow the book", 400);
        }
        book.setAvailable(true);
        book.setUser(null);
        bookRepository.save(book);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return org.springframework.security.core.userdetails.User.withUsername(user.getName())
                .password(user.getPassword())
                .authorities(user.getRole())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
