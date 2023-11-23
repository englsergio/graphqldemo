package com.lsalmeida.graphqldemo.controller;

import com.lsalmeida.graphqldemo.mapper.PostMapper;
import com.lsalmeida.graphqldemo.records.Comment;
import com.lsalmeida.graphqldemo.records.Post;
import com.lsalmeida.graphqldemo.records.PostInput;
import com.lsalmeida.graphqldemo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClient;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostRepository repository;
    private final RestClient client = RestClient.create();
    private final PostMapper mapper;

//    @SchemaMapping(typeName = "Query", value = "findAllPosts")
    @QueryMapping
    public Iterable<Post> findAllPosts() {
        return repository.findAll();
    }

    @QueryMapping
    public Iterable<Post> findPostById(@Argument Long id) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível encontrar um post com o id: " + id));
        return new ArrayList<>(Collections.singletonList(post));
    }

    @SchemaMapping
    List<Comment> comments(Post post) {
        log.info("Obtendo comentários do post: {}", post.title());
        List<Comment> comments = client.get()
                .uri("https://jsonplaceholder.typicode.com/comments")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        if (comments == null) throw new RuntimeException("Não foi possível obter os comentários.");
        return comments.stream().filter(c -> c.postId().equals(post.id())).collect(Collectors.toList());
    }

    @MutationMapping
    Post createPost(@Argument PostInput input) {
        Post post = mapper.toPost(input);
        return repository.save(post);
    }

    @MutationMapping
    Boolean deletePost(@Argument Long id) {
        repository.deleteById(id);
        return true;
    }

}
