package application.model;

import javafx.beans.property.SimpleStringProperty;

public class PieceModel {
	private SimpleStringProperty vorname = new SimpleStringProperty();
	private SimpleStringProperty nachname = new SimpleStringProperty();
	private SimpleStringProperty geburtsdatum = new SimpleStringProperty();
	
	public PieceModel(String vorname, String nachname) {
		this.vorname.setValue(vorname);
		this.nachname.setValue(nachname);
	}
	
	public SimpleStringProperty geburtsdatum() {
		return geburtsdatum;
	}
	
	public SimpleStringProperty vorname() {
		return vorname;
	}
	
	public SimpleStringProperty nachname() {
		return nachname;
	}
	
	
}