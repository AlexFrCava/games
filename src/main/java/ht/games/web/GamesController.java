package ht.games.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ht.games.domain.Games;
import ht.games.domain.GamesRepository;
import ht.games.domain.GenreRepository;

@Controller
public class GamesController {
	@Autowired
	private GamesRepository gamesRepository;

	@Autowired
	private GenreRepository genreRepository;

	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String homepage() {
		return "homepage";
	}

	@RequestMapping(value = "/gamelist", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("game", gamesRepository.findAll());
		return "gamelist";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editGames(@PathVariable("id") Long gamesId, Model model) {
		model.addAttribute("game", gamesRepository.findById(gamesId));
		return "editGames";
	}

	@RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
	public String deleteGames(@PathVariable("id") Long gamesId, Model model) {
		gamesRepository.deleteById(gamesId);
		return "redirect:gamelist";

	}

	@RequestMapping(value = "/add")
	public String addGames(Model model) {
		model.addAttribute("games", new Games());
		model.addAttribute("genres", genreRepository.findAll());
		return "add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Games games) {
		gamesRepository.save(games);
		return "redirect:gamelist";
	}
}