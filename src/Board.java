import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board extends JFrame implements ActionListener {

    private Game game;
    public final Integer _ROWS_ = 7;
    public final Integer _COLS_ = 7;
    private final JButton exitButton;
    private final JPanel centerPanel;
    private final JButton[][] fieldButtons = new JButton[_ROWS_][_COLS_];
    private final JLabel labelPlayer;
    private final JLabel labelRounds;
    public Board(Game game) throws HeadlessException {
        setGameController(game);
        setTitle("4 Gewinnt");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLayout(null);

        exitButton = new JButton("Ende");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        exitButton.setSize(100, 30);
        exitButton.setLocation(770, 520);
        exitButton.addActionListener(this);

        labelPlayer = new JLabel();
        labelPlayer.setFont(new Font("Arial", Font.BOLD, 18));
        labelPlayer.setLocation(10,10);
        labelPlayer.setSize(700, 30);

        labelRounds = new JLabel();
        labelRounds.setFont(new Font("Arial", Font.PLAIN, 16));
        labelRounds.setLocation(10,520);
        labelRounds.setSize(200, 30);
        labelRounds.setText("Runden: 0");

        centerPanel = new JPanel();
        GridLayout grid = new GridLayout(_ROWS_ ,_COLS_);
        centerPanel.setLayout(grid);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setSize(860,450);
        centerPanel.setLocation(10,50);

        for (int row = 0; row < _ROWS_; row++) {
            for (int col = 0; col < _COLS_; col++){
                JButton field = new JButton();
                field.setBorder(BorderFactory
                        .createLineBorder(Color.BLACK));
                field.addActionListener(this);
                field.setFont(new Font("Arial", Font.PLAIN, 32));
                fieldButtons[row][col] = field;
                centerPanel.add(field);

            }
        }

        add(exitButton);
        add(labelPlayer);
        add(labelRounds);
        add(centerPanel);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(exitButton)) {
            getGameController().exitButtonEvent();
        } else {
            JButton activeField = (JButton) e.getSource();
            for (int row = 0; row < _ROWS_; row++) {
                for (int col = 0; col < _COLS_; col++) {
                    if (fieldButtons[row][col] == activeField) {
                        getGameController().evaluateFieldButton(row, col);
                        return;
                    }
                }
            }
        }
    }

    public void updateLabelPlayer(Player currPlayer) {
        labelPlayer.setText("Aktueller Spieler: " + currPlayer.getName());
        labelPlayer.setForeground(currPlayer.getColor());
    }

    public void setLabelRounds(Integer count) {
        labelRounds.setText("Runden: " + count.toString());
    }

    private Game getGameController() {
        return game;
    }

    private void setGameController(Game game) {
        this.game = game;
    }

    public JButton getField(int row, int col)
    {
        return fieldButtons[row][col];
    }

    public void setField(int row, int col, String value, Color color) {
        JButton field = getField(row, col);
        field.setText(value);
        field.setForeground(color);
    }

}