package com.lsalmeida.graphqldemo.mapper;

import com.lsalmeida.graphqldemo.records.Post;
import com.lsalmeida.graphqldemo.records.PostInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface PostMapper {

    @Mapping(target = "datePublished", expression = "java(publishedToday())")
    Post toPost(PostInput input);

    @Named(value = "publishedToday")
    default LocalDateTime publishedToday() {
        return LocalDateTime.now();
    }
}
