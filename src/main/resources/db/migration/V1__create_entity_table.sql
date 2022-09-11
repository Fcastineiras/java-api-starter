CREATE TABLE IF NOT EXISTS public.entity
(
  id SERIAL NOT NULL,
  small_id INT NOT NULL,
  price NUMERIC(10,2) NOT NULL,
  enum varchar NOT NULL,
  some_date TIMESTAMP NOT NULL
);

-- use that line to create relationship between tables
-- CONSTRAINT entity_small_id_fk FOREIGN KEY (small_id) REFERENCES master_entity (id)