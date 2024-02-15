package bll;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBLL {

    public ProductDAO cBLL;

    public ProductBLL()
    {
        cBLL = new ProductDAO();
    }

    public ProductDAO getProductDAO()
    {
        return cBLL;
    }

    public Product findById(int idClient)
    {

        return cBLL.findById(idClient);
    }


    public Product insert(Product c)
    {
        return cBLL.insert(c);
    }


    public Product updateById( Product c){return cBLL.updateById(c);}

    public Product deleteById( Product c){return cBLL.deleteById(c);}


    public List<Product> findAll()
    {
        return cBLL.findAll();
    }


    public ArrayList<String> getTableFields(List<Product> list){return cBLL.getTableFields(list);}


    public String[] findValuesById(int id)
    {
        return cBLL.findValuesById(id);
    }

    public int getLastId()
    {

        return cBLL.getLastId();
    }
}
