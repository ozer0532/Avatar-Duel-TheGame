package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.util.LongValue;

public class AvatarDuel extends Application {
  @Override
  public void start(Stage stage) {
    
    Group root = new Group();
    Scene scene = new Scene(root, 1280, 720);
    
    Canvas canvas = new Canvas(1280, 720);
    root.getChildren().add(canvas);
    Image background = new Image("com/avatarduel/generic/image/Background.png"); // TODO: Change to white square later...
    
    GraphicsContext gc = canvas.getGraphicsContext2D();
    GameManager gm = new GameManager(gc);
    
    LongValue previousFrame = new LongValue(System.nanoTime());

    scene.setOnMouseClicked(
      new EventHandler<MouseEvent>(){
        public void handle(MouseEvent e)
        {
          gm.sendMouseClickEvent(e);
        }
      }
    );
    
    new AnimationTimer()
    {
      public void handle(long currentNanoTime) {
        double deltaTime = (double)(currentNanoTime - previousFrame.value) / 1000000000;

        gc.drawImage(background, 0, 0, 1280, 720);
        gm.gameLoop(deltaTime);

        previousFrame.value = currentNanoTime;
      }
    }.start();

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}