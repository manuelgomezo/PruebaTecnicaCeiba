package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		SimpleDateFormat formateoFecha = new SimpleDateFormat("dd/MM/yyyy");
		int dias = 15;
		Date fecha1 = formateoFecha.parse("24/05/2017");
		Date fecha2 = formateoFecha.parse("26/05/2017");
		Date fecha3 = formateoFecha.parse("8/08/2019");
		Date fecha4 = formateoFecha.parse("16/08/2019");
		
		// act
		Date fechaEntrega1 = Util.calcularFecha(fecha1, dias);
		Date fechaEntrega2 = Util.calcularFecha(fecha2, dias);
		Date fechaEntrega3 = Util.calcularFecha(fecha3, dias);
		Date fechaEntrega4 = Util.calcularFecha(fecha4, dias);
		
		//assert
		assertEquals(fechaEntrega1, formateoFecha.parse("09/06/2017"));
		assertEquals(fechaEntrega2, formateoFecha.parse("12/06/2017"));
		assertEquals(fechaEntrega3, formateoFecha.parse("24/08/2019"));
		assertEquals(fechaEntrega4, formateoFecha.parse("2/09/2019"));
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
