package service.impl;

import model.Brand;
import repository.BrandRepository;
import repository.impl.BrandRepositoryImpl;
import service.BrandService;

import java.util.List;

public class BrandServiceImpl implements BrandService {

    private  BrandRepository brandRepository = new BrandRepositoryImpl();
    @Override
    public Brand findBrandById(int id) {

        Brand brand = brandRepository.findBrandById(id);
        return brand;
    }

    @Override
    public List<Brand> findBrands() {

        List<Brand> brands = brandRepository.findBrands();
        return brands;
    }
}
