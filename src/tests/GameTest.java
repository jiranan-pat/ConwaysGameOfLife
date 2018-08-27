import app.Cell;
import app.Game;
import junit.framework.TestCase;

public class GameTest extends TestCase {

    private Game game;

    public GameTest(String name) {
        super(name);
    }

    public void setUp() {
        game = new Game();
    }

    public void testIsCellFollowConditionToAlive(){
        Cell deadCell = new Cell(9,9);
        Cell aliveCell = new Cell(8,8);
        aliveCell.setAlive();

        assertTrue(game.isCellFollowConditionToAlive(3,deadCell));
        assertFalse(game.isCellFollowConditionToAlive(1,deadCell));
        assertTrue(game.isCellFollowConditionToAlive(2,aliveCell));
        assertTrue(game.isCellFollowConditionToAlive(3,aliveCell));
        assertFalse(game.isCellFollowConditionToAlive(1,aliveCell));
        assertFalse(game.isCellFollowConditionToAlive(4,aliveCell));
    }

    public void testFindNextGenerationLShape() {
        game.initialCellDeadOrAlive(4, 4);
        game.initialCellDeadOrAlive(4, 5);
        game.initialCellDeadOrAlive(5, 4);
        assertEquals(4, game.findNextGeneration().size());
    }

    public void testFindNextGenerationLineShape() {
        game.initialCellDeadOrAlive(1, 1);
        game.initialCellDeadOrAlive(1, 2);
        game.initialCellDeadOrAlive(1, 3);
        assertEquals(3, game.findNextGeneration().size());
    }

    public void testFindNextGenerationToadShape() {
        game.initialCellDeadOrAlive(1, 2);
        game.initialCellDeadOrAlive(1, 3);
        game.initialCellDeadOrAlive(2, 4);
        game.initialCellDeadOrAlive(4, 2);
        game.initialCellDeadOrAlive(3, 1);
        game.initialCellDeadOrAlive(4, 3);
        assertEquals(6, game.findNextGeneration().size());
    }

    public void testGameLogicBeaconShape(){
        game.initialCellDeadOrAlive(1, 1);
        game.initialCellDeadOrAlive(1, 2);
        game.initialCellDeadOrAlive(2, 1);
        game.initialCellDeadOrAlive(2, 2);
        game.initialCellDeadOrAlive(4, 4);
        game.initialCellDeadOrAlive(4, 3);
        game.initialCellDeadOrAlive(3, 4);
        game.initialCellDeadOrAlive(3, 3);
        game.gameLogic();
        assertEquals(6, game.getCurrentAliveCellList().size());
        assertFalse(game.isStable());
        game.gameLogic();
        assertEquals(8, game.getCurrentAliveCellList().size());
    }
}