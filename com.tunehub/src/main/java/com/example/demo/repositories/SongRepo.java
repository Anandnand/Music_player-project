package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Song;

public interface SongRepo extends JpaRepository<Song, Integer>{

public Song findByName(String name);

}
