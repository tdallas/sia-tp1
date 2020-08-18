package strategies;

import game.*;
import game.tiles.EmptyTile;
import game.tiles.FinishTile;
import game.tiles.RockTile;
import game.tiles.Tile;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoardFactory {

    final static String hard =  "  XXXX \n" +
                                "  X@ X \n" +
                                "XXX**XX\n" +
                                "X    .X\n" +
                                "X   * X\n" +
                                "XX. XXX\n" +
                                " X. X  \n" +
                                " XXXX  ";

    final static String medium =    "XXXXXXX\n" +
                                    "X.  . X\n" +
                                    "X**   X\n" +
                                    "X     X\n" +
                                    "X@XX  X\n" +
                                    "XXXXXXX";

    final static String hard2 =     " XXXX\n" +
                                    " X  XXX\n" +
                                    " X*   X\n" +
                                    "XX@*. X\n" +
                                    "X *X.XX\n" +
                                    "X   .X\n" +
                                    "X    X\n" +
                                    "X    X\n" +
                                    "XXXXXX";

    final static String easy =  "XXX\n" +
                                "X.X\n" +
                                "X X\n" +
                                "X*X\n" +
                                "X@X\n" +
                                "XXX";

    final static String test =  "XXXXX\n" +
                                "X *.X\n" +
                                "X @XX\n" +
                                "XXXXXX";

    public static enum Level {EASY, MEDIUM, HARD2, HARD, TEST}

    @AllArgsConstructor
    private static class BoardGame {
        private final Set<Coordinate> finishTiles;
        private final Set<Coordinate> rockTiles;
        private final Set<Coordinate> emptyTiles;
        private final State state;
    }

    public static Board createBoard(final Level level) {
        BoardGame boardGame = generateGame(level);

        return new Board(boardGame.finishTiles,
                boardGame.rockTiles,
                boardGame.emptyTiles,
                boardGame.state
        );
    }

    private static BoardGame generateGame(final Level level) {
        switch (level) {
            case EASY:
                return generateLevel(easy);
            case MEDIUM:
                return generateLevel(medium);
            case HARD2:
                return generateLevel(hard2);
            case HARD:
                return generateLevel(hard);
            case TEST:
                return generateLevel(test);
        }
        return null;
    }

    private static BoardGame generateLevel(final String level) {
        Set<Box> boxes = new HashSet<>();
        Set<Coordinate> finishTiles = new HashSet<>();
        Set<Coordinate> rockTiles = new HashSet<>();
        Set<Coordinate> emptyTiles = new HashSet<>();
        Pusher pusher = new Pusher();
        State state = new State(pusher, boxes);
        boolean newLine = true;
        int colCount = 0;
        int rowCount = 0;
        int boxCount = 0;
        for (int i = 0; i < level.length(); i++) {
            if (newLine) {
                colCount = 0;
                newLine = false;
            }
            char item = level.charAt(i);
            switch (item) {
                case 'X':
                    rockTiles.add(new Coordinate(rowCount, colCount++));
                    break;
                case ' ':
                    emptyTiles.add(new Coordinate(rowCount, colCount++));
                    break;
                case '.':
                    finishTiles.add(new Coordinate(rowCount, colCount++));
                    break;
                case '*':
                    emptyTiles.add(new Coordinate(rowCount, colCount));
                    boxes.add(new Box("Box" + boxCount++, new Coordinate(rowCount, colCount++)));
                    break;
                case '@':
                    emptyTiles.add(new Coordinate(rowCount, colCount));
                    pusher.setCoordinate(new Coordinate(rowCount, colCount++));
                    break;
                case '\n':
                    newLine = true;
                    rowCount++;
                    break;
                default:
                    break;
            }

        }
        return new BoardGame(finishTiles, rockTiles, emptyTiles, state);
    }
}
