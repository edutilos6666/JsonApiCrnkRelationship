package com.codenotfound.crnk;

import static org.assertj.core.api.Assertions.assertThat;

import com.codenotfound.crnk.domain.model.Student;
import com.codenotfound.crnk.domain.model.University;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.codenotfound.crnk.client.BlogClient;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringCrnkApplicationTest {
  @Autowired
  BlogClient blogClient;

  @Test
  public void testFindOneArticle() {
    assertThat(blogClient.findOneArticle(1L).getTitle()).isEqualTo("JSON API paints my bikeshed!");
  }

  @Test
  public void findOnePerson() {
    assertThat(blogClient.findOnePerson(1L).getName()).isEqualTo("John");
  }

  @Test
  public void testFindOneUniversity() {
    University u = blogClient.findOneUniversity(1L);
    assertThat(u.getId()).isEqualTo(1L);
    assertThat(u.getName()).isEqualToIgnoringCase("RUB");
    assertThat(u.getCity()).isEqualToIgnoringCase("bochum");
    assertThat(u.getCountry()).isEqualToIgnoringCase("germany");
    assertThat(u.getPlz()).isEqualToIgnoringCase("44801");
    assertThat(u.getRector()).isEqualToIgnoringCase("cristiano ronaldo");
  }

  @Test
  public void testFindOneStudent() {
    Student student = blogClient.findOneStudent(1L);
    assertThat(student.getId()).isEqualTo(1L);
    assertThat(student.getFname()).isEqualToIgnoringCase("foo");
    assertThat(student.getLname()).isEqualToIgnoringCase("bar");
    assertThat(student.getAge()).isEqualTo(10);
    assertThat(student.getWage()).isEqualTo(100.0);
    assertThat(student.isActive()).isEqualTo(true);
  }

  @Test
  public void testFindAllStudents() {
    List<Student> all = blogClient.findAllStudents();
    assertThat(all.size()).isEqualTo(3);
    assertThat(all.get(1).getFname()).isEqualToIgnoringCase("edu");
  }
}
