package torneidicalcio;

public class Tesserato {
	protected String nome;
	protected String cognome;

	public Tesserato(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCognome() {
		return this.cognome;
	}
}
