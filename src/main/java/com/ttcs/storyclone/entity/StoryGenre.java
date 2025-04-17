package com.ttcs.storyclone.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "story_genre")
@Data
public class StoryGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}