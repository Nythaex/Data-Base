import entity.Sales.Customer;
import entity.Sales.Product;
import entity.Sales.Sale;
import entity.Sales.StoreLocation;
import entity.UniversitySystem.Course;
import entity.UniversitySystem.Person;
import entity.UniversitySystem.Student;
import entity.UniversitySystem.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory test = Persistence.createEntityManagerFactory("codeFirst");
        EntityManager entityManager = test.createEntityManager();

        entityManager.getTransaction().begin();

















        //ManyToMany example failed
//        Course c=entityManager.createQuery("SELECT c FROM Course c WHERE c.id=3",Course.class).getSingleResult();
//        Student s=entityManager.createQuery("SELECT s FROM Student s WHERE s.id=5",Student.class).getSingleResult();
////        c.getStudents().add(s);
//        entityManager.persist(c);
        ////////////////
//        Student s=new Student();
//        Teacher t=new Teacher();
//        Course c=new Course();
//
//
//        s.getCourses().add(c);
//        entityManager.persist(s);
//        entityManager.persist(t);
            entityManager.getTransaction().commit();




    }
}
