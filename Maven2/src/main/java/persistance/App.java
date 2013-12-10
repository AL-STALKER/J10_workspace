package persistance;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class App 
{
	private static final String UNIT_NAME = "CashM";
  	private static EntityManagerFactory factory;
	public static void main(String[] args) {
	    factory = Persistence.createEntityManagerFactory(UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        EntityManager em2 = factory.createEntityManager();
        Merchant merch = null;
        Customer cust = null;
        Payment pay = null;
        TypedQuery<Payment> query = em.createQuery(
        		"SELECT p FROM Payment p WHERE p.merchant.id = 3",
        		Payment.class);
        List<Payment> listM = null;
        try {
        	listM = query.getResultList();
        } finally {
        	//em.close();
        }
        if (listM != null) {
        	for(Payment p: listM) {
        		System.out.println(p.getStringForPrint());
        	}
        }
        TypedQuery<String> query2 = em2.createQuery(
        		"SELECT c.name FROM Payment p, Customer c " +
        		"WHERE c.ID = p.customerId AND p.total > 500.0",
        				String.class);
        List<String> listM2 = null;
        try {
        	listM2 = query2.getResultList();
        } finally {
        	//em2.close();
        }
        
        TypedQuery<Double> query3 = em.createQuery(
        		"SELECT SUM(p.total) FROM Payment p", Double.class);
        	double result = 0.0;
        	try {
        		result = query3.getSingleResult();
        	} finally {
        		//em.close();
        	}
        	System.out.println("total = " + result);

        	TypedQuery<Res> query4 = em.createQuery(
        	"SELECT new persistance.Res(p.merchant.name, SUM(p.charge)) FROM Payment p GROUP BY p.merchant.name", 
        		Res.class);
        	List<Res> nResult = null;
        	try {nResult = query4.getResultList();}
        	finally{
        		//em.close();
        	}
        	for (Res r : nResult){System.out.format("%1$25s  %2$8.2f \n", 	r.getName(), r.getSum()); }

        	
        	
        if (listM2 != null) {
        	for(String s: listM2) {
        		System.out.println(s);
        	}
        }
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
