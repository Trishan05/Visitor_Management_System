package com.elca.project.entity;

import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name="visitor")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="visitor_id")
    private Long visitorId;

    @NotNull
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Column
    private String email;

    @Nullable
    @Column(name="phone_no")
    private Long phoneNo;

    @Nullable
    @Column(name="organisation")
    private String organisation;

}

