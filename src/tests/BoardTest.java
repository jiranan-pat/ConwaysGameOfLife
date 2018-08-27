import app.Board;
import app.Cell;
import junit.framework.TestCase;

public class BoardTest extends TestCase {

    public BoardTest(String name) {
        super(name);
    }

    private Board board;

    public void setUp() {
        board = new Board();
    }

    public void testExceedBoard() {
        board.initialCellDeadOrAlive(50,1);
        assertEquals(0, board.getAliveCellList().size());
    }

    public void testFindAllBeConsideredCellsAdjacentBoarder(){
        board.initialCellDeadOrAlive(1,0);
        board.initialCellDeadOrAlive(1,1);
        board.initialCellDeadOrAlive(1,2);
        assertEquals(12,board.findAllBeConsideredCells(board.getAliveCellList()).size());
    }

    public void testFindAllBeConsideredCellsNormal(){
        board.initialCellDeadOrAlive(1,2);
        board.initialCellDeadOrAlive(2,2);
        board.initialCellDeadOrAlive(3,2);
        assertEquals(15,board.findAllBeConsideredCells(board.getAliveCellList()).size());
    }

    public void testCountingNumberOfNeighbors() {
        board.initialCellDeadOrAlive(1,2);
        board.initialCellDeadOrAlive(1,3);
        board.initialCellDeadOrAlive(0,2);
        assertEquals(2,board.countAliveNeighborsOf(new Cell(1,2)));
        assertEquals(3,board.countAliveNeighborsOf(new Cell(0,3)));
    }

}