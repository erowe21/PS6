package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.PersonDomainModel;
import util.HibernateUtil;

public class PersonDAL {

	public static PersonDomainModel addPerson(PersonDomainModel per) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		int employeeID = 0;
		try{
			tr = s.beginTransaction();
			s.save(per);
			tr.commit();
		}
		catch(HibernateException e){
			if(tr != null)
				tr.rollback();
			e.printStackTrace();
		}
		finally{
			s.close();
		}
		return per;
	}

	public static ArrayList<PersonDomainModel> getPersons() {		
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		PersonDomainModel pdm = null;		
		ArrayList<PersonDomainModel> pers = new ArrayList<PersonDomainModel>();
		
		try {
			tr = s.beginTransaction();	
			
			List persons = s.createQuery("FROM PersonDomainModel").list();
			for (Iterator iterator = persons.iterator(); iterator.hasNext();) {
				PersonDomainModel per = (PersonDomainModel) iterator.next();
				pers.add(per);

			}
			
			tr.commit();
		} catch (HibernateException e) {
			if (tr != null)
				tr.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return pers;

	}

	public static PersonDomainModel getPerson(UUID perID) {		
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction tr = null;
		PersonDomainModel pdm = null;		
		
		try {
			tr = s.beginTransaction();	
									
			Query query = s.createQuery("from PersonDomainModel where personId = :id ");
			query.setParameter("id", perID.toString());
			
			List<?> list = query.list();
			pdm = (PersonDomainModel)list.get(0);
			
			tr.commit();
		} catch (HibernateException e) {
			if (tr != null)
				tr.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return pdm;
	}

public static void deletePerson(UUID perID) {
	Session s = HibernateUtil.getSessionFactory().openSession();
	Transaction tr = null;
	PersonDomainModel pdm = null;		
	
	try {
		tr = s.beginTransaction();	
								
		PersonDomainModel per = (PersonDomainModel) s.get(PersonDomainModel.class, perID);
		s.delete(per);
	
		
		tr.commit();
	} catch (HibernateException e) {
		if (tr != null)
			tr.rollback();
		e.printStackTrace();
	} finally {
		s.close();
	}
}

public static PersonDomainModel updatePerson(PersonDomainModel per) {
	//PS6 - please implement		
	Session s = HibernateUtil.getSessionFactory().openSession();
	Transaction tr = null;
	PersonDomainModel pdm = null;		
	
	try {
		tr = s.beginTransaction();	
								
		s.update(pdm);

		
		tr.commit();
	} catch (HibernateException e) {
		if (tr != null)
			tr.rollback();
		e.printStackTrace();
	} finally {
		s.close();
	}

	return pdm;
	}
}