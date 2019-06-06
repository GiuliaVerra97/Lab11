package it.polito.tdp.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.model.Evento.TipoEvento;


public class Simula {
	
	
	//coda
	private PriorityQueue<Evento> queue=new PriorityQueue<>();
	
	//stato del mondo
	private Map<Integer, Tavolo> tavoliDisponibili;
	
	//variabili interne
	private Random random=new Random();	//deve essere tra 1 e 10 minuti o tra 1 e 10 clienti in arrivo per volta
	private List<Float> tolleranze;		//tolleranza cliente variabile da 0.1 a 0.91
	private List<Integer> arrivi;		//ogni quanto arrivano i clienti
	private List<Integer> durate;		//quanto stanno al tavolo i clienti
	private float tolleranzaBar;		//tolleranza fissa del bar
	
	//statistiche da stampare in output
	private int numTotaleClienti;
	private int numSoddisfatti;
	private int numInsoddisfatti;
	
	
	
	public Simula() {
		
		tavoliDisponibili=new HashMap<Integer, Tavolo>();
		arrivi=new ArrayList<Integer>();
		tolleranze=new ArrayList<Float>();
		durate=new ArrayList<Integer>();
		
	}
	
	
	
	
	
	
	public void init() {

		numTotaleClienti=0;
		numSoddisfatti=0;
		numInsoddisfatti=0;
		
		tolleranzaBar=(float) 0.50;
		
		//inizializzo tutti i tavoli presenti nel bar dando a loro un codice
		tavoliDisponibili.put(1, new Tavolo(1,10, null, false));
		tavoliDisponibili.put(2, new Tavolo(2,10, null, false));
		
		tavoliDisponibili.put(3, new Tavolo(3,8, null, false));
		tavoliDisponibili.put(4, new Tavolo(4,8, null, false));
		tavoliDisponibili.put(5, new Tavolo(5,8, null, false));
		tavoliDisponibili.put(6, new Tavolo(6,8, null, false));
		
		tavoliDisponibili.put(7, new Tavolo(7,6, null, false));
		tavoliDisponibili.put(8, new Tavolo(8,6, null, false));
		tavoliDisponibili.put(9, new Tavolo(9,6, null, false));
		tavoliDisponibili.put(10, new Tavolo(10,6, null, false));
		
		tavoliDisponibili.put(11, new Tavolo(11,4, null, false));
		tavoliDisponibili.put(12, new Tavolo(12,4, null, false));
		tavoliDisponibili.put(13, new Tavolo(13,4, null, false));
		tavoliDisponibili.put(14, new Tavolo(14,4, null, false));
		tavoliDisponibili.put(15, new Tavolo(15,4, null, false));

		
		
		for(int i=1; i<11; i++) {		//riempo il tempi di arrivo (possono arrivare ogni 1-10 minuti di sìdistanza)
			arrivi.add(i);
		}
		
		for(float i=1; i<91;i++) {		//riempo tutte le tolleranze
			float perc=(i/100);
			tolleranze.add(perc);
		}
		
		
		for(int i=60; i<121; i++) {		//riempo la lista delle possibili durate di permanenza
			durate.add(i);
		}
		
		int tempoArrivo=0;
		
		for(int k=0; k<2000; k++) {
			
			int i=random.nextInt(arrivi.size());	//prendo un indice a random
			int minuti=arrivi.get(i);
			tempoArrivo=tempoArrivo+minuti;
			
			int numPersone=arrivi.get(random.nextInt(arrivi.size()));
			int j=random.nextInt(tolleranze.size());
			float tolleranza=tolleranze.get(j);
			
			GruppoClienti cliente=new GruppoClienti(k, tempoArrivo, numPersone, 0, tolleranza);
			queue.add(new Evento(tempoArrivo, TipoEvento.ARRIVO_GRUPPO_CLIENTI, cliente));	
			
		}

		System.out.println("dimensione coda: "+queue.size());
		System.out.println("tolleranza Bar: "+tolleranzaBar);
		
	}
	
	
	
	
	
	
	
	
	
	
	public void run() {
		
		while(!queue.isEmpty()) {
			
			Evento ev=queue.poll();
			
			switch(ev.getTipo()) {
			
				case ARRIVO_GRUPPO_CLIENTI:
					
					int numPersone=ev.getCliente().getNumPersone();
					
					int min=Integer.MAX_VALUE;		//numero di posti che ha il tavolo in cui si siederanno i clienti
					Tavolo tavolo=null;
					boolean trovato=false;
										
					for(Tavolo t: tavoliDisponibili.values()) {
						if(t.getNumPosti()<min && numPersone>=((50/100)*t.getNumPosti()) && numPersone<=t.getNumPosti() && t.isOccupato()==false) {
							min=t.getNumPosti();
							tavolo=t;
							trovato=true;
						}
						
						
					}
					
					
					if(trovato==true) {
						
						int i=random.nextInt(durate.size());	//prendo un indice a random
						int durata=durate.get(i);		//la permanenza al tavolo dei clienti
						int oraPartenza=ev.getTempo()+durata;
						ev.getCliente().setDurata(durata); //setto la durata di permanenza del cliente
						tavoliDisponibili.get(tavolo.getCodice()).setCliente(ev.getCliente()); //faccio sedere i clienti al tavolo
						tavoliDisponibili.get(tavolo.getCodice()).setOccupato(true);			//tavolo occupato
						queue.add(new Evento(oraPartenza, TipoEvento.PARTENZA_GRUPPO_CLIENTI, ev.getCliente()));		//nuovo evento partenza

						numSoddisfatti++;
						
					}else {
						
						if(ev.getCliente().getTolleranza()>tolleranzaBar) {		//se la tolleranza dei clienti è maggiore della tolleranza al bar allora i clienti vanno al bancone e sono soddisfatti lo stesso
							numSoddisfatti++;
						}else {
							numInsoddisfatti++;
						}
						
					}
					
					numTotaleClienti++;
					
					break;
					
				case PARTENZA_GRUPPO_CLIENTI:
					
					GruppoClienti cliente=ev.getCliente();
					
					System.out.println("partenza: "+cliente);
					
					for(Tavolo t: tavoliDisponibili.values()) {
						if(t.isOccupato()==true) {
							if(t.getCliente().equals(cliente)) {
								t.setCliente(null);
								t.setOccupato(false);
								System.out.println("cliente partito da tavolo: "+t.getCodice()+" con numero posti "+t.getNumPosti());
							}
						}
					}
					
					break;
				
			}
				
		}
		
	}






	public int getNumTotaleClienti() {
		return numTotaleClienti;
	}


	public int getNumSoddisfatti() {
		return numSoddisfatti;
	}


	public int getNumInsoddisfatti() {
		return numInsoddisfatti;
	}
		
	
	
}
