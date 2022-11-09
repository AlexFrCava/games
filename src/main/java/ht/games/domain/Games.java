package ht.games.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Games {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private int hoursPlayed;
	private int releaseYear;
	private int personalScore;

	@ManyToOne
	@JoinColumn(name = "genreid")
	private Genre genre;

	public Games() {
	}

	public Games(String title, int hoursPlayed, int releaseYear, int personalScore, Genre genre) {
		super();
		this.title = title;
		this.hoursPlayed = hoursPlayed;
		this.releaseYear = releaseYear;
		this.personalScore = personalScore;
		this.genre = genre;

	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getHoursPlayed() {
		return hoursPlayed;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public int getPersonalScore() {
		return personalScore;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setHoursPlayed(int hoursPlayed) {
		this.hoursPlayed = hoursPlayed;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public void setPersonalScore(int personalScore) {
		this.personalScore = personalScore;
	}

	@Override
	public String toString() {
		return "Games [id=" + id + ",title" + title + ", hours played" + hoursPlayed + ", release year" + releaseYear
				+ ", personal score" + personalScore + "]";
	}
}