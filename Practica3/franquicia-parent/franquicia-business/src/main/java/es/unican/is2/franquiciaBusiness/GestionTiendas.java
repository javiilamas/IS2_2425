package es.unican.is2.franquiciaBusiness;

import es.unican.is2.franquiciaCommon.*;


public class GestionTiendas implements IGestionTiendas {

    private final ITiendasDAO tiendas;

    public GestionTiendas(ITiendasDAO daoTiendas) {
        this.tiendas = daoTiendas;
    }

    @Override
    public Tienda nuevaTienda(Tienda t) throws DataAccessException {
        if (tiendas.tiendaPorNombre(t.getNombre()) != null) {
            return null;
        }
        return tiendas.crearTienda(t);
    }

    @Override
    public Tienda eliminarTienda(String nombre)
            throws OperacionNoValidaException, DataAccessException {

        Tienda t = tiendas.tiendaPorNombre(nombre);
        if (t == null) {
            return null;
        }
        if (!t.getEmpleados().isEmpty()) {
            throw new OperacionNoValidaException(nombre);
        }
        return tiendas.eliminarTienda(t.getId());
    }

    @Override
    public Tienda tienda(String nombre) throws DataAccessException {
        return tiendas.tiendaPorNombre(nombre);
    }
}
