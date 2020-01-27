package com.letshadow.api.domain;

import javafx.beans.DefaultProperty;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@Where(clause = "deleted = false")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    private int age;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    private String phoneNumber;

    @ColumnDefault("0")
    private boolean deleted;


    public Person(PersonDTO dto){
        if(!StringUtils.isEmpty(dto.getName())) this.name = dto.getName();
        if(!StringUtils.isEmpty(dto.getAddress())) this.address = dto.getAddress();
        if(!StringUtils.isEmpty(dto.getBirthday())) this.birthday = Birthday.of(dto.getBirthday());
        if(!StringUtils.isEmpty(dto.getJob())) this.job = dto.getJob();
        if(!StringUtils.isEmpty(dto.getPhoneNumber())) this.phoneNumber = dto.getPhoneNumber();
    }

    public void set(PersonDTO dto){
        if(!StringUtils.isEmpty(dto.getName())) this.name = dto.getName();
        if(!StringUtils.isEmpty(dto.getAddress())) this.address = dto.getAddress();
        if(!StringUtils.isEmpty(dto.getBirthday())) this.birthday = Birthday.of(dto.getBirthday());
        if(!StringUtils.isEmpty(dto.getJob())) this.job = dto.getJob();
        if(!StringUtils.isEmpty(dto.getPhoneNumber())) this.phoneNumber = dto.getPhoneNumber();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    public static class PersonDTO {

        private String name;
        private String address;
        private LocalDate birthday;
        private String job;
        private String phoneNumber;
    }
}
