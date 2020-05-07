package com.elca.project.exporttoexcel;

import lombok.Data;

@Data
public class ExcelDto {
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNo;
    private String organisation;

    public ExcelDto(String firstName, String lastName, String email, Long phoneNo, String organisation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.organisation = organisation;
    }
}
