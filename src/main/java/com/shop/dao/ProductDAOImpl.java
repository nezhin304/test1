package com.shop.dao;

import com.shop.entity.Product;
import com.shop.pool.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductDAOImpl extends AbstractDAO implements ProductDAO {

    private Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    @Override
    public void save(Product product) {

        PreparedStatement statement = null;
        ResultSet resultSet;
        Collection categories =  product.getCategories();
        long productId = 0;

        try (Connection connection = getConnection()) {

            statement = connection.
                    prepareStatement("INSERT INTO products (name, code) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getCode());
            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            productId = resultSet.getLong(1);

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {

            Helper.closeStatementResultSet(statement, null);
        }

        for (String category : (ArrayList<String>) categories) {

            long categoryId = saveCategory(category);

            try (Connection connection = Pool.getConnection()) {

                statement = connection.prepareStatement("INSERT INTO products_categories (product_id, category_id) VALUES (?,?)");
                statement.setLong(1, productId);
                statement.setLong(2, categoryId);
                statement.execute();
                connection.commit();

            } catch (SQLException e) {
                logger.error(e.getMessage());
            } finally {
                Helper.closeStatementResultSet(statement, null);
            }
        }
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public long getById(long id) {
        return 0;
    }

    @Override
    public List<Product> getByCategory(String category) {
        return null;
    }

    @Override
    public Collection getCategories(Product product) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Collection list = new ArrayList<String>();

        try (Connection connection = getConnection()){

            statement = connection.prepareStatement("select name from categories where category_id = (select category_id from products_categories where product_id = (select product_id from products where name = ?));");
            statement.setString(1, product.getName());
            resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()){

                list.add(resultSet.getString(1));

            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            Helper.closeStatementResultSet(statement, resultSet);
        }

        return list;
    }

    @Override
    public Collection getAll() {

        PreparedStatement statement = null;
        Collection list = new ArrayList();
        ResultSet resultSet = null;


        try (Connection connection = getConnection()){

            statement = connection.prepareStatement("SELECT p.*, c.* FROM products p JOIN products_categories p_c ON p.product_id = p_c.product_id \n" +
                    "JOIN categories c ON c.category_id = p_c.category_id");
            resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()){
                Product product = new Product();
                product.setName(resultSet.getString(2));
                product.setCode(resultSet.getString(3));
                product.setCategories(getCategories(product));
                list.add(product);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            Helper.closeStatementResultSet(statement, resultSet);
        }

        return list;
    }

    private long saveCategory(String category) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long categoryId = 0;

        try (Connection connection = getConnection()) {

            statement = connection.prepareStatement("INSERT INTO categories (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category);
            statement.executeUpdate();
            connection.commit();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            categoryId = resultSet.getLong(1);

        } catch (SQLException e) {

            if (e.getMessage().contains("duplicate key value")) {
                categoryId = getCategiryId(category);
            } else {
                logger.error(e.getMessage());
            }

        } finally {
            Helper.closeStatementResultSet(statement, resultSet);
        }

        return categoryId;
    }


    private long getCategiryId(String category) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        long categoryId = 0;

        try (Connection connection = getConnection()) {

            statement = connection.
                    prepareStatement("SELECT MAX(category_id) FROM categories WHERE name = ?");
            statement.setString(1, category);
            resultSet = statement.executeQuery();
            connection.commit();
            resultSet.next();
            categoryId = resultSet.getLong(1);

        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            Helper.closeStatementResultSet(statement, resultSet);
        }

        return categoryId;
    }
}


