package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.CellType;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PuzzleView implements FXComponent {
  AlternateMvcController controller;

  public PuzzleView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    GridPane gridPane = new GridPane();
    gridPane.setHgap(2);
    gridPane.setVgap(2);

    for (int i = 0; i < controller.getActivePuzzle().getHeight(); i++) {
      for (int j = 0; j < controller.getActivePuzzle().getWidth(); j++) {
        gridPane.add(makeTile(i, j), j, i);
      }
    }

    return gridPane;
  }

  public Button makeTile(int r, int c) {
    Button button = new Button();
    button.setPrefSize(90, 90);
    if (controller.getActivePuzzle().getCellType(r, c) == CellType.WALL) {
      button.getStyleClass().add("regular-button");
      button.setDisable(true);
      button.setStyle("-fx-opacity: 1");
    } else if (controller.getActivePuzzle().getCellType(r, c) == CellType.CLUE) {
      button.setText(String.valueOf(controller.getActivePuzzle().getClue(r, c)));
      if (controller.isClueSatisfied(r, c)) {
        button.getStyleClass().add("satisfied-clue-button");
      } else {
        button.getStyleClass().add("regular-button");
      }
      button.setDisable(true);
      button.setStyle("-fx-opacity: 1");
    } else {
      if (controller.isLamp(r, c)) {
        // String light = ;
        if (controller.isIllegal(r, c)) {
          button.getStyleClass().add("illegal-button");
          button.setText("\u2606");
        } else {
          button.getStyleClass().add("light-button");
          button.setText("\u2605");
        }
      } else if (controller.isLit(r, c)) {
        button.getStyleClass().add("lit-button");
      }
    }

    button.setOnAction(
        (ActionEvent event) -> {
          controller.clickCell(r, c);
        });

    return button;
  }
}
