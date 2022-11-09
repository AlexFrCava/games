package ht.games;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ht.games.domain.Games;
import ht.games.domain.GamesRepository;
import ht.games.domain.Genre;
import ht.games.domain.GenreRepository;

@SpringBootApplication
public class GamesApplication {
	private static final Logger log = LoggerFactory.getLogger(GamesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GamesApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(GamesRepository gamesRepository, GenreRepository genreRepository) {
		return (args) -> {
			log.info("save games");
			genreRepository.save(new Genre("FPS"));
			genreRepository.save(new Genre("MMORPG"));

			log.info("save games");
			gamesRepository.save(new Games("testgame", 999, 1998, 7, genreRepository.findByName("FPS").get(0)));
		};
	}
}
