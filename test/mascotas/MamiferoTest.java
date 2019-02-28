package mascotas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mascotas.Mamifero;
import mascotas.Mascota;
import mascotas.Revision;

public class MamiferoTest {
	 public Mamifero nestor, samos;
	 Revision rev1, rev2, rev3, rev4;

	@Before
	public void setUp() throws Exception {
		nestor= new Mamifero("Nestor","Perro", LocalDate.of(2014, 6, 20),"012345678901234");
		 rev1= new Revision(LocalDate.of(2014,9,20), "Revisión a los 3 meses");
		 rev2= new Revision(LocalDate.of(2015,6,20), "Revisión anual y vacunaciones");
		 rev3= new Revision(LocalDate.of(2016,6,20), "Revisión a los 2 años de nacimiento");
	}

	@After
	public void tearDown() throws Exception {
		nestor=null;
	}

	@Test
	public void testConstructor1() {
		System.out.println("Pruebo constructor correcto de mamifero");
		assertTrue(nestor instanceof Mamifero && nestor instanceof Mascota );
		assertNotNull(nestor);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor2() {
		System.out.println("Pruebo constructor con chip de 14 dígitos");
		samos= new Mamifero("Samos","Perro", LocalDate.of(2014, 6, 20),"0123456789012345");
		assertNull(samos);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor3() {
		System.out.println("Pruebo constructor con chip de 16 dígitos");
		samos= new Mamifero("Samos","Perro", LocalDate.of(2014, 6, 20),"01234567890123");
		assertNull(samos);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testConstructor4() {
		System.out.println("Pruebo constructor con chip de 15 caracteres no dígitos");
		samos= new Mamifero("Samos","Perro", LocalDate.of(2014, 6, 20),"0123456789ABCDE");
		assertNull(samos);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testConstructor5() {
		System.out.println("Pruebo constructor con fecha de nacimiento improbable");
		samos= new Mamifero("Samos","Perro", LocalDate.now().plusMonths(3L),"012345678901234");
		assertNull(samos);
	}
	
	@Test
	public void testCalcularEdad() {
		System.out.println("Probamos la edad ");
		int esperada= Period.between(LocalDate.of(2014, 6, 20), LocalDate.now()).getMonths()+
				12*Period.between(LocalDate.of(2014, 6, 20), LocalDate.now()).getYears();
		assertEquals(esperada, nestor.calcularEdad());
	}
	
	@Test
	public void testAgregarRevision() {
		System.out.println("Probamos el método para agregar revisiones, añadiendo 2 ");
		assertTrue(nestor.agregarRevision(LocalDate.of(2014,9,20), "Revisión a los 3 meses"));
		assertTrue(nestor.agregarRevision(LocalDate.of(2015,6,20), "Revisión anual y vacunaciones"));
	
	}
	
	@Test
	public void testListarRevisiones() {
		Revision[] esperada= new Revision[] {rev1, rev2, rev3 };
		//agregamos 3 revisiones a la lista
		nestor.agregarRevision(LocalDate.of(2014,9,20), "Revisión a los 3 meses");
		nestor.agregarRevision(LocalDate.of(2015,6,20), "Revisión anual y vacunaciones");
		nestor.agregarRevision(LocalDate.of(2016,6,20), "Revisión a los 2 años de nacimiento");
		assertArrayEquals(esperada, nestor.listarRevisiones());
	}
	
	@Test
	public void testToString() {
		//comprobamos que en el método toString están contenidos los datos esenciales
		assertTrue( nestor.toString().indexOf("Nestor")>=0 && nestor.toString().indexOf("Perro")>=0 &&
				nestor.toString().indexOf("012345678901234")>=0 && nestor.toString().indexOf("2014")>=0);
	}
	
	@Test
	public void testCalcularProximaRevision1() {
		System.out.println("para los perros de más de 3 años, las revisiones son semestrales");
		Period tiempo= Period.between(nestor.getFechaNacimiento(), LocalDate.now());
		//convertimos el período en unidades enteras de 6 meses
		int semestres=(12*tiempo.getYears()+tiempo.getMonths())/6;
		int resto= (12*tiempo.getYears()+tiempo.getMonths())%6;
		LocalDate proximaRev;
		if (resto>0)
			proximaRev= nestor.getFechaNacimiento().plusMonths(6*(semestres+1));
		else
			proximaRev= nestor.getFechaNacimiento().plusMonths(6*semestres);
		System.out.println(proximaRev);
		System.out.println(nestor.calcularProximaRevision());
		assertTrue(proximaRev.equals(nestor.calcularProximaRevision()));
	
	}
	
	@Test
	public void testCalcularProximaRevision2() {
		System.out.println("para los perros de hasta 3 años, las revisiones son trimestrales. Probamos un perro nacido hace 2 años y 9 meses");
		samos= new Mamifero("Samos","Perro", LocalDate.now().minusMonths(33),"012345678901234");
		//la siguiente revisión debe ser justo hoy
		assertTrue(LocalDate.now().equals(samos.calcularProximaRevision()));
	
	}

}
