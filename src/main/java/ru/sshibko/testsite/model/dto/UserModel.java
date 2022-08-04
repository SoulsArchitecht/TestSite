package ru.sshibko.testsite.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import ru.sshibko.testsite.model.entity.Place;
import ru.sshibko.testsite.model.entity.User;
import ru.sshibko.testsite.service.PlaceService;

import java.io.Serializable;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@ToString
public class UserModel implements Serializable {

    private String login;

    private String email;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    private String phone;

    private String place;

    private Boolean active;


    public UserModel(User user) {
        if (user == null) {
            return;
        }

        BeanUtils.copyProperties(this, user);
    }

    public String getPlace(Place place) {
        if (place != null) {
            return place.getCity() + ", " + place.getRegion() + ", " + place.getCountry();
        } else {
            return "Place not available";
        }

    }
}
