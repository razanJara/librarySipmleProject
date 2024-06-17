package com.library.library.servies;

import com.library.library.Enum.Status;
import com.library.library.dto.request.StatusRequest;
import com.library.library.dto.request.UserRequest;
import com.library.library.dto.response.UserResponse;
import com.library.library.entity.User;
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

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream(). map(x->modelMapper.map(x, UserResponse.class)).toList();
    }

    public UserResponse getUser(int id) {
        return modelMapper.map(userRepository.findById(id).get(), UserResponse.class);
    }

    public UserResponse createNewUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    public UserResponse updateUser(int id, UserRequest userRequest) {
        User user = userRepository.findById(id).get();
        user.setName(userRequest.getName());
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    public UserResponse updateStatus(int id, StatusRequest statusRequest) {
        User user = userRepository.findById(id).get();
        user.setStatus(statusRequest.getStatus().equals(Status.ACTIVE));
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }
}
