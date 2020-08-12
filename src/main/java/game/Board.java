package game;

import game.tiles.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import strategies.Direction;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static strategies.Direction.UP;
import static strategies.Direction.DOWN;
import static strategies.Direction.RIGHT;
import static strategies.Direction.LEFT;

@AllArgsConstructor
@Getter
public class Board {
    private List<List<Tile>> matrix;
    private int rowSize;
    private int columnSize;
    private Pusher pusher;
    private List<Coordinate> finishPositions;
    private List<Box> boxList;

    private final Map<Direction, Coordinate> coordinateVectorMap = new HashMap<Direction, Coordinate>(){{
        put(UP, new Coordinate(0,1));
        put(DOWN, new Coordinate(0, -1));
        put(RIGHT, new Coordinate(1, 0));
        put(LEFT, new Coordinate(-1,0));
    }};

    private Tile getTileIfExists(final Coordinate coordinate) {
        final List<Tile> row = matrix.get(coordinate.getX());
        if (row != null && row.size() > 0) {
            return row.get(coordinate.getY());
        }
        return null;
    }

     private Coordinate getCoordinateToMoveTo(final Coordinate fromCoordinate, final Direction direction) {
        final Coordinate coordinateToMove = Coordinate.add(fromCoordinate, coordinateVectorMap.get(direction));
        final Tile tileToMove = getTileIfExists(coordinateToMove);
        if (tileToMove != null && tileToMove.isWalkable()) {
            return coordinateToMove;
        }
        return null;
     }

    public List<Coordinate> getPusherPosibleCoordinatesToMove() {
        return coordinateVectorMap
                .keySet()
                .parallelStream()
                .map(direction -> getCoordinateToMoveTo(pusher.getCurrentCoordinate(), direction))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public boolean coordinateContainsBox(final Coordinate coordinateToMove) {
         return boxList
                 .parallelStream()
                 .map(Box::getCoordinate)
                 .anyMatch(coordinate -> coordinate.equals(coordinateToMove));
    }

    /**
     * This function is always called after calling coordinateContainsBox iff that function returns true
     * @param coordinate
     * @return
     */
    private Box getBoxInCoordinate(final Coordinate coordinate) {
        return boxList
                .parallelStream()
                .filter(box -> box.getCoordinate().equals(coordinate))
                .collect(Collectors.toList()).get(0);
    }

    /**
     * This function moves pusher to desired direction.
     * If that direction also push a box, that move is also made
     * @param direction
     * @throws Exception
     */
    public void moveTo(final Direction direction) throws Exception {
        final Coordinate coordinateToMove = getCoordinateToMoveTo(pusher.getCurrentCoordinate(), direction);
        if (coordinateToMove != null) {
            if (coordinateContainsBox(coordinateToMove)) {
                final Coordinate coordinateToMoveBox = getCoordinateToMoveTo(coordinateToMove, direction);
                if (coordinateToMoveBox != null) {
                    getBoxInCoordinate(coordinateToMove).setCoordinate(coordinateToMoveBox);
                } else {
                    throw new Exception("Cant move there because box cant move there");
                }
            }
            pusher.setCurrentCoordinate(coordinateToMove);
        } else {
            throw new Exception("Cant move pusher to that coordinate");
        }
    }

}
