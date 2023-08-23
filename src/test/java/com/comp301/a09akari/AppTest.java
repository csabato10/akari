package com.comp301.a09akari;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

/** Unit test for simple App. */
public class AppTest {
  /**
   * Rigorous Test :-)
   */
  @Test
  public void shouldAnswerWithTrue() {
    PuzzleLibrary puzzleLibrary = new PuzzleLibraryImpl();
    int[][] board = {{6, 6}, {6,6}};
    Puzzle puzzle = new PuzzleImpl(board);
    puzzleLibrary.addPuzzle(puzzle);
    Model model = new ModelImpl(puzzleLibrary);
    model.addLamp(0, 0);
    model.addLamp(0, 1);
    model.addLamp(1, 1);
    model.removeLamp(0, 1);

    assertTrue(model.isSolved());
//
//    model.addLamp(0, 0);
//    assertTrue(model.isLamp(0, 1));

//
//    model.addLamp(0, 3);
//    //model.addLamp(1, 0);
//    model.addLamp(1, 1);
//    model.addLamp(2, 5);
//    model.addLamp(3, 4);
//    model.addLamp(3, 6);
//    model.addLamp(4, 5);
//    model.addLamp(5, 2);
//    model.addLamp(5, 6);
//    model.addLamp(6, 0);
//    model.addLamp(6, 3);
//
//    assertTrue(model.isSolved());
  }
}
