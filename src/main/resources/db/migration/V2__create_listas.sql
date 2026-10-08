CREATE TABLE listas (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);


ALTER TABLE favoritos ADD COLUMN lista_id BIGINT;


ALTER TABLE favoritos 
ADD CONSTRAINT fk_favorito_lista 
FOREIGN KEY (lista_id) REFERENCES listas(id);