package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class View implements FXComponent {
  AlternateMvcController controller;

  public View(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    BorderPane border = new BorderPane();

    // Controls view
    ControlView controlsView = new ControlView(controller);
    border.setTop(controlsView.render());

    // Grid view
    StackPane stack = new StackPane();
    PuzzleView puzzleView = new PuzzleView(controller);
    stack.getChildren().add(puzzleView.render());

    if (controller.isSolved()) {
      Rectangle message = new Rectangle(450, 100);
      message.setFill(Paint.valueOf("71B1D9"));
      Text congrats = new Text("Congratulations! You solved it!");
      congrats.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 25));
      congrats.getStyleClass().add("success-view");
      congrats.setTextAlignment(TextAlignment.CENTER);
      stack.getChildren().add(message);
      stack.getChildren().add(congrats);
    }
    stack.setAlignment(Pos.CENTER);
    border.setCenter(stack);

    VBox sideOne = new VBox();
    sideOne.setPadding(new Insets(30));
    sideOne.setSpacing(15);
    sideOne.setStyle("-fx-background-color: #F2CDC4");
    border.setLeft(sideOne);
    VBox sideTwo = new VBox();
    sideTwo.setPadding(new Insets(30));
    sideTwo.setSpacing(15);
    sideTwo.setStyle("-fx-background-color: #F2CDC4");
    border.setRight(sideTwo);
    HBox bottom = new HBox();
    bottom.setPadding(new Insets(15));
    bottom.setSpacing(15);
    border.setStyle("-fx-background-color: #F2CDC4");
    border.setBottom(bottom);
    return border;
  }
}
