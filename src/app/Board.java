package app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board {

    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private List<Cell> cellList;
    private List<Cell> aliveCellList;
    private List<Cell> toBeDeadCellList;
    private List<Cell> nextGenAliveCellList;
//    private List<Cell> deadNeighborList;

    public Board() {
        init();
    }

    private void init() {
        aliveCellList = new ArrayList<>();
        nextGenAliveCellList = new ArrayList<>();

        cellList = new ArrayList<>();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                cellList.add(new Cell(i, j));
            }
        }
    }

    public void initialCellDeadOrAlive(int x, int y) {
        Cell cell = searchCell(x, y);
        if (cell.getX() >= 0) {
            cell.switchDeadOrAlive();
            if (cell.isAlive())
                aliveCellList.add(cell);
        }
    }

    public List<Cell> getAliveCellList() {
        return aliveCellList;
    }

    //Any live cell with fewer than two live neighbors dies, as if by under population.
    //Any live cell with two or three live neighbors lives on to the next generation.
    //Any live cell with more than three live neighbors dies, as if by overpopulation.
    public List<Cell> findNextGeneration() {
        nextGenAliveCellList = new ArrayList<>();
        toBeDeadCellList = new ArrayList<>();
        // consider only alive cells
        for (Cell currentAliveCell : aliveCellList) {
            int count = countingNumberOfNeighbors(currentAliveCell);
            System.out.println("alvlist: " + currentAliveCell.getX() + "," + currentAliveCell.getY() + "=" + count);
            if (count < 2 || count > 3) toBeDeadCellList.add(currentAliveCell);
            else if (!nextGenAliveCellList.contains(currentAliveCell)) nextGenAliveCellList.add(currentAliveCell);
        }
        aliveCellList = new ArrayList<>(nextGenAliveCellList);
        resetBoardAliveCells();
        return nextGenAliveCellList;
    }

    //Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    private void checkingConditionsToReborn(Cell cell, int number) {
        if (number == 3 && !nextGenAliveCellList.contains(cell)) {
//            cell.setAlive();
            nextGenAliveCellList.add(cell);
        }
    }

    public int countingNumberOfNeighbors(Cell centerCell) {
        int x = centerCell.getX();
        int y = centerCell.getY();
        int count = 0;

        count += checkToReborn(searchCell(x - 1, y - 1));
        count += checkToReborn(searchCell(x - 1, y));
        count += checkToReborn(searchCell(x - 1, y + 1));
        count += checkToReborn(searchCell(x, y + 1));
        count += checkToReborn(searchCell(x + 1, y + 1));
        count += checkToReborn(searchCell(x + 1, y));
        count += checkToReborn(searchCell(x + 1, y - 1));
        count += checkToReborn(searchCell(x, y - 1));

        return count;
    }

    private int countAliveNeighborsOfDeadCell(Cell centerCell) {
        int x = centerCell.getX();
        int y = centerCell.getY();
        int count = 0;

        if (searchCell(x - 1, y - 1).isAlive()) count++;
        if (searchCell(x - 1, y).isAlive()) count++;
        if (searchCell(x - 1, y + 1).isAlive()) count++;
        if (searchCell(x, y + 1).isAlive()) count++;
        if (searchCell(x + 1, y + 1).isAlive()) count++;
        if (searchCell(x + 1, y).isAlive()) count++;
        if (searchCell(x + 1, y - 1).isAlive()) count++;
        if (searchCell(x, y - 1).isAlive()) count++;

        return count;
    }

    private int checkToReborn(Cell cell) {
        if (cell.isAlive())
            return 1;
        else {
            if (cell.getX() >= 0) {
                int count = countAliveNeighborsOfDeadCell(cell);
                checkingConditionsToReborn(cell, count);
            }
            return 0;
        }
    }

    public Cell searchCell(int x, int y) {
        if (x >= 0 && x < 20 && y >= 0 && y < 20) {
            for (Cell currentCell : cellList) {
                if (currentCell.getX() == x && currentCell.getY() == y) {
                    return currentCell;
                }
            }
        }
        //TODO: refactor
        return new Cell(-10, -10);
    }

    public void resetBoardAliveCells() {
        List<Cell> tempAliveCellList = new ArrayList<>(nextGenAliveCellList);
        List<Cell> temptoBeDeadCellList = new ArrayList<>(toBeDeadCellList);
        //TODO: refactor
        for (Cell boardCell : cellList) {
            Iterator<Cell> iterAlive = tempAliveCellList.iterator();
            while (iterAlive.hasNext()) {
                Cell cell = iterAlive.next();
                if (boardCell.equals(cell)) {
                    boardCell.setAlive();
                    iterAlive.remove();
                }
            }
            Iterator<Cell> iterDead = temptoBeDeadCellList.iterator();
            while (iterDead.hasNext()) {
                Cell cell = iterDead.next();
                if (boardCell.equals(cell)) {
                    boardCell.setDead();
                    iterDead.remove();
                }
            }
        }
    }

}
