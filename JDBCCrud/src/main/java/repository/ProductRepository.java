package repository;

import model.Product;

import java.util.List;

public interface ProductRepository {

    Product saveProduct(Product product);
    boolean saveBatchProduct(List<Product> products);
    Product updateProduct(Product product);
    boolean removeProduct(int id);
    Product findProductById(int id);
    List<Product> findProducts();

}
