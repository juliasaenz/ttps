
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unlp");
        EntityManager em = emf.createEntityManager();
        
        em.close();
        emf.close();
        
        System.out.println("Entidades subidas a la base de datos");
    }
}
