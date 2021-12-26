package jpabook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable // JPA의 내장타입임을 의미함.
@Getter @Setter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
