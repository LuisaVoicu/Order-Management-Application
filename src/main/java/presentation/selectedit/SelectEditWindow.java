package presentation.selectedit;

import dao.AbstractDAO;
import presentation.design.CoolButton;
import presentation.edit.EditWindow;
import presentation.model.window.AbstractWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import static presentation.Dialog.doDialog;

public class SelectEditWindow<T> extends JFrame{

    private static final int width = 110;
    private static final int height = 20;
    private static final Color foreground = Color.white;
    private static final Color background = new Color(	118, 67, 24);
    private static final Color background2 = new Color(	230, 219, 193);

    private AbstractDAO t;
    private AbstractWindow win;
    private JPanel selectPanel;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton editButton;
    private JList list;
    private T toBeEdited=null;

    public SelectEditWindow(AbstractWindow win)
    {
        this.win = win;
        init();
        setElements();


        this.add(mainPanel);
        this.setTitle("Edit");
        this.setSize(500,310);
        this.setVisible(true);
        this.setEditButton(new EditButton(win));

    }

    public void init()
    {
        selectPanel= new JPanel(new BorderLayout());
        mainPanel= new JPanel(new GridLayout(1,0));
        buttonPanel = new JPanel(new FlowLayout());
        editButton = new CoolButton(foreground,background,"Edit");

    }
    public  DefaultListModel<T> getListModel()
    {
        return null;
    }

    protected void setElements()
    {
        DefaultListModel<T> listModel = win.getListModel();
        if(listModel==null)
        {
            return;
        }

        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setVisibleRowCount(-1);
        JScrollPane scrollPane = new JScrollPane(list);
        selectPanel.add(scrollPane);
        mainPanel.add(selectPanel);
        editButton.setPreferredSize(new Dimension(width,height));
        buttonPanel.add(editButton);
        buttonPanel.setBackground(background2);
        mainPanel.add(buttonPanel);
    }

    public void setEditButton(ActionListener listener){
        editButton.addActionListener(listener);
    }

    class EditButton implements ActionListener {
        AbstractWindow win;
        public EditButton(AbstractWindow win)
        {
            this.win = win;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            toBeEdited= (T) list.getSelectedValue();
            if(toBeEdited==null)
            {
                doDialog("Select Warning", " Please select an item!");
            }
            /*List<T> all = win.gettDAO().findAll(); //todo daca nu am niciun client cum extrag campurile?
            ArrayList<String> fields = win.gettDAO().getTableFields(all);*/

            int id = win.getId(toBeEdited);
            win.doEdit(win,id);
        }
    }



}
