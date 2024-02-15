package presentation.design;

import javax.swing.*;
import java.awt.*;

public class MainBackground extends JPanel {
    private ImageIcon background;
    public MainBackground(GridBagLayout grid)
    {
        super(grid);
        background = new ImageIcon("src/main/image/main.jpg");
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g) ;
        System.out.println();
        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}

