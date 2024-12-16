package torneidicalcio;

public class Calciatore extends Tesserato{
	private Squadra squadra;
	private String ruolo;
	private int numeroMaglia;

	public Calciatore(String nome, String cognome, Squadra squadra, String ruolo, int numeroMaglia) {
		super(nome,cognome);
		this.squadra = squadra;
		this.ruolo = ruolo;
		this.numeroMaglia = numeroMaglia;
	}

	public Squadra getSquadra() {
		return this.squadra;
	}

	public String getRuolo() {
		return this.ruolo;
	}

	public int getNumeroMaglia() {
		return this.numeroMaglia;
	}
	
}

