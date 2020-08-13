package game;

import org.junit.Before;
import org.junit.Test;
import strategies.BoardFactory;
import strategies.Direction;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static strategies.Direction.*;

public class BoardTest {

    private Board currentBoard;

    @Before
    public void setup() {
        currentBoard = BoardFactory.createBoard(BoardFactory.Level.MEDIUM);
    }

    @Test
    public void getExistentTileTest() {
        assertNotNull(currentBoard.getTileIfExists(new Coordinate(3, 3)));
    }

    @Test
    public void getNonExistentTileTest() {
        assertNull(currentBoard.getTileIfExists(new Coordinate(10, 10)));
    }

    @Test
    public void getNonExistentTileOverXTest() {
        assertNull(currentBoard.getTileIfExists(new Coordinate(10, 3)));
    }

    @Test
    public void getNonExistentTileOverYTest() {
        assertNull(currentBoard.getTileIfExists(new Coordinate(3, 10)));
    }

    @Test
    public void getValidCoordinateToMoveIfTileExistsAndIsWalkableTest() {
        Coordinate to = currentBoard.getCoordinateToMoveTo(new Coordinate(4,1), UP);
        assertNotNull(to);
        assertEquals(new Coordinate(3,1), to);
    }

    @Test
    public void getNullIfTileDoesNotExistTest() {
        assertNull(currentBoard.getCoordinateToMoveTo(new Coordinate(10,10),Direction.DOWN));
    }

    @Test
    public void getNullIfTileExistsButItIsNotWalkableTest() {
        assertNull(currentBoard.getCoordinateToMoveTo(new Coordinate(4,1), Direction.DOWN));
    }

    @Test
    public void getPusherPossibleDirectionsToMoveIfCanMoveOnlyInOneDirectionTest() {
        List<Direction> directionsToMove = currentBoard.getPusherPossibleDirectionsToMove();
        assertEquals(1, directionsToMove.size());
        assertEquals(UP, directionsToMove.get(0));
    }

    @Test
    public void getPusherPossibleDirectionsToMoveIfCanMakeMultipleMovesTest() {
        currentBoard.getPusher().setCurrentCoordinate(new Coordinate(3,3));
        List<Direction> directionsToMove = currentBoard.getPusherPossibleDirectionsToMove();

        assertEquals(3, directionsToMove.size());
        assertTrue(directionsToMove.contains(UP));
        assertTrue(directionsToMove.contains(LEFT));
        assertTrue(directionsToMove.contains(RIGHT));
    }

    @Test
    public void shouldReturnTrueIfCoordinateContainsBoxTest() {
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,1)));
    }

    @Test
    public void shouldReturnFalseIfCoordinateDoesNotContainsBoxTest() {
        assertFalse(currentBoard.coordinateContainsBox(new Coordinate(2,4)));
    }

    @Test
    public void shouldMoveBoxIfPossibleTest() {
        currentBoard.moveBoxIfPossible(new Coordinate(2,1), UP);

        assertEquals(new Coordinate(1,1), currentBoard.getBoxList().get(0).getCoordinate());
    }

    @Test
    public void shouldNotMoveBoxIfNotPossibleTest() {
        currentBoard.moveBoxIfPossible(new Coordinate(3,2), UP);

        assertEquals(new Coordinate(2,1), currentBoard.getBoxList().get(0).getCoordinate());
    }

    @Test
    public void shouldReturnTrueIfItIsFinalTileAndContainsBox() {
        currentBoard.getBoxList().get(0).setCoordinate(new Coordinate(1,1));
        assertTrue(currentBoard.isFinalTileAndContainsBox(new Coordinate(1,1)));
    }

    @Test
    public void shouldReturnFalseIfItIsNotFinalTileAndContainsBox() {
        assertFalse(currentBoard.isFinalTileAndContainsBox(new Coordinate(1,1)));
    }

    @Test
    public void gameHasNotBeenEndedIfThereIsAtLeastOneFinalTileWithoutBox() {
        assertFalse(currentBoard.gameHasEnded(currentBoard.getBoxList()));
    }

    @Test
    public void gamesHasBeenEndedIfBothBoxesAreOnFinalTiles() {
        currentBoard.getBoxList().get(0).setCoordinate(new Coordinate(1,1));
        currentBoard.getBoxList().get(1).setCoordinate(new Coordinate(1,4));
        assertTrue(currentBoard.gameHasEnded(currentBoard.getBoxList()));
    }

    @Test
    public void shouldMoveOnlyPusherTest() {
        HashMap<String, Coordinate> previousBoxesCoordinateMap = new HashMap<>();
        previousBoxesCoordinateMap.put(currentBoard.getBoxList().get(0).getLabel(), currentBoard.getBoxList().get(0).getCoordinate());
        previousBoxesCoordinateMap.put(currentBoard.getBoxList().get(1).getLabel(), currentBoard.getBoxList().get(1).getCoordinate());

        currentBoard.moveTo(UP);
        assertEquals(new Coordinate(3,1), currentBoard.getPusher().getCurrentCoordinate());
        assertEquals(previousBoxesCoordinateMap.get(currentBoard.getBoxList().get(0).getLabel()), currentBoard.getBoxList().get(0).getCoordinate());
        assertEquals(previousBoxesCoordinateMap.get(currentBoard.getBoxList().get(1).getLabel()), currentBoard.getBoxList().get(1).getCoordinate());
    }

    @Test
    public void shouldMovePusherAndBoxTest() {
        currentBoard.getPusher().setCurrentCoordinate(new Coordinate(3,1));
        HashMap<String, Coordinate> previousBoxesCoordinateMap = new HashMap<>();
        previousBoxesCoordinateMap.put(currentBoard.getBoxList().get(0).getLabel(), currentBoard.getBoxList().get(0).getCoordinate());
        previousBoxesCoordinateMap.put(currentBoard.getBoxList().get(1).getLabel(), currentBoard.getBoxList().get(1).getCoordinate());

        currentBoard.moveTo(UP);
        assertEquals(new Coordinate(2,1), currentBoard.getPusher().getCurrentCoordinate());
        assertEquals(new Coordinate(1,1), currentBoard.getBoxList().get(0).getCoordinate());
        assertEquals(previousBoxesCoordinateMap.get(currentBoard.getBoxList().get(1).getLabel()), currentBoard.getBoxList().get(1).getCoordinate());
    }

    @Test
    public void shouldNotMovePusherIfItCannotMove() {
        Coordinate previousCoordinate = currentBoard.getPusher().getCurrentCoordinate();
        HashMap<String, Coordinate> previousBoxesCoordinateMap = new HashMap<>();
        previousBoxesCoordinateMap.put(currentBoard.getBoxList().get(0).getLabel(), currentBoard.getBoxList().get(0).getCoordinate());
        previousBoxesCoordinateMap.put(currentBoard.getBoxList().get(1).getLabel(), currentBoard.getBoxList().get(1).getCoordinate());

        currentBoard.moveTo(DOWN);
        assertEquals(previousCoordinate, currentBoard.getPusher().getCurrentCoordinate());
        assertEquals(previousBoxesCoordinateMap.get(currentBoard.getBoxList().get(0).getLabel()), currentBoard.getBoxList().get(0).getCoordinate());
        assertEquals(previousBoxesCoordinateMap.get(currentBoard.getBoxList().get(1).getLabel()), currentBoard.getBoxList().get(1).getCoordinate());
    }


}
