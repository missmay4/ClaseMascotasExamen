package mascotas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mascotas.Ave;
import mascotas.Mascota;

public class AveTest {
	 public Ave arami, lucas;


	@Before
	public void setUp() throws Exception {
		arami= new Ave("Aramí","Amazonas Aestivia", LocalDate.of(2016, 6, 20),"BSD-091");

	}

	@After
	public void tearDown() throws Exception {
		arami=null;
		lucas=null;
	}

	@Test
	public void testConstructor1() {
		System.out.println("Pruebo constructor correcto de ave");
		assertTrue(arami instanceof Ave && arami instanceof Mascota );
		assertNotNull(arami);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor2() {
		System.out.println("Pruebo constructor con anilla sin dígitos");
		lucas= new Ave("Aramí","Amazonas Aestivia", LocalDate.of(2016, 6, 20),"BSD-XYZ");
		assertNull(lucas);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor3() {
		System.out.println("Pruebo constructor con anilla sin letras");
		lucas= new Ave("Aramí","Amazonas Aestivia", LocalDate.of(2016, 6, 20),"456-091");
		assertNull(lucas);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor4() {
		System.out.println("Pruebo constructor con anilla con formato incorrecto");
		lucas= new Ave("Aramí","Amazonas Aestivia", LocalDate.of(2016, 6, 20),"BSD091");
		assertNull(lucas);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testConstructor5() {
		System.out.println("Pruebo constructor con fecha de nacimiento improbable");
		lucas= new Ave("Aramí","Amazonas Aestivia", LocalDate.now().plusMonths(3L),"BSD-091");
		assertNull(lucas);
	}
	
	@Test
	public void testCalcularEdad() {
		System.out.println("Probamos la edad ");
		int esperada= Period.between(LocalDate.of(2016, 6, 20), LocalDate.now()).getMonths()+
				12*Period.between(LocalDate.of(2016, 6, 20), LocalDate.now()).getYears();
		assertEquals(esperada, arami.calcularEdad());
	}
	
	@Test
	public void testToString() {
		//comprobamos que en el método toString están contenidos los datos esenciales
		assertTrue( arami.toString().indexOf("Aramí")>=0 && arami.toString().indexOf("Amazonas Aestivia")>=0 &&
				arami.toString().indexOf("BSD-091")>=0 && arami.toString().indexOf("2016")>=0)  ;
	}
	
	
	
	@Test
	public void testCalcularProximaRevision1() {
		System.out.println("para las AVESlas revisiones son semestrales");
		Period tiempo= Period.between(arami.getFechaNacimiento(), LocalDate.now());
		//convertimos el período en unidades enteras de 6 meses
		int semestres=(12*tiempo.getYears()+tiempo.getMonths())/6;
		int resto= (12*tiempo.getYears()+tiempo.getMonths())%6;
		LocalDate proximaRev;
		if (resto>0)
			proximaRev= arami.getFechaNacimiento().plusMonths(6*(semestres+1));
		else
			proximaRev= arami.getFechaNacimiento().plusMonths(6*semestres);
		System.out.println(proximaRev);
		System.out.println(arami.calcularProximaRevision());
		assertTrue(proximaRev.equals(arami.calcularProximaRevision()));
	
	}
	
	@Test
	public void testCalcularProximaRevision2() {
		System.out.println("para las aves, las revisiones son semestrales. Probamos con un ave nacida hace 1 año justamente");
		lucas= new Ave("Aramí","Amazonas Aestivia", LocalDate.now().minusYears(1),"BSD-091");
	
		assertTrue(LocalDate.now().equals(lucas.calcularProximaRevision()));
	
	}

}
