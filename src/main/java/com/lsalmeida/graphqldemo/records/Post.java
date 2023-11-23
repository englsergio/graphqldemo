package com.lsalmeida.graphqldemo.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Post(
        @Id
        Long id,
        String url,
        String title,
        @NotNull
        String summary,
        @JsonProperty("date_published")
        LocalDateTime datePublished,
        @Version
        Integer version
) {
}
