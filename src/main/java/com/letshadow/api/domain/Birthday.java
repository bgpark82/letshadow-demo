package com.letshadow.api.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Birthday {

    private int yearOfBirthday;
    private int monthOfBirthday;
    private int dayOfBirthday;

}
