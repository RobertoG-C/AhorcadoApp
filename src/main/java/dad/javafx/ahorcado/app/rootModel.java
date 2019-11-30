package dad.javafx.ahorcado.app;

import dad.javafx.ahorcado.puntos.Puntuacion;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class rootModel {
	private ListProperty<String> listaPalabras = new SimpleListProperty<String>(this,"listaPalabras",FXCollections.observableArrayList());
	private ListProperty<Puntuacion> listapuntaciones = new SimpleListProperty<Puntuacion>(this,"listaPalabrasJuego",FXCollections.observableArrayList());
	private ListProperty<Puntuacion> listaaux = new SimpleListProperty<Puntuacion>(this,"listaaux",FXCollections.observableArrayList());
	public final ListProperty<String> listaPalabrasProperty() {
		return this.listaPalabras;
	}
	
	public final ObservableList<String> getListaPalabras() {
		return this.listaPalabrasProperty().get();
	}
	
	public final void setListaPalabras(final ObservableList<String> listaPalabras) {
		this.listaPalabrasProperty().set(listaPalabras);
	}
	
	public final ListProperty<Puntuacion> listapuntacionesProperty() {
		return this.listapuntaciones;
	}
	
	public final ObservableList<Puntuacion> getListapuntaciones() {
		return this.listapuntacionesProperty().get();
	}
	
	public final void setListapuntaciones(final ObservableList<Puntuacion> listapuntaciones) {
		this.listapuntacionesProperty().set(listapuntaciones);
	}

	public final ListProperty<Puntuacion> listaauxProperty() {
		return this.listaaux;
	}
	

	public final ObservableList<Puntuacion> getListaaux() {
		return this.listaauxProperty().get();
	}
	

	public final void setListaaux(final ObservableList<Puntuacion> listaaux) {
		this.listaauxProperty().set(listaaux);
	}
	
	


	
	
	
	
}
