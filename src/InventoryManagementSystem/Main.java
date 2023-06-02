package InventoryManagementSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // caminho para o arquivo FXML da cena principal
    private static final String mainSceneFXMLPath = "Views/Main.fxml";
    // O tamanho padrão do stage, valor X
    int stageInitialSizeX = 1000;
    // O tamanho padrão do stage, valor Y
    int stageInitialSizeY = 500;

    /**
     * @param args Uso: ponto de entrada de execução do programa
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage Uso: o palco principal do programa JavaFX.
     * @throws IOException se os recursos não estiverem disponíveis ao acessá-los.
     *                     Uso: Inicia o programa JavaFX, define o stage e abre a
     *                     primeira cena.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(mainSceneFXMLPath));
        primaryStage.getIcons().add(Properties.getAPPLICATION_ICON());
        primaryStage.setTitle(Properties.getAPPLICATION_NAME_AND_VERSION());
        Scene mainScene = new Scene(root, stageInitialSizeX, stageInitialSizeY);
        primaryStage.setScene(mainScene);
        mainScene.getRoot().requestFocus();
        primaryStage.show();
    }
}
