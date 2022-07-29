package ru.sshibko.testsite.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sshibko.testsite.model.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByLogin(String login);

    void deleteUserByLogin(String login);

//    User findById(Long id);

//    User findByPhoneNormal(String phone);


    String query1 = "SELECT * FROM users  WHERE "
            + "url LIKE %:keyword%"
            + " OR email LIKE %:keyword%"
            + " OR lastName LIKE %:keyword%"
            + " OR firstName LIKE %:keyword%";

    String query33 = "SELECT * FROM urlinformation WHERE url LIKE %:keyword%";
    String query34 = "SELECT * FROM urlinformation WHERE url LIKE %?1%";
    @Query(value = query1, nativeQuery = true)
    List<User> search(String keyword);
}
