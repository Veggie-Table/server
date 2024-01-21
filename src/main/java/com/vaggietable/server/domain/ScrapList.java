package com.vaggietable.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScrapList {
    private String email;
    private Long scrapId;
    private Long rId;
}
