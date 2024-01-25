package com.vaggietable.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ScrapList {
    @Id
    private Long scrapId;
    private String email;
    private List<Restaurant> rId;
}
