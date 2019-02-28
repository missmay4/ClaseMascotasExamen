package mascotas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mascotas.Mascota;
import mascotas.Reptil;
import mascotas.Revision;

public class ReptilTest {
	 public Reptil henri, henri2;
	 Revision rev1, rev2, rev3, rev4;

	@Before
	public void setUp() throws Exception {
		henri= new Reptil("Henri","Tuatara", LocalDate.of(1901, 1, 15));
		 rev1= new Revision(LocalDate.of(2014,9,20), "Revisión a los 3 meses");
		 rev2= new Revision(LocalDate.of(2015,6,20), "Revisión anual y vacunaciones");
		 rev3= new Revision(LocalDate.of(2016,6,20), "Revisión a los 2 años de nacimiento");
	}

	@After
	public void tearDown() throws Exception {
		henri=null;
	}

	@Test
	public void testConstructor1() {
		System.out.println("Pruebo constructor correcto de reptil");
		assertTrue(henri instanceof Reptil && henri instanceof Mascota );
		assertNotNull(henri);
	}
	
		
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor2() {
		System.out.println("Pruebo constructor con fecha de nacimiento improbable");
		henri2= new Reptil("Henri","Tuatara", LocalDate.now().plusMonths(3L));
		
		assertNull(henri2);
	}
	
	@Test
	public void testCalcularEdad() {
		System.out.println("Probamos la edad ");
		int esperada= Period.between(LocalDate.of(1901, 1, 15), LocalDate.now()).getMonths()+
				12*Period.between(LocalDate.of(1901, 1, 15), LocalDate.now()).getYears();
		System.out.println(esperada);
		assertEquals(esperada, henri.calcularEdad());
	}
	
	@Test
	public void testToString() {
		System.out.println("Probamos el método toString()");
		//comprobamos que en el método toString están contenidos los datos esenciales
		assertTrue( henri.toString().indexOf("Henri")>=0 && henri.toString().indexOf("Tuatara")>=0 &&
				henri.toString().indexOf("1901")>=0);
	}
	
	@Test
	public void testCalcularProximaRevision() {
		System.out.println("para los reptiles, las revisiones son anuales");
		Period tiempo= Period.between(LocalDate.of(1901, 1, 15), LocalDate.now());
		LocalDate proximaRevision= LocalDate.of(1901, 1, 15).plusYears(tiempo.getYears()+1);
		assertEquals(proximaRevision,  henri.calcularProximaRevision());
	
	}
	
	

}
