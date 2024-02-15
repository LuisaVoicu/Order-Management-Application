package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import dao.LogDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Bill;
import model.Client;
import model.Order;
import model.Product;
import presentation.design.CoolButton;
import presentation.design.MainBackground;
import presentation.model.window.ClientWindow;
import presentation.model.window.OrderWindow;
import presentation.model.window.ProductWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindow extends JFrame{
    private static final int height = 200;
    private static final int width = 50;
    private static final Color foreground = Color.white;
    private static final Color background = new Color(	230, 219, 193);
    private ImageIcon [] icons ;

    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;
    private JButton logButton;
    private JPanel panel;
    private JPanel miniPanel;
    private JPanel midPanel;
    //private ClientDAO clientDAO;
    private ClientBLL clientDAO;
    //private ProductDAO productDAO;

    private ProductBLL productDAO;


    //private OrderDAO orderDAO;
    private OrderBLL orderDAO;
    private LogDAO logDAO;

    /**
     *
     */
    public MainWindow ()
    {
        initElements();
        design();
        addInPanel();

        this.setContentPane(panel);
        this.setPreferredSize(new Dimension(600,500));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Order Management");
        this.setVisible(true);
        this.clientButton(new ClientButtonListener());
        this.productButton(new ProductButtonListener());
        this.orderButton(new OrderButtonListener());
        this.logButton(new LogButtonListener());
    }

    /**
     *
     */
    void initElements()
    {
        clientDAO = new ClientBLL();

        productDAO = new ProductBLL();
       // orderDAO = new OrderDAO();
        orderDAO = new OrderBLL();

        logDAO = new LogDAO();

        //set id for model classes
        Client.setGeneratedId(clientDAO.getLastId());
        Product.setGeneratedId(productDAO.getLastId());
        Order.setGeneratedId(orderDAO.getLastId());

        panel = new MainBackground(new GridBagLayout());
        miniPanel = new JPanel();
        midPanel = new JPanel(new FlowLayout());
        setIcons();
        clientButton = new CoolButton(foreground,background,icons[0], " Clients ");
        productButton = new CoolButton(foreground,background,icons[1]," Products");
        orderButton = new CoolButton(foreground,background,icons[2],  " Orders  ");
        logButton = new CoolButton(foreground,background,icons[3],    " Log     ");
    }

    void setIcons()
    {
        icons = new ImageIcon[4];
        icons[0] = new ImageIcon("src/main/image/client.png");
        icons[1] = new ImageIcon("src/main/image/package.png");
        icons[2] = new ImageIcon("src/main/image/order.png");
        icons[3] = new ImageIcon("src/main/image/log.png");
    }

    /**
     *
     */
    void addInPanel()
    {
        miniPanel.add(clientButton);
        miniPanel.add(productButton);
        miniPanel.add(orderButton);
        miniPanel.add(logButton);

       // midPanel.add(miniPanel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;

        panel.add(miniPanel,gbc);

    }

    /**
     * this method is used to create the design of the main page
     */
    void design()
    {
        midPanel.setPreferredSize(new Dimension(250,250));
        miniPanel.setLayout(new GridLayout(0, 1));
        clientButton.setPreferredSize(new Dimension(height,width));
        orderButton.setPreferredSize(new Dimension(height,width));
        productButton.setPreferredSize(new Dimension(height,width));
        logButton.setPreferredSize(new Dimension(height,width));
    }

    /**
     *
     * @param listener
     */
    public void clientButton(ActionListener listener){
        clientButton.addActionListener(listener);
    }

    /**
     *
     * @param listener
     */
    public void productButton(ActionListener listener){
        productButton.addActionListener(listener);
    }

    /**
     *
     * @param listener
     */
    public void orderButton(ActionListener listener){
        orderButton.addActionListener(listener);
    }

    /**
     *
     * @param listener
     */
    public void logButton(ActionListener listener){
        logButton.addActionListener(listener);
    }


    class ClientButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClientWindow cw = new ClientWindow(clientDAO.getClientDAO());
        }
    }

    class ProductButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductWindow pw = new ProductWindow(productDAO.getProductDAO());
        }
    }

    class OrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderWindow ow = new OrderWindow(orderDAO.getOrderDAO(),clientDAO.getClientDAO(),productDAO.getProductDAO(),logDAO);
        }
    }

    class LogButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            List<Bill> list = logDAO.selectAll();
            DefaultTableModel abstractTable = logDAO.getAllInTable(list);
            JTable j=new JTable(abstractTable);
            JScrollPane sp = new JScrollPane(j);
            panel.add(sp);
            JDialog window = new JDialog(new JFrame("Log Table"), "Log Table");
            window.add(sp);
            window.setSize(700,500);
            window.setVisible(true);
        }
    }

}
