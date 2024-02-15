package model;

public class Product {
    private static int generatedId=0;
    private int idProduct;
    private String nameProduct;
    private int quantity;

    /**
     *
     * @param nameProduct
     * @param quantity
     */
    public Product(String nameProduct, int quantity)
    {
        this.idProduct = ++generatedId;
        this.nameProduct = nameProduct;
        this.quantity=quantity;
    }

    /**
     *
     */
    public Product()
    {

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
    public String getNameProduct() {
        return nameProduct;
    }

    /**
     *
     * @param nameProduct
     */
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
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
    public String toString()
    {
        return "Product ~ Product ID "+idProduct + " ; Name: " + nameProduct+ " ;   Quantity : "+ quantity;
    }

    /**
     *
     * @param generatedId
     */
    public static void setGeneratedId(int generatedId) {
        Product.generatedId = generatedId;
    }
}
