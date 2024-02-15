package presentation.edit;


import dao.AbstractDAO;
import presentation.design.CoolButton;
import presentation.model.window.AbstractWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public  class EditWindow extends JFrame{
    private static final int width = 110;
    private static final int height = 20;
    private static final Color foreground = Color.white;
    private static final Color background = new Color(	118, 67, 24);
    private static final Color background2 = new Color(	230, 219, 193);

    private AbstractDAO t;
    private int id;
    private AbstractWindow view;
    private String title;
    private JPanel panel;
    private JPanel buttonPanel;
    private JPanel[] miniPanels;
    private JTextField[] textFields;
    private JLabel[] labelFields;
    private ArrayList<String> fieldsTable;
    private JButton editButton;
    public ArrayList<String> infoEdit;

    /**
     *
     * @param t
     * @param view
     * @param title
     * @param fields
     * @param id
     */

    public EditWindow (AbstractDAO t, AbstractWindow view, String title, ArrayList<String> fields, int id)
    {
        this.t=t;
        this.id=id;
        this.view = view;
        this.title = title;
        fieldsTable = fields;

        init();
        setWindow();
        this.add(panel);
        this.setTitle(title);
        this.setSize(500,310);
        this.setVisible(true);
        this.editButton(new EditButton());

    }

    /**
     *
     */
    private void init()
    {
        textFields = new JTextField[fieldsTable.size()];
        labelFields = new JLabel[fieldsTable.size()];
        panel = new JPanel();
        buttonPanel = new JPanel(new FlowLayout());
        panel.setLayout(new GridLayout(3,0));
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
        editButton = new CoolButton(foreground,background,"Edit");
        editButton.setPreferredSize(new Dimension(20,30));
        buttonPanel.add(editButton);
        miniPanels[fieldsTable.size()].add(buttonPanel);
    }

    /**
     *
     */
    private void setWindow()
    {
        int index=0;
        String [] values = view.gettDAO().findValuesById(id);

        for(String s : fieldsTable){
            textFields[index].setText(values[index]);

            if(!s.contains("id")) {
                labelFields[index].setText(s);
                miniPanels[index].setLayout(new FlowLayout());
                miniPanels[index].add(labelFields[index]);
                miniPanels[index].add(textFields[index]);
                panel.add(miniPanels[index]);

            }
            index++;
        }
        panel.add(editButton);
    }

    /**
     *
     * @param listener
     */
    public void editButton(ActionListener listener){
        editButton.addActionListener(listener);
    }

    /**
     *
     * @param t
     * @param infoEdit
     * @param id
     * @param view
     */
    public void doEditWindow(AbstractDAO t, ArrayList<String> infoEdit, int id, AbstractWindow view)
    {
        System.out.println("hellooo i am in edit window");
    }


    /**
     *
     */
    class EditButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            infoEdit = new ArrayList<String>();
            for(int i = 0 ; i < textFields.length ; i++)
            {
                 infoEdit.add(textFields[i].getText());
            }
            int id = Integer.parseInt(infoEdit.get(0));
            doEditWindow(t,infoEdit,id,view);

        }
    }
}
