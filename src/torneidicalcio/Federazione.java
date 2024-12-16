package torneidicalcio;

import java.util.*;

public class Federazione {
	private String sigla;
	private String denominazione;
	private String sitoWeb;
	private TreeMap<String, Torneo> tornei = new TreeMap<>();  
	private LinkedList<Torneo> torneiList = new LinkedList<>();
	private TreeMap<String, Squadra> squadre = new TreeMap<>(); 
	private TreeMap<Integer,Tesserato> tesserati = new TreeMap<>();
	private TreeMap<String,Tesserato> tesseratiPerNome = new TreeMap<>();
	private int numTesserato = 1000;
	private LinkedList<Incontro> incontri = new LinkedList<>();


	public Federazione(String sigla, String denominazione, String sitoWeb){
		this.sigla = sigla;
		this.denominazione = denominazione;
		this.sitoWeb = sitoWeb;
	}
	
	public String getSigla() {
		return this.sigla;
	}

	public String getDenominazione() {
		return this.denominazione;
	}

	public String getSitoWeb() {
		return this.sitoWeb;
	}

	public Torneo nuovoTorneo(String nomeTorneo, int numeroSquadre){
		if (tornei.containsKey(nomeTorneo)) {
			return tornei.get(nomeTorneo);
		}
		Torneo t = new Torneo(nomeTorneo, numeroSquadre);
		tornei.put(nomeTorneo, t);
		torneiList.add(t);
		return t;
	}

	public void nuovaSquadra(String nome, String citta, int anno, String stadio){
		if (!squadre.containsKey(nome)) {
			squadre.put(nome, new Squadra(nome,citta,anno,stadio));
		}
	}

	public Squadra cercaSquadra(String nomeSquadra){
		return squadre.get(nomeSquadra);
	}

	public Collection<Torneo> elencoTornei(){
		return torneiList;
	}

	public Collection<Squadra> elencoSquadre(){
		return squadre.values().stream()
							   .sorted(Comparator.comparing(Squadra::getNome))
							   .toList();
	}
	
	public void iscriviSquadraTorneo(String nomeTorneo, String nomeSquadra){
		if (tornei.containsKey(nomeTorneo) && squadre.containsKey(nomeSquadra)) {
			Squadra s = squadre.get(nomeSquadra);
			Torneo t = tornei.get(nomeTorneo);
			if (!(t.getSquadreIscritte().size()+1 > t.getNumeroSquadre())) {
				t.iscrivi(s);
			}
		}
	}

	public Collection<Squadra> elencoSquadreTorneo(String nomeTorneo){
		if (tornei.containsKey(nomeTorneo)) {
			Torneo t = tornei.get(nomeTorneo);
			return t.getSquadreIscritte();
		}
		return null;
	}
	
	public int tesseramento(String nome, String cognome, String nomeSquadra, String ruolo) throws EccezioneErroreDatiTesseramento{
		if (nome == null || cognome == null || nomeSquadra == null || ruolo == null) {
			throw new EccezioneErroreDatiTesseramento();
		}
		if (nome == "" || cognome == "" || nomeSquadra == "" || ruolo == "") {
			throw new EccezioneErroreDatiTesseramento();
		}
		if (squadre.containsKey(nomeSquadra) && !tesseratiPerNome.containsKey(nome + " " + cognome)) {
			Dirigente d = new Dirigente(nome, cognome, squadre.get(nomeSquadra), ruolo);
			tesserati.put(numTesserato, d);
			tesseratiPerNome.put(nome + " " + cognome, d);
			return numTesserato++;
		}
		return 0;
	}

	public int tesseramento(String nome, String cognome, String nomeSquadra, String ruolo, int numeroMaglia) throws EccezioneErroreDatiTesseramento{
		if (nome == null || cognome == null || nomeSquadra == null || ruolo == null || numeroMaglia < 0) {
			throw new EccezioneErroreDatiTesseramento();
		}
		if (nome == "" || cognome == "" || nomeSquadra == "" || ruolo == "") {
			throw new EccezioneErroreDatiTesseramento();
		}
		if (squadre.containsKey(nomeSquadra) && !tesseratiPerNome.containsKey(nome + " " + cognome)) {
			Calciatore c = new Calciatore(nome, cognome, squadre.get(nomeSquadra), ruolo, numeroMaglia);
			tesserati.put(numTesserato,c);
			tesseratiPerNome.put(nome + " " + cognome, c);
			return numTesserato++;
		}
		return 0;
	}
	
	public int tesseramento(String nome, String cognome, String sezione) throws EccezioneErroreDatiTesseramento{
		if (nome == null || cognome == null || sezione == null) {
			throw new EccezioneErroreDatiTesseramento();
		}
		if (nome == "" || cognome == "" || sezione == "") {
			throw new EccezioneErroreDatiTesseramento();
		}
		if (!tesseratiPerNome.containsKey(nome + " " + cognome)) {
			Arbitro a = new Arbitro(nome, cognome, sezione);
			tesserati.put(numTesserato, a);
			tesseratiPerNome.put(nome + " " + cognome, a);
			return numTesserato++;
		}
		return 0;
	}
	
	public Tesserato cercaTesseratoPerNumeroTessera(int numeroTessera) throws EccezioneTesseratoInesistente{
		if (tesserati.containsKey(numeroTessera)) {
			return tesserati.get(numeroTessera);
		}
		throw new EccezioneTesseratoInesistente();
	}

	public Tesserato cercaTesseratoPerNomeCognome(String nome, String cognome) throws EccezioneTesseratoInesistente{
		if (tesseratiPerNome.containsKey(nome + " " + cognome)) {
			return tesseratiPerNome.get(nome + " " + cognome);
		}
		throw new EccezioneTesseratoInesistente();
	}
	
	public Collection<Tesserato> elencoTesseratiSquadra(String nomeSquadra){
		LinkedList<Tesserato> tes = new LinkedList<>();
		List<Calciatore> calc = tesserati.values().stream()
												  .filter(t -> t instanceof Calciatore)
												  .map(t -> (Calciatore)t)
												  .sorted(Comparator.comparing(Calciatore::getNome)
												  .thenComparing(Calciatore::getCognome))
												  .toList();
		List<Dirigente> dir = tesserati.values().stream()
												  .filter(t -> t instanceof Dirigente)
												  .map(t -> (Dirigente)t)
												  .sorted(Comparator.comparing(Dirigente::getNome)
												  .thenComparing(Dirigente::getCognome))
												  .toList();
		tes.addAll(dir);
		tes.addAll(calc);
		return tes;
	}
	
	public Incontro nuovoIncontro(String nomeTorneo, int giornata, String nomeSquadraCasa, String nomeSquadraOspite, String risultato, String nomeArbitro, String cognomeArbitro){
		if (squadre.containsKey(nomeSquadraCasa) && 
			squadre.containsKey(nomeSquadraOspite) && 
			tornei.containsKey(nomeTorneo) && 
			tesseratiPerNome.containsKey(nomeArbitro + " " + cognomeArbitro)) {
			String e[] = risultato.split("-");
			int risCasa = Integer.parseInt(e[0]);
			int risOspite = Integer.parseInt(e[1]);
			Incontro i = new Incontro(nomeTorneo, giornata, squadre.get(nomeSquadraCasa), squadre.get(nomeSquadraOspite), risCasa, risOspite, (Arbitro)tesseratiPerNome.get(nomeArbitro + " " + cognomeArbitro));
			incontri.add(i);
			return i;
		}
		return null;
	}

	public Collection<Incontro> elencoIncontriPerGiornata(String nomeTorneo){
		if (tornei.containsKey(nomeTorneo)) {
			return incontri.stream()
						   .filter(t -> t.getNomeTorneo() == nomeTorneo)
						   .sorted(Comparator.comparing(Incontro::getGiornata))
						   .toList();
		}
		return null;
	}

	public Collection<Incontro> elencoIncontriPerDifferenzaReti(String nomeTorneo){
		if (tornei.containsKey(nomeTorneo)) {
			return incontri.stream()
						   .filter(t -> t.getNomeTorneo() == nomeTorneo)
						   .sorted(Comparator.comparing(Incontro::getDifferenzaReti))
						   .toList();
		}
		return null;
	}

	public int puntiSquadra(String nomeSquadra){
		int tot = 0;
		for (Incontro i : incontri) {
			if (i.getSquadraCasa().getNome() == nomeSquadra) {
				if (i.getNumeroGolSquadraCasa() > i.getNumeroGolSquadraOspite()) {
					tot += 3;
				}
				else if (i.getNumeroGolSquadraCasa() == i.getNumeroGolSquadraOspite()) {
					tot += 1;
				}
			}
			if (i.getSquadraOspite().getNome() == nomeSquadra) {
				if (i.getNumeroGolSquadraCasa() < i.getNumeroGolSquadraOspite()) {
					tot += 3;
				}
				else if (i.getNumeroGolSquadraCasa() == i.getNumeroGolSquadraOspite()) {
					tot += 1;
				}
			}
		}
		return tot;
	}
	
	public String classificaTorneo(String nomeTorneo) {
		if (!tornei.containsKey(nomeTorneo)) {
			return null;
		}
	
		Map<String, Integer> classifica = new TreeMap<>();
		for (Squadra squadra : tornei.get(nomeTorneo).getSquadreIscritte()) {
			classifica.put(squadra.getNome(), puntiSquadra(squadra.getNome()));
		}
	
		return classifica.entrySet().stream()
			.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
			.map(entry -> entry.getValue() + " " + entry.getKey() + ";")
			.reduce((line1, line2) -> line1 + "\n" + line2)
			.map(result -> result.substring(0, result.length() - 1)) // Rimuove l'ultimo punto e virgola
			.orElse("");
	}
}
