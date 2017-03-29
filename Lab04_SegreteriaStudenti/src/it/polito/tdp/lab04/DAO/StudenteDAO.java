package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(int matricola){
		final String sql = "SELECT * FROM studente WHERE matricola =  ? ";
		
		Studente result = null;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();

			if(rs.next()){
				Studente s = new Studente(rs.getInt("matricola"), 
						                  rs.getString("cognome"), 
						                  rs.getString("nome"), 
						                  rs.getString("cds"));
				result = s;
			}else{
				result = null;
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("Errore Db");
		}

	}
	
	public void corsiStudente(Studente s){
		final String sql = "SELECT * "+
                           "FROM corso, iscrizione "+
                           "WHERE corso.codins=iscrizione.codins && iscrizione.matricola = ? ";
		LinkedList<Corso> listaCorsi = new LinkedList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			
			ResultSet rs = st.executeQuery();

			while(rs.next()){
				Corso c = new Corso(rs.getString("codins"),
						            rs.getInt("crediti"),
						            rs.getString("nome"),
						            rs.getInt("pd"));
				listaCorsi.add(c);
			}
			s.setListaCorsi(listaCorsi);
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("Errore Db");
		}
		
	}

}
