package game;

import org.junit.Before;
import org.junit.Test;
import strategies.BoardFactory;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static game.Direction.*;

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
        Coordinate to = currentBoard.getPusherCoordinateToMoveTo(new Coordinate(4,1), UP, currentBoard.getInitialState());
        assertNotNull(to);
        assertEquals(new Coordinate(3,1), to);
    }

    @Test
    public void getNullIfTileDoesNotExistTest() {
        assertNull(currentBoard.getPusherCoordinateToMoveTo(new Coordinate(10,10),Direction.DOWN, currentBoard.getInitialState()));
    }

    @Test
    public void getNullIfTileExistsButItIsNotWalkableTest() {
        assertNull(currentBoard.getPusherCoordinateToMoveTo(new Coordinate(4,1), Direction.DOWN, currentBoard.getInitialState()));
    }

    @Test
    public void getPusherPossibleDirectionsToMoveIfCanMoveOnlyInOneDirectionTest() {
        currentBoard.getInitialState().getPusher().setCoordinate(new Coordinate(4,1));
        List<Direction> directionsToMove = currentBoard.getPusherPossibleDirectionsToMove(currentBoard.getInitialState());
        assertEquals(1, directionsToMove.size());
        assertEquals(UP, directionsToMove.get(0));
    }

    @Test
    public void getPusherPossibleDirectionsToMoveIfCanMakeMultipleMovesTest() {
        currentBoard.getInitialState().getPusher().setCoordinate(new Coordinate(3,3));
        List<Direction> directionsToMove = currentBoard.getPusherPossibleDirectionsToMove(currentBoard.getInitialState());

        assertEquals(3, directionsToMove.size());
        assertTrue(directionsToMove.contains(UP));
        assertTrue(directionsToMove.contains(LEFT));
        assertTrue(directionsToMove.contains(RIGHT));
    }

    @Test
    public void shouldReturnTrueIfCoordinateContainsBoxTest() {
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,1), currentBoard.getInitialState()));
    }

    @Test
    public void shouldReturnFalseIfCoordinateDoesNotContainsBoxTest() {
        assertFalse(currentBoard.coordinateContainsBox(new Coordinate(2,4), currentBoard.getInitialState()));
    }

    @Test
    public void shouldMoveBoxIfPossibleTest() {
        State newState = currentBoard.moveBoxIfPossible(new Coordinate(2,1), UP, currentBoard.getInitialState());

        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(1,1), newState));
    }

    @Test
    public void shouldNotMoveBoxIfNotPossibleTest() {
        State newState = currentBoard.moveBoxIfPossible(new Coordinate(3,2), UP, currentBoard.getInitialState());

        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,1), newState));
    }

    @Test
    public void shouldReturnTrueIfItIsFinalTileAndContainsBox() {
        State newState = currentBoard.moveBoxIfPossible(new Coordinate(2,1), UP, currentBoard.getInitialState());
        assertTrue(currentBoard.isFinalTileAndContainsBox(new Coordinate(1,1), newState));
    }

    @Test
    public void shouldReturnFalseIfItIsNotFinalTileAndContainsBox() {
        assertFalse(currentBoard.isFinalTileAndContainsBox(new Coordinate(1,1), currentBoard.getInitialState()));
    }

    @Test
    public void gameHasNotBeenEndedIfThereIsAtLeastOneFinalTileWithoutBox() {
        assertFalse(currentBoard.gameHasEnded(currentBoard.getInitialState()));
    }

    @Test
    public void gamesHasBeenEndedIfBothBoxesAreOnFinalTiles() {
        State newState = currentBoard.moveBoxIfPossible(new Coordinate(2,1), UP, currentBoard.getInitialState());
        newState = currentBoard.moveBoxIfPossible(new Coordinate(2,2), UP, newState);
        newState = currentBoard.moveBoxIfPossible(new Coordinate(1,2), RIGHT, newState);
        newState = currentBoard.moveBoxIfPossible(new Coordinate(1,3), RIGHT, newState);
        assertTrue(currentBoard.gameHasEnded(newState));
    }

    @Test
    public void shouldMoveOnlyPusherTest() {
        currentBoard.getInitialState().getPusher().setCoordinate(new Coordinate(4,1));
        State newState = currentBoard.moveTo(UP, currentBoard.getInitialState());

        assertEquals(new Coordinate(3,1), newState.getPusher().getCoordinate());
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,1), newState));
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,2), newState));
    }

    @Test
    public void shouldMovePusherAndBoxTest() {
        currentBoard.getInitialState().getPusher().setCoordinate(new Coordinate(3,1));

        State newState = currentBoard.moveTo(UP, currentBoard.getInitialState());
        assertEquals(new Coordinate(2,1),newState.getPusher().getCoordinate());
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(1,1), newState));
        assertTrue(currentBoard.coordinateContainsBox(new Coordinate(2,2), newState));
    }

    @Test
    public void shouldNotMovePusherIfItCannotMove() {
        currentBoard.getInitialState().getPusher().setCoordinate(new Coordinate(4,1));

        State newState = currentBoard.moveTo(DOWN, currentBoard.getInitialState());
        assertNull(newState);
    }

    @Test
    public void shouldNotMoveAnythingIfPusherIsTryingToMoveTwoConsecutiveBoxes() {
        currentBoard.getInitialState().getPusher().setCoordinate(new Coordinate(2,3));
        State newState = currentBoard.moveTo(LEFT, currentBoard.getInitialState());
        assertNull(newState);
    }

    @Test
    public void shouldNotBeAValidMoveIfPusherIsTryingToMoveTwoConsecutiveBoxes() {
        currentBoard.getInitialState().getPusher().setCoordinate(new Coordinate(2,3));

        List<Direction> possibleMovements = currentBoard.getPusherPossibleDirectionsToMove(currentBoard.getInitialState());
        assertFalse(possibleMovements.contains(LEFT));
        assertTrue(possibleMovements.contains(RIGHT));
        assertTrue(possibleMovements.contains(UP));
        assertTrue(possibleMovements.contains(DOWN));

    }

    @Test
    public void stateShouldBeEqual() {
        Board board1 = BoardFactory.createBoard(BoardFactory.Level.EASY);
        Board board2 = BoardFactory.createBoard(BoardFactory.Level.EASY);

        assertEquals(board1.getInitialState(), board2.getInitialState());
    }

}
