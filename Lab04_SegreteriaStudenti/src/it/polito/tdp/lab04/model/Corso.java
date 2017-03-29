package it.polito.tdp.lab04.model;

import java.util.*;

public class Corso {

	private String codins;
	private int crediti;
	private String nome;
	private int pd;
	private List<Studente> listaStudenti;
	
	
	public Corso(String codins, int crediti, String nome, int pd) {
		listaStudenti = new ArrayList<Studente>();
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}


	public String getCodins() {
		return codins;
	}

	public int getCrediti() {
		return crediti;
	}

	public String getNome() {
		return nome;
	}

	public int getPd() {
		return pd;
	}


	public void setListaStudenti(List<Studente> listaStudenti) {
		this.listaStudenti = listaStudenti;
		
	}


	public List<Studente> getListaStudenti() {
		return listaStudenti;
	}
	
	
	
	
	
}
