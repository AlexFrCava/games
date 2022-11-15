package ht.games.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ht.games.domain.Games;
import ht.games.domain.GamesRepository;
import ht.games.domain.Genre;
import ht.games.domain.GenreRepository;
import ht.games.domain.StatusRepository;

@CrossOrigin
@Controller
public class GamesController {
	@Autowired
	private GamesRepository gamesRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private StatusRepository statusRepository;

	@RequestMapping(value = "/api/game,", method = RequestMethod.GET)
	public @ResponseBody List<Games> listGamesRest() {
		return (List<Games>) gamesRepository.findAll();
	}

	@RequestMapping(value = "/api/games/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Games> findGamesRest(@PathVariable("id") Long gamesId) {
		return gamesRepository.findById(gamesId);
	}

	@RequestMapping(value = "/api/game", method = RequestMethod.POST)
	public @ResponseBody Games saveGamesRest(@RequestBody Games games) {
		return gamesRepository.save(games);
	}

	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String homepage() {
		return "homepage";
	}

	@RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
	public String unauthorized() {
		return "unauthorized";
	}

	@RequestMapping(value = "/gamelist", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("game", gamesRepository.findAll());
		return "gamelist";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editGames(@PathVariable("id") Long gamesId, Model model) {
		model.addAttribute("games", gamesRepository.findById(gamesId));
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("statusses", statusRepository.findAll());
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
		model.addAttribute("statusses", statusRepository.findAll());
		return "add";
	}

	@RequestMapping(value = "/addGenre")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addGenre(Model model) {
		model.addAttribute("genre", new Genre());
		return "addGenre";
	}

	@PostMapping("/save")
	public String saveGame(@Valid Games games, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("games", games);
			model.addAttribute("genres", genreRepository.findAll());
			model.addAttribute("statusses", statusRepository.findAll());
			return "add";
		}
		gamesRepository.save(games);
		return "redirect:gamelist";
	}

	@PostMapping("/save2")
	public String saveGenre(@Valid Genre genre, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("genre", genre);
			return "addGenre";
		}
		genreRepository.save(genre);
		return "redirect:gamelist";
	}

}
