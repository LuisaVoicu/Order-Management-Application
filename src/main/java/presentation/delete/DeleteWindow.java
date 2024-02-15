package presentation.delete;

import dao.AbstractDAO;
import presentation.design.CoolButton;
import presentation.model.window.AbstractWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static presentation.Dialog.doDialog;

public class DeleteWindow<T> extends  JFrame{

    private static final int width = 110;
    private static final int height = 20;
    private static final Color foreground = Color.white;
    private static final Color background = new Color(	118, 67, 24);
    private static final Color background2 = new Color(	230, 219, 193);

    private AbstractDAO  t;
    private AbstractWindow win;
    private JPanel selectPanel;
    private JPanel mainPanel;
    private JButton deleteButton;
    private JList list;
    private JPanel buttonPanel;

    /**
     *
     * @param win
     * @param t
     */
    public DeleteWindow(AbstractWindow win, T t)
    {
        this.win = win;
        this.t= (AbstractDAO) t;
        init();
        setElements();


        this.add(mainPanel);
        this.setTitle("Delete");
        this.setSize(500,310);
        this.setVisible(true);
        this.setDeleteButton(new DeleteButton());

    }

    /**
     *
     */
    public void init()
    {
        selectPanel= new JPanel();
        mainPanel= new JPanel();
        buttonPanel = new JPanel();
        deleteButton = new CoolButton(foreground,background,"Delete");
        selectPanel.setLayout(new BorderLayout());
        mainPanel.setLayout(new GridLayout(1,0));
         buttonPanel = new JPanel(new FlowLayout());

    }

    /**
     *
     * @return
     */
    public  DefaultListModel<T> getListModel()
    {
        return null;
    }

    /**
     *
     */
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
        mainPanel.add(deleteButton);
        deleteButton.setPreferredSize(new Dimension(width,height));
        buttonPanel.add(deleteButton);
        buttonPanel.setBackground(background2);
        mainPanel.add(buttonPanel);
    }


    /**
     *
     * @return
     */
    public AbstractDAO getT() {
        return t;
    }

    /**
     *
     * @param del
     */
    public void doDelete(T del)
    {
    }
    public void setDeleteButton(ActionListener listener){
        deleteButton.addActionListener(listener);
    }

    /**
     *
     */
    class DeleteButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            T toBeDeleted= (T) list.getSelectedValue();
            if(toBeDeleted==null)
            {
                doDialog("Select Warning", " Please select an item!");
            }
            doDelete(toBeDeleted);

        }
    }
}
