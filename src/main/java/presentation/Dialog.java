package presentation;

import javax.swing.*;
import java.awt.*;

public class Dialog {

    private static final Color background = new Color(	118, 67, 24);

    /**
     *
     * @param title
     * @param message
     */
    public static void doDialog(String title, String message)
    {
        JDialog dial = new JDialog(new JFrame(title), title);
        JLabel messageLabel  = new JLabel(message);
        dial.add(messageLabel);
        dial.setSize(new Dimension(300,300));
        dial.setVisible(true);
        dial.setBackground(background);
    }
}
