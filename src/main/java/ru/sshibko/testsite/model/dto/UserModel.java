package ru.sshibko.testsite.model.dto;

import org.springframework.beans.BeanUtils;
import ru.sshibko.testsite.model.entity.User;

import java.io.Serializable;

public class UserModel implements Serializable {

    private Long id;

    private String login;

    private String email;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    private String phone;

    private PlaceModel place;

    private Boolean active;

    public UserModel(User user) {
        if (user == null) {
            return;
        }

        BeanUtils.copyProperties(this, user);
    }
}
