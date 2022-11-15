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
import ht.games.domain.Status;
import ht.games.domain.StatusRepository;
import ht.games.domain.User;
import ht.games.domain.UserRepository;

@SpringBootApplication
public class GamesApplication {
	private static final Logger log = LoggerFactory.getLogger(GamesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GamesApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(GamesRepository gamesRepository, GenreRepository genreRepository,
			UserRepository urepository, StatusRepository statusRepository) {
		return (args) -> {
			log.info("save games");
			genreRepository.save(new Genre("FPS"));
			genreRepository.save(new Genre("MMORPG"));

			statusRepository.save(new Status("plan on playing"));
			statusRepository.save(new Status("currently playing"));
			statusRepository.save(new Status("on break"));
			statusRepository.save(new Status("dropped"));

			User user1 = new User("user_test", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("premium_test", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("save games");
			gamesRepository.save(new Games("Fortnite", 2, 2017, 6, genreRepository.findByName("FPS").get(0),
					statusRepository.findByStatusQuo("dropped").get(0)));
			gamesRepository.save(new Games("Final Fantasy XIV", 2354, 2014, 9,
					genreRepository.findByName("MMORPG").get(0), statusRepository.findByStatusQuo("on break").get(0)));
		};
	}
}
