package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private List<Corso> corsi;
	

	public Model() {
		corsi = new LinkedList<Corso>();
	}


	public List<Corso> getCorsi(){
		CorsoDAO cd = new CorsoDAO();
		corsi = (LinkedList<Corso>) cd.getTuttiICorsi();
		return corsi;
	}
	
	public Studente completaStudente(int matricola){
		StudenteDAO st = new StudenteDAO();
		Studente s = st.getStudente(matricola);
		return s;
	}
	
	public List<Studente> iscrittiCorso(String corso){
		//System.out.println(corso);
		CorsoDAO cd = new CorsoDAO();
		Corso res = null;
		
		for(Corso c : corsi){
			if(c.getNome().compareTo(corso)==0){
				res = c;
			}
		}
		
		if(res!=null)
			cd.getStudentiIscrittiAlCorso(res);
		
		return res.getListaStudenti();
	}
	
	public List<Corso> corsiStudente(Studente s){
		
		StudenteDAO st = new StudenteDAO();
		st.corsiStudente(s);
		
		return s.getListaCorsi();
	}


	public boolean cercaStudenteNelCorso(String corso, Studente s) {
		
	Corso corsoSelezionato = null;
	
	for(Corso c : corsi){
		if(c.getNome().equals(corso))
			corsoSelezionato = c;
	}
	StudenteDAO std = new StudenteDAO();
	
	return std.cercaStudenteNelCorso(s.getMatricola(), corsoSelezionato.getCodins());
	
	}


	public boolean iscriviStudenteAlCorso(String corso, Studente s) {
		Corso corsoSelezionato = null;
		
		for(Corso c : corsi){
			if(c.getNome().equals(corso))
				corsoSelezionato = c;
		}
		
		CorsoDAO cd = new CorsoDAO();
		return cd.inscriviStudenteACorso(s, corsoSelezionato);
	}
	
}
