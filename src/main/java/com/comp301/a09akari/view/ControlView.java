package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ControlView implements FXComponent {
  AlternateMvcController controller;

  public ControlView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    layout.setSpacing(15);
    layout.setPadding(new Insets(15, 15, 15, 15));
    layout.setStyle("-fx-background-color: #F2CDC4");
    Label puzzleNum =
        new Label("Puzzle: " + (controller.getIndex() + 1) + "/" + controller.librarySize());
    layout.getChildren().add(puzzleNum);

    Text title = new Text("Akari: Light Puzzle");
    title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 15));
    layout.getChildren().add(title);

    Button shuffleButton = new Button("Shuffle");
    shuffleButton.getStyleClass().add("controls");
    shuffleButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickRandPuzzle();
        });
    layout.getChildren().add(shuffleButton);

    Button nextButton = new Button("Next Puzzle");
    nextButton.getStyleClass().add("controls");
    nextButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickNextPuzzle();
        });
    layout.getChildren().add(nextButton);

    Button previousButton = new Button("Previous Puzzle");
    previousButton.getStyleClass().add("controls");
    previousButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });
    layout.getChildren().add(previousButton);

    Button resetButton = new Button("Reset Puzzle");
    resetButton.getStyleClass().add("controls");
    resetButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    layout.getChildren().add(resetButton);

    return layout;
  }
}
