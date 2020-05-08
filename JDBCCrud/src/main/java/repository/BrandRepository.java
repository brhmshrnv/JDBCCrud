package repository;

import model.Brand;

import java.util.List;

public interface BrandRepository {

    Brand findBrandById(int id);
    List<Brand> findBrands();
}
