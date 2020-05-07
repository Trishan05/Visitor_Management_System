package com.elca.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="role_id")
    private Long roleId;

    @Column(name="role_name")
    private String name;

    @Column(name="role_description")
    private String description;
}