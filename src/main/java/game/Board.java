package game;

import game.tiles.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import strategies.utils.Step;

import java.util.*;
import java.util.stream.Collectors;

import static game.Direction.*;

@AllArgsConstructor
@Getter
public class Board {
    private List<List<Tile>> matrix;
    private List<Coordinate> finishPositions;
    private State initialState;

    public static final Map<Direction, Coordinate> coordinateVectorMap = new HashMap<>() {{
        put(UP, new Coordinate(-1, 0));
        put(DOWN, new Coordinate(1, 0));
        put(RIGHT, new Coordinate(0, 1));
        put(LEFT, new Coordinate(0, -1));
    }};

    protected Tile getTileIfExists(final Coordinate coordinate) {
        if (coordinate.getX() >= matrix.size() || coordinate.getX() < 0) {
            return null;
        }
        final List<Tile> row = matrix.get(coordinate.getX());
        if (row != null && row.size() > 0) {
            if (coordinate.getY() >= row.size() || coordinate.getY() < 0) {
                return null;
            }
            return row.get(coordinate.getY());
        }
        return null;
    }

    /**
     * Returns coordinate to move if it exists and if walkable, otherwise null
     *
     * @param fromCoordinate
     * @param direction
     * @return
     */
    protected Coordinate getPusherCoordinateToMoveTo(final Coordinate fromCoordinate, final Direction direction, final State state) {
        final Coordinate firstCoordinateNext = Coordinate.add(fromCoordinate, coordinateVectorMap.get(direction));
        final Coordinate secondCoordinateNext = Coordinate.add(firstCoordinateNext, coordinateVectorMap.get(direction));
        final Tile firstTileNext = getTileIfExists(firstCoordinateNext);
        final Tile secondTileNext = getTileIfExists(secondCoordinateNext);
        if(firstTileNext != null){
            if(firstTileNext.isWalkable()){
                if(!coordinateContainsBox(firstCoordinateNext, state)){
                    return firstCoordinateNext;
                }
                else if(secondTileNext != null && !coordinateContainsBox(secondCoordinateNext, state)){
                    return firstCoordinateNext;
                }
            }
        }
        return null;
    }

    protected Coordinate getBoxCoordinateToMoveTo(final Coordinate fromCoordinate, final Direction direction, final State state) {
        if(!coordinateContainsBox(fromCoordinate, state)){
            return null;
        }
        final Coordinate coordinateNext = Coordinate.add(fromCoordinate, coordinateVectorMap.get(direction));
        final Tile tileNext = getTileIfExists(coordinateNext);
        if(tileNext != null){
            if(tileNext.isWalkable()){
                if(!coordinateContainsBox(coordinateNext, state)){
                    return coordinateNext;
                }
            }
        }
        return null;
    }

    public List<Direction> getPusherPossibleDirectionsToMove(final State state) {
        return coordinateVectorMap
                .keySet()
                .parallelStream()
                .filter(direction -> getPusherCoordinateToMoveTo(state.getPusher().getCoordinate(), direction, state) != null)
                .collect(Collectors.toList());
    }

    public boolean coordinateContainsBox(final Coordinate coordinateToMove, final State state) {
        return state.getBoxes()
                .parallelStream()
                .map(Box::getCoordinate)
                .anyMatch(coordinate -> coordinate.equals(coordinateToMove));
    }

    /**
     * This function is always called after calling coordinateContainsBox iff that function returns true
     *
     * @param coordinate
     * @return
     */
    public Box getBoxInCoordinate(final Coordinate coordinate, final State state) {
        List<Box> boxToReturn = state.getBoxes()
                .parallelStream()
                .filter(box -> box.getCoordinate().equals(coordinate))
                .collect(Collectors.toList());
        if (boxToReturn.size() == 0) return null;
        return boxToReturn.get(0);
    }

    public State moveBoxIfPossible(final Coordinate coordinate, final Direction direction, final State state) {
        if (coordinateContainsBox(coordinate, state)) {
            final Coordinate coordinateToMoveBox = getBoxCoordinateToMoveTo(coordinate, direction, state);
            if (coordinateToMoveBox != null) {
                Box box = getBoxInCoordinate(coordinate, state);
                if (box != null) {
                    State newState = new State(state);
                    newState.getBoxes().remove(box);
                    newState.getBoxes().add(new Box(box.getLabel(), coordinateToMoveBox));
                    return newState;
                }
            }
        }
        return new State(state);
    }

    /**
     * This function moves pusher to desired direction.
     * If that direction also push a box, that move is also made
     *
     * @param direction
     * @throws Exception
     */
    public State moveTo(final Direction direction, final State state) {
        final Coordinate coordinateToMove = getPusherCoordinateToMoveTo(state.getPusher().getCoordinate(), direction, state);
        if (coordinateToMove != null) {
            if (!isFinalTileAndContainsBox(coordinateToMove, state)) {
                State newState = moveBoxIfPossible(coordinateToMove, direction, state);
                newState.getPusher().setCoordinate(coordinateToMove);
                return newState;
            } else {
                System.out.println("ROMPEMOS");
                // FIXME THROW AN ERROR or something like that
            }
        }
        return null;
    }

    protected boolean isFinalTileAndContainsBox(Coordinate coordinateToMove, final State state) {
        Tile tile = getTileIfExists(coordinateToMove);
        return tile != null && tile.isFinalTile() && getBoxInCoordinate(coordinateToMove, state) != null;
    }

    /**
     * returns true if a given boxesList are in final spots, false otherwise
     *
     * @return
     */
    public boolean gameHasEnded(final State state) {
        return state.getBoxes()
                .parallelStream()
                .map(box -> {
                    final Tile boxTile = getTileIfExists(box.getCoordinate());
                    return boxTile != null && boxTile.isFinalTile();
                })
                .reduce(true, (acum, current) -> acum && current);
    }

    // TODO

    /**
     * returns true if pusher is in deadlock, false otherwise
     *
     * @return
     */
    public boolean isDeadlock() {
        return false;
    }

}
