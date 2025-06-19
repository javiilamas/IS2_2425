// franquicia-business/src/main/java/es/unican/is2/franquiciaBusiness/GestionTiendas.java
package es.unican.is2.franquiciaBusiness;

import java.util.List;
import es.unican.is2.franquiciaCommon.*;

public class GestionTiendas implements IGestionTiendas {

    private final ITiendasDAO   tiendas;
    private final IEmpleadosDAO empleados;

    public GestionTiendas(ITiendasDAO tiendas, IEmpleadosDAO empleados) {
        this.tiendas   = tiendas;
        this.empleados = empleados;
    }

    @Override
    public Tienda tienda(String nombre) throws DataAccessException {
        return null;
    }

    @Override
    public List<Tienda> tiendas() throws DataAccessException {
        return List.of();
    }

    @Override
    public Tienda nuevaTienda(Tienda t)
            throws DataAccessException, OperacionNoValidaException {
        return null;
    }

    @Override
    public Tienda bajaTienda(String nombre)
            throws DataAccessException, OperacionNoValidaException {
        return null;
    }

    @Override
    public double costeMensual(String nombre) throws DataAccessException {
        return 0.0;
    }
}
