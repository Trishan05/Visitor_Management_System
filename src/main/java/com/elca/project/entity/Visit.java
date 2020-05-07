package com.elca.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="visit_id")
    private Long visitId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column
    private LocalDate date;

    @NotNull
    @JsonFormat(pattern = "HH.mm", shape = JsonFormat.Shape.STRING)
    @Column(name="time_in")
    private LocalTime timeIn;

    @JsonFormat(pattern = "HH.mm", shape = JsonFormat.Shape.STRING)
    @Column(name="time_out")
    @Nullable
    private LocalTime timeOut;

    @ManyToOne
    @JoinColumn(name="visitor_id", referencedColumnName = "visitor_id")
    private Visitor visitor;

    @ManyToOne
    @JoinColumn(name="visitortype_id", referencedColumnName = "visitortype_id")
    private VisitorType visitorType;

    @ManyToOne
    @JoinColumn(name="badge_id", referencedColumnName = "badge_id")
    private Badge badge;
}
