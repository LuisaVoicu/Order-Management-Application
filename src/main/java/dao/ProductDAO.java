package dao;

import model.Client;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO<Product> {

    /**
     *
     * @param id
     * @return
     */
    public Product findById(int id)
    {
        return super.findById(id);
    }

    /**
     *
     * @param p
     * @return
     */
    public Product insert(Product p)
    {
        return super.insert(p);
    }

    /**
     *
     * @param c
     * @return
     */
    public Product updateById(Product c){
        return super.updateById(c);}

    /**
     *
     * @param c
     * @return
     */
    public Product deleteById( Product c){return super.deleteById(c);}

    /**
     *
     * @return
     */
    public List<Product> findAll()
    {
        return super.findAll();
    }

    /**
     *
     * @param list
     * @return
     */
    public ArrayList<String> getTableFields(List<Product> list){return super.getTableFields(list);}

    /**
     *
     * @param id
     * @return
     */
    public String[] findValuesById(int id) {
        System.out.println("my id is::::::: "+id);
        Product c = findById(id);
        String [] result = new String[3];
        result[0] = String.valueOf(c.getIdProduct());
        result[1] = c.getNameProduct();
        result[2] = String.valueOf(c.getQuantity());
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public int getLastId()
    {
        List<Product> list =findAll();
        int lastId=0;
        for(Product l:list)
        {
            if(l.getIdProduct()>lastId)
            {
                lastId=l.getIdProduct();
            }
        }
        return lastId;
    }
}
