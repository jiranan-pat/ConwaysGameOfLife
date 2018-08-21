package app;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private List<Cell> cellList;
    private List<Cell> aliveCellList;
    private List<Cell> nextGenAliveCellList;

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

    public void switchStatusCell(int x, int y) {
        searchCell(x, y).switchStatus();
    }


    public List<Cell> getAliveCellList() {
        return aliveCellList;
    }

    //Any live cell with fewer than two live neighbors dies, as if by under population.
    //Any live cell with two or three live neighbors lives on to the next generation.
    //Any live cell with more than three live neighbors dies, as if by overpopulation.
    public void findNextGeneration() {
        for (int i = 0; i < aliveCellList.size(); i++) {
            Cell currentAliveCell = aliveCellList.get(i);
            int count = countingNumberOfNeighbors(currentAliveCell);
            if (count < 2 || count > 3) currentAliveCell.switchStatus();
            else nextGenAliveCellList.add(currentAliveCell);

        }
    }

    //Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    public void checkingConditionsToReborn(Cell cell, int number) {
        if (number == 3) {
            cell.switchStatus();
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

    public int checkToReborn(Cell cell) {
        if (cell.isAlive())
            return 1;
        else {
            checkingConditionsToReborn(cell, countingNumberOfNeighbors(cell));
            return 0;
        }
    }

    public Cell searchCell(int x, int y) {
        for (int i = 0; i < cellList.size(); i++) {
            Cell currentCell = cellList.get(i);
            if (currentCell.getX() == x && currentCell.getY() == y) {
                return currentCell;
            }
        }
        //refactor
        return new Cell(-1, -1);
    }


}
