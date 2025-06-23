package es.unican.is2.franquiciaCommon;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


/**
 * Pruebas unitarias de caja negra sobre Empleado.
 *
 * Técnicas aplicadas
 * ───────────────────
 * • Partición Equivalente  (valores “normales”, “límite” e “inválidos”)
 * • AVL (Análisis de Valores Límite) sobre la antigüedad
 *
 * Cobertura requerida   → 100 % decisión/condición en sueldoBruto()
 */
class EmpleadoITest {

    /*  Particiones de equivalencia consideradas
        ─────────────────────────────────────────
        1. Categoría             {ENCARGADO, VENDEDOR, AUXILIAR}
        2. Antigüedad (años)     {0-5, 6-10, 11-20, >20}
        3. Baja                  {false, true}
        4. Datos nulos/incorrectos
     */

    // 1) Casos VÁLIDOS  ────────────────────────────────────────────────
    @Nested
    @DisplayName("Cálculo de sueldo bruto – casos válidos")
    class SueldoValido {

        @Test
        @DisplayName("ENCARGADO, <=5 años, sin baja")
        void sueldoEncargadoRecienteSinBaja() {
            Empleado e = new Empleado( "11111111A", "Ana",
                                Categoria.ENCARGADO,
                                LocalDate.now().minusYears(3),
                                false );
            assertEquals(2000.0, e.sueldoBruto(), 1e-6);
        }

        @Test
        @DisplayName("VENDEDOR, 8 años, sin baja")
        void sueldoVendedorAntiguedadMediaSinBaja() {
            Empleado e = new Empleado ( "22222222B", "Bea",
                                Categoria.VENDEDOR,
                                LocalDate.now().minusYears(8),
                                false );
            // 1 tramo de antigüedad (50 €)  →  1500 + 50 = 1550
            assertEquals(1550.0, e.sueldoBruto(), 1e-6);
        }

        @Test
        @DisplayName("AUXILIAR, 15 años, con baja")
        void sueldoAuxiliarAntiguoConBaja() {
            Empleado e = new Empleado( "33333333C", "Carlos",
                                Categoria.AUXILIAR,
                                LocalDate.now().minusYears(15),
                                true );
            // Base 1000  +100 antigüedad  =1100  →  25 % de descuento → 825
            assertEquals(825.0, e.sueldoBruto(), 1e-6);
        }

        @Test
        @DisplayName("ENCARGADO, 26 años, sin baja (máxima antigüedad)")
        void sueldoEncargadoMuyAntiguo() {
            Empleado e = new Empleado( "44444444D", "Dora",
                                Categoria.ENCARGADO,
                                LocalDate.now().minusYears(26),
                                false );
            // 3 tramos antigüedad (50+50+100) =200   →  2000+200=2200
            assertEquals(2200.0, e.sueldoBruto(), 1e-6);
        }
    }

    // 2) Casos INVÁLIDOS  ──────────────────────────────────────────────
    @Nested
    @DisplayName("Creación de empleado – casos no válidos")
    class DatosInvalidos {

        @Test
        @DisplayName("Categoría nula")
        void categoriaNulaLanzaNPE() {
        	//prueba de categoria nula
    		Empleado empleado1 =  new Empleado("Pepe", "77439923A", null, LocalDate.of(2020, 1, 1), false);
    		assertThrows(NullPointerException.class, () -> empleado1.getCategoria());
        }	
    	
        @Test
        @DisplayName("Fecha contratación futura")
        void fechaFuturaLanzaIAE() {
        	Empleado empleado2 =  new Empleado("Pepe", "77439923A", Categoria.ENCARGADO, LocalDate.of(2028, 1, 1), false);
    		assertThrows(IllegalArgumentException.class, () -> empleado2.getFechaContratacion());
        }
    }   
    
}
