import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Engine implements Runnable {
    private EntityManager manager;
    private BufferedReader reader;

    public Engine(EntityManager manager) {
        this.manager = manager;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        System.out.print("Write ex:");
        try {
            int ex = Integer.parseInt(reader.readLine());

            switch (ex) {
                case 2:
                    callEx2();
                    break;
                case 3:
                    callEx3();
                    break;
                case 4:
                    callEx4();
                    break;
                case 5:
                    callEx5();
                    break;
                case 6:
                    callEx6();
                    break;
                case 7:
                    callEx7();
                    break;
                case 8:
                    callEx8();
                    break;
                case 9:
                    callEx9();
                    break;
                case 10:
                    callEx10();
                    break;
                case 11:
                    callEx11();
                    break;
                case 12:
                    callEx12();
                    break;
                case 13:
                    callEx13();
                    break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void callEx13() throws IOException {
        System.out.print("Write a town:");
        String townName = reader.readLine();

        Town town = manager.createQuery("SELECT t FROM Town as t where t.name=:town", Town.class).setParameter("town", townName).getSingleResult();
        manager.getTransaction().begin();



        List<Address> addresses = manager.createQuery("SELECT a from Address as a where a.town.id=:town ", Address.class).setParameter("town", town.getId()).getResultList();

        for (Address a : addresses
        ) {
//             a.getEmployees().stream().forEach(s->{
//                 s.setAddress(null);
//             });
            manager.remove(a);

        }
        manager.remove(town);

        System.out.println(addresses.size() + " address in " + townName + " deleted");
        manager.getTransaction().commit();
    }

    //    private void callEx13() throws IOException {
//        System.out.print("Write a town:");
//        String townName=reader.readLine();
//        manager.getTransaction().begin();
//        manager.createQuery("DELETE FROM Employee as e where e.address.town.name=:town").setParameter("town",townName);
//        int deleted_address=manager.createQuery("DELETE a from Address as a where a.town.name=:town ").setParameter("town",townName).executeUpdate();
//        manager.createQuery("DELETE  from Town as t where t.name=:town").setParameter("town",townName);
//
//        System.out.println(deleted_address+" address in "+townName+" deleted");
//     manager.getTransaction().commit();
//    };
//    private void callEx12() {
//        List<Object[]> employees=manager.createQuery("SELECT e.department.name,MAX(e.salary)  FROM Employee as e GROUP BY e.department HAVING MAX(e.salary)NOt BETWEEN 30000 AND 70000")
//                .getResultList();
//        for (Object[] e:employees
//        ) {
//            System.out.println(e[0]+" "+e[1]);
//        }
//
//
//    }
    private void callEx12() {
        List<Object[]> employees = manager.createQuery("SELECT concat(e.department.name,:space ,MAX(e.salary)) as max_salary FROM Employee as e GROUP BY e.department HAVING max_salary BETWEEN 30000 AND 70000")
                .setParameter("space", " ").getResultList();
        for (Object[] e : employees
        ) {
            System.out.println(e[0]);
        }


    }

    private void callEx10() {
        List<String> str = new ArrayList<>();
        str.add("Engineering");
        str.add("Tool Design");
        str.add("Marketing");
        str.add("Information Services");

        manager.getTransaction().begin();
        List<Employee> employees = manager.createQuery("SELECT e FROM Employee as e WHERE e.department.name IN (:jobs)", Employee.class).setParameter("jobs", str).getResultList();
        for (Employee e : employees
        ) {
            e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
            System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary());
        }
        manager.getTransaction().commit();

    }

    private void callEx11() {
        List<Employee> employees = manager.createQuery("SELECT e FROM Employee as e WHERE e.firstName LIKE :starts_with", Employee.class).setParameter("starts_with", "Sa%").getResultList();
        for (Employee e : employees
        ) {
            ;
//           e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
            System.out.printf("%s %s - %s - ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary());
        }

    }

    private void callEx9() {
        List<Project> projects = manager.createQuery("SELECT p FROM Project as p where p.endDate is null ORDER BY p.startDate DESC", Project.class).setMaxResults(10).getResultList();
        projects.stream().sorted(Comparator.comparing(Project::getName)).forEach(s -> System.out.printf("Project name: %s%n" +
                "      Project Description: %s%n" +
                "      Project Start Date:" + s.getStartDate().format(DateTimeFormatter.ofPattern("y-m-d h:m:s")) + "%n" +
                "      Project End Date: %s%n", s.getName(), s.getDescription(), String.valueOf(s.getEndDate())));


    }

    private void callEx8() throws IOException {
        System.out.print("Write id:");
        int id = Integer.parseInt(reader.readLine());
        Employee employee = manager.createQuery("SELECT e FROM Employee AS e WHERE e.id=:id", Employee.class).setParameter("id", id).getSingleResult();
        System.out.println(employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getJobTitle());
        employee.getProjects().stream().sorted(Comparator.comparing(Project::getName)).forEach(s -> System.out.println(s.getName()));
    }

    private void callEx7() {
        List<Address> info = manager.createQuery("SELECT a from Address AS a  Order by a.employees.size DESC ", Address.class).setMaxResults(10).getResultList();
        for (int i = 0; i < info.size(); i++) {
            System.out.println(info.get(i).getText() + ", " + info.get(i).getTown().getName() + ", " + info.get(i).getEmployees().size());
        }
    }

    private void callEx6() throws IOException {
        System.out.print("Write last name:");
        String lastName = reader.readLine();
        Address address = new Address();
        address.setText("Vitoshka 15");
        int contains = manager.createQuery("SELECT count(a) from Address AS a WHERE a.text= :text ", Long.class).setParameter("text", "Vitoshka 15").getResultList().size();
        manager.getTransaction().begin();
        if (contains == 0) {
            manager.persist(address);
        } else {
            address = manager.createQuery("SELECT a from Address AS a WHERE a.text= :text ", Address.class).setParameter("text", "Vitoshka 15").getSingleResult();
        }
        List<Employee> emp = manager.createQuery("SELECT e from Employee AS e WHERE e.lastName= :last_name ", Employee.class).setParameter("last_name", lastName).getResultList();
        for (Employee e : emp
        ) {
            e.setAddress(address);
        }
        manager.getTransaction().commit();
        manager.close();
    }


    private void callEx5() {
        List<String> list = manager.createQuery("SELECT e from Employee as e " +
                "where e.department.name IN ('Research and Development') " +
                "order by e.salary ASC,e.id ASC", Employee.class).getResultStream().map(e -> String.format("%s %s from Research and Development - $%.2f", e.getFirstName(), e.getLastName(), e.getSalary())).collect(Collectors.toList());

        list.forEach(e -> System.out.println(e));

    }

    private void callEx4() {
        List<String> salary_over = manager.createQuery("SELECT e.firstName from Employee as e " +
                "where e.salary>:salary_over").setParameter("salary_over", BigDecimal.valueOf(50000)).getResultList();
        salary_over.forEach(System.out::println);

    }


    private void callEx3() throws IOException {
        String[] names = reader.readLine().split(" ");
        Query query = (manager.createQuery("SELECT e FROM Employee e " +
                "WHERE e.firstName=:first_name AND e.lastName=:last_name").setParameter("first_name", names[0]).setParameter("last_name", names[1]));
        List resultList = query.getResultList();

        if (resultList.size() == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

    }


    private void callEx2() {
        Query query = manager.createQuery("UPDATE Town as t " +
                "SET t.name=UPPER(t.name) " +
                "WHERE length(t.name) <=5");
        manager.getTransaction().begin();
        query.executeUpdate();
        manager.getTransaction().commit();
        manager.close();
    }
}
