package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SegreteriaStudentiController {

	Model model;
	
	public void setModel(Model m){
		this.model = m;
		
		LinkedList<Corso> corsi = (LinkedList<Corso>) model.getCorsi();
		comboCorso.getItems().add("Tutti");
		for(int i = 0; i<corsi.size(); i++){
	       	comboCorso.getItems().add(corsi.get(i).getNome());
	    }
	    
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboCorso;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private ImageView btnCercaNome;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {

    	if(!txtMatricola.getText().equals("")){
	    	int matricola = Integer.parseInt(txtMatricola.getText());
	    	Studente s = model.completaStudente(matricola);
	    	
	    	
	    	if(s==null){
	    		txtResult.appendText("ERRORE!! Matricola inserita non valida!");
	    		return;
	    	}
	    	else{
	    		LinkedList<Corso> corsi = (LinkedList<Corso>) model.corsiStudente(s);
	    		for(Corso c : corsi)
	    			txtResult.appendText(String.format("%.10s %.10s %-50s %-20s\n", c.getCodins(), c.getCrediti(), c.getNome(), c.getPd()));
	    	}
    	}
    	else{
    		txtResult.appendText("Devi inserire una matricola!\n");
    	}
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {

    	String result = comboCorso.getValue();
    	if(result == null || result.equals("Tutti")){
    		txtResult.setText("ERRORE!! Devi selezionare un corso!\n");
    		return;
    	}else{
    		LinkedList<Studente> studenti = (LinkedList<Studente>) model.iscrittiCorso(result);
    		for(Studente s : studenti){
    			//txtResult.appendText(s.getMatricola()+s.getCognome()+s.getNome()+s.getCds()+"\n");
    			txtResult.appendText(String.format("%.10s %-20s %-20s %-10s\n", s.getMatricola(), s.getNome(), s.getCognome(), s.getCds()));
    		}
    	}
    	
    }

    @FXML
    void doCercaNome(MouseEvent event) {

    	if(txtMatricola.getText().equals("")){
    		txtResult.appendText("Inserire matricola da completare!!\n");
    		return;
    	}
    	int matricola;
    	try{
    		matricola = Integer.parseInt(txtMatricola.getText());
        	}catch(NumberFormatException e){
        		txtResult.setText("La matricola e' composta da soli numeri!!\n");
        		return;
        	}
    	
    	Studente s = model.completaStudente(matricola);
    	if(s==null){
    		txtResult.appendText("Matricola inserita non valida!\n");
    		return;
    	}
    	
    	if(comboCorso.getValue()==null){
		    txtNome.setText(s.getNome());
		   	txtCognome.setText(s.getCognome());
    	}
    	else{
    		String corso = comboCorso.getValue();
	    	txtNome.setText(s.getNome());
	    	txtCognome.setText(s.getCognome());
	    	boolean ris = model.cercaStudenteNelCorso(corso, s);
	    	
	    	if(ris==true)
	    		txtResult.appendText("Studente gia iscritto a questo corso.\n");
	    	else
	    		txtResult.appendText("Studente non iscritto a questo corso.\n");
	    	
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    	if(txtMatricola.getText().equals("")){
    		txtResult.appendText("Inserire matricola!!\n");
    		return;
    	}
    	int matricola;
    	try{
    		matricola = Integer.parseInt(txtMatricola.getText());
        	}catch(NumberFormatException e){
        		txtResult.setText("La matricola e' composta da soli numeri!!\n");
        		return;
        	}
    	
    	Studente s = model.completaStudente(matricola);
    	
    	if(comboCorso.getValue()==null || comboCorso.getValue().equals("Tutti")){
    		txtResult.appendText("Selezionare un corso!\n");
    		return;
    	}
    	if(s==null){
    		txtResult.appendText("Matricola inserita non valida!!");
    		return;
    	}
    	
    	if(!model.cercaStudenteNelCorso(comboCorso.getValue(), s)){
    		boolean ris = model.iscriviStudenteAlCorso(comboCorso.getValue(), s);
    		
    		if(ris == true){
    			txtResult.appendText("Studente "+matricola+" iscritto correttamente al corso "+comboCorso.getValue()+"\n");
    		}
    	}
    	else{
    		txtResult.appendText("Studente "+matricola+" gia iscritto al corso "+comboCorso.getValue()+"\n");
    	}
    	
    	
    	
    }
    	
    

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();

    }

    @FXML
    void initialize() {
        assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

        
        txtResult.setStyle("-fx-font-family: monospace");
      
        
        
    }
}
