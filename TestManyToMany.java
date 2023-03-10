import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestManyToMany {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Teacher teacher1 = Teacher.builder()
                    .firstName("Jane")
                    .lastName("Air")
                    .subject("History")
                    .build();
            Teacher teacher2 = Teacher.builder()
                    .firstName("Max")
                    .lastName("Haircraft")
                    .subject("Math")
                    .build();
            Student student1 = Student.builder()
                    .firstName("Nick")
                    .lastName("Jameson")
                    .build();
            Student student2 = Student.builder()
                    .firstName("Elise")
                    .lastName("Jhonson")
                    .build();

            List<Teacher> teachers = new ArrayList<>(Arrays.asList(teacher1, teacher2));
            List<Student> students = new ArrayList<>(Arrays.asList(student1, student2));

            teacher1.setStudents(students);
            teacher2.setStudents(students);
            student1.setTeachers(teachers);
            student2.setTeachers(teachers);

            session.save(student1);
            session.save(student2);
            session.save(teacher1);
            session.save(teacher2);

            transaction.commit();
        }
    }
}
