package it.polito.tdp.model;

public class Tavolo {
	
	
	private int codice;
	private int numPosti;
	private GruppoClienti cliente;
	private boolean occupato;

	public Tavolo(int codice, int numPosti, GruppoClienti cliente, boolean occupato) {
		super();
		this.codice=codice;
		this.numPosti = numPosti;
		this.cliente=cliente;
		this.occupato=occupato;
	}

	public int getNumPosti() {
		return numPosti;
	}

	public void setNumPosti(int numPosti) {
		this.numPosti = numPosti;
	}

	public GruppoClienti getCliente() {
		return cliente;
	}

	public void setCliente(GruppoClienti cliente) {
		this.cliente = cliente;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	@Override
	public String toString() {
		String s="Tavolo: "+codice+" "+occupato+" ";
		return s;
	}
	
	
	

}
