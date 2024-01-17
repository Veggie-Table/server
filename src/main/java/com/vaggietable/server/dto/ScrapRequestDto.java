package com.vaggietable.server.dto;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class ScrapRequestDto {
    private String email;
    private Long scrapId;
    private Long rId;
}
