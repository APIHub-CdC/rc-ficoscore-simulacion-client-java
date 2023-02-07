package com.cdc.apihub.mx.RC.FicoScore.simulacion.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdc.apihub.mx.RC.FicoScore.simulacion.client.ApiClient;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.client.ApiException;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.CatalogoEstados;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.DomicilioPeticion;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.PersonaPeticion;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.Respuesta;


public class ReporteDeCreditoConFicoScoreApiTest {
    private final ReporteDeCreditoConFicoScoreApi api = new ReporteDeCreditoConFicoScoreApi();
    private Logger logger = LoggerFactory.getLogger(ReporteDeCreditoConFicoScoreApiTest.class.getName());
    private ApiClient apiClient;
    
    @Before()
    public void setUp() {
        this.apiClient = api.getApiClient();
        this.apiClient.setBasePath("the_url");
    }
    
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
    
}
