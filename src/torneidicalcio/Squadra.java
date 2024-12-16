package torneidicalcio;

public class Squadra {
	private String nome;
	private String citta;
	private int anno;
	private String stadio;

	public Squadra(String nome, String citta, int anno, String stadio) {
		this.nome = nome;
		this.citta = citta;
		this.anno = anno;
		this.stadio = stadio;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCitta() {
		return this.citta;
	}

	public int getAnno() {
		return this.anno;
	}

	public String getStadio() {
		return this.stadio;
	}
}
