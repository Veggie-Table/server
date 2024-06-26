package com.vaggietable.server.dto;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class BookmarkRequestDto {
    private String username;
    private Long bookmarkId;
    private Long rId;
}
