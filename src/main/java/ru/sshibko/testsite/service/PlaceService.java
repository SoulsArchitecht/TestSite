package ru.sshibko.testsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sshibko.testsite.model.entity.Place;
import ru.sshibko.testsite.model.entity.User;
import ru.sshibko.testsite.model.repository.PlaceRepository;

import java.util.Optional;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public void savePlace(Place place) {
        placeRepository.save(place);
    }

    public Place getPlaceByCity(String city) {
/*        if (city == null) {
            return null;
        } else {
            Optional<Place> place = Optional.ofNullable(placeRepository.findByCity(city));
            return place.isPresent() ? place.get() : null;
        }*/
        Place place = new Place();
        place = placeRepository.findByCity(city);
        return place;
    }

    public void deleteByCity (String city) {
        if (city != null) {
            placeRepository.deletePlaceByCity(city);
        }
    }

}
