package dominio;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class Util {
	
	private Util() { }
	
	/**
	 * Calcula la fecha sumandole n dias con la restricción de que
	 * "Si la fecha de entrega cae un domingo deberá ser el siguiente día hábil. 
	 * @param actual Fecha actual
	 * @param dias Entero numero de dias que quiere sumar
	 * @return Retorna la fecha agregandole el numero de dias y teniendo en cuenta la restricción de los domingos
	 * 
	*/	
	public static Date calcularFecha(Date actual, int dias) {
		int diasAgregados = dias - 1;
		Calendar c = Calendar.getInstance();
		c.setTime(actual);
        
		while(diasAgregados > 0){
			if(c.get(Calendar.DAY_OF_WEEK) == 7) {
				c.add(Calendar.DATE, 1);
	        } else {
	        	c.add(Calendar.DATE, 1);
	        	diasAgregados--;
	        }
		}
		return c.getTime();
	}

	/**
	 *  Hace uso de alReves para invertir el string entrado
	 *  y si el invertido es igual al entrado entonces retorna true. 
	 *  @param isbn String a verificar si es palindromo
	 *  @return Devuelve true si el string ingresado es palindromo
	*/
	public static boolean esPalindromo(String cadena) {
		String cadenaInvertida = alReves(cadena);
		return cadena.equals(cadenaInvertida);
	}
	
	/**
	 * Invierte un string "dato" entrado.
	 * @param dato String que se quiere invertir
	 * @return Devuelve el string invertido
	 */	
	private static String alReves(String cadena) {
		if (cadena.length() <= 1) {
			return cadena;
		}
		return alReves(cadena.substring(1)) + cadena.charAt(0);
	}

	/**
	 * Verifica si la suma de los digitos del string entrado es mayor a 30
	 * @param isbn String a verificar si la suma de los digitos del string entrado es mayor a 30
	 * @return Retorna TRUE si los digitos numericos del string isbn suman mas de 30
	 * 
	*/	
	public static boolean sumaDigitosMayorA(String cadena, int i) {
		String cadenaNumerico = cadena.replaceAll("[^\\d.]", "");
		int sum = String.valueOf(cadenaNumerico)
        	    .chars()
        	    .map(Character::getNumericValue)
        	    .sum();
		return sum > i;
	}
}
