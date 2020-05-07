package com.elca.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="interview")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="interview_id")
    private Long interviewId;

    @Basic(optional = false)
    private String interviewer;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column
    private LocalDate date;

    @JsonFormat(pattern = "HH.mm", shape = JsonFormat.Shape.STRING)
    @Column
    private LocalTime time;

    @Column
    private String status;

    @Column
    private String step;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id")
    private Candidate candidate;
}