package com.casadelacultura.casadelacultura.entity.vm;

import lombok.*;

@Data
@AllArgsConstructor
public class Asset {
    private byte[] content;
    private String contentType;
}
