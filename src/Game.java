import java.awt.*;

public class Game  {
    private Player currPlayer;
    Player player1;
    Player player2;
    private Integer rounds;
    Board board;

    private int grid[][];

    public Game() {
         board = new Board(this);
         grid = new int[board._ROWS_][board._COLS_];
         initialize();
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    private void initialize() {
        player1 = new Player("Spieler 1", Color.RED, 1,"X");
        player2 = new Player("Spieler 2", Color.GREEN, 2,"O");
        setCurrPlayer(player1);
        rounds = 0;
    }

    private void setCurrPlayer(Player player) {
        currPlayer = player;
        board.updateLabelPlayer(currPlayer);
    }

    private void incrementRounds() {
        rounds++;
        board.setLabelRounds(rounds);
    }

    public void exitButtonEvent() {
        board.dispose();
        System.exit(0);
    }

    public void evaluateFieldButton(int row, int col) {
        System.out.println("pressed index " + row + "," + col);
        //TODO prüfen falls es keine freien Felder mehr gibt, ist das Game zu ende
        //TODO = Unentschieden , message in Board anzeigen
        if (!checkFieldFree(row, col)) return;
        int firstFreeRow = checkFirstFreeRowInColumn(col);
        if (firstFreeRow >= 0) {
            System.out.printf("Putting marker in row %s, column %s\n", firstFreeRow, col);
            markField(firstFreeRow, col);
            incrementRounds();
            if (checkGameIsWon()) {
                System.out.printf("\n%s has won the game.", currPlayer.getName());
                //TODO clear the board
                //TODO show Won Label in Board
            } else {
                if (currPlayer.equals(player1)) {
                    setCurrPlayer(player2);
                } else {
                    setCurrPlayer(player1);
                }
            }
        }

    }

    private boolean checkGameIsWon() {
        System.out.println("Validating player: " + currPlayer.getName());
        if (checkVertical()) return true;
        if (checkHorizontal()) return true;
        if (checkDiagonal()) return true;
        return false;
    }

    private boolean checkVertical() {
        int hits = 0;
        for (int col = 0; col < board._COLS_; col++) {
            for (int row = 0; row < board._ROWS_; row++) {
                if (grid[row][col] == currPlayer.getId()) {
                    hits++;
                    System.out.println("checking column "+col+", hits: " + hits);
                } else hits = 0;
                if (hits == 4) return true;
            }
        }
        return false;
    }

    private boolean checkHorizontal() {
        for (int row = 0; row < board._ROWS_; row++) {
        //TODO
        }

        return false;
    }

    private boolean checkDiagonal() {
        //TODO
        return false;
    }


    private boolean checkFieldFree(int row, int col) {
        return grid[row][col] == 0;
    }

    private int checkFirstFreeRowInColumn(int col) {
        for (int row = board._ROWS_ - 1; row >= 0; row--) {
            if (getFieldValue(row, col) == 0)
                return row;
        }
        return -1;
    }

    private int getFieldValue(int row, int col) {
        return grid[row][col];
    }

    private void markField(int row, int col) {
        grid[row][col] = currPlayer.getId();
        board.setField(row,col,currPlayer.getMarker(), currPlayer.getColor());

    }
}
