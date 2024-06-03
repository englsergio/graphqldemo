package com.lsalmeida.graphqldemo.repository;

import com.lsalmeida.graphqldemo.records.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ListCrudRepository<Post, Long> {
}
