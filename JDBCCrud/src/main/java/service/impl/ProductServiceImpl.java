package service.impl;

import model.Product;
import repository.ProductRepository;
import repository.impl.ProductRepositoryImpl;
import service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductRepository  productRepository = new ProductRepositoryImpl();
    @Override
    public Product saveProduct(Product product) {
        Product p = productRepository.saveProduct(product);
        return p;
    }

    @Override
    public boolean saveBatchProduct(List<Product> products) {

        boolean check= productRepository.saveBatchProduct(products);
        return check;
    }

    @Override
    public Product updateProduct(Product product) {
        Product p = productRepository.updateProduct(product);
        return p;
    }

    @Override
    public boolean removeProduct(int id) {
        boolean check = productRepository.removeProduct(id);
        return check;
    }

    @Override
    public Product findProductById(int id) {
        Product  product = productRepository.findProductById(id);
        return product;
    }

    @Override
    public List<Product> findProducts() {
        List<Product> products =productRepository.findProducts();
        return products;
    }
}
