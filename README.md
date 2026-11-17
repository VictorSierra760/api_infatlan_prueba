# Especificaciones de Evaluación Java - Spring

# Backend

Proyecto desarrollado con Spring Boot.

Java JDK 21
Maven 3.9.15
PostgreSQL 16

Dependencias
Spring web, spring data JPA, spring validation, postgreSQL

cd api_infatlan

Base de datos: PruebaTec

Antes de ejecutar el proyecto, configurar las credenciales de postgreSQL en: application.properties para luego correr proyecto y crear tablas automaticamente.

Ejecutar
./mvnw spring-boot:run

Realizar Inserts en Base de datos:

INSERT INTO accounts (id, holder_name, balance) VALUES
(1001, 'Juan Pérez', 5000.00),
(1002, 'María López', 2000.00),
(1003, 'Carlos Gómez', 10000.00);

# Endoints

# Get Consultar Cuentas

http://localhost:8080/api/accounts/1001

# Get Consultar Cuenta por Id

http://localhost:8080/api/accounts/1001

# Post Realizar Transferencia

http://localhost:8080/api/transfers

# Decisiones

Se utlizo DTO para las entradas y salidas, igualmente manejo de excepciones globales con
@RestControllerAdvice y anotación @ExceptionHandler para centralización de errores en conunto de ResponseEntity.

Tambien se utiliaron validaciones Bean Validation con @Valid en TransferenciaController.

Se Utilizo Services para la logica de negocio.
