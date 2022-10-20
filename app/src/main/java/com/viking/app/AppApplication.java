package com.viking.app;
import com.viking.app.model.Genre;
import com.viking.app.model.Playlist;
import com.viking.app.repository.GenreRepository;
import com.viking.app.repository.PlaylistRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AppApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}	

	@Autowired
	PlaylistRepository playlistRepository;

	@Autowired
	GenreRepository genreRepository;
	
	String name = "Eagles - Hotel California";
	String style = "Rock";
	String link = "https://www.youtube.com/embed/3394H9tGsoQ";
	LocalDateTime date = LocalDateTime.now().plusDays(-7);

	Playlist playlist = new Playlist(name, link, style, date);
	Genre genre = new Genre("all");
		
	

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("###########################################");
		System.out.println("##  PLAYLIST - API RESTFULL WITH MYSQL!  ##");
		System.out.println("###########################################");
		//this.playlistRepository.save(playlist);
		//this.genreRepository.save(genre);
		
	}

}
