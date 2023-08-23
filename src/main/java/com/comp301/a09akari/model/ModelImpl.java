package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private final PuzzleLibrary library;
  private final List<ModelObserver> observers;
  private int index = 0;
  private Puzzle activePuzzle;
  private int[][] lights;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }
    this.library = library;
    this.activePuzzle = library.getPuzzle(index);
    this.lights = new int[activePuzzle.getHeight()][activePuzzle.getWidth()];
    for (int i = 0; i < this.activePuzzle.getHeight(); i++) {
      for (int j = 0; j < this.activePuzzle.getWidth(); j++) {
        this.lights[i][j] = 0;
      }
    }
    observers = new ArrayList<>();
  }

  @Override
  public void addLamp(int r, int c) {
    if (r > activePuzzle.getHeight() || r < 0 || c > activePuzzle.getWidth() || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    this.lights[r][c] = 1;

    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r > activePuzzle.getHeight() || r < 0 || c > activePuzzle.getWidth() || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    if (this.lights[r][c] == 1) {
      this.lights[r][c] = 0;
    }

    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r > activePuzzle.getHeight() || r < 0 || c > activePuzzle.getWidth() || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (this.lights[r][c] == 1) {
      return true;
    }
    // for (int i = 0; i < this.activePuzzle.getWidth(); i++){
    int i = c - 1;
    while (i >= 0) {
      if (this.activePuzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (this.lights[r][i] == 1) {
        return true;
      }
      i--;
    }
    i = c + 1;
    while (i < this.activePuzzle.getWidth()) {
      if (this.activePuzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (this.lights[r][i] == 1) {
        return true;
      }
      i++;
    }

    // check for other lamps in column
    i = r - 1;
    while (i >= 0) {
      if (this.activePuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (this.lights[i][c] == 1) {
        return true;
      }
      i--;
    }
    i = r + 1;
    while (i < this.activePuzzle.getHeight()) {
      if (this.activePuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (this.lights[i][c] == 1) {
        return true;
      }
      i++;
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r > activePuzzle.getHeight() || r < 0 || c > activePuzzle.getWidth() || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return this.lights[r][c] == 1;
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r > activePuzzle.getHeight() || r < 0 || c > activePuzzle.getWidth() || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.activePuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    // for (int i = 0; i < this.activePuzzle.getWidth(); i++){
    int i = c - 1;
    while (i >= 0) {
      if (this.activePuzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (this.lights[r][i] == 1) {
        return true;
      }
      i--;
    }
    i = c + 1;
    while (i < this.activePuzzle.getWidth()) {
      if (this.activePuzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (this.lights[r][i] == 1) {
        return true;
      }
      i++;
    }

    // check for other lamps in column
    i = r - 1;
    while (i >= 0) {
      if (this.activePuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (this.lights[i][c] == 1) {
        return true;
      }
      i--;
    }
    i = r + 1;
    while (i < this.activePuzzle.getHeight()) {
      if (this.activePuzzle.getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (this.lights[i][c] == 1) {
        return true;
      }
      i++;
    }

    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return this.activePuzzle;
  }

  @Override
  public int getActivePuzzleIndex() {
    return this.index;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index > library.size() || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    this.index = index;
    this.activePuzzle = library.getPuzzle(index);
    this.lights = new int[activePuzzle.getHeight()][activePuzzle.getWidth()];
    for (int i = 0; i < this.activePuzzle.getHeight(); i++) {
      for (int j = 0; j < this.activePuzzle.getWidth(); j++) {
        this.lights[i][j] = 0;
      }
    }
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    for (int i = 0; i < this.activePuzzle.getHeight(); i++) {
      for (int j = 0; j < this.activePuzzle.getWidth(); j++) {
        this.lights[i][j] = 0;
      }
    }
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < this.activePuzzle.getHeight(); i++) {
      for (int j = 0; j < this.activePuzzle.getWidth(); j++) {
        if (this.activePuzzle.getCellType(i, j) == CellType.CORRIDOR) {
          if (!isLit(i, j) || (isLampIllegal(i, j) && isLamp(i, j))) {
            return false;
          }
        } else if (this.activePuzzle.getCellType(i, j) == CellType.CLUE) {
          if (!this.isClueSatisfied(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r > activePuzzle.getHeight() || r < 0 || c > activePuzzle.getWidth() || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (activePuzzle.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }

    int clues = 0;
    // check left and right squares if in bounds
    if (r - 1 >= 0) {
      if (this.lights[r - 1][c] == 1) {
        clues += 1;
      }
    }
    if (r + 1 < this.activePuzzle.getHeight()) {
      if (this.lights[r + 1][c] == 1) {
        clues += 1;
      }
    }
    // check top and bottom squares if in bounds
    if (c - 1 >= 0) {
      if (this.lights[r][c - 1] == 1) {
        clues += 1;
      }
    }
    if (c + 1 < this.activePuzzle.getWidth()) {
      if (this.lights[r][c + 1] == 1) {
        clues += 1;
      }
    }

    return clues == this.activePuzzle.getClue(r, c);
  }

  @Override
  public void addObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    this.observers.remove(observer);
  }
}
