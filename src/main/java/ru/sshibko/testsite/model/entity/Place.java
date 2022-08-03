package ru.sshibko.testsite.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "places")
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place", fetch = FetchType.LAZY)
    private List<User> userList;
}
