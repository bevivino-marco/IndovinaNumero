package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {
	private final int NMAX = 100;
	private final int TMAX = 8;

	private int segreto;
	private int tentativiFatti;
	private boolean inGioco ;
	
	
    public NumeroModel() {
    	inGioco=false;
    }
    /**
     * avvia nuova partita
     */
	public void newGame() {
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti = 0;
		this.inGioco = true;
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
		this.tentativiFatti++;
		if (this.tentativiFatti== TMAX){
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
    	if (t< 1 || t>NMAX) {
    		return false;
    
    	}
        return true;
    }
}
