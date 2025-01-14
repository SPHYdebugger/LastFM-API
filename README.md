<p align="center">
<img width="200px" src="">
<h1 align="center"> LastFM APP</h1>
</p>

# App de escritorio para buscar álbumes de artistas

### Descripción

Esta es una aplicación de escritorio desarrollada en JavaFX que consume una API externa para buscar los álbumes de artistas musicales y las portadas de los mismos.

### Características Principales

- **Búsqueda de artistas y álbumes:**
Permite a los usuarios ingresar el nombre de un artista y recuperar su discografía desde la API de LastFM.
- **Ejecución concurrente:**
Las consultas a la API se realizan de forma concurrente utilizando programación reactiva, mejorando el tiempo de respuesta y la capacidad de manejar múltiples solicitudes.
- **Interfaz gráfica:**
Basada en JavaFX, proporciona una experiencia de usuario rica y fluida.

### Tecnologías Utilizadas

- **Retrofit 2:** Biblioteca para realizar solicitudes HTTP de forma sencilla y estructurada.
- **Converter-Gson y Converter-Jackson:** Adaptadores para convertir las respuestas JSON de la API en objetos Java.
- **Logging-Interceptor:** Para registrar las solicitudes y respuestas HTTP en la consola durante el desarrollo.
- **RxJava 3:** Biblioteca para manejar programación reactiva y concurrencia, optimizando el manejo de las solicitudes a la API.
- **Jackson:** Librería para serialización/deserialización de objetos JSON.
- **Lombok:** Para reducir la escritura de código repetitivo en clases POJO.



### Resumen pantallas APP
﻿<p align="center">
<img width="100%" src="">
<h1 align="center"> LastFM- Api Desktop Application</h1>
</p>
