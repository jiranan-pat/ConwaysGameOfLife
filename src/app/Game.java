package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * RULE:
 *     Any live cell with fewer than two live neighbors dies, as if by under population.
 *     Any live cell with two or three live neighbors lives on to the next generation.
 *     Any live cell with more than three live neighbors dies, as if by overpopulation.
 *     Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 */
public class Game {

    private Board board = new Board();
    private boolean isStable = false;
    private List<Cell> currentGenAliveCells = new ArrayList<>();

    public List<Cell> findNextGeneration() {
        List<Cell> nextGenAliveCellList = new ArrayList<>();
        List<Cell> beConsideredCells = board.findAllBeConsideredCells(currentGenAliveCells);
        for (Cell currentCell : beConsideredCells) {
            int numbersOfAliveNeighbors = board.countAliveNeighborsOf(currentCell);
            if (isCellFollowConditionToAlive(numbersOfAliveNeighbors, currentCell) && !nextGenAliveCellList.contains(currentCell)) {
                currentCell.setAlive();
                nextGenAliveCellList.add(currentCell);
            }
        }
        return nextGenAliveCellList;
    }

    public boolean isCellFollowConditionToAlive(int count, Cell cell) {
        return (!cell.isAlive() && count == 3) || (cell.isAlive() && count >= 2 && count <= 3);
    }

    public void initialCellDeadOrAlive(int x, int y) {
        board.initialCellDeadOrAlive(x, y);
        currentGenAliveCells = board.getAliveCellList();
    }

    public List<Cell> getCurrentAliveCellList() {
        return currentGenAliveCells;
    }

    public boolean isStable(List<Cell> nextGenAliveCells) {
        if (currentGenAliveCells.size() != nextGenAliveCells.size())
            return false;

        int countEqual = 0;
        for (Cell preCell : currentGenAliveCells) {
            for (Cell nextCell : nextGenAliveCells) {
                if (preCell.equals(nextCell)) {
                    countEqual++;
                    break;
                }
            }
        }
        return countEqual == currentGenAliveCells.size();
    }

    public boolean isStable() {
        return isStable;
    }

    public void gameLogic() {
        List<Cell> nextGenAliveCells = findNextGeneration();
        System.out.println(">>>" + Arrays.toString(nextGenAliveCells.toArray()));
        if (!isStable(nextGenAliveCells)) {
            currentGenAliveCells = new ArrayList<>(nextGenAliveCells);
            board.setAliveCellList(nextGenAliveCells);
            isStable = false;
        } else {
            isStable = true;
        }
    }

}
