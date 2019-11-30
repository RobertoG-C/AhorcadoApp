package dad.javafx.ahorcado.app;


import java.util.List;

import dad.javafx.ahorcado.palabras.PalabrasLogic;
import dad.javafx.ahorcado.puntos.PuntosLogic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AhorcadoApp extends Application {

	private RootController root;
	@Override
	public void start(Stage primaryStage) throws Exception {
		root =new RootController(PalabrasLogic.cargar(),PuntosLogic.cargar());
		Scene scene=new Scene(root.getView());
		primaryStage.setTitle("Ahorcado");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop() throws Exception {
		PalabrasLogic.guardar(root.getModel().getListaPalabras());
		PuntosLogic.guardar(root.getModel().getListapuntaciones());
		super.stop();
	}

	


}
