package app;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAProj {

	private static final String UNIT_NAME = "CashM";
  	private static EntityManagerFactory factory;
	public static void main(String[] args) {
	    factory = Persistence.createEntityManagerFactory(UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        Merchant merch = null;
	    try { 
	    	merch = em.find(Merchant.class, 1);
	    }
		finally {
			em.close();
		}
	    if (merch != null) {
	    	System.out.println(merch.getStringForPrint());
	    }
  }
}

