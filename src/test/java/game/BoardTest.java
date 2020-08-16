package game;

import org.junit.Before;
import org.junit.Test;
import strategies.BoardFactory;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static game.Direction.*;
/*
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
        Coordinate to = currentBoard.getCoordinateToMoveTo(new Coordinate(4,1), UP, false);
        assertNotNull(to);
        assertEquals(new Coordinate(3,1), to);
    }

    @Test
    public void getNullIfTileDoesNotExistTest() {
        assertNull(currentBoard.getCoordinateToMoveTo(new Coordinate(10,10),Direction.DOWN, false));
    }

    @Test
    public void getNullIfTileExistsButItIsNotWalkableTest() {
        assertNull(currentBoard.getCoordinateToMoveTo(new Coordinate(4,1), Direction.DOWN, false));
    }

    @Test
    public void getPusherPossibleDirectionsToMoveIfCanMoveOnlyInOneDirectionTest() {
        currentBoard.getState().getPusher().setCoordinate(new Coordinate(4,1));
        List<Direction> directionsToMove = currentBoard.getPusherPossibleDirectionsToMove();
        assertEquals(1, directionsToMove.size());
        assertEquals(UP, directionsToMove.get(0));
    }

    @Test
    public void getPusherPossibleDirectionsToMoveIfCanMakeMultipleMovesTest() {
        currentBoard.getState().getPusher().setCoordinate(new Coordinate(3,3));
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

        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(1,1)));
    }

    @Test
    public void shouldNotMoveBoxIfNotPossibleTest() {
        currentBoard.moveBoxIfPossible(new Coordinate(3,2), UP);

        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,1)));
    }

    @Test
    public void shouldReturnTrueIfItIsFinalTileAndContainsBox() {
        currentBoard.moveBoxIfPossible(new Coordinate(2,1), UP);
        assertTrue(currentBoard.isFinalTileAndContainsBox(new Coordinate(1,1)));
    }

    @Test
    public void shouldReturnFalseIfItIsNotFinalTileAndContainsBox() {
        assertFalse(currentBoard.isFinalTileAndContainsBox(new Coordinate(1,1)));
    }

    @Test
    public void gameHasNotBeenEndedIfThereIsAtLeastOneFinalTileWithoutBox() {
        assertFalse(currentBoard.gameHasEnded());
    }

    @Test
    public void gamesHasBeenEndedIfBothBoxesAreOnFinalTiles() {
        currentBoard.moveBoxIfPossible(new Coordinate(2,1), UP);
        currentBoard.moveBoxIfPossible(new Coordinate(2,2), UP);
        currentBoard.moveBoxIfPossible(new Coordinate(1,2), RIGHT);
        currentBoard.moveBoxIfPossible(new Coordinate(1,3), RIGHT);
        assertTrue(currentBoard.gameHasEnded());
    }

    @Test
    public void shouldMoveOnlyPusherTest() {
        currentBoard.getState().getPusher().setCoordinate(new Coordinate(4,1));
        currentBoard.moveTo(UP);

        assertEquals(new Coordinate(3,1), currentBoard.getState().getPusher().getCoordinate());
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,1)));
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,2)));
    }

    @Test
    public void shouldMovePusherAndBoxTest() {
        currentBoard.getState().getPusher().setCoordinate(new Coordinate(3,1));

        currentBoard.moveTo(UP);
        assertEquals(new Coordinate(2,1), currentBoard.getState().getPusher().getCoordinate());
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(1,1)));
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,2)));
    }

    @Test
    public void shouldNotMovePusherIfItCannotMove() {
        currentBoard.getState().getPusher().setCoordinate(new Coordinate(4,1));

        currentBoard.moveTo(DOWN);
        assertEquals(new Coordinate(4,1), currentBoard.getState().getPusher().getCoordinate());
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,1)));
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,2)));
    }

    @Test
    public void stateShouldBeEqual() {
        Board board1 = BoardFactory.createBoard(BoardFactory.Level.EASY);
        Board board2 = BoardFactory.createBoard(BoardFactory.Level.EASY);

        assertEquals(board1.getState(), board2.getState());
    }

}
*/