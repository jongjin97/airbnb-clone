package com.example.airbnb.repository.template;

import com.example.airbnb.document.Accommodation;

import java.util.List;
import java.util.Map;

public interface AccommodationTemplate {

    List<Accommodation> findAllByParam(Map<String,String> accommodationParam);
}
