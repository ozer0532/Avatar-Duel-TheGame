package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.avatarduel.gameManager.GameManager;
import com.avatarduel.model.Element;
import com.avatarduel.model.Land;
import com.avatarduel.util.CSVReader;
import com.avatarduel.util.LongValue;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";

  public void loadCards() throws IOException, URISyntaxException {
    File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
    CSVReader landReader = new CSVReader(landCSVFile, "\t");
    landReader.setSkipHeader(true);
    List<String[]> landRows = landReader.read();
    for (String[] row : landRows) {
      Land l = new Land(row[1], row[3], Element.valueOf(row[2]));
    }
  }

  @Override
  public void start(Stage stage) {

    Group root = new Group();
    Scene scene = new Scene(root, 1280, 720);

    Canvas canvas = new Canvas(1280, 720);
    root.getChildren().add(canvas);
    Image background = new Image("src/res/image/Eastern Air Temple.png"); // TODO: Change to white square later...

    GraphicsContext gc = canvas.getGraphicsContext2D();
    GameManager gm = new GameManager(gc);

    LongValue previousFrame = new LongValue(System.nanoTime());

    new AnimationTimer()
    {
      public void handle(long currentNanoTime) {
        double deltaTime = (double)(currentNanoTime - previousFrame.value) / 1000000000;

        gc.drawImage(background, 0, 0);
        gm.GameLoop(deltaTime);

        previousFrame.value = currentNanoTime;
      }
    }.start();

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();



    try {
      this.loadCards();
      //text.setText("Avatar Duel!");
    } catch (Exception e) {
      //text.setText("Failed to load cards: " + e);
    }
  }

  public static void main(String[] args) {
    launch();
  }
}