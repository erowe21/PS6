package base;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class PersonTest {
	private static PersonDomainModel p;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
		p = new PersonDomainModel();
		
		p.setFirstName("Jack");
		p.setLastName("Lee");
		p.setCity("Newark");
		p.setPostalCode(12345);
		p.setStreet("1 Main street");
		
	}
	
	@Test
	public void AddPersonTest(){
		PersonDAL.addPerson(p);
	}
	
	@Test
	public void GetPersonTest(){
		String LastName = p.getLastName();
		assertEquals("Lee",LastName);
	}
	
	@Test
	public void UpdatePersonTest(){
		p.setFirstName("Lee");
		PersonDAL.updatePerson(p);
	}
	
	@Test
	public void deletePersontest(){
		PersonDAL.addPerson(p);
	}



	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


}
