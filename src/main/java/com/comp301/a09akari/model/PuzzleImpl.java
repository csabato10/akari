package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  int width;
  int height;
  int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
    for (int[] row : board) {
      height += 1;
    }
    for (int row : board[0]) {
      width += 1;
    }
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r > height || r < 0 || c > width || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    int cell = board[r][c];
    if (cell >= 0 && cell <= 4) {
      return CellType.CLUE;
    } else if (cell == 5) {
      return CellType.WALL;
    } else if (cell == 6) {
      return CellType.CORRIDOR;
    }
    return null;
  }

  @Override
  public int getClue(int r, int c) {
    if (r >= height || r < 0 || c >= width || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int cell = board[r][c];
    if (cell == 0) {
      return 0;
    }
    if (cell == 1) {
      return 1;
    }
    if (cell == 2) {
      return 2;
    }
    if (cell == 3) {
      return 3;
    }
    if (cell == 4) {
      return 4;
    }
    return 0;
  }
}
