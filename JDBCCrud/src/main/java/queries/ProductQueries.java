package queries;

public class ProductQueries {

    public static final String saveProductQuery="INSERT INTO product(productId,productName,unitPrice,available,addDate,updateDate,categoryId,brandId) values(?,?,?,?,?,?,?,?)";
    public static final String updateProductQuery="UPDATE product SET productName=?,unitPrice=?,available=?,updateDate=?,categoryId=?,brandId=? where productId=?";
    public static final String deleteUser_ProductQuery="delete from user_product where productId=?";
    public static final String deleteProductByIdQuery="delete from product where productId=?";
    public static final String findProductByIdQuery="select * from product where productId=?";
    public static final String findProductsQuery="select * from product";

}
