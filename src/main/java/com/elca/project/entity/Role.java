package com.elca.project.entity;

import com.sun.istack.Nullable;
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
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="role_id")
    private Long roleId;

    @NotNull
    @Column(name="role_name")
    private String name;

    @NotNull
    @Column(name="role_description")
    private String description;
}