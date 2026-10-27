package org.example.Assignment1.springcore;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Course course() {
        return new Course("C01", "Java Full Stack", 3);
    }

    @Bean
    public Student student(Course course) {
        return new Student(246, "Shubham Kumar", course);
    }

    @Bean
    public StudentService studentService(Student student) {
        return new StudentService(student);
    }
}
