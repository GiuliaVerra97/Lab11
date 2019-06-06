package it.polito.tdp.model;

public class Model {
	
	public void simula() {
		Simula sim=new Simula();
		sim.init();
		sim.run();
	}
	
	public String calcolaClienti() {
		Simula sim=new Simula();
		sim.init();
		sim.run();
		String s="";
		s="Numero clienti arrivati nel bar: "+sim.getNumTotaleClienti()+"\n numero clienti soddisfatti "+sim.getNumSoddisfatti()+"\n numero clienti non soddisfatti "+sim.getNumInsoddisfatti();
		return s;
	}
	
	

}
