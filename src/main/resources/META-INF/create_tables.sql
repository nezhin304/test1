CREATE TABLE categories
(
  category_id BIGSERIAL NOT NULL PRIMARY KEY ,
  name character varying NOT NULL UNIQUE
);

CREATE TABLE customers
(
  customer_id BIGSERIAL NOT NULL PRIMARY KEY ,
  name character varying NOT NULL UNIQUE
);

CREATE TABLE orders
(
  order_id BIGSERIAL NOT NULL PRIMARY KEY ,
  customer_id bigint NOT NULL,
  product_code character varying NOT NULL UNIQUE ,
  CONSTRAINT orders_customer_id_fkey
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE products
(
  product_id BIGSERIAL NOT NULL PRIMARY KEY ,
  name character varying NOT NULL,
  code character varying NOT NULL UNIQUE
);

CREATE TABLE products_categories
(
  products_categories_id BIGSERIAL NOT NULL PRIMARY KEY ,
  product_id bigint NOT NULL,
  category_id bigint NOT NULL,
  CONSTRAINT products_categories_category_id_fkey FOREIGN KEY (category_id)
  REFERENCES categories (category_id),
  CONSTRAINT products_categories_product_id_fkey FOREIGN KEY (product_id)
  REFERENCES products (product_id)
);