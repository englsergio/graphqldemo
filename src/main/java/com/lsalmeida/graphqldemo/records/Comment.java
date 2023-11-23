package com.lsalmeida.graphqldemo.records;

public record Comment(
        Long id,
        Long postId,
        String name,
        String email,
        String body
) {
}
