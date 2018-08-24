package app;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private List<Cell> cellList;
    private List<Cell> aliveCellList;
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

    public void setCellAlive(int x, int y){
        Cell cell = searchCell(x,y);
        if(cell.getX()>=0){
            cell.setAlive();
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
        // consider only alive cells
        for (Cell currentAliveCell : aliveCellList) {
            int count = countingNumberOfNeighbors(currentAliveCell);
            if (count < 2 || count > 3) currentAliveCell.setDead();
            else nextGenAliveCellList.add(currentAliveCell);
        }
        return nextGenAliveCellList;
    }

    //Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
   private void checkingConditionsToReborn(Cell cell, int number) {
        if (number == 3) {
            cell.setAlive();
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

//        if(searchCell(x-1,y-1).isAlive()) count++;
//        if(searchCell(x - 1, y).isAlive()) count++;
//        if(searchCell(x - 1, y + 1).isAlive()) count++;
//        if(searchCell(x, y + 1).isAlive()) count++;
//        if(searchCell(x + 1, y + 1).isAlive()) count++;
//        if(searchCell(x + 1, y).isAlive()) count++;
//        if(searchCell(x + 1, y - 1).isAlive()) count++;
//        if(searchCell(x, y - 1).isAlive()) count++;

        return count;
    }

    private int countAliveNeighborsOfDeadCell(Cell centerCell) {
        int x = centerCell.getX();
        int y = centerCell.getY();
        int count = 0;

        if(searchCell(x-1,y-1).isAlive()) count++;
        if(searchCell(x - 1, y).isAlive()) count++;
        if(searchCell(x - 1, y + 1).isAlive()) count++;
        if(searchCell(x, y + 1).isAlive()) count++;
        if(searchCell(x + 1, y + 1).isAlive()) count++;
        if(searchCell(x + 1, y).isAlive()) count++;
        if(searchCell(x + 1, y - 1).isAlive()) count++;
        if(searchCell(x, y - 1).isAlive()) count++;

        return count;
    }

    private int checkToReborn(Cell cell) {
        if (cell.isAlive())
            return 1;
        else {
            checkingConditionsToReborn(cell, countAliveNeighborsOfDeadCell(cell));
            return 0;
        }
    }

    public Cell searchCell(int x, int y) {
        for (Cell currentCell: cellList) {
            if (currentCell.getX() == x && currentCell.getY() == y) {
                return currentCell;
            }
        }
        //TODO: refactor
        return new Cell(-1, -1);
    }




}
