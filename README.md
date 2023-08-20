# Taller 1 AREP - Daniel Sebastian Ochoa Urrego

Este proyecto es un servidor web de consulta de peliculas. En el se uso un API externa www.omdbapi.com de donde sacamos toda la informacion y una implementacion basica de Cache junto con un servidor basico que maneja a muy baje nivel las conexiones creadas en este.

## Iniciando

Estas instrucciones te ayudaran a tener una copia de este proyecto corriendo en tu maquina local, en donde podras hacer pruebas o desarrollar sobre él 

### Prerrequisitos

- Java
- Maven
- Git 

Si aun no tienes instaladas estas tecnologias, los siguientes tutoriales te pueden ayudar

- Java: https://www.youtube.com/watch?v=BG2OSaxWX4E
- Maven: https://www.youtube.com/watch?v=1QfiyR_PWxU
- Git: https://www.youtube.com/watch?v=4xqVv2lTo40

### Instalando el proyecto

Para hacer una copia local del proyecto, debes abrir tu terminal, dirigirte a la carpeta donde quieras que este el proyecto y usar el siguiente comando

```
git clone https://github.com/DanielOchoa1214/Lab1-AREP.git
```

Luego muevete al directorio creado con el momando anterior y desde ahi ejecuta este comando

```
mvn exec:java
```

Ya que la aplicacion haya iniciado, puedes dirigirte a tu navegador de preferencia y entrar en http://localhost:35000 para ver la app corriendo, en ella encontraras una barra de busqueda y un boton. Si quieres buscar una pelicula solo debes poner el titulo en la barra y darle click al boton (Nota: el hacer enter no funcionara, solo recargaras la pagina)

<img width="1680" alt="Ejemplo busqueda" src="https://github.com/DanielOchoa1214/Lab1-AREP/assets/77862016/9be37034-82fa-4650-a3c4-34dd8dda9b81">
