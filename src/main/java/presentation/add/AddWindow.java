package presentation.add;

import presentation.design.CoolButton;
import presentation.model.window.AbstractWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddWindow extends  JFrame{
    private static final Color foreground = Color.white;
    private static final Color background = new Color(	118, 67, 24);
    private static final Color background2 = new Color(	230, 219, 193);

    private AbstractWindow view; //todo schimba nume sugestiv
    private String title;
    private JPanel panel;
    private JPanel[] miniPanels;
    private JTextField[] textFields;
    private JLabel[] labelFields;
    private ArrayList<String> fieldsTable;
    private JButton addButton;
    public ArrayList<String> infoAdd;

    /**
     *
     * @param view
     * @param title
     * @param fields
     */
    public AddWindow (AbstractWindow view, String title, ArrayList<String> fields)
    {
        this.view = view;
        this.title = title;
        fieldsTable = fields;

        init();
        setWindow();

        this.add(panel);
        this.setTitle(title);
        this.setSize(500,310);
        this.setVisible(true);
        this.addButton(new AddButton());

    }

    /**
     *
     */
    private void init()
    {
        textFields = new JTextField[fieldsTable.size()];
        labelFields = new JLabel[fieldsTable.size()];
        panel = new JPanel(new GridLayout(3,0));
        miniPanels = new JPanel[fieldsTable.size()+1];

        for(int i = 0 ; i < fieldsTable.size(); i++)
        {
            textFields[i] = new JTextField();
            textFields[i].setPreferredSize(new Dimension(200,20));
            textFields[i].setFont(new Font("Courier New", Font.BOLD, 14));
            labelFields[i] = new JLabel();
            labelFields[i].setFont(new Font("Courier New", Font.BOLD, 14));
            miniPanels[i] = new JPanel();
            miniPanels[i].setBackground(background2);
        }
        miniPanels[fieldsTable.size()] = new JPanel();
        miniPanels[fieldsTable.size()].setBackground(background2);
        addButton = new CoolButton(foreground,background,"Add");
        addButton.setPreferredSize(new Dimension(100,30));
        miniPanels[fieldsTable.size()].add(addButton);

    }

    /**
     *
     */
    private void setWindow()
    {
        int index=0;
        for(String s : fieldsTable){
            System.out.println(s);
            labelFields[index].setText(s);
            miniPanels[index].setLayout(new FlowLayout());
            miniPanels[index].add(labelFields[index]);
            miniPanels[index].add(textFields[index]);
            panel.add(miniPanels[index]);
            index++;
        }
        panel.add(miniPanels[index]);
    }

    /**
     *
     * @param listener
     */
    public void addButton(ActionListener listener){
        addButton.addActionListener(listener);
    }

    /**
     *
     * @param infoAdd
     * @param view
     */
    public void doAdd(ArrayList<String> infoAdd, AbstractWindow view)
    {
        System.out.println("i'm in main AddWindow");
    }
    class AddButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("am apasat pe add in window!!!");
            infoAdd = new ArrayList<String>();
            for(int i = 0 ; i < textFields.length; i++)
            {
                infoAdd.add(textFields[i].getText());
            }

            doAdd(infoAdd,view);
        }
    }

}
