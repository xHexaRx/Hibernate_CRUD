package hbcrud;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PManager {
	
	private static SessionFactory factory;

	public static void main(String[] args) {
		factory = new Configuration().configure().addAnnotatedClass(Programmer.class).buildSessionFactory();
		PManager pmanager = new PManager();
		Integer programmer1= pmanager.addProgrammer("Vova", "St.Petersburg", "Junior");
		Integer programmer2= pmanager.addProgrammer("Vasya", "Moscow", "Senior");
		Integer programmer3= pmanager.addProgrammer("Petya", "St.Petersburg", "Middle");
		System.out.println("After adding:");
		pmanager.listProgrammers();
		pmanager.updateProgrammer(programmer2, "Uryupinsk");
		System.out.println("After updating:");
		pmanager.listProgrammers();
		pmanager.deleteProgrammer(programmer3);
		System.out.println("After deleting:");
		pmanager.listProgrammers();
	}
	
	public Integer addProgrammer(String name, String location, String qualification) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Programmer programmer = new Programmer();
		programmer.setName(name);
		programmer.setLocation(location);
		programmer.setQualification(qualification);
		Integer programmer_id=(Integer)session.save(programmer);
		tx.commit();
		session.close();
		return programmer_id;
	}
	
	public void listProgrammers() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		List programmers = session.createQuery("FROM Programmer").list();
		for(Iterator iterator = programmers.iterator(); iterator.hasNext();) {
			Programmer programmer = (Programmer)iterator.next();
			System.out.print("Programmer_id: "+programmer.getId());
			System.out.print("  Name: "+programmer.getName());
			System.out.print("  Location: "+programmer.getLocation());
			System.out.println("  Qualification: "+programmer.getQualification());
		}
		tx.commit();
		session.close();
	}
	
	public void updateProgrammer(Integer id, String location) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Programmer programmer = session.get(Programmer.class, id);
		programmer.setLocation(location);
		session.update(programmer);
		tx.commit();
		session.close();
	}
	
	public void deleteProgrammer(Integer id) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Programmer programmer = session.get(Programmer.class, id);
		session.delete(programmer);
		tx.commit();
		session.close();
	}

}
