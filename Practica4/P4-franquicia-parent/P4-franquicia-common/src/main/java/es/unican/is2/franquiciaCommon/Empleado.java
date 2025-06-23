package es.unican.is2.franquiciaCommon;




import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 * Clase que representa un empleado de la franquicia, 
 * con sus datos personales 
 * y su estado en la franquicia (baja y categoria)
 */
public class Empleado {
	
	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja = false;
	
	public Empleado() {	}
	
	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false. 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion,boolean baja) {
		this.nombre = nombre;
		this.DNI=DNI;
		this.categoria=categoria;
		this.fechaContratacion=fechaContratacion;
		this.baja=baja;
	}
	
	/**
	 * Retorna el sueldo bruto del empleado
	 */
	public double sueldoBruto() {
		if (categoria == null) {
	            throw new IllegalStateException("Categoría no establecida");
	        }
        double base;
        switch (categoria) {
            case ENCARGADO: base = 2000; break;
            case VENDEDOR:  base = 1500; break;
            case AUXILIAR:  base = 1000; break;
            default:        base = 0;
        }
        long antig = ChronoUnit.YEARS.between(fechaContratacion, LocalDate.now());
        double comp = antig > 20 ? 200 : antig > 10 ? 100 : antig > 5 ? 50 : 0;
        double sueldo = base + comp;
        return baja ? sueldo * 0.75 : sueldo;
    }

	
	
	/** 
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja=true;
	}
	
	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja=false;
	}
	
	
	/**
	 * Retorna el dni del vendedor
	 * @return id
	 */
	public String getDNI() {
		if (DNI== null || DNI.isBlank()) {
            throw new IllegalArgumentException("DNI nulo o vacío");
        }
		return DNI;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre nulo o vacío");
        }
		return nombre;
	}
	
	/**
	 * Retorna la categoria del empleado
	 *  @return categoria
	 */
	public Categoria getCategoria () {
		if (categoria == null) {
            throw new NullPointerException("Fecha de contratación nula");
        }
		return categoria;
	}
	
	/**
	 * Retorna la fecha de contrato
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		if (fechaContratacion == null) {
            throw new NullPointerException("Fecha de contratación nula");
        }
        if (fechaContratacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha de contratación futura");
        }
		return fechaContratacion;
	}
	
	/**
	 * Retorna si el empleado est� de baja
	 * @return true si esta de baja
	 *         false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}
		
	
	public void setDNI(String dNI) {
		
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		
		this.nombre = nombre;
	}
	
	public void setFechaContratacion(LocalDate fechaContratacion) {
		
		this.fechaContratacion = fechaContratacion;
	}
	
	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
