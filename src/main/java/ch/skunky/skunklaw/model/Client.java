package ch.skunky.skunklaw.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
