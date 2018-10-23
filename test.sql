select *
from products;
select *
from categories;
select *
from products_categories;
select *
from customers;
select *
from orders;






delete from products_categories;
delete from products;
delete from categories;
delete from orders;
delete from customers;


delete from products
where name = 'doll';


select
  p.*,
  c.*
from
  products p
  join products_categories p_c on p.product_id = p_c.product_id
  join categories c on c.category_id = p_c.category_id;


select
  p.product_id as p_id,
  p.name       as p_name,
  p.code       as p_code,
  c.name       as c_name
from products p
  join products_categories p_c on p.product_id = p_c.product_id
  join categories c on c.category_id = p_c.category_id
where p.code = '00003'
;


select
  o.order_id as o_id,
  o.product_code as p_code,
  (select name as customer from customers where customer_id = o.customer_id)
from orders o;





