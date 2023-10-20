package com.example.airbnb.service;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.document.User;
import com.example.airbnb.dto.ResponseUser;
import com.example.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final UserRepository userRepository;

    public ResponseUser addFavorite(String id, User user){
        user.getFavorites().add(id);
        userRepository.save(user);
        return new ResponseUser(user);
    }

    public ResponseUser removeFavorite(String id, User user){
        user.getFavorites().remove(id);
        userRepository.save(user);
        return new ResponseUser(user);
    }
}
