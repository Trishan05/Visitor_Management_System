package com.elca.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="visitortype")
public class VisitorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitortype_id")
    private Long visitorTypeId;

    @NotNull
    @Column(name = "visitortype_name")
    private String name;

    @NotNull
    @Column(name = "visitortype_description")
    private String description;
}