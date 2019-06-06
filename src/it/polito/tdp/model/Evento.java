package it.polito.tdp.model;

import java.time.LocalTime;

public class Evento implements Comparable<Evento> {
	
	
	public enum TipoEvento{
		ARRIVO_GRUPPO_CLIENTI, PARTENZA_GRUPPO_CLIENTI
	}
	
	
	private int tempo;
	private TipoEvento tipo;
	private GruppoClienti cliente;
	
	
	public Evento(int tempo, TipoEvento tipo, GruppoClienti cliente) {
		super();
		this.tempo = tempo;
		this.tipo = tipo;
		this.cliente = cliente;
	}


	public int getTempo() {
		return tempo;
	}


	public void setTempo(int tempo) {
		this.tempo = tempo;
	}


	public TipoEvento getTipo() {
		return tipo;
	}


	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}


	public GruppoClienti getCliente() {
		return cliente;
	}


	public void setCliente(GruppoClienti cliente) {
		this.cliente = cliente;
	}


	@Override
	public int compareTo(Evento o) {
		
		return this.tempo-o.getTempo();
	}
	
	
	
	
	
	

}
