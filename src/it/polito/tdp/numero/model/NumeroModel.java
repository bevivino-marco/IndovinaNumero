package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class NumeroModel {
	private final int NMAX = 100;
	private final int TMAX = 8;

	private int segreto;
	//private int tentativiFatti;
	private boolean inGioco ;
	private IntegerProperty tentativiFatti;
	private List <Integer> tentativi ;
	
	
    public NumeroModel() {
    	inGioco=false;
    	tentativiFatti = new SimpleIntegerProperty();
    	tentativi = new LinkedList <Integer>();
    }
    /**
     * avvia nuova partita
     */
	public void newGame() {
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti.set(0);
		this.inGioco = true;
		this.tentativi.clear();
	}
	public int tentativo (int t) {
		// controllo se l apartita è in gioco
		if (!inGioco) {
			throw new IllegalStateException("la partita è terminata");
		}
		// controllo se l ' input è corretto
		if (!tentativoValido(t)) {
			throw new InvalidParameterException(String.format("devi inserire un numero tra %d e %d",1, NMAX));
		}
		// gestione tentativo
		this.tentativiFatti.set(this.tentativiFatti.get()+1);
		this.tentativi.add(t);
		if (this.tentativiFatti.get()== TMAX){
			this.inGioco=false;
		}
		if (t== this.segreto) {
			this.inGioco= false;
			return 0;
		}
		if (t> this.segreto) {
			return 1;
		}
		
			return -1;

	}    
	
	public boolean tentativoValido ( int t) {
    	if ((t< 1 || t>NMAX) || tentativi.contains(t)) {
    		return false;
    
    	}
        return true;
    }
	public int getSegreto() {
		return segreto;
	}
	/*public int getTentativiFatti() {
		return tentativiFatti;
	}*/
	public boolean isInGioco() {
		return inGioco;
	}
	public int getTMAX() {
		return TMAX;
	}
	public final IntegerProperty tentativiFattiProperty() {
		return this.tentativiFatti;
	}
	
	public final int getTentativiFatti() {
		return this.tentativiFattiProperty().get();
	}
	
	public final void setTentativiFatti(final int tentativiFatti) {
		this.tentativiFattiProperty().set(tentativiFatti);
	}
	
	
	
}
