package model;

public class Client {
    private static int generatedId = 0;
    private  int idClient;
    private String nameClient;
    private String address;

    /**
     *
     * @param name
     * @param addr
     */
    public Client(String name, String addr)
    {
        idClient=++generatedId;
        nameClient=name;
        address=addr;
    }

    /**
     *
     */
    public Client()
    {
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
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getNameClient() {
        return nameClient;
    }

    /**
     *
     * @param nameClient
     */
    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return "Client ~ ID:"+idClient+ " ; Name: "+nameClient+" ; address: "+address;
    }

    /**
     *
     * @param generatedId
     */
    public static void setGeneratedId(int generatedId) {
        Client.generatedId = generatedId;
    }
}
