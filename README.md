# rc-ficoscore-simulacion-client-java
[![GitHub Packages](https://img.shields.io/badge/Maven&nbsp;package-Last&nbsp;version-lemon)](https://github.com/orgs/APIHub-CdC/packages?repo_name=rcc-ficoscore-pld-simulacion-client-java) 

Esta API reporta el historial crediticio, el cumplimiento de pago de los compromisos que la persona ha adquirido con entidades financieras, no financieras e instituciones comerciales que dan crédito o participan en actividades afines al crédito. En esta versión se retornan los campos del Crédito Asociado a Nomina (CAN) en el nodo de créditos. <br/><img src='https://github.com/APIHub-CdC/imagenes-cdc/blob/master/circulo_de_credito-apihub.png' height='37' width='160'/></p><br/>

## Requisitos

1. Java >= 1.7
2. Maven >= 3.3

## Instalación

Para la instalación de las dependencias se deberá ejecutar el siguiente comando:

```shell
mvn install -Dmaven.test.skip=true
```

> **NOTA:** Este fragmento del comando *-Dmaven.test.skip=true* evitará que se lance la prueba unitaria.


## Guía de inicio

### Paso 1. Agregar el producto a la aplicación

Al iniciar sesión seguir los siguientes pasos:

 1. Dar clic en la sección "**Mis aplicaciones**".
 2. Seleccionar la aplicación.
 3. Ir a la pestaña de "**Editar '@tuApp**' ".
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/edit_applications.jpg" width="900">
    </p>
 4. Al abrirse la ventana emergente, seleccionar el producto.
 5. Dar clic en el botón "**Guardar App**":
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/selected_product.jpg" width="400">
    </p>

### Paso 2. Capturar los datos de la petición

Los siguientes datos a modificar se encuentran en ***src/test/java/com/cdc/apihub/mx/RC/FicoScore/simulacion/ReporteDeCreditoConFicoScoreApiTest.java***

Es importante contar con el setUp() que se encargará de inicializar la url. Modificar la URL ***('the_url')***, como se muestra en el siguiente fragmento de código:

```java
private final ReporteDeCreditoConFicoScoreApi api = new ReporteDeCreditoConFicoScoreApi();
    private Logger logger = LoggerFactory.getLogger(ReporteDeCreditoConFicoScoreApiTest.class.getName());
    private ApiClient apiClient;
    
    @Before()
    public void setUp() {
        this.apiClient = api.getApiClient();
        this.apiClient.setBasePath("the_url");
    }
```

De igual manera, en el archivo **ReporteDeCreditoConFicoScoreApiTest.java**, se deberá modificar el siguiente fragmento de código con los datos correspondientes:

```java
@Test
    public void getReporteTest() throws ApiException {
    	
        String xApiKey = "your_apikey";
        
        PersonaPeticion request = new PersonaPeticion();
        DomicilioPeticion domicilio = new DomicilioPeticion();
        
        request.setPrimerNombre("JUAN PRUEBA SIETE");
        request.setApellidoPaterno("PRUEBA");
        request.setApellidoMaterno("SIETE");
        request.setFechaNacimiento("1965-08-09");
        request.setRFC("PUSJ800107H2O");
        request.setNacionalidad("MX");
        
        domicilio.setDireccion("INSURGENTES SUR 1001");
        domicilio.setColoniaPoblacion("INSURGENTES SUR");
        domicilio.setDelegacionMunicipio("CIUDAD DE MEXICO");
        domicilio.setCiudad("CIUDAD DE MEXICO");
        domicilio.setEstado(CatalogoEstados.CDMX);
        domicilio.setCP("11230");
        
        request.setDomicilio(domicilio);
        
        Respuesta response = api.getReporte(xApiKey, request);
        
        Assert.assertTrue(response.getFolioConsulta() != null);
        
        logger.info(response.toString());
        
    }
```

### Paso 3. Ejecutar la prueba unitaria

Teniendo los pasos anteriores ya solo falta ejecutar la prueba unitaria, con el siguiente comando:

```shell
mvn test -Dmaven.install.skip=true
```