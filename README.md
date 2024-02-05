Api para transacciones financieras.

Generar la BD y el proyecto genera la tabla.
Inserts:
INSERT INTO .company(id,recharge_type, money)
VALUES (1, Phone, 9500.00);
INSERT INTO company(id,recharge_type, money)
VALUES (2, Transport, 3000.00);
INSERT INTO company(id,recharge_type, money)
VALUES (3, Television, 4500.00);

El file genius.tar es la imagen docker, pero hay que setearle los parametros
de la BD postgres.

Se agrega una postman collection para las pruebas manuales.


