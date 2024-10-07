# users-ms

users-ms bci

# Instalación

1 - Descargue el proyecto como formato ZIP

2 - Descomprimir el proyecto

3 - Asegurece de tener el JDK 17 configurado en su entorno de trabajo

3 - Cargue el proyecto con su IDE preferido ya sea SPRINGBOOT ó Intellij

4 - Ejecute su proyecto con el servidor tomcat por defecto por el puerto 8080 (Asegurece de no tener en uso el puerto)

5 - Ir a la carpeta postmanCollections descargarla y ejecutarla con postman - [postmaCollection](../postmanCollection)

6 - crea un usuario con la plantilla proporcionada (asegurece de tener el Bearer token)

eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGF0aWNVc2VyIiwiaWF0IjoxNjk2MTk0NDAwfQ.0ErH9fOpzQojisfSoszSDzEAw-j4jLVN5F4MGEZh_oI (este es un token statico para el primer consumo luego puede utilizar el que genere al crear el usuario)

7 - segunda forma

    despues del paso 4 puede ejecutar a travez de la ui que proporciona swagger
    para ello necesitara ingresar a la siguiente url
    http://localhost:8080/doc/swagger-ui.html alli encontrara los endpoints expuestos y en la parte de 
    superior derecha donde dice Authorize agrega el token generico que se ha proporcionado previamente

    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdGF0aWNVc2VyIiwiaWF0IjoxNjk2MTk0NDAwfQ.0ErH9fOpzQojisfSoszSDzEAw-j4jLVN5F4MGEZh_oI