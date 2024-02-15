package presentation.design;

import javax.swing.*;
import java.awt.*;

public class CoolButton extends JButton {
    private Color foreground;
    private Color background;
    private ImageIcon icon;
    private String title;
    public CoolButton(Color foreground,Color background, ImageIcon i, String t)
    {
        this.foreground = foreground;
        this.background = background;
        icon = i;
        title = t;
        style();
    }
    public CoolButton(Color foreground,Color background, String t)
    {
        this.foreground = foreground;
        this.background = background;
        icon = null;
        title = t;
        style();
    }

    void style()
    {
        this.setForeground(foreground);
        this.setBackground(background);
        if(icon!=null) {
            this.setIcon(icon);
        }
        this.setText(title);
        this.setFont(new Font("Courier New", Font.BOLD, 16));

        this.setMargin(new Insets(10, 20, 10, 20));

    }
}
