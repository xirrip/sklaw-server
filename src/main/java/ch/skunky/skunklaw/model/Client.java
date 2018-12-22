package ch.skunky.skunklaw.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String nickName;
    private String firstName;
    private String middleName;
    private String lastName;

    private String address;
    private String city;
    private String zip;
    private String country;

    private String phoneNumber;
    private String email;

    @ManyToMany(
        mappedBy = "clients",
        cascade = CascadeType.ALL
    )

    private List<LawCase> cases;
}
