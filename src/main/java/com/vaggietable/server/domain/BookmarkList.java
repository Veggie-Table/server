package com.vaggietable.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkList {
    @Id
    private Long bookmarkId;
    private String username;
    private List<Restaurant> rId;
    private boolean status; //false -> 북마크 해제 / true -> 북마크 추가
}
