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

    public void testFindNextGenerationLShape() {
        game.initialCellDeadOrAlive(4, 4);
        game.initialCellDeadOrAlive(4, 5);
        game.initialCellDeadOrAlive(5, 4);
        assertEquals(4, game.findNextGeneration().size());
    }

    public void testFindNextGenerationLineShape() {
        game.initialCellDeadOrAlive(4, 4);
        game.initialCellDeadOrAlive(4, 5);
        game.initialCellDeadOrAlive(5, 4);
        assertEquals(4, game.findNextGeneration().size());
    }
}