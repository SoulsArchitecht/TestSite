package ru.sshibko.testsite.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import ru.sshibko.testsite.model.entity.Place;
import ru.sshibko.testsite.model.entity.User;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Data
public class PlaceModel {

    private Long id;

    private String country;

    private String region;

    private String city;

    private List<User> userList;


    public PlaceModel(Place place) {
        if (place != null) {
            BeanUtils.copyProperties(this, place);
        }
    }

}
