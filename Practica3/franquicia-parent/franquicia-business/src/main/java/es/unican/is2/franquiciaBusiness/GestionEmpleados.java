package es.unican.is2.franquiciaBusiness;

import es.unican.is2.franquiciaCommon.*;


public class GestionEmpleados implements IGestionEmpleados {

    private final ITiendasDAO   tiendas;
    private final IEmpleadosDAO empleados;

    public GestionEmpleados(ITiendasDAO daoTiendas, IEmpleadosDAO daoEmpleados) {
        this.tiendas   = daoTiendas;
        this.empleados = daoEmpleados;
    }

    @Override
    public Empleado nuevoEmpleado(Empleado e, String nombre)
            throws OperacionNoValidaException, DataAccessException {

        Tienda t = tiendas.tiendaPorNombre(nombre);
        if (t == null) {
            return null;
        }
        if (t.buscaEmpleado(e.getDNI()) != null) {
            throw new OperacionNoValidaException(nombre);
        }
        Empleado creado = empleados.crearEmpleado(e);
        t.getEmpleados().add(creado);
        tiendas.modificarTienda(t);
        return creado;
    }

    @Override
    public Empleado eliminarEmpleado(String dni, String nombre)
            throws OperacionNoValidaException, DataAccessException {

        Tienda t = tiendas.tiendaPorNombre(nombre);
        if (t == null) {
            return null;
        }
        Empleado e = empleados.empleado(dni);
        if (e == null) {
            return null;
        }
        if (!t.getEmpleados().contains(e)) {
            throw new OperacionNoValidaException(nombre);
        }
        Empleado eliminado = empleados.eliminarEmpleado(dni);
        t.getEmpleados().remove(eliminado);
        tiendas.modificarTienda(t);
        return eliminado;
    }

    @Override
    public boolean trasladarEmpleado(String dni, String actual, String destino)
            throws OperacionNoValidaException, DataAccessException {

        Tienda origen  = tiendas.tiendaPorNombre(actual);
        Tienda destinoT = tiendas.tiendaPorNombre(destino);
        if (origen == null || destinoT == null) {
            return false;
        }
        Empleado e = empleados.empleado(dni);
        if (e == null) {
            return false;
        }
        if (!origen.getEmpleados().contains(e)) {
            throw new OperacionNoValidaException(destino);
        }
        origen.getEmpleados().remove(e);
        destinoT.getEmpleados().add(e);
        tiendas.modificarTienda(origen);
        tiendas.modificarTienda(destinoT);
        return true;
    }

    @Override
    public Empleado empleado(String dni) throws DataAccessException {
        return empleados.empleado(dni);
    }
}


