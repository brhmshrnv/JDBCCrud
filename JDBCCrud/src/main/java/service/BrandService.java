package service;

import model.Brand;

import java.util.List;

public interface BrandService {

    Brand findBrandById(int id);
    List<Brand> findBrands();
}
