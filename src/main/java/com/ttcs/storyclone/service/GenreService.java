package com.ttcs.storyclone.service;

import com.ttcs.storyclone.model.Genre;

import java.util.List;

public interface GenreService {
    Genre findById(Long id);
    List<Genre> findAll();
    Genre save(Genre genre);
    void delete(Genre genre);
}
