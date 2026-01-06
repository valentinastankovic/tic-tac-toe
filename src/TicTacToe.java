import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame form = new JFrame("TIC-TAC-TOE");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        form.setVisible(true);
        form.setSize(boardWidth, boardHeight);
        form.setLocationRelativeTo(null);
        form.setResizable(false);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("TIC-TAC-TOE");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        form.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        form.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                // tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver)
                            return;
                        JButton tile = (JButton) e.getSource();

                        if (tile.getText().isEmpty()) {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                if (currentPlayer == playerX) {
                                    currentPlayer = playerO;
                                } else {
                                    currentPlayer = playerX;
                                }
                                textLabel.setText(currentPlayer + "'s turn!");
                            }

                        }

                    }
                });
            }
        }

    }

    void checkWinner() {
        // horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText().isEmpty()) {
                continue;
            }
            if (board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;

                return;
            }

        }
        // vertical
        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText().isEmpty()) {
                continue;
            }
            if (board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()) {
                for (int j = 0; j < 3; j++) {
                    setWinner(board[j][c]);
                }
                gameOver = true;

                return;
            }
        }
        // diagonal
        if (board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() &&
                !board[0][0].getText().isEmpty()) {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        // anti-diagonal
        if (board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() &&
                !board[0][2].getText().isEmpty()) {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);

            gameOver = true;
            return;

        }
        //tie
        if (turns == 9){
            for(int i=0; i<3; i++){
                for(int j = 0; j<3; j++){
                    setTie(board[i][j]);
                }
            }
            gameOver = true;
            return;
        }

    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.PINK);
        textLabel.setText(currentPlayer + " is the winner!");

    }

    void setTie(JButton tile){
        tile.setForeground(Color.CYAN);
        textLabel.setText("It's tie.");
    }

}