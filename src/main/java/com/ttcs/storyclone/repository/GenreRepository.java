package com.ttcs.storyclone.repository;

import com.ttcs.storyclone.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
