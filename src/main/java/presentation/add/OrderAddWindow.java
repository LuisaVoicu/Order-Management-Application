package presentation.add;

import dao.ClientDAO;
import dao.LogDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Bill;
import model.Client;
import model.Order;
import model.Product;
import presentation.design.WindowBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static presentation.Dialog.doDialog;

public class OrderAddWindow extends JFrame {

    private OrderDAO o;
    private ClientDAO c;
    private ProductDAO p;
    private LogDAO l;
    private JComboBox<String> clientCombo;
    private JComboBox<String> productCombo;
    private JTextField quantityTF;
    private JButton addButton;
    private JPanel panel;
    private JPanel panelCombos;
    private Product selectedProduct = null;
    private Client selectedClient = null;

    /**
     *
     * @param orderDAO
     * @param clientDAO
     * @param productDAO
     * @param logDAO
     * @param title
     */
    public OrderAddWindow(OrderDAO orderDAO, ClientDAO clientDAO, ProductDAO productDAO, LogDAO logDAO, String title)
    {
        this.o = orderDAO;
        this.c = clientDAO;
        this.p = productDAO;
        this.l = logDAO;

        initElem();
        addToPanel();
        design();

        this.addComboClientListener(new ComboClientListener());
        this.addComboProductListener(new ComboProductListener());
        this.addAddButton(new AddButtonListener());

        this.add(panel);
        this.setTitle(title);
        this.setSize(500,310);
        this.setVisible(true);
    }

    /**
     *
     */
    private void initElem()
    {
        panel = new WindowBackground(new GridBagLayout(),"src/main/image/clientBackground.png");
        panelCombos = new JPanel();
        initCombo();
        quantityTF = new JTextField();
        quantityTF.setPreferredSize(new Dimension(80,25));
        addButton = new JButton("Add Order");

    }

    /**
     *
     */
    private void addToPanel()
    {
        panelCombos.add(clientCombo);
        panelCombos.add(productCombo);
        panel.add(panelCombos);
        panel.add(quantityTF);
        panel.add(addButton);
    }

    /**
     *
     */
    private void design()
    {
        panelCombos.setLayout(new FlowLayout());
        panel.setLayout(new FlowLayout());
    }

    /**
     *
     */
    private void initCombo()
    {
        List<Client> allClients = c.findAll();
        List<Product> allProducts = p.findAll();
        ArrayList<String> choisesClients = new ArrayList<>();
        ArrayList<String> choisesProducts = new ArrayList<>();

        for(Client c : allClients)
        {
            String aux = c.getIdClient()+":"+c.getNameClient();
            choisesClients.add(aux);
        }
        int size = choisesClients.size();
        String[] choiceC = new String[size];
        int index=0;
        for(String s:choisesClients)
        {
            choiceC[index++]=s;
        }
        clientCombo = new JComboBox<>(choiceC);

        for(Product p : allProducts)
        {
            String aux = p.getIdProduct()+":"+p.getNameProduct()+" - "+p.getQuantity();
            choisesProducts.add(aux);
        }
        size = choisesProducts.size();
        String[] choiceP = new String[size];
        index=0;
        for(String s: choisesProducts)
        {
            choiceP[index++] = s;
        }
        productCombo=new JComboBox<>(choiceP);
    }


    /**
     *
     * @param s
     * @return
     */
    private int isNumber(String s)
    {
        String regex = "\\d+";
        if(s.matches(regex)==false) {
            return -1;
        }
        else{
            int nb = Integer.parseInt(s);
            return nb;
        }
    }

    /**
     *
     */
    void doAddOperation()
    {

        if(selectedProduct==null)
        {
            doDialog("Product Warning","Please select a product!");
            return;
        }

        if(selectedClient==null)
        {
            doDialog("Client Warning","Please select a client!");
            return;
        }

        String s = quantityTF.getText();
        int quantity=isNumber(s);
        if(s==null||quantity<0)
        {
            doDialog("Quantity Warning","Please type a quantity!");
            return;
        }


        if(selectedProduct.getQuantity() < quantity)
        {
            doDialog("Insufficient Quantity", "Insufficient quantity for "+selectedProduct.getNameProduct());
            return;
        }

        Order newOrder = new Order(selectedClient.getIdClient(),selectedProduct.getIdProduct(),quantity,"Order no.");
        Bill newBill = new Bill(newOrder.getIdOrder(),selectedClient.getIdClient(),selectedProduct.getIdProduct(),quantity,"Order no.");

        newOrder.setTitle("Order no."+newOrder.getIdOrder());
        o.insert(newOrder);
        l.insert(newBill);

        int newQuantity = selectedProduct.getQuantity()-quantity;
        if(newQuantity > 0 ) {
            selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);
            p.updateById(selectedProduct);
        }
        else
        {
            p.deleteById(selectedProduct);
        }

    }

    /**
     *
     * @param listener
     */
    public void addComboClientListener(ActionListener listener){
        clientCombo.addActionListener(listener);
    }

    /**
     *
     */
    class ComboClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) clientCombo.getSelectedItem();
            Pattern pattern = Pattern.compile("^[^:]+");
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                String result = matcher.group();
                int id = Integer.parseInt(result);
                selectedClient = c.findById(id);
            }
        }
    }


    /**
     *
     * @param listener
     */
    public void addComboProductListener(ActionListener listener){
        productCombo.addActionListener(listener);
    }


    /**
     *
     */
    class ComboProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = (String) productCombo.getSelectedItem();
            Pattern pattern = Pattern.compile("^[^:]+");
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                String result = matcher.group();
                int id = Integer.parseInt(result);
                selectedProduct = p.findById(id);
            }
        }
    }

    /**
     *
     * @param listener
     */
    public void addAddButton(ActionListener listener)
    {
        addButton.addActionListener(listener);
    }

    class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("pressed add!!!");
            doAddOperation();
        }
    }



}
