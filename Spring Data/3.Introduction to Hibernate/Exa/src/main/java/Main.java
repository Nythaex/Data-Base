import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
         BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        EntityManagerFactory emp=Persistence.createEntityManagerFactory("provider");
        EntityManager entityManager = emp.createEntityManager();
        Engine engine=new Engine(entityManager);
        engine.run();




    }
}
