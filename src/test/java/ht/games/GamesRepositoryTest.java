package ht.games;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ht.games.domain.Games;
import ht.games.domain.GamesRepository;
import ht.games.domain.GenreRepository;
import ht.games.domain.StatusRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GamesRepositoryTest {
	@Autowired
	private GamesRepository arepository;
	@Autowired
	private GenreRepository brepository;
	@Autowired
	private StatusRepository crepository;

	@Test
	public void findByTitleShouldReturnGames() {
		List<Games> games = arepository.findByTitle("Fortnite");

		assertThat(games).hasSize(1);
		assertThat(games.get(0).getHoursPlayed()).isEqualTo(2);
	}

	@Test
	public void createNewGame() {
		Games games = new Games("call of duty", 12, 2012, 7, brepository.findByName("FPS").get(0),
				crepository.findByStatusQuo("dropped").get(0));
		arepository.save(games);
		assertThat(games.getId()).isNotNull();
	}

	@Test
	public void deleteGames() {

		arepository.delete(arepository.findByTitle("Fortnite").get(0));
		List<Games> games = arepository.findByTitle("Fortnite");
		assertThat(games).hasSize(0);
	}
}
