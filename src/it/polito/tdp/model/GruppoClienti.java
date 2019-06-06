package it.polito.tdp.model;

public class GruppoClienti {
	
	
	private int id;
	private int tempo;	
	private int numPersone;
	private int durata;
	private float tolleranza;
	
	
	
	public GruppoClienti( int id, int tempo, int numPersone, int durata, float tolleranza) {
		super();
		this.id=id;
		this.tempo = tempo;
		this.numPersone = numPersone;
		this.durata = durata;
		this.tolleranza = tolleranza;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	public int getNumPersone() {
		return numPersone;
	}
	public void setNumPersone(int numPersone) {
		this.numPersone = numPersone;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public float getTolleranza() {
		return tolleranza;
	}
	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}



	@Override
	public String toString() {
		return "cliente con "+numPersone+" persone";
	}
	
	
	
	
	
	

}
