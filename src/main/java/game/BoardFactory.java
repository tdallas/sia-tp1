package game;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class BoardFactory {

    public enum Level {TEST, EASY, MEDIUM, HARD1, HARD2, HARD3}

    @AllArgsConstructor
    private static class BoardGame {
        private final Set<Coordinate> finishTiles;
        private final Set<Coordinate> rockTiles;
        private final Set<Coordinate> emptyTiles;
        private final State state;
    }

    public static Board createBoardFromInputFile(String inputFile) {
        BoardGame boardGame = generateLevel(inputFile);
        if(boardGame != null){
            return new Board(boardGame.finishTiles,
                    boardGame.rockTiles,
                    boardGame.emptyTiles,
                    boardGame.state
            );
        }
        return null;
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
            case TEST:
                return generateLevel("maps/m1.txt");
            case EASY:
                return generateLevel("maps/m2.txt");
            case MEDIUM:
                return generateLevel("maps/m3.txt");
            case HARD1:
                return generateLevel("maps/m4.txt");
            case HARD2:
                return generateLevel("maps/m5.txt");
            case HARD3:
                return generateLevel("maps/m6.txt");

        }
        return null;
    }

    private static BoardGame generateLevel(final String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            Set<Coordinate> boxes = new HashSet<>();
            Set<Coordinate> finishTiles = new HashSet<>();
            Set<Coordinate> rockTiles = new HashSet<>();
            Set<Coordinate> emptyTiles = new HashSet<>();
            Coordinate pusher = null;
            int colCount = 0;
            int rowCount = 0;
            String line = null;
            while((line = reader.readLine()) != null) {
                line = line + "\n";
                colCount = 0;
                for(int i = 0; i < line.length(); i++) {
                    char item = line.charAt(i);
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
                            boxes.add(new Coordinate(rowCount, colCount++));
                            break;
                        case '@':
                            emptyTiles.add(new Coordinate(rowCount, colCount));
                            pusher = new Coordinate(rowCount, colCount++);
                            break;
                        case '\n':
                            rowCount++;
                            break;
                        default:
                            System.out.println("Error, invalid map character.");
                            return null;
                    }
                }
            }
            State state = new State(pusher, boxes);
            return new BoardGame(finishTiles, rockTiles, emptyTiles, state);
        } catch (Exception e) {
            System.out.println("Error, map filename invalid.");
            return null;
        }
    }
}
