package com.codenotfound.crnk;

import javax.annotation.PostConstruct;

import com.codenotfound.crnk.domain.model.Student;
import com.codenotfound.crnk.domain.model.University;
import com.codenotfound.crnk.domain.repository.StudentRepository;
import com.codenotfound.crnk.domain.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.codenotfound.crnk.domain.model.Article;
import com.codenotfound.crnk.domain.model.Person;
import com.codenotfound.crnk.domain.repository.ArticleRepository;
import com.codenotfound.crnk.domain.repository.PersonRepository;

import java.util.Arrays;

@Configuration
public class SpringCrnkApplicationConfig {

  @Autowired
  ArticleRepository articleRepository;

  @Autowired
  PersonRepository personRepository;

  @Autowired
  private StudentRepository studentRepository;
  @Autowired
  private UniversityRepository universityRepository;

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

  }
}
