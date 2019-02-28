package mascotas;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mascotas.Mascota;


public class MascotaTest {
	Mascota nestor, arami, henri;

	@Before
	public void setUp() throws Exception {
		nestor = new Mascota("Nestor", "Hamster", LocalDate.of(1998, 9, 02));
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test 
	public void testConstructor() {
		assertTrue(nestor instanceof Mascota);
		
	}
	@Test
	public void testCalcularEdad() {
		assertTrue(nestor.calcularEdad() == 245);
	}
	

	@Test
	//sólo probamos el método compareTu
	public void testCompareTo() {
		nestor= new Mamifero("Nestor","Perro", LocalDate.of(2014, 6, 20),"012345678901234");
		arami= new Ave("Aramí","Amazonas Aestivia", LocalDate.of(2016, 6, 20),"BSD-091");
		henri= new Reptil("Henri","Tuatara", LocalDate.of(1901, 1, 15));
		//arami y nestor tienen igual fecha de siguiente revisión, por lo que aparece primer nestor
		assertTrue( nestor.compareTo(arami)<0);
		if (henri.calcularProximaRevision().compareTo(nestor.calcularProximaRevision()) >0){
			//las revisiones de Nestor y Aramí son antes que la de Henri
			assertTrue( nestor.compareTo(henri)<0);
			assertTrue( arami.compareTo(henri)<0);
		} else {
			assertTrue( nestor.compareTo(henri)>0);
			assertTrue( arami.compareTo(henri)>0);
		}
		
	}

}
