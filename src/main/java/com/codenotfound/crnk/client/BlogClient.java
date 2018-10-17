package com.codenotfound.crnk.client;

import javax.annotation.PostConstruct;

import com.codenotfound.crnk.domain.model.*;
import io.crnk.core.queryspec.FilterOperator;
import io.crnk.core.queryspec.FilterSpec;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryV2;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Component
public class BlogClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(BlogClient.class);

  private APIClient crnkClient = new APIClient("http://localhost:9090/api");

  private ResourceRepositoryV2<Article, Long> articleResourceRepositoryV2;
  private ResourceRepositoryV2<Person, Long> personResourceRepositoryV2;
  private ResourceRepositoryV2<University, Serializable> universityLongResourceRepositoryV2;
  private ResourceRepositoryV2<Student, Serializable> studentLongResourceRepositoryV2;
  private ResourceRepositoryV2<HibernateUniversity, Serializable> hibernateUniversitySerializableResourceRepositoryV2;
  private ResourceRepositoryV2<HibernateStudent, Serializable> hibernateStudentSerializableResourceRepositoryV2;
  private ResourceRepositoryV2<ChartistChartDataBarChart, Serializable> chartistChartDataBarSerializableResourceRepositoryV2;
  private ResourceRepositoryV2<TmpWorker, Serializable> tmpWorkerSerializableResourceRepositoryV2;
  private ResourceRepositoryV2<TmpAddress , Serializable> tmpAddressSerializableResourceRepositoryV2;
  private ResourceRepositoryV2<TmpPerson, Serializable> tmpPersonSerializableResourceRepositoryV2;

  @PostConstruct
  public void init() {
    articleResourceRepositoryV2 = crnkClient.getRepositoryForType(Article.class);
    personResourceRepositoryV2 = crnkClient.getRepositoryForType(Person.class);
    universityLongResourceRepositoryV2 = crnkClient.getRepositoryForType(University.class);
    studentLongResourceRepositoryV2 = crnkClient.getRepositoryForType(Student.class);
    hibernateUniversitySerializableResourceRepositoryV2 = crnkClient.getRepositoryForType(HibernateUniversity.class);
    hibernateStudentSerializableResourceRepositoryV2 = crnkClient.getRepositoryForType(HibernateStudent.class);
    chartistChartDataBarSerializableResourceRepositoryV2 = crnkClient.getRepositoryForType(ChartistChartDataBarChart.class);
    tmpWorkerSerializableResourceRepositoryV2 = crnkClient.getRepositoryForType(TmpWorker.class);
    tmpAddressSerializableResourceRepositoryV2 = crnkClient.getRepositoryForType(TmpAddress.class);
    tmpPersonSerializableResourceRepositoryV2 = crnkClient.getRepositoryForType(TmpPerson.class);
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

  public List<HibernateUniversity> findAllHibernateUniversity() {
    return hibernateUniversitySerializableResourceRepositoryV2.findAll(new QuerySpec(HibernateUniversity.class));
  }
  public HibernateUniversity findOneHibernateUniversity(long id) {
    return hibernateUniversitySerializableResourceRepositoryV2.findOne(id, new QuerySpec(HibernateUniversity.class));
  }
  public void saveHibernateUniversity(HibernateUniversity hibernateUniversity) {
    hibernateUniversitySerializableResourceRepositoryV2.save(hibernateUniversity);
  }
  public void deleteHibernateUniversity(long id) {
    hibernateUniversitySerializableResourceRepositoryV2.delete(id);
  }

  public List<ChartistChartDataBarChart> findAllChartistChartDataBar() {
    List<ChartistChartDataBarChart> all = chartistChartDataBarSerializableResourceRepositoryV2.findAll(new QuerySpec(ChartistChartDataBarChart.class));
    all.forEach(chartData -> Hibernate.initialize(chartData.getContent()));
    return all;
  }
  public ChartistChartDataBarChart findOneChartistChartDataBar(long id) {
    return chartistChartDataBarSerializableResourceRepositoryV2.findOne(id, new QuerySpec(ChartistChartDataBarChart.class));
  }
  public void saveChartistChartDataBar(ChartistChartDataBarChart chartistChartDataBarChart) {
    chartistChartDataBarSerializableResourceRepositoryV2.save(chartistChartDataBarChart);
  }
  public void deleteChartistChartDataBar(long id) {
    chartistChartDataBarSerializableResourceRepositoryV2.delete(id);
  }

  public void createTmpWorker(TmpWorker worker) {
    tmpWorkerSerializableResourceRepositoryV2.create(worker);
  }

  public List<TmpWorker> findAllTmpWorkers() {
    return tmpWorkerSerializableResourceRepositoryV2.findAll(new QuerySpec(TmpWorker.class));
  }

  public void createTmpAddress(TmpAddress address) {
    tmpAddressSerializableResourceRepositoryV2.create(address);
  }
  public List<TmpAddress> findAllTmpAddresses() {
    return tmpAddressSerializableResourceRepositoryV2.findAll(new QuerySpec(TmpAddress.class));
  }

  public void createTmpPerson(TmpPerson person) {
    tmpPersonSerializableResourceRepositoryV2.create(person);
  }
  public void saveTmpPerson(TmpPerson person) {
    tmpPersonSerializableResourceRepositoryV2.save(person);
  }
  public void deleteTmpPerson(long id) {
    tmpPersonSerializableResourceRepositoryV2.delete(id);
  }
  public List<TmpPerson> findAllTmpPerson() {
    return tmpPersonSerializableResourceRepositoryV2.findAll(new QuerySpec(TmpPerson.class));
  }
  public List<TmpPerson> findTmpPersonByName(String name) {
    FilterSpec filterSpec = new FilterSpec(Collections.singletonList("name"), FilterOperator.EQ, name);
    QuerySpec querySpec = new QuerySpec(TmpPerson.class);
    querySpec.addFilter(filterSpec);
    return tmpPersonSerializableResourceRepositoryV2.findAll(querySpec);
  }
  public TmpPerson findOneTmpPerson(long id) {
    return tmpPersonSerializableResourceRepositoryV2.findOne(id, new QuerySpec(TmpPerson.class));
  }
}
