package app;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board = new Board();
    private boolean isStable = false;
    private List<Cell> previousGenAliveCells = new ArrayList<>();

    public List<Cell> getNextGeneration() {
        List<Cell> nextGenAliveCells = board.findNextGeneration();
        isStable = isStable(nextGenAliveCells);
        return nextGenAliveCells;
    }

    public void setInitialCellAlive(int x, int y) {
        board.setCellAlive(x, y);
        previousGenAliveCells.add(board.searchCell(x, y));
    }

    public boolean isStable(List<Cell> nextGenAliveCells) {
        for (Cell preCell : previousGenAliveCells) {
            boolean isFind = false;
            for (Cell nextCell : nextGenAliveCells) {
               if(preCell.getX() == nextCell.getX() && preCell.getY() == nextCell.getY()) {
                   isFind = true;
                   break;
               }
            }
            if(!isFind)
                return false;
        }
        return true;
    }

}
