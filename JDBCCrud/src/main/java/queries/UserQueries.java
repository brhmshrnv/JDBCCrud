package queries;

public class UserQueries {

    public static final String  saveUserQuery="INSERT INTO user(userId,firstName,lastName,birthOfDate,userName) values(?,?,?,?,?)";
    public static final String saveUser_ProductQuery="INSERT INTO user(userId,productId) values(?,?)";
    public static final String updateUserQuery="UPDATE user SET firstName=?,lastName=?,birthOfDate=?,userName=? WHERE userId=?";
    public static final String deleteUser_ProductQuery="DELETE FROM user_product WHERE userId=?";
    public static final String deleteUserByIdQuery="DELETE FROM user WHERE userId=?";
    public static final String findUserByIdQuery="select * from user where userId=?";
    public static final String findUsersQuery="select * from user";
    public static final String findUserProductQuery="SELECT * " +
            "FROM " +
            "USER u " +
            "LEFT OUTER JOIN user_product up ON ( u.userId = up.userId ) " +
            "LEFT JOIN product p ON ( p.productId = up.productId ) " +
            "LEFT JOIN category c ON ( c.categoryID = p.categoryId ) " +
            "LEFT JOIN brand b ON ( b.brandId = p.brandId ) WHERE userId=?";

}
