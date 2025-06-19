// franquicia-business/src/main/java/es/unican/is2/franquiciaBusiness/GestionEmpleados.java
package es.unican.is2.franquiciaBusiness;

import java.util.List;
import es.unican.is2.franquiciaCommon.*;

public class GestionEmpleados implements IGestionEmpleados {

    private final IEmpleadosDAO empleados;
    private final ITiendasDAO   tiendas;

    public GestionEmpleados(IEmpleadosDAO empleados, ITiendasDAO tiendas) {
        this.empleados = empleados;
        this.tiendas   = tiendas;
    }

    @Override
    public Empleado empleado(String dni) throws DataAccessException {
        return null;
    }

    @Override
    public Empleado nuevoEmpleado(Empleado e, String nombreTienda)
            throws DataAccessException, OperacionNoValidaException {
        return null;
    }

    @Override
    public Empleado bajaEmpleado(String dni, String nombreTienda)
            throws DataAccessException, OperacionNoValidaException {
        return null;
    }

    @Override
    public Empleado registrarBaja(String dni)
            throws DataAccessException, OperacionNoValidaException {
        return null;
    }

    @Override
    public Empleado registrarAlta(String dni)
            throws DataAccessException, OperacionNoValidaException {
        return null;
    }

    @Override
    public Empleado moverEmpleado(String dni,
                                  String tiendaActual,
                                  String tiendaDestino)
            throws DataAccessException, OperacionNoValidaException {
        return null;
    }

    @Override
    public List<Empleado> empleados() throws DataAccessException {
        return List.of();
    }
}

