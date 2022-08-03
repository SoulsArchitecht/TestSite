package ru.sshibko.testsite.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sshibko.testsite.model.entity.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    //Place findById();

    Place findByCity(String city);

    void deletePlaceByCity(String city);



}
