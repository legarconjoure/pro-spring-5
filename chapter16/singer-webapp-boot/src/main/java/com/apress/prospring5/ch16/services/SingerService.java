package com.apress.prospring5.ch16.services;

import com.apress.prospring5.ch16.entities.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);
}
