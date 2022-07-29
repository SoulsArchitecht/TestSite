package ru.sshibko.testsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sshibko.testsite.common.Util;
import ru.sshibko.testsite.model.entity.User;
import ru.sshibko.testsite.model.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByLogin(String login) {
        User user = null;
        if (login != null && !login.trim().isEmpty()) {
            user = userRepository.findByEmail(login);
            if (user == null)
                user = userRepository.findByLogin(login);
/*            if (user == null) {
                user = userRepository.findByPhoneNormal(Util.normalizePhone(login));
            }*/
        }
        return user;
    }

//    public User getUserByPhone(String phone) {
//        return userRepository.findByPhoneNormal(Util.normalizePhone(phone));
//    }

    public User getById(Long id) {
        if (id == null)
            return null;
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? user.get() : null;
    }

    @Transactional
    public void save(User user) {
        if (user == null)
            return;
        // TODO: remove after normalization of all currently existing users
        //user.setPhoneNormal(Util.normalizePhone(user.getPhoneNormal()));
        userRepository.save(user);
    }

    public List<User> userList() {
        return userRepository.findAll();
    }

    public void delete (Long id) {
        if (id != null) {
            userRepository.deleteById(id);
        }
    }

    public List<User> search (String keyword) {
        return userRepository.search(keyword);
    }

}
