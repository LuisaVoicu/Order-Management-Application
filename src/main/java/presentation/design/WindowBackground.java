package presentation.design;

import javax.swing.*;
import java.awt.*;

public class WindowBackground extends JPanel {
    private ImageIcon background;
    public WindowBackground(GridBagLayout grid, String path)
    {
        super(grid);
        background = new ImageIcon(path);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
}
