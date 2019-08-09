package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Test;

import dominio.Util;

public class UtilTest {

	@Test
	public void sumaDigitosMayorATest() {
		// arrange
		String isbn1 = "AB056740987";
		String isbn2 = "XDF99997";
		String isbn3 = "A811B34Q";
		String isbn4 = "T818B85Z";
		int i = 30;
		
		//assert
		assertTrue(Util.sumaDigitosMayorA(isbn1, i));
		assertTrue(Util.sumaDigitosMayorA(isbn2, i));
		assertFalse(Util.sumaDigitosMayorA(isbn3, i));
		assertFalse(Util.sumaDigitosMayorA(isbn4, i));
}
		
	@Test
	public void fechaDeEntregaTest() throws ParseException {
		// arrange
		int dias = 15;
		LocalDate fecha1 = LocalDate.of(2017, 5, 24);
		LocalDate fecha2 = LocalDate.of(2017, 5, 26);
		LocalDate fecha3 = LocalDate.of(2019, 8, 8);
		LocalDate fecha4 = LocalDate.of(2019, 8, 16);
		
		// act
		LocalDate fechaEntrega1 = Util.calcularFecha(fecha1, dias);
		LocalDate fechaEntrega2 = Util.calcularFecha(fecha2, dias);
		LocalDate fechaEntrega3 = Util.calcularFecha(fecha3, dias);
		LocalDate fechaEntrega4 = Util.calcularFecha(fecha4, dias);
		
		//assert
		assertEquals(fechaEntrega1, LocalDate.of(2017, 6, 9));
		assertEquals(fechaEntrega2, LocalDate.of(2017, 6, 12));
		assertEquals(fechaEntrega3, LocalDate.of(2019, 8, 24));
		assertEquals(fechaEntrega4, LocalDate.of(2019, 9, 2));
	}
	
	@Test
	public void isbnEsPalindromoTest() {
		// arrange
		String isbn1 = "1221";
		String isbn2 = "1234";
		String isbn3 = "AA11AA";
		String isbn4 = "MADAM";
		String isbn5 = "MOM";
		String isbn6 = "CEIBA";
		
		//assert
		assertTrue(Util.esPalindromo(isbn1));
		assertFalse(Util.esPalindromo(isbn2));
		assertTrue(Util.esPalindromo(isbn3));
		assertTrue(Util.esPalindromo(isbn4));
		assertTrue(Util.esPalindromo(isbn5));
		assertFalse(Util.esPalindromo(isbn6));
	}
	
	
}
