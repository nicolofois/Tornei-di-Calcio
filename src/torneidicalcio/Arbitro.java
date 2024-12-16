package torneidicalcio;

public class Arbitro extends Tesserato{
	private String sezione;

	public Arbitro(String nome, String cognome, String sezione) {
		super(nome,cognome);
		this.sezione = sezione;
	}

	public String getSezione(){
		return this.sezione;
	}

}
