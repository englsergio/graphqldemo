package com.lsalmeida.graphqldemo.records;

public record PostInput(
        String title,
        String summary,
        String url
) {
}
