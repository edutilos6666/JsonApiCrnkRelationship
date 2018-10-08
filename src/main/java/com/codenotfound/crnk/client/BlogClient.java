package com.codenotfound.crnk.client;

import javax.annotation.PostConstruct;

import com.codenotfound.crnk.domain.model.Student;
import com.codenotfound.crnk.domain.model.University;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.codenotfound.crnk.domain.model.Article;
import com.codenotfound.crnk.domain.model.Person;

import io.crnk.client.CrnkClient;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryV2;

import java.io.Serializable;
import java.util.List;

@Component
public class BlogClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(BlogClient.class);

  private CrnkClient crnkClient = new CrnkClient("http://localhost:9090/codenotfound/api");

  private ResourceRepositoryV2<Article, Long> articleResourceRepositoryV2;
  private ResourceRepositoryV2<Person, Long> personResourceRepositoryV2;
  private ResourceRepositoryV2<University, Serializable> universityLongResourceRepositoryV2;
  private ResourceRepositoryV2<Student, Serializable> studentLongResourceRepositoryV2;

  @PostConstruct
  public void init() {
    articleResourceRepositoryV2 = crnkClient.getRepositoryForType(Article.class);
    personResourceRepositoryV2 = crnkClient.getRepositoryForType(Person.class);
    universityLongResourceRepositoryV2 = crnkClient.getRepositoryForType(University.class);
    studentLongResourceRepositoryV2 = crnkClient.getRepositoryForType(Student.class);
  }

  public Article findOneArticle(long id) {
    Article result = articleResourceRepositoryV2.findOne(id, new QuerySpec(Article.class));

    LOGGER.info("found {}", result.toString());
    return result;
  }

  public Person findOnePerson(long id) {
    Person result = personResourceRepositoryV2.findOne(id, new QuerySpec(Person.class));

    LOGGER.info("found {}", result.toString());
    return result;
  }

  public University findOneUniversity(long id) {
    University uni = universityLongResourceRepositoryV2.findOne(id, new QuerySpec(University.class));
    return uni;
  }

  public Student findOneStudent(long id) {
    return studentLongResourceRepositoryV2.findOne(id, new QuerySpec(Student.class));
  }

  public List<Student> findAllStudents() {
    return studentLongResourceRepositoryV2.findAll(new QuerySpec(Student.class));
  }
}
