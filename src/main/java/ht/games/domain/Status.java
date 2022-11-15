package ht.games.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long statusid;
	@NotEmpty
	private String statusQuo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
	@JsonIgnoreProperties("status")
	private List<Games> games;

	public Status() {
	}

	public Status(String statusQuo) {
		super();
		this.statusQuo = statusQuo;
	}

	public Long getStatusid() {
		return statusid;
	}

	public void setStatusId(Long statusid) {
		this.statusid = statusid;
	}

	public String getStatusQuo() {
		return statusQuo;
	}

	public void setStatusQuo(String statusQuo) {
		this.statusQuo = statusQuo;
	}

	public List<Games> getGames() {
		return games;
	}

	public void setGames(List<Games> games) {
		this.games = games;
	}
}