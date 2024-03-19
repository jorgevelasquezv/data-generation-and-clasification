## Entrega Modulo Conceptos Fundamentales de Programación Politecnico Grancolombiano 

Programa que tome como entrada una serie de archivos con información de vendedores. Habrá un archivo de texto plano por cada vendedor, el cual tendrá el siguiente formato (de una venta por línea):

**TipoDocumentoVendedor;NúmeroDocumentoVendedor**

**IDProducto1;CantidadProducto1Vendido;**  
**IDProducto2;CantidadProducto2Vendido;**  
**IDProducto3;CantidadProducto3Vendido;**  

La cantidad de vendedores es desconocida, pero como entrada el programa tendrá varios (posiblemente, muchos) archivos planos en una carpeta, cada uno con la información de ventas de un vendedor. Todos los archivos de vendedores deben estar en la misma carpeta de proyecto que el programa a entregar, con el fin de facilitar la exploración de estos archivos desde el programa. Adicionalmente, el programa tendrá como entrada un archivo con la información de los vendedores. El archivo de texto plano tendrá el formato que se describe a continuación, con un vendedor por línea.

**Formato archivo de información vendedores:**

**TipoDocumento;NúmeroDocumento;NombresVendedor1;ApellidosVendedor1**
**TipoDocumento;NúmeroDocumento;NombresVendedor2;ApellidosVendedor2**
**TipoDocumento;NúmeroDocumento;NombresVendedor3;ApellidosVendedor3**

El programa también tendrá como entrada un archivo con la información de todos los productos disponibles. Cada producto debe ir con el ID y el nombre.

**Formato archivo de información productos:**  
**IDProducto1;NombreProducto1;PrecioPorUnidadProducto1**  
**IDProducto2;NombreProducto2;PrecioPorUnidadProducto2**  
**IDProducto3;NombreProducto3;PrecioPorUnidadProducto3**  

La tarea mínima del proyecto consiste en diseñar e implementar un programa en Java bien sustentado y con buenas costumbres de programación que, tomando como entrada los archivos descritos, haga las siguientes tareas:

1. Muy buena documentación del código según los estándares de documentación en Java.
2. Excelentes prácticas de programación, especialmente en el espaciado y nombramiento de variables.
3. El programa debe crear un archivo con la información de todos los vendedores, de a uno por línea. Al frente del nombre de cada vendedor, separado por punto y coma, debe estar la cantidad de dinero que recaudó según los archivos. El archivo debe estar ordenado por cantidad de dinero, de mayor a menor, de a un vendedor por línea. Es básicamente un archivo de reporte de ventas de los vendedores, del mejor al peor; un archivo CSV.
4. El programa debe crear un archivo con la información de los productos vendidos por cantidad, ordenados en forma descendente. Deben ir el nombre y el precio, separados por punto y coma, y de a un producto por línea. Es básicamente un archivo plano CSV.
5. Para propósitos de prueba, se deben crear métodos de generación de archivos de prueba para el programa en cuestión. Entre estos métodos deben estar al menos:
    - **createSalesMenFile**( int randomSalesCount, String name, long id): dada una cantidad, un nombre y un id, crea un archivo pseudoaleatorio de ventas de un vendedor con el nombre y el id dados.
    - **createProductsFile**( int productsCount ): crea un archivo con información pseudoaleatoria de productos, con los datos de productsCount productos.
    - **createSalesManInfoFile**( int salesmanCount ): crea un archivo con información de salesmanCount vendedores; el número de estos según lo indique el argumento entero. La información debe ser generada de manera pseudoaleatoria y ser coherente, es decir, los nombres y apellidos pueden ser extraídos de listas de nombres reales de personas. 

**Extra:** el programa puede tener al menos uno de los siguientes elementos, lo cual influirá muy positivamente en la nota asignada:
 - La posibilidad de procesar más de un archivo por vendedor.
 - La posibilidad de trabajar con archivos serializados.
 - La posibilidad de detectar archivos con formato erróneo o con información incoherente, como un id de producto que no exista, o precios o cantidades negativas.

Dado que hay 5 elementos en la lista de instrucciones, cada uno tiene la misma prioridad en la calificación.  

En este proyecto deben haber dos (y solo dos) clases con método main.
La primera clase debe llamarse **GenerateInfoFiles** y al ejecutarse debe generar los archivos planos pseudoaleatorios que servirán como entrada para la ejecución de la segunda clase con método main. El programa debe mostrar un mensaje de finalización exitosa o un mensaje de error, en caso de que algo salga mal.  

La segunda clase debe llamarse **main** y al ejecutarse debe realizar las tareas de creación de los archivos solicitados de reportes en los puntos 3 y 4 de la lista de requisitos señalados anteriormente. El programa debe mostrar un mensaje de finalización exitosa o un mensaje de error, en caso de que algo salga mal. Vale la pena anotar que ninguno de los dos programas a ejecutar puede solicitar información al
usuario. En todos los programas se espera que el estudiante o grupo de estudiantes compartan un hipervínculo a un repositorio de código como github o bitbucket en donde tengan su proyecto almacenado.