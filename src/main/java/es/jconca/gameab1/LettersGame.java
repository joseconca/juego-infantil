package es.jconca.gameab1;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LettersGame {
    private Stage stage;
    private Scene scene;
    private Label letterLabel;
    private Button nextButton, shuffleButton, backButton;
    private ProgressBar progressBar;
    private StackPane card;
    private int index = -1;
    private String[] letters = {
            "A","B","C","D","E","F","G","H","I","J","K","L","M",
            "N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"
    };

    public LettersGame(Stage stage, Scene menuScene) {
        this.stage = stage;

        // --- Label y tarjeta ---
        letterLabel = new Label();
        letterLabel.setFont(new Font(240));
        letterLabel.setTextFill(Color.BLACK);

        card = new StackPane(letterLabel);
        card.setStyle("-fx-background-color: yellow; -fx-background-radius: 20px;"
                + "-fx-border-color: black; -fx-border-width: 3px; -fx-border-radius: 20px;");
        card.prefWidthProperty().bind(stage.widthProperty().multiply(0.6));
        card.maxWidthProperty().bind(stage.widthProperty().multiply(0.6));
        card.prefHeightProperty().bind(stage.heightProperty().multiply(0.6));
        card.maxHeightProperty().bind(stage.heightProperty().multiply(0.6));

        // --- ProgressBar ---
        progressBar = new ProgressBar(0);
        progressBar.prefWidthProperty().bind(stage.widthProperty().multiply(0.6));
        progressBar.setPrefHeight(20);
        progressBar.setStyle("-fx-accent: #4CAF50;");

        // --- Botones ---
        nextButton = new Button("Siguiente");
        nextButton.setFont(new Font(24));
        nextButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 30px;");
        nextButton.setOnAction(e -> advance(card));

        shuffleButton = new Button("Mezclar");
        shuffleButton.setFont(new Font(24));
        shuffleButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-background-radius: 30px;");
        shuffleButton.setOnAction(e -> {
            shuffleLetters();
            index = 0;
            advance(card);
        });

        backButton = new Button("Menú");
        backButton.setFont(new Font(24));
        backButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-background-radius: 30px;");
        backButton.setOnAction(e -> stage.setScene(menuScene));

        // --- Layout ---
        VBox layout = new VBox(15, card, nextButton, shuffleButton, backButton,progressBar);
        layout.setAlignment(Pos.CENTER);
        layout.setFillWidth(false);

        scene = new Scene(layout, 800, 600);

        // --- Teclas ---
        scene.setOnKeyPressed(evt -> {
            if (evt.getCode() == KeyCode.SPACE) {
                advance(card);
            } else if (evt.getCode() == KeyCode.ESCAPE) {
                stage.setScene(menuScene);
            }
        });

        advance(card);
    }

    public Scene getScene() {
        return scene;
    }

    private void advance(StackPane card) {
        // Actualizar índice y letra
        index = (index + 1) % letters.length;
        letterLabel.setText(letters[index]);

        // Color de fondo
        String[] colors = {"#FFD700","#FFB6C1","#7FFFD4","#87CEFA","#FFE4B5"};
        card.setStyle("-fx-background-color: "+ colors[index % colors.length] +
                "; -fx-background-radius:20px; -fx-border-color:black; -fx-border-width:3px; -fx-border-radius:20px;");

        // Avanzar barra de progreso
        progressBar.setProgress((double)(index + 1) / letters.length);
    }

    private void shuffleLetters() {
        List<String> list = Arrays.asList(letters);
        Collections.shuffle(list);
        letters = list.toArray(new String[0]);
    }

    private void sort() {
        List<String> list = Arrays.asList(letters);
        Collections.sort(list);
        letters = list.toArray(new String[0]);
    }
    public void reset() {
        index = -1;
        sort();
        advance(card);
    }
}
