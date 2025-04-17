package com.ttcs.storyclone.service;

import com.ttcs.storyclone.repository.GenreRepository;
import com.ttcs.storyclone.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre findById(Long id) {
        Optional<Genre> result = genreRepository.findById(id);
        Genre genre = null;
        if (result.isPresent()) {
            genre = result.get();
        } else {
            throw new RuntimeException("Genre not found");
        }
        return genre;
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void delete(Genre genre) {
        genreRepository.delete(genre);
    }
}
