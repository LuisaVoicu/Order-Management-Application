package model;

public class Order {
    private static int generatedId;
    private int idOrder;
    private int idClient;
    private int idProduct;
    private int quantity;
    private String title;

    /**
     *
     * @param idC
     * @param idP
     * @param quantity
     * @param title
     */
    public Order(int idC, int idP, int quantity, String title)
    {
        idOrder = ++generatedId;
        idClient = idC;
        idProduct = idP;
        this.quantity = quantity;
        this.title = title;
    }

    /**
     *
     */
    public Order()
    {

    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     *
     * @param idProduct
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     *
     * @return
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     *
     * @param idClient
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     *
     * @return
     */
    public int getIdOrder() {
        return idOrder;
    }

    /**
     *
     * @param idOrder
     */
    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    /**
     *
     * @return
     */
    public String toString()
    {
        return "Order ~ Order ID: " + idOrder +
                " ; Client ID: " + idClient + " ; Product ID: "
                +idProduct + " ; Quantity: "+quantity+ " ; Title: " + title;
    }

    /**
     *
     * @param generatedId
     */
    public static void setGeneratedId(int generatedId) {
        Order.generatedId = generatedId;
    }
}
