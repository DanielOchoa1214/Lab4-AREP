# Taller 1 AREP - Daniel Sebastian Ochoa Urrego

Este proyecto es un servidor web de consulta de peliculas. En el se uso un API externa www.omdbapi.com de donde sacamos toda la informacion y una implementacion basica de Cache junto con un servidor basico que maneja a muy baje nivel las conexiones creadas en este.

## Iniciando

Estas instrucciones te ayudaran a tener una copia de este proyecto corriendo en tu maquina local, en donde podras hacer pruebas o desarrollar sobre él 

### Prerrequisitos

* Git 
* Java
* Maven

Si aun no tienes instaladas estas tecnologias, los siguientes tutoriales te pueden ayudar

* Git: https://www.youtube.com/watch?v=4xqVv2lTo40
* Java: https://www.youtube.com/watch?v=BG2OSaxWX4E
* Maven: https://www.youtube.com/watch?v=1QfiyR_PWxU

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

* Amor
* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [OMDAPI](https://www.omdbapi.com) - API externa de consulta

## Version

1.0-SNAPSHOT

## Autores

Daniel Sebastián Ochoa Urrego - !(https://github.com/DanielOchoa1214)[DanielOchoa1214]

## Licencia

GNU General Public License family

## Diseño

En la aplicación separamos las clases de manera logica usando los paquetes para abstraer la arquitectura dada en el enunciado. Segun el enunciado tenemos que crear 3 componentes en la aruqitectura, los cuales fueron creados de la siguiente manera: 

* Web Client - Para separar logicamente el cliente web creamos un paquete "webclient" donde tenemos la clase HttpServer, la cual es la encargada de administrar las peticiones del cliente y enviarle las respuestas a travez de los Sockets.
* Web Server Facade - Ahora, para este componete se creo el paquete "apifacade" en donde tenemos las clases HttpConnection y Cache, las cuales en conjunto son las escargadas de consultar la información de la pelicula buscada, ya sea haciendo una peticion a la API esterna o con la información guardada en cache.
* Concurrent Java Test Client - Para este componente usamos la estructura por defecto que Maven nos provee para hacer tests, por lo que si navemos hasta el final de la carpeta test del proyecto podremos encontrar la clase de prueba de la fachada en donde probramos la correctitud de la informacion buscada por la API esterna y la resistencia a la concurrencia del cache.

### Extencibilidad

Para ilustrar la extencibilidad de la aplicación se me ocurren 2 ejemplos:

* Si se quisiera cambiar el proveedor externo de informacóon sobre las peliculas, solo deberiamos cambiar el valor de la variable "GET_URL" de la clase HttpConnection y todo deberia seguir funcionando normalmente
* Si se quisieraan añadir funcionalidades a la fachada solo se deberia crear un nuevo metodo dentro de la clase HttpConnection siguiendo los patrones de nombramiento con los verbos HTTP y tomar en cuenta el nuevo path dentro del cliente web para correr cada metodo según se necesite.

### Patrones

Se puede observar un patron de diseño y un patron que arquitectura

* El patron de diseño corresponde al patron Singleton, el cual se implemento pues dentro de la aplicacion se debe tener (por ahora) solo un cache en donde guardemos las peliculas
* El patron de arquitectura seria la implementación a bajo nivel de un MOM, ya que nuestra fachada sirve en resumidas cuentas como un intermediario que hace peticiones a la API externa y construye la respuesta de manera que sea mas facilmente legible por el cliente Web 

### Modularización

En la aplicación cada clase tiene una funcion en especifico 

* HttpServer: Es el Cliente Web, el encargado de manejar los Sockets, las peticiones recividas y acorde a ellas dar la respuesta apropiada al navegador
* HttpConnection: Es la clase principal de la fachada, es la encargada de hacer las peticiones al API externa y transformar la respuesta en un objeto mas facilmente manejable por el cliente web.
* Cache: Es la clase encargada de simular un cache en la fachada, este se encarga de guardar y obtener una pelicula en él, ademas de mirar si la pelicula ya ha sido buscada antes.
  
## Agradecimientos

* A nuestro querido profesor de Arquitectura empresariales Daniel Benavides
* Figo

