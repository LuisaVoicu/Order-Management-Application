package model;

public record Bill(int idOrder, int idClient, int idProduct, int quantity, String title) {
    /**
     *
     * @param idOrder
     * @param idClient
     * @param idProduct
     * @param quantity
     * @param title
     */

    public Bill(int idOrder, int idClient, int idProduct,int quantity, String title)
    {
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.idProduct= idProduct;
        this.quantity = quantity;
        this.title = title;
    }


}
