package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;
import java.util.Random;

public class ControllerImpl implements AlternateMvcController {
  private final Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    int next = model.getActivePuzzleIndex() + 1;
    System.out.println(model.getPuzzleLibrarySize());
    if (next < model.getPuzzleLibrarySize()) {
      this.model.setActivePuzzleIndex(next);
    }
  }

  @Override
  public void clickPrevPuzzle() {
    int past = model.getActivePuzzleIndex() - 1;
    if (past >= 0) {
      this.model.setActivePuzzleIndex(past);
    }
  }

  @Override
  public void clickRandPuzzle() {
    Random rand = new Random();
    int current = this.model.getActivePuzzleIndex();
    int newIndex = rand.nextInt(model.getPuzzleLibrarySize());
    while (newIndex == current) {
      newIndex = rand.nextInt(model.getPuzzleLibrarySize());
    }
    this.model.setActivePuzzleIndex(newIndex);
  }

  @Override
  public void clickResetPuzzle() {
    this.model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (this.model.isLit(r, c)) {
      if (this.model.isLamp(r, c)) {
        this.model.removeLamp(r, c);
      } else {
        this.model.addLamp(r, c);
      }
    } else {
      this.model.addLamp(r, c);
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    return this.model.isLit(r, c);
  }

  @Override
  public boolean isLamp(int r, int c) {
    return this.model.isLamp(r, c);
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    return this.model.isClueSatisfied(r, c);
  }

  @Override
  public boolean isSolved() {
    return this.model.isSolved();
  }

  @Override
  public Puzzle getActivePuzzle() {
    return this.model.getActivePuzzle();
  }

  @Override
  public int getIndex() {
    return this.model.getActivePuzzleIndex();
  }

  @Override
  public int librarySize() {
    return this.model.getPuzzleLibrarySize();
  }

  @Override
  public boolean isIllegal(int r, int c) {
    return this.model.isLampIllegal(r, c);
  }
}
