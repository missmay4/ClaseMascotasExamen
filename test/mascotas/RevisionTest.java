package mascotas;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mascotas.Revision;

public class RevisionTest {
	Revision revi;

	@Before
	public void setUp() throws Exception {
		revi= new Revision(LocalDate.of(2019, 02, 02),"Revisión periódica para desparasitado");
	}
	

	@After
	public void tearDown() throws Exception {
		revi =null;
	}

	@Test
	public void testConstructor() {
		assertTrue(revi instanceof Revision);
		assertNotNull(revi);
		
	}

}
