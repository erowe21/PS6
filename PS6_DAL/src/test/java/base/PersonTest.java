package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class PersonTest {
	private static PersonDomainModel p;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
		p = new PersonDomainModel();
		
		p.setFirstName("Eric");
		p.setLastName("Rowe");
		p.setCity("Newark");
		p.setPostalCode(19717);
		p.setStreet("1 Scholar Drive");
		
		PersonDAL.addPerson(p);
		PersonDomainModel p1 = PersonDAL.getPerson(p.getPersonID());
		assertNotNull(p1);
		
	}
	
	@Test
	public void AddPersonTest(){
		PersonDAL.addPerson(p);
	}
	
	@Test
	public void GetPersonTest(){
		String LastName = p.getLastName();
		assertEquals("Rowe",LastName);
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
	@Test
	public void testDeleteUpdate(){
		PersonDomainModel p1 = PersonDAL.getPerson(p.getPersonID());
		assertEquals(p.getPersonID(), p1.getPersonID());
		p1.setLastName("Smith");
		PersonDAL.updatePerson(p1);
		
		PersonDomainModel p3 = PersonDAL.getPerson(p.getPersonID());
		assertEquals(p1.getLastName(), p3.getLastName()); 
		assertNotEquals(p.getLastName(),p3.getLastName());
		
		
		PersonDAL.deletePerson(p.getPersonID());
		
		PersonDomainModel p4 = PersonDAL.getPerson(p.getPersonID());
		
		assertNull(p4);
	}


	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


}


