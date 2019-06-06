package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class BarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

	private Model model;

    @FXML
    void doSimula(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	String s=model.calcolaClienti();
    	txtResult.appendText(s);
    	
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bar.fxml'.";
    }

	public void setModel(Model model) {
		this.model=model;
	}
   
    
}