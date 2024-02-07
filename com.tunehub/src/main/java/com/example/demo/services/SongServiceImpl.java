package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Song;
import com.example.demo.repositories.SongRepo;

@Service
public class SongServiceImpl implements SongService {
	@Autowired
      SongRepo sp;
	public SongServiceImpl(SongRepo sp) {
		super();
		this.sp = sp;
	}


	@Override
	public void addSong(Song song) {
		sp.save(song);
	}


	@Override
	public List<Song> fetchAllSongs() {
		return sp.findAll();
	}


	@Override
	public boolean songExists(String name) {
	Song song=sp.findByName(name);
		if(song==null) {
			return false;
		}else {
			return true;
		}
	}


	@Override
	public void updateSong(Song song) {
		sp.save(song);
	}
	
}
