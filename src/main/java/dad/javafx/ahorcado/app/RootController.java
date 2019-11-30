package dad.javafx.ahorcado.app;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.net.httpserver.Authenticator.Result;

import dad.javafx.ahorcado.palabras.PalabrasController;
import dad.javafx.ahorcado.partida.PartidaControler;
import dad.javafx.ahorcado.puntos.PuntosController;
import dad.javafx.ahorcado.puntos.Puntuacion;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;


public class RootController implements Initializable {

	@FXML
	private TabPane rootTabPane;

	@FXML
	private Tab partidaTab;

	@FXML
	private Tab palabrasTab;

	@FXML
	private Tab puntosTab;
	
	private PartidaControler partidaControler =new PartidaControler();
	private PalabrasController palabrasController=new PalabrasController();
	private PuntosController puntosController=new PuntosController();
	rootModel model=new rootModel();
	

	public RootController(List<String> palabras, List<Puntuacion> puntos) throws IOException {
		model.getListaPalabras().addAll(palabras);
		model.getListaaux().addAll(puntos);
		if(puntos.size()>0)
		System.out.println(puntos.get(0).getNombre());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		partidaTab.setContent(partidaControler.getView());
		palabrasTab.setContent(palabrasController.getView());
		puntosTab.setContent(puntosController.getView());
		Bindings.bindBidirectional(palabrasController.getModel().listPalabrasProperty(),model.listaPalabrasProperty());
		Bindings.bindBidirectional(puntosController.getModel().puntosListProperty(),model.listapuntacionesProperty());
		partidaControler.getModel().enJuegoProperty().addListener((o,ov,nv)-> guardaJuego(nv));
		System.out.println(model.getListaPalabras().size());
		nuevaPartida();
		model.setListapuntaciones(model.getListaaux());
	}


	private void guardaJuego(Boolean nv) {
		
		if (nv==false) {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Ahorcado App");
			dialog.setHeaderText("Quieres guardar las puntuaciónes");
			dialog.setContentText("Nombre:");

			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent() && result.get().length()>0){
			  Puntuacion pt=new Puntuacion();
				pt.setNombre(result.get());
				pt.setPuntos(partidaControler.getModel().getPoint());
				System.out.println(pt.getNombre()+pt.getPuntos());
				puntosController.getModel().getPuntosList().addAll(pt);
				puntosController.nuevaPuntuacion();
			}
				nuevaPartida();
			  partidaControler.getModel().setEnJuego(true);  
		}
		
	}

	private void nuevaPartida() {
		partidaControler.getModel().setVidas(partidaControler.getModel().getLista().size()-1);
		System.out.println(partidaControler.getModel().getVidas());
		partidaControler.getModel().setImagen(partidaControler.getModel().getLista().get(partidaControler.getModel().getVidas()));
		partidaControler.getModel().getPalabrasJuego().addAll(model.getListaPalabras());
		partidaControler.getModel().setFalladasLetras("");
		partidaControler.getModel().setPoint(0);
		partidaControler.palabraNext();
		
	}

	public TabPane getView() {
		return rootTabPane;
	}
	
	public rootModel getModel() {
		return model;
	}
}
