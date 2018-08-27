package app;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    private List<Cell> aliveCellList;

    public Board(){
        aliveCellList = new ArrayList<>();
    }

    public void initialCellDeadOrAlive(int x, int y) {
        Cell cell = searchCell(x, y);
        if (isCellInTable(cell)) {
            cell.switchDeadOrAlive();
            if (cell.isAlive())
                aliveCellList.add(cell);
            else
                aliveCellList.remove(cell);
        }
    }

    public List<Cell> getAliveCellList() {
        return aliveCellList;
    }

    public void setAliveCellList(List<Cell> nextGenAliveCellList) {
        aliveCellList = new ArrayList<>(nextGenAliveCellList);
    }

    public List<Cell> findAllBeConsideredCells(List<Cell> currentGenAliveCells) {
        List<Cell> neighborCells = new ArrayList<>(currentGenAliveCells);
        for (Cell cell : currentGenAliveCells) {
            int x = cell.getX();
            int y = cell.getY();

            addUniqueCellTo(new Cell(x - 1, y - 1), neighborCells);
            addUniqueCellTo(new Cell(x - 1, y), neighborCells);
            addUniqueCellTo(new Cell(x - 1, y + 1), neighborCells);
            addUniqueCellTo(new Cell(x, y + 1), neighborCells);
            addUniqueCellTo(new Cell(x + 1, y + 1), neighborCells);
            addUniqueCellTo(new Cell(x + 1, y), neighborCells);
            addUniqueCellTo(new Cell(x + 1, y - 1), neighborCells);
            addUniqueCellTo(new Cell(x, y - 1), neighborCells);
        }
        return neighborCells;
    }

    public void addUniqueCellTo(Cell newCell, List<Cell> list) {
        if (!list.contains(newCell) && isCellInTable(newCell))
            list.add(newCell);
    }

    public boolean isCellInTable(Cell cell) {
        return cell.getX() < WIDTH && cell.getX() >= 0 && cell.getY() < HEIGHT && cell.getY() >= 0;
    }

    public int countAliveNeighborsOf(Cell centerCell) {
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

    public Cell searchCell(int x, int y) {
        for (Cell currentCell : aliveCellList) {
            if (currentCell.getX() == x && currentCell.getY() == y) {
                return currentCell;
            }
        }
        return new Cell(x, y);
    }

}
