package com.ll.ticket.domain.admin.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadImageDto {
    private MultipartFile file;
}
