package ch.skunky.skunklaw.dto.law;

import lombok.Data;

/**
 * https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
 */
@Data
public class LawClientDto {
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
