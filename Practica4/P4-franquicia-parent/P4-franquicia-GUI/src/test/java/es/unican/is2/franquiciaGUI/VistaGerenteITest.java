package es.unican.is2.franquiciaGUI;

import static org.junit.jupiter.api.Assertions.*;

import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.junit.jupiter.api.*;

import es.unican.is2.franquiciaCommon.*;
import es.unican.is2.franquiciaBusiness.*;
import es.unican.is2.franquiciaDao.*;

/**
 * Pruebas de integración GUI-Negocio-DAO (Consulta Tienda).
 *
 * · IT porque se ejercita la aplicación completa
 * · FEST-Swing para automatizar la interfaz
 */

public class VistaGerenteITest {

    /* ---------- fixtures ---------- */

    private FrameFixture gui;            // Ventana Swing a pilotar
    private ITiendasDAO tiendasDAO;      // Para obtener “la verdad” desde la BD
    private VistaGerente vista;          // Referencia a la GUI real

    
    @BeforeEach
    public void setUp() {
        /* 1. Capa DAO (crea e inicializa la H2 in-memory) */
        tiendasDAO    = new TiendasDAO();
        IEmpleadosDAO empleadosDAO = new EmpleadosDAO();

        /* 2. Capa de Negocio */
        GestionTiendas   gTiendas   = new GestionTiendas(tiendasDAO);
        GestionEmpleados gEmpleados = new GestionEmpleados(tiendasDAO, empleadosDAO);

        /* 3. Capa de Presentación */
        vista = new VistaGerente(gTiendas, gEmpleados);
        gui   = new FrameFixture(vista);
        vista.setVisible(true);
    }

    @AfterEach
    public void tearDown() {
        gui.cleanUp();
    }

    /* ==========  CASO VÁLIDO  (la tienda existe)  ========== */
    @Test
    public void testConsultaTiendaExistente() throws DataAccessException {

        /* --- Paso 1: introducir dato y pulsar “Buscar” --- */
        gui.textBox("txtNombreTienda").setText("Tienda A");
        gui.button("btnBuscar").click();

        
      
        

        /* --- Paso 3: aserciones caja negra --- */
        gui.textBox("txtDireccionTienda").requireText("Dirección A");
        
        
        
    }
    @Test
    public void testConsultaTiendaExistente2() throws DataAccessException {

        
        
        
        /* --- Paso 1: introducir dato y pulsar “Buscar” --- */
        gui.textBox("txtNombreTienda").setText("Tienda B");
        gui.button("btnBuscar").click();

        
      
        

        /* --- Paso 3: aserciones caja negra --- */
        gui.textBox("txtDireccionTienda").requireText("Dirección B");
        
    }
    @Test
    public void testConsultaEmpleadoTienda() throws DataAccessException {
    	
        
    	gui.textBox("txtNombreTienda").setText("Tienda A");
        gui.button("btnBuscar").click();
        
    	String[] empleados = gui.list("listNombreEmpleados").contents();
        boolean encontrado = false;
        for (String nombre : empleados) {
            if (nombre.contains("Juan") || nombre.contains("Perez") || nombre.equalsIgnoreCase("Juan Perez")) {
                encontrado = true;
                break;
            }
        }
        assertTrue(encontrado, "No se ha encontrado un empleado esperado en Tienda A");    
    }
    @Test
    public void testConsultaTiendaVacia() throws DataAccessException {
    	
        
    	gui.textBox("txtNombreTienda").setText("Tienda C");
        gui.button("btnBuscar").click();
        
    	String[] empleados = gui.list("listNombreEmpleados").contents();
        int n_empleados = empleados.length;
        assertEquals(n_empleados,0);
    }
    
}
