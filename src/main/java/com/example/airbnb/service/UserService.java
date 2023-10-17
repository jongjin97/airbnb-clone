package com.example.airbnb.service;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.config.jwt.JwtTokenUtil;
import com.example.airbnb.dto.RequestSignin;
import com.example.airbnb.dto.RequestUser;
import com.example.airbnb.dto.ResponseUser;
import com.example.airbnb.document.User;
import com.example.airbnb.errors.AccountAlreadyExistsException;
import com.example.airbnb.errors.PhoneAlreadyExistsException;
import com.example.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public ResponseUser signUp(RequestUser requestUser) throws Exception {
        Optional<User> findUser = userRepository.findByEmail(requestUser.getEmail());
        if(findUser.isPresent())
            throw new AccountAlreadyExistsException("Email already exists");

        requestUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        User user = new User(requestUser);
        User result = userRepository.save(user);
        ResponseUser responseUser = new ResponseUser(result);
        return responseUser;
    }

    @Transactional
    public ResponseUser signIn(RequestSignin requestUser) throws Exception {
        Optional<User> findUser = userRepository.findByEmail(requestUser.getEmail());
        if(findUser.isEmpty())
            throw new Exception("Email does not exist");
        if(!passwordEncoder.matches(requestUser.getPassword(), findUser.get().getPassword()))
            throw new Exception("Password is incorrect");
        ResponseUser responseUser = new ResponseUser(findUser.get());
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(findUser.get());
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String jwtToken = jwtTokenUtil.generateToken(userDetailsImpl);
        responseUser.setToken(jwtToken);
        return responseUser;
    }
}
