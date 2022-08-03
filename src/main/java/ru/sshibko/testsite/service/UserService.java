package ru.sshibko.testsite.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sshibko.testsite.common.Util;
import ru.sshibko.testsite.model.entity.User;
import ru.sshibko.testsite.model.repository.UserRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteByLogin (String login) {
        userRepository.deleteUserByLogin(login);
    }

    public User getByLogin(String login) {
/*        User user = null;
        if (login != null && !login.trim().isEmpty()) {
            user = userRepository.findByEmail(login);
            if (user == null)
                user = userRepository.findByLogin(login);
*//*            if (user == null) {
                user = userRepository.findByPhoneNormal(Util.normalizePhone(login));
            }*//*
        }*/
        User user = new User();
        user = userRepository.findByLogin(login);
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
/*        if (user == null)
            return;*/
        // TODO: remove after normalization of all currently existing users
        //user.setPhoneNormal(Util.normalizePhone(user.getPhoneNormal()));
        userRepository.save(user);
    }

    @Transactional
    public void saveFromRemovedServer(String sourcePath, Path target) {
        Gson gson = new Gson();
        List<String> list = getAllFilesToParse(sourcePath);
        for (String fileName : list) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                User user = gson.fromJson(reader, User.class);
                if (user != null) {
                    user.setId(user.getId() + 1000);
                    userRepository.save(user);
                } else {
                    //TODO write\copy jsonentity from file to Special Directory
//                try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
//
//                }
                moveFileToSpecialDir(target, sourcePath);
                //TODO write log about it


                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        Optional<User> user = userRepository.findById(id);
//        return user.isPresent() ? user.get() : null;
    }

    public List<String> getAllFilesToParse(String sourcePath) {
        try (Stream<Path> walk = Files.walk(Paths.get(sourcePath))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(f -> f.toString()).collect(Collectors.toList());

            //result.forEach(System.out::println);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void moveFileToSpecialDir(Path target, String sourcePath) {
        try {
            Files.move(target, Paths.get(sourcePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
