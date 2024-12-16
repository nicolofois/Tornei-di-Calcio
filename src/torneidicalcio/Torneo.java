package torneidicalcio;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Torneo {
	private String nome;
	private int numeroSquadre;
	private LinkedList<Squadra> squadreIscritte;

	public Torneo(String nome, int numeroSquadre) {
		this.nome = nome;
		this.numeroSquadre = numeroSquadre;
		this.squadreIscritte = new LinkedList<>();
	}

	public String getNome() {
		return this.nome;
	}

	public int getNumeroSquadre() {
		return this.numeroSquadre;
	}

	public List<Squadra> getSquadreIscritte() {
		return squadreIscritte.stream().sorted(Comparator.comparing(Squadra::getNome)).toList();
	}

	public void iscrivi(Squadra s) {
		this.squadreIscritte.add(s);
	}

}
