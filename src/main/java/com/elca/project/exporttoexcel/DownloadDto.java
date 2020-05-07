package com.elca.project.exporttoexcel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DownloadDto {
    private String name;
    private byte[] file;
}
