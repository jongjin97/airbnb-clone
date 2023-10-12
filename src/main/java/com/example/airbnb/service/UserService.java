package com.example.airbnb.service;

import com.example.airbnb.dto.RequestUser;
import com.example.airbnb.dto.ResponseUser;
import com.example.airbnb.entity.User;
import com.example.airbnb.errors.AccountAlreadyExistsException;
import com.example.airbnb.errors.PhoneAlreadyExistsException;
import com.example.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public ResponseUser signUp(RequestUser requestUser) throws Exception {
        Optional<User> findUser = userRepository.findByEmail(requestUser.getEmail());
        if(findUser.isPresent())
            throw new AccountAlreadyExistsException("Email already exists");
        if(userRepository.existsByPhone(requestUser.getPhone()))
            throw new PhoneAlreadyExistsException("Phone already exists");

        requestUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        User user = new User(requestUser);
        User result = userRepository.save(user);
        ResponseUser responseUser = new ResponseUser(result);
        return responseUser;
    }
}
