package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
public class MainClass {
 
    private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "LoginProject";  
 
    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
 
    // This Method Is Used To Retrieve The 'EntityManager' Object
    public static EntityManager getEntityManager() {
        return emFactoryObj.createEntityManager();
    }
 
    //public static void main(String[] args)
    public MainClass()
    {
 
        EntityManager entityMgr = getEntityManager();
        entityMgr.getTransaction().begin();
 
        User userObj = new User();
        userObj.setUsername("u1");
        userObj.setPassword("pass");
     
        entityMgr.persist(userObj);
 
        entityMgr.getTransaction().commit();
 
        entityMgr.clear();
        System.out.println("Record Successfully Inserted In The Database");
    }
}