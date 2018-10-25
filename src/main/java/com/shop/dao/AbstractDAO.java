package com.shop.dao;

import com.shop.pool.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

 class AbstractDAO {

    private Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    Connection getConnection(){

        Connection connection = null;

        try {
            connection = Pool.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return connection;
    }

}
