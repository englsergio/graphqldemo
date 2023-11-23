package com.lsalmeida.graphqldemo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectReader;
import com.lsalmeida.graphqldemo.records.Post;
import com.lsalmeida.graphqldemo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class Bootstrap implements CommandLineRunner {

    private final ObjectReader reader;
    private final PostRepository repository;

    @Override
    public void run(String... args) {

        if (repository.count() == 0) {
            try(InputStream dataInputStream = this.getClass().getResourceAsStream("/data/posts.json")) {
                ObjectReader readerForType = reader.forType(new TypeReference<List<Post>>() {});
                List<Post> postList = readerForType.readValue(dataInputStream);
                log.info("{} posts lidos do arquivo posts.json", postList.size());
                repository.saveAll(postList);
            } catch (IOException exception) {
                throw new RuntimeException("Não foi possível ler arquivo json.");
            }
        }

    }
}
