package com.junkapa.server.db;

import javax.persistence.EntityManager;

public class DataBaseService {
	public static void insertEntity(){  
        EntityManager em = EntityManagerHelper.getEntity();  
        try{  
         /*    em.getTransaction().begin();  
       //      test t = new test("hello");
         	test2 t2 = new test2("한글");
         	em.persist(test);
         	test.getTest2().add(t2);
         	
         	t2.setTest(test);
         	em.persist(t2);
             em.getTransaction().commit();  */
        }catch(Exception e){  
             em.getTransaction().rollback();  
        }  
        finally{  
             em.close();  
        }  
   }  
   /*public static test findEntitybyId(int id){  
        EntityManager em = EntityManagerHelper.getEntity();  
        test anEntity = em.find(test.class, id);  
        em.close();  
        return anEntity;  
   }  */
}
