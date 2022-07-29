package ru.sshibko.testsite.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import ru.sshibko.testsite.model.entity.Place;
import ru.sshibko.testsite.model.entity.User;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class PlaceModel implements Serializable {

    private String country;

    private String region;

    private String city;

    //private List<User> userList;


    public PlaceModel(Place place) {
        if (place != null) {
            BeanUtils.copyProperties(this, place);
        }
    }

//    public String getName() {
//        return getCountry() + getRegion() + getCity();
//    }

    @Override
    public String toString() {
        return "PlaceModel{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
