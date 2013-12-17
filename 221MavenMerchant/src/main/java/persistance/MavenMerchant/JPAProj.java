package persistance.MavenMerchant;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAProj {

	private static final String UNIT_NAME = "CashM";
  	private static EntityManagerFactory factory;
	public static void main(String[] args) {
	    factory = Persistence.createEntityManagerFactory(UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        EntityManager em2 = factory.createEntityManager();
        Merchant merch = null;
        Customer cust = null;
        Payment pay = null;
	    try { 
	    	merch = em.find(Merchant.class, 1);
	    	cust = em2.find(Customer.class, 1);
	    	pay = em2.find(Payment.class, 1);
	    }
		finally {
			em.close();
			em2.close();
		}
	    if (merch != null) {
	    	System.out.println(merch.getStringForPrint());
	    }
	    if (cust != null) {
	    	System.out.println(cust.getStringForPrint());
	    }
	    if (pay != null) {
	    	Collection<Payment> payments = merch.getPayments();
	    	for(Payment payValue: payments) {
	    		System.out.println(payValue.getStringForPrint());
	    	}
	    	System.out.println(pay.getStringForPrint());
	    }
  }
}

