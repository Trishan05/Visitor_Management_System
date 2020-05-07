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
@Table(name="candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name="candidate_id")
    private Long candidateId;

    @NotNull
    @Column(name="position_applied")
    private String postApplied;

    @ManyToOne
    @JoinColumn(name="visitor_id", referencedColumnName = "visitor_id")
    private Visitor visitor;

}