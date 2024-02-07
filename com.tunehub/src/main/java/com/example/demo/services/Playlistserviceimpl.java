package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Playlist;
import com.example.demo.repositories.PlaylistRepo;

@Service
public class Playlistserviceimpl implements PlaylistService {
    @Autowired
	PlaylistRepo pr;

	@Override
	public void addPlaylist(Playlist playlists) {
	pr.save(playlists);
	}

	@Override
	public List<Playlist> fetchAllPlaylist() {
		return pr.findAll();
	}

}
