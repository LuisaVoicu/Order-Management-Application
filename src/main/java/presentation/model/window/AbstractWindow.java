package presentation.model.window;

import dao.AbstractDAO;
import presentation.design.CoolButton;
import presentation.design.MainBackground;
import presentation.design.WindowBackground;
import presentation.selectedit.SelectEditWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AbstractWindow<T> extends JFrame{
    private static final int width = 110;
    private static final int height = 20;
    private static final Color foreground = Color.white;
    private static final Color background = new Color(	118, 67, 24);
    private static final Color background2 = new Color(64, 80, 148);

    private AbstractDAO tDAO;
    public DefaultTableModel abstractTable;
    private String path;
    public JPanel mainPanel;
    private JPanel miniPanel;
    private JButton showTableButton;
    private JButton addButton;
    private JButton editButtoon;
    private JButton deleteButton;
    private JList list;


    /**
     * Constructor For Abstract Window
     * @param t
     * @param title
     */
    public AbstractWindow(AbstractDAO t, String title)
    {
        tDAO=t;
        initElements();
        design();

        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(500,310));
        this.pack();
        this.setTitle(title);
        this.setVisible(true);
        this.addShowTableButtonListener( new ShowTableButtonListener());
        this.addButton(new AddButtonListener(this));
        this.editButtoon(new EditButtonListener(this,tDAO));
        this.deleteButton(new DeleteButtonListener(this));
    }

    /**
     * Method to initialize all elements
     */
    private void initElements()
    {
        abstractTable= new DefaultTableModel();
        miniPanel= new JPanel(new GridLayout(1, 0));
        mainPanel = new WindowBackground(new GridBagLayout(),"src/main/image/clientBackground.png");
        showTableButton = new CoolButton(foreground,background2,"Show");
        addButton = new CoolButton(foreground,background2,"Add");
        editButtoon = new CoolButton(foreground,background2,"Edit");
        deleteButton = new CoolButton(foreground,background2,"Delete");
        showTableButton.setPreferredSize(new Dimension(width,height));
        addButton.setPreferredSize(new Dimension(width,height));
        editButtoon.setPreferredSize(new Dimension(width,height));
        deleteButton.setPreferredSize(new Dimension(width,height));
    }

    void design()
    {


        miniPanel.add(showTableButton);
        miniPanel.add(addButton);
        miniPanel.add(editButtoon);
        miniPanel.add(deleteButton);
        mainPanel.add(miniPanel);
    }

    /**
     * Method for adding a table with results from database
     * @param list
     * @param <T>
     */
    public <T> void setTableInfo(java.util.List<T> list) {
        DefaultTableModel abstractTable = tDAO.getAllInTable(list);
        JTable j=new JTable(abstractTable);
        JScrollPane sp = new JScrollPane(j);
        mainPanel.add(sp);
        JDialog window = new JDialog(this, "Table");
        window.add(sp);
        window.setSize(500,310);
        window.setBackground(background);
        window.setVisible(true);

    }

    /**
     * Abstract class for ShowTable Button
     */
    class ShowTableButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            List<T> all = tDAO.findAll();
            setTableInfo(all);
        }
    }


    /**
     * Method for inserting new data that will be inherited
     * @param win
     */
    public void doAdd(AbstractWindow win)
    {
        System.out.println("Am apasat Add!");
    }
    class AddButtonListener implements ActionListener {
        private AbstractWindow win;
        public AddButtonListener(AbstractWindow v)
        {
            win = v;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            doAdd( win);

        }
    }

    /**
     * Method for editing existing data based on id that will be inherited
     * @param win
     * @param id
     */
    public void doEdit(AbstractWindow win, int id)
    {
        System.out.println("Am apasat Edit!");

    }
    class EditButtonListener implements ActionListener{
        private AbstractWindow win;
        private AbstractDAO t;
        public EditButtonListener(AbstractWindow v, AbstractDAO tDAO)
        {
            win = v;
            t=tDAO;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

//            List<T> all = tDAO.findAll(); //todo daca nu am niciun client cum extrag campurile?
//            ArrayList<String> fields = tDAO.getTableFields(all);
//            int id = 1;
//            EditWindow window = new EditWindow(win,"Edit Client",fields,id);

            new SelectEditWindow<T>(win);
        }
    }


    /**
     * Delete row based on id
     * @param win
     */
    public void doDelete(AbstractWindow win)
    {

        System.out.println("Am apasat Delete!");
    }

    class DeleteButtonListener implements ActionListener{
        private AbstractWindow win;
        public DeleteButtonListener(AbstractWindow v)
        {
            win = v;
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            doDelete(win);
        }
    }

    /**
     *
     * @param listener
     */
    public void addShowTableButtonListener(ActionListener listener){
        showTableButton.addActionListener(listener);
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
     * @param listener
     */
    public void editButtoon(ActionListener listener){
        editButtoon.addActionListener(listener);
    }

    /**
     *
     * @param listener
     */
    public void deleteButton(ActionListener listener){
        deleteButton.addActionListener(listener);
    }

    /**
     *
     * @return
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }
    public AbstractDAO gettDAO() {
        return tDAO;
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
     * @param t
     * @return
     */
    public int getId(T t)
    {
        return 0;
    }

}
