package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PlaylistController {
	@Autowired
	SongService ss;
	@Autowired
	PlaylistService ps;

	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		List<Song> songList = ss.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createPlaylist";
	}

	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlists) {
		ps.addPlaylist(playlists);
		List<Song> songLists = playlists.getSongs();
		for (Song s : songLists) {
			s.getPlaylists().add(playlists);
			ss.updateSong(s);
		}
		return "adminHome";
	}
	@GetMapping("/viewPlaylist")
	public String viewPlaylist(Model model) {
	List<Playlist> allPlaylists=ps.fetchAllPlaylist();
	model.addAttribute("allPlaylists", allPlaylists);
		return "displayPlaylist";
	}
	

}
