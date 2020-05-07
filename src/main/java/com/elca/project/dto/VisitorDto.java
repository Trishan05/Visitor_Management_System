package com.elca.project.dto;

import lombok.Data;

@Data
public class VisitorDto {
    private long visitorId;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNo;
    private String organisation;
}
