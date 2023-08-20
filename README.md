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

## Corriendo los tests

Para correr los tests unitarios creados debes entrar al directorio del proyecto (Al clonarlo probablemente sea llamado "Lab1-AREP") y correr el siguiente comando 

```
mvn test
```

Y si tanto tu como yo hicimos todo bien, los test deberian correr y ser exitosos. 

Ahora, el proyecto tiene 2 tests, el primero es una prueba simple donde vemos que la API fachada si sea capaz de trar informacion correcta dado el titulo de una palicula, y el segundo prueba que nuestra app sea resistente a pedidos concurrentes chequeando que el cache se mantenga consistente a travez de estos pedidos.

## Documentacion

Para visualizar la documentación del proyecto solo debes correr el siguiente comando desde el directorio raiz del proyecto 

```
mvn javadoc:javadoc
```

Y en la siguiente ruta encontraras el archivo index.html en donde si lo abres desde el navegador podras toda la documentacion

```
./target/site/apidocs
```

## Construido con

* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [OMDAPI](https://www.omdbapi.com) - API externa de consulta
* Amor

## Version

1.0-SNAPSHOT

## Autores

Daniel Sebastián Ochoa Urrego - (DanielOchoa1214)[https://github.com/DanielOchoa1214]

## Licencia

GNU General Public License family

## Diseño



## Agradecimientos

* A nuestro querido profesor de Arquitectura empresariales Daniel Benavides
* Figo

