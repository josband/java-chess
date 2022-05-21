package chess.gui;

import java.awt.*;

import javax.swing.*;

public class GameOverFrame extends JFrame {
    public GameOverFrame(String string) {
        super();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/chess/pieces/piece PNGs/bK.png").getImage());
        this.setResizable(false);
        if (string.equals("Checkmate"))
            this.getContentPane().setBackground(new Color(0xED2939));
        else
            this.getContentPane().setBackground(Color.DARK_GRAY);
        EndPanel panel = new EndPanel(string);
        panel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        panel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        this.add(panel);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private class EndPanel extends JPanel {
        public EndPanel(String string) {
            super();
            this.setLayout(new GridBagLayout());
            this.setPreferredSize(new Dimension(300, 100));
            JLabel label = new JLabel();
            label.setText(string + "!");
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setForeground(Color.WHITE);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);

            this.add(label);
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            this.setOpaque(false); // So the background color of the frame can be seen
        }
    }
}
