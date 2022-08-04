package ru.sshibko.testsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sshibko.testsite.model.entity.Place;
import ru.sshibko.testsite.model.entity.User;
import ru.sshibko.testsite.model.repository.PlaceRepository;

import java.util.List;
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

    public List<Place> placeList() {
        return placeRepository.findAll();
    }

    public Place getPlaceByCity(String city) {
/*        if (city == null) {
            return null;
        } else {
            Optional<Place> place = Optional.ofNullable(placeRepository.findByCity(city));
            return place.isPresent() ? place.get() : null;
        }*/
        if (city == null) {
            return null;
        }
        Optional<Place> place = Optional.ofNullable(placeRepository.findByCity(city));
        return place.orElse(null);
    }

    public Place getPlaceById(Long id) {
        if (id == null) {
            return null;
        }
        Optional<Place> place =placeRepository.findById(id);
        return place.orElse(null);
    }

    public void deleteByCity (String city) {
        if (city != null) {
            placeRepository.deletePlaceByCity(city);
        }
    }

}
