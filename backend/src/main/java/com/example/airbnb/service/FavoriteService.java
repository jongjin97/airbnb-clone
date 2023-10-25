package com.example.airbnb.service;

import com.example.airbnb.config.auth.UserDetailsImpl;
import com.example.airbnb.document.Accommodation;
import com.example.airbnb.document.User;
import com.example.airbnb.dto.ResponseAccommodation;
import com.example.airbnb.dto.ResponseUser;
import com.example.airbnb.repository.AccommodationRepository;
import com.example.airbnb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final FileService fileService;
    @Transactional
    public ResponseUser addFavorite(String id, User user){
        user.getFavorites().add(id);
        userRepository.save(user);
        return new ResponseUser(user);
    }
    @Transactional
    public ResponseUser removeFavorite(String id, User user){
        user.getFavorites().remove(id);
        userRepository.save(user);
        return new ResponseUser(user);
    }
    @Transactional
    public List<ResponseAccommodation> getFavorites(User user) throws Exception {
        List<Accommodation> accommodationList = accommodationRepository.findAllById(user.getFavorites());
        List<ResponseAccommodation> responseAccommodationList = accommodationList.stream().map(ResponseAccommodation::new).toList();
        for(ResponseAccommodation responseAccommodation : responseAccommodationList){
            responseAccommodation.setImageByte(fileService.downloadFile(responseAccommodation.getImageSrc()));
        }


        return responseAccommodationList;
    }
}
