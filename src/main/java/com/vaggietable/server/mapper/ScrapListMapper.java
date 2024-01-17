package com.vaggietable.server.mapper;
import org.apache.ibatis.annotations.Mapper;
public interface ScrapListMapper {
    public String findScrapIdByEmail(String email);
}
