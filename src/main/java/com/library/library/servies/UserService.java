package com.library.library.servies;

import com.library.library.dto.response.UserResponse;
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
}
