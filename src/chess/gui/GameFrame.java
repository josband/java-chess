package chess.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import chess.Game;
import chess.board.Board;
import chess.board.BoardManager;
import chess.board.Move;
import chess.board.Tile;
import chess.pieces.Alliance;
import chess.pieces.King;
import chess.pieces.Piece;

// New Territory, will implement when there is more functionality
public class GameFrame extends JFrame {
    private final Game game;
    private final BoardPanel boardPanel;
    private final BoardManager manager;
    private final Board board;

    private Tile sourceTile;
    private Tile destinationTile;
    private Piece humanMovedPiece;
    
    public GameFrame() {
        this.game = new Game();
        this.board = this.game.getBoard();
        this.manager = this.game.getManager();
        this.sourceTile = null;
        this.destinationTile = null;
        this.humanMovedPiece = null;
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY);
        getContentPane().setPreferredSize(new Dimension(640, 640));;
        setIconImage((new ImageIcon("./src/chess/pieces/piece PNGs/black-knight.png")).getImage());
        setLayout(null);
        setResizable(false);

        this.boardPanel = new BoardPanel();

        this.add(boardPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

// *************************************************************************************************************************************************************

    private class BoardPanel extends JPanel {
        List<TilePanel> boardTiles;
        
        public BoardPanel() {
            super(new GridLayout(8,8));
            boardTiles = new ArrayList<TilePanel>();
            addTilePanels();
            this.setSize(640, 640);
            this.setOpaque(true);
        }
    
        private void addTilePanels() {
            for (int i = 0; i < 8; i ++) {
                for (int j = 0; j < 8; j++) {
                    TilePanel newPanel = new TilePanel(board.get(i, j));
                    boardTiles.add(newPanel);
                    this.add(newPanel);
                }
            }
        }

        public void drawBoard(Board board) {
            removeAll();
            for (TilePanel tilePanel : boardTiles) {
                tilePanel.drawTile(board);
                add(tilePanel);
            }
            validate();
            repaint();
        }
    }

// *************************************************************************************************************************************************************

    private class TilePanel extends JPanel {
        private Tile tile;
        private int x;
        private int y;
        private GridBagConstraints constraints = new GridBagConstraints();
    
        private static final Color WHITE_COLOR = new Color(0xFFFBF1);
        private static final Color BLACK_COLOR = new Color(0x1234567);
        private static final ImageIcon BLACK_KING_CHECK = new ImageIcon("src/chess/pieces/piece PNGs/bKcheck.png");
        private static final ImageIcon WHITE_KING_CHECK = new ImageIcon("src/chess/pieces/piece PNGs/wKcheck.png");
        //private static final JLabel GREEN_DOT = new JLabel(new ImageIcon("./src/chess/pieces/piece PNGs/green-dot.png"));
    
        // Will need a JLabel of the piece Image Icon that will be centered
        public TilePanel(Tile tile) {
            super(new GridBagLayout());
            this.tile = tile;
            this.x = tile.getX();
            this.y = tile.getY();
            setColor();
            assignTilePiece();
    
            addMouseListener(new MouseInputListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        sourceTile = null;
                        destinationTile = null;
                        humanMovedPiece = null;
                    } else if (SwingUtilities.isLeftMouseButton(e)) {
                        if (sourceTile == null) {
                            // First click
                            sourceTile = tile;
                            humanMovedPiece = sourceTile.getPiece();
                            // If no piece selected or selected wrong color piece or the piece doesn't have any legal moves, go back to null
                            if (humanMovedPiece == null || humanMovedPiece.getAlliance() != game.getTurn() || pieceLegalMoves(board).isEmpty()) {
                                sourceTile = null;
                                humanMovedPiece = null;
                            }
                        } else {
                            // Second click
                            if (tile == sourceTile) {
                                sourceTile = null;
                                humanMovedPiece = null;
                            } else {
                                destinationTile = tile;
                                // Check if there is a legal move for the human moved piece, reset destination if not a legal destination selection or legal and move is executed
                                executeIfLegalMove();
                                destinationTile = null;
                            }
                            
                        }
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            boardPanel.drawBoard(board);
                        }
                    });
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    return;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    return;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    return;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    return;
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    return;
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    return;
                }
            });
            
            this.setBounds(x * 80, y * 80, 80, 80);
            this.setOpaque(true);
        }

        public void drawTile(Board board) {
            setColor();
            
            highlightLegalMoves(board);
            assignTilePiece();
            validate();
            repaint();
        }

        private void highlightLegalMoves(Board board) {
            this.removeAll();
            if (true) { // In case I want this to be an option later on
                for (Move move : pieceLegalMoves(board)) {
                    if (move.getTo() == this.tile) {
                        constraints.gridx = GridBagConstraints.CENTER;
                        constraints.gridy = GridBagConstraints.CENTER;
                        this.add(new JLabel(new ImageIcon("./src/chess/pieces/piece PNGs/green-dot.png")), constraints);
                    }
                }
            }
        }

        private List<Move> pieceLegalMoves(Board board) {
            List<Move> legalMoves = new ArrayList<Move>();
            if (humanMovedPiece != null && humanMovedPiece.getAlliance() == game.getTurn()) {
                for (Move move : humanMovedPiece.calculateLegalMoves(board, sourceTile)) {
                    // If the move pseudo-legal move is FULLY legal, add it
                    if (manager.testMove(move)) {
                        legalMoves.add(move);
                    }
                }
            }
            return legalMoves;
        }

        private void executeIfLegalMove() {
            for (Move move : pieceLegalMoves(board)) {
                // If the legal move destination is our selected destination, execute the move
                if (move.getTo() == destinationTile) {
                    manager.executeMove(move, false);
                    game.endTurn();
                    checkForGameOver();
                    // Reset source and tile
                    sourceTile = null;
                    humanMovedPiece = null;
                    return;
                }
            }
        }

        private void checkForGameOver() {
            if (manager.isStaleMated(game.getTurn())) {
                System.out.println("Stalemate");
                new GameOverFrame("Stalemate");
            }
            else if (manager.isMated(game.getTurn())) {
                System.out.println("Checkmate");
                new GameOverFrame("Checkmate");
            } 
        }
    
        private void setColor() {
            if ((x + y) % 2 == 0) {
                this.setBackground(WHITE_COLOR);
            } else {
                this.setBackground(BLACK_COLOR);
            }
        }
    
        private void assignTilePiece() {
            // this.removeAll();
            JLabel label = new JLabel();
            if (tile.getPiece() != null) {
                if (tile.getPiece() instanceof King && tile.getPiece().getAlliance() == game.getTurn() 
                    && manager.isChecked(game.getTurn())) {
                    ImageIcon img = game.getTurn() == Alliance.WHITE ? WHITE_KING_CHECK : BLACK_KING_CHECK;
                    label.setIcon(img);
                } else {
                    label.setIcon(tile.getPiece().Image());
                }
                label.setLayout(null);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);;
            }
            
            constraints.gridx = GridBagConstraints.CENTER;
            constraints.gridy = GridBagConstraints.CENTER;
            this.add(label, constraints);
        }
    }
}
