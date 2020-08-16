package game;

import game.tiles.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import strategies.utils.Step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static game.Direction.*;

@AllArgsConstructor
@Getter
public class Board {
    private List<List<Tile>> matrix;
    private List<Coordinate> finishPositions;
    private State state;

    private static final Map<Direction, Coordinate> coordinateVectorMap = new HashMap<>() {{
        put(UP, new Coordinate(-1, 0));
        put(DOWN, new Coordinate(1, 0));
        put(RIGHT, new Coordinate(0, 1));
        put(LEFT, new Coordinate(0, -1));
    }};

    public void setPusher(final State state){
        this.state = state;
    }

    protected Tile getTileIfExists(final Coordinate coordinate) {
        if (coordinate.getX() >= matrix.size()) {
            return null;
        }
        final List<Tile> row = matrix.get(coordinate.getX());
        if (row != null && row.size() > 0) {
            if (coordinate.getY() >= row.size()) {
                return null;
            }
            return row.get(coordinate.getY());
        }
        return null;
    }

    /**
     * Returns coordinate to move if it exists and if walkable, otherwise null
     * @param fromCoordinate
     * @param direction
     * @return
     */
    protected Coordinate getCoordinateToMoveTo(final Coordinate fromCoordinate, final Direction direction, boolean isBoxMovement) {
        final Coordinate coordinateToMove = Coordinate.add(fromCoordinate, coordinateVectorMap.get(direction));
        final Tile tileToMove = getTileIfExists(coordinateToMove);
        if (tileToMove != null && tileToMove.isWalkable()) {
            if (getBoxInCoordinate(coordinateToMove) != null) {
                if(isBoxMovement) {
                    return null;
                }
                else{
                    final Coordinate nextCoordinate = Coordinate.add(coordinateToMove, coordinateVectorMap.get(direction));
                    final Tile nextTile = getTileIfExists(nextCoordinate);
                    if(nextTile != null && nextTile.isWalkable() && getBoxInCoordinate(nextCoordinate) == null){
                        return coordinateToMove;
                    }
                    else{
                        return null;
                    }
                }
            }
            return coordinateToMove;
        }
        return null;
    }

    public List<Direction> getPusherPossibleDirectionsToMove() {
        return coordinateVectorMap
                .keySet()
                .parallelStream()
                .filter(direction -> getCoordinateToMoveTo(state.getPusher().getCoordinate(), direction, false) != null)
                .collect(Collectors.toList());
    }

    public boolean coordinateContainsBox(final Coordinate coordinateToMove) {
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
    private Box getBoxInCoordinate(final Coordinate coordinate) {
        List<Box> boxToReturn = state.getBoxes()
                .parallelStream()
                .filter(box -> box.getCoordinate().equals(coordinate))
                .collect(Collectors.toList());
        if (boxToReturn.size() == 0) return null;
        return boxToReturn.get(0);
    }

    public void moveBoxIfPossible(final Coordinate coordinate, final Direction direction) {
        if (coordinateContainsBox(coordinate)) {
            final Coordinate coordinateToMoveBox = getCoordinateToMoveTo(coordinate, direction, true);
            if (coordinateToMoveBox != null) {
                Box box = getBoxInCoordinate(coordinate);
                if(box != null) {
                    box.setCoordinate(coordinateToMoveBox);
                }
            }
        }
    }

    /**
     * This function moves pusher to desired direction.
     * If that direction also push a box, that move is also made
     *
     * @param direction
     * @throws Exception
     */
    public void moveTo(final Direction direction) {
        final Coordinate coordinateToMove = getCoordinateToMoveTo(state.getPusher().getCoordinate(), direction, false);
        if (coordinateToMove != null) {
            if (!isFinalTileAndContainsBox(coordinateToMove)) {
                moveBoxIfPossible(coordinateToMove, direction);
                state.getPusher().setCoordinate(coordinateToMove);
            } else {
                // FIXME THROW AN ERROR or something like that
            }
        }
    }

    protected boolean isFinalTileAndContainsBox(Coordinate coordinateToMove) {
        Tile tile = getTileIfExists(coordinateToMove);
        return tile != null && tile.isFinalTile() && getBoxInCoordinate(coordinateToMove) != null;
    }

    /**
     * returns true if a given boxesList are in final spots, false otherwise
     *
     * @return
     */
    public boolean gameHasEnded() {
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
