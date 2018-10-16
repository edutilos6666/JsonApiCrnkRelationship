package com.codenotfound.crnk;

import javax.annotation.PostConstruct;

import com.codenotfound.crnk.domain.model.*;
import com.codenotfound.crnk.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class SpringCrnkApplicationConfig {

  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private UniversityRepository universityRepository;

  @Autowired
  private HibernateStudentRepository hibernateStudentRepository;
  @Autowired
  private HibernateUniversityRepository hibernateUniversityRepository;

  @Autowired
  private HibernateWorkerRepository hibernateWorkerRepository;

  @PostConstruct
  public void init() {
    Article article1 = new Article(1L, "JSON API paints my bikeshed!");
    Article article2 = new Article(2L, "Rails is Omakase");

    Person person1 = new Person(1L, "John");

    article1.setAuthor(person1);
    article2.setAuthor(person1);

    person1.getArticles().add(article1);
    person1.getArticles().add(article2);

    articleRepository.save(article1);
    articleRepository.save(article2);

    personRepository.save(person1);

    Student s1 = new Student(1L, "foo", "bar", 10, 100.0, true);
    Student s2 = new Student(2L, "edu", "tilos", 20, 200.0, false);
    Student s3 = new Student(3L, "pako", "deko", 30, 300.0, true);
    // public University(Long id, String name, String country, String city, String plz, String rector)
    University u1 = new University(1L, "RUB", "Germany", "Bochum", "44801", "cristiano ronaldo");
    s1.setUniversity(u1);
    s2.setUniversity(u1);
    s3.setUniversity(u1);
    u1.getStudents().addAll(Arrays.asList(s1, s2, s3));
    universityRepository.save(u1);
    studentRepository.save(s1);
    studentRepository.save(s2);
    studentRepository.save(s3);

    HibernateStudent hs1, hs2, hs3;
    HibernateUniversity hu1, hu2;

    hs1 = new HibernateStudent("foo", "bar", 10, 100.0, true);
    hs2 = new HibernateStudent("leo", "messi",20, 200.0, false);
    hs3 = new HibernateStudent("cris", "tiano", 30, 300.0, true);
    hu1 = new HibernateUniversity("RUB", "Germany", "Bochum", "44801", "andrey");
    hu2 = new HibernateUniversity("Essen University", "Germany", "Essen", "34523", "shevchenko");
    hs1.setHibernateUniversity(hu1);
    hs2.setHibernateUniversity(hu1);
    hs3.setHibernateUniversity(hu2);
    hu1.setHibernateStudents(Stream.of(hs1, hs2).collect(Collectors.toList()));
    hu2.setHibernateStudents(Stream.of(hs3).collect(Collectors.toList()));
    hibernateUniversityRepository.save(hu1);
    hibernateUniversityRepository.save(hu2);


    HibernateWorker hw1, hw2, hw3, hw4;
    hw1 = new HibernateWorker("foo", "bar", "Germany", "Bochum", "44801", 10, 100.0, true);
    hw2 = new HibernateWorker("edu", "tilos", "Germany", "Essen", "44325", 20, 200.0, false);
    hw3 = new HibernateWorker("leo", "messi", "Spain", "Barcelona", "12345", 30, 300.0, true);
    hw4 = new HibernateWorker("cris", "tiano", "Italy", "Tourin", "54321", 40, 400.0, false);
    hibernateWorkerRepository.save(hw1);
    hibernateWorkerRepository.save(hw2);
    hibernateWorkerRepository.save(hw3);
    hibernateWorkerRepository.save(hw4);
  }
}
