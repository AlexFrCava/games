package ht.games;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ht.games.web.GamesController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GamesApplicationTests {

	@Autowired
	private GamesController gamesController;

	@Test
	public void contextLoads() {
		assertThat(gamesController).isNotNull();
	}

}
