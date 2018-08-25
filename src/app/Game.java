package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private Board board = new Board();
    private boolean isStable = false;
    private List<Cell> previousGenAliveCells = new ArrayList<>();

    public List<Cell> getNextGeneration() {
        return board.findNextGeneration();
    }

    public void initialCellDeadOrAlive(int x, int y) {
        board.initialCellDeadOrAlive(x, y);
        previousGenAliveCells = board.getAliveCellList();
    }

    public List<Cell> getCurrentAliveCellList() {
        System.out.println("getCurrentAliveCellList");
        return previousGenAliveCells;
    }

    public boolean isStable(List<Cell> nextGenAliveCells) {
        System.out.println(">>>p" + Arrays.toString(previousGenAliveCells.toArray()));
        if(previousGenAliveCells.size() != nextGenAliveCells.size())
            return false;

        int countEqual = 0;
        for (Cell preCell : previousGenAliveCells) {
            for (Cell nextCell : nextGenAliveCells) {
                if (preCell.equals(nextCell)) {
                    countEqual++;
                    break;
                }
            }
        }
        return countEqual == previousGenAliveCells.size();
    }

    public boolean isStable() {
        return isStable;
    }

    public void gameLogic() {
        List<Cell> nextGenList = getNextGeneration();
        System.out.println(">>>" + Arrays.toString(nextGenList.toArray()));
        if (!isStable(nextGenList)) {
            System.out.println("!isstable");
            previousGenAliveCells = new ArrayList<>(nextGenList);
            isStable = false;
        } else {
            System.out.println("isstable");
            isStable = true;
        }
    }

}
