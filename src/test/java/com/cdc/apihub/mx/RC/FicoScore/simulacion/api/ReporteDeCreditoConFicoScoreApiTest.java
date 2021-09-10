package com.cdc.apihub.mx.RC.FicoScore.simulacion.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdc.apihub.mx.RC.FicoScore.simulacion.client.ApiClient;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.client.ApiException;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.CatalogoEstados;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.Consultas;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.Creditos;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.DomicilioPeticion;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.DomiciliosRespuesta;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.Empleos;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.Mensajes;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.PersonaPeticion;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.Respuesta;
import com.cdc.apihub.mx.RC.FicoScore.simulacion.model.Scores;


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
        String xFullReport = null;
        
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
        
        Respuesta response = api.getReporte(xApiKey, request, xFullReport);
        
        Assert.assertTrue(response.getFolioConsulta() != null);
        
        	logger.info(response.toString());
        
        
        if (response.getFolioConsulta() != null && (xFullReport == null ||  xFullReport.equals("false") || xFullReport.equals("FALSE"))) {
        	
			String folioConsulta = response.getFolioConsulta();

			Consultas consultas = api.getConsultas(folioConsulta, xApiKey);
			logger.info(consultas.toString());
			Assert.assertTrue(consultas.getConsultas() != null);

			Creditos creditos = api.getCreditos(folioConsulta, xApiKey);
			logger.info(creditos.toString());
			Assert.assertTrue(creditos.getCreditos() != null);

			DomiciliosRespuesta domicilios = api.getDomicilios(folioConsulta, xApiKey);
			logger.info(domicilios.toString());
			Assert.assertTrue(domicilios.getDomicilios() != null);

			Empleos empleos = api.getEmpleos(folioConsulta, xApiKey);
			logger.info(empleos.toString());
			Assert.assertTrue(empleos.getEmpleos() != null);

			Scores scores = api.getScores(folioConsulta, xApiKey);
			logger.info(scores.toString());
			Assert.assertTrue(scores.getScores() != null);
		
			Mensajes mensajes = api.getMensajes(folioConsulta, xApiKey);
			logger.info(mensajes.toString());
			Assert.assertTrue(mensajes.getMensajes() != null);
		}
        
    }
    
}
