package com.ttcs.storyclone.repository;

import com.ttcs.storyclone.model.StoryGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryGenreRepository extends JpaRepository<StoryGenre, Long> {
}
