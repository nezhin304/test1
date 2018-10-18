import com.shop.dao.ProductDAOImpl;
import com.shop.entity.Category;
import com.shop.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestProductDAOImpl {

    public static void main(String[] args) throws SQLException {

        Product product = new Product();

        product.setName("doll");
        product.setCode("0000000062");

        Category category = new Category();
        category.setName("default");

        Category category1 = new Category();
        category1.setName("sport");

        List<Category> categories = new ArrayList<>();
        categories.add(category);
        categories.add(category1);

        List<Product> products = new ArrayList<>();
        products.add(product);

        product.setCategories(categories);

        ProductDAOImpl productDAO = new ProductDAOImpl();
        productDAO.create(product);







    }
}
