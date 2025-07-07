package es.jconca.gameab1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Botones del menú principal
        Button btnLetras = new Button("Letras");
        btnLetras.setFont(new Font(24));
        btnLetras.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white; -fx-background-radius: 30px;");

        Button btnNumeros = new Button("Números");
        btnNumeros.setFont(new Font(24));
        btnNumeros.setStyle("-fx-background-color: #3F51B5; -fx-text-fill: white; -fx-background-radius: 30px;");

        // Layout horizontal para los botones del menú (simulando un carrusel simple)
        HBox menuLayout = new HBox(50, btnLetras, btnNumeros);
        menuLayout.setAlignment(Pos.CENTER);

        // Crear la escena del menú
        Scene menuScene = new Scene(menuLayout, 800, 600);

        // Instanciar las escenas de los juegos de letras y números, pasando la escena del menú
        LettersGame lettersGame = new LettersGame(primaryStage, menuScene);
        NumbersGame numbersGame = new NumbersGame(primaryStage, menuScene);

        // Acción al hacer clic en cada botón del menú
        btnLetras.setOnAction(e ->{
            lettersGame.reset();
            primaryStage.setScene(lettersGame.getScene());
            });
        btnNumeros.setOnAction(e -> primaryStage.setScene(numbersGame.getScene()));

        // Configurar la ventana principal
        primaryStage.setTitle("Juego Educativo Infantil");
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
