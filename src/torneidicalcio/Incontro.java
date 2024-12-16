package torneidicalcio;

public class Incontro {
	private String nomeTorneo;
	private int giornata;
	private Squadra squadraCasa;
	private Squadra squadraOspite;
	private int numeroGolSquadraCasa;
	private int numeroGolSquadraOspite;
	private Arbitro arbitro;

	public Incontro(String nomeTorneo, int giornata, Squadra squadraCasa, Squadra squadraOspite, int numeroGolSquadraCasa,
			int numeroGolSquadraOspite, Arbitro arbitro) {
		this.nomeTorneo = nomeTorneo;
		this.giornata = giornata;
		this.squadraCasa = squadraCasa;
		this.squadraOspite = squadraOspite;
		this.numeroGolSquadraCasa = numeroGolSquadraCasa;
		this.numeroGolSquadraOspite = numeroGolSquadraOspite;
		this.arbitro = arbitro;
	}

	public String getNomeTorneo() {
		return this.nomeTorneo;
	}

	public int getGiornata() {
		return this.giornata;
	}

	public Squadra getSquadraCasa() {
		return this.squadraCasa;
	}

	public Squadra getSquadraOspite() {
		return this.squadraOspite;
	}

	public int getNumeroGolSquadraCasa() {
		return this.numeroGolSquadraCasa;
	}

	public int getNumeroGolSquadraOspite() {
		return this.numeroGolSquadraOspite;
	}

	public String getArbitro() {
		return "(" + this.arbitro.getNome() + " " + this.arbitro.getCognome() + ", " + this.arbitro.getSezione() + ")";
	}

	public int getDifferenzaReti() {
		if (this.numeroGolSquadraCasa >= this.numeroGolSquadraOspite) {
			return this.numeroGolSquadraCasa - this.numeroGolSquadraOspite;
		}
		else {
			return this.numeroGolSquadraOspite - this.numeroGolSquadraCasa;
		}
	}

}
