package it.polito.tdp.lab04.model;

import java.util.LinkedList;

public class Studente {
	
	private int matricola;
	private String nome;
	private String cognome;
	private String cds;
	private LinkedList<Corso> listaCorsi;
	
	public Studente(int matricola, String nome, String cognome, String cds) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.cds = cds;
		listaCorsi = new LinkedList<Corso>();
	}

	public int getMatricola() {
		return matricola;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getCds() {
		return cds;
	}

	public void setListaCorsi(LinkedList<Corso> listaCorsi) {
		this.listaCorsi = listaCorsi;
		
	}

	public LinkedList<Corso> getListaCorsi() {
		return listaCorsi;
	}
	
	
	
	
	
	
	
}
