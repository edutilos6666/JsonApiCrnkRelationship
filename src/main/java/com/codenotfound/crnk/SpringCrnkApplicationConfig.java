package com.codenotfound.crnk;

import com.codenotfound.crnk.domain.model.*;
import com.codenotfound.crnk.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.*;
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

  @Autowired
  private ChartistChartDataBarChartRepository chartistChartDataBarChartRepository;

  @Autowired
  private ChartistChartDataPieChartRepository chartistChartDataPieChartRepository;


  @Autowired
  private ChartistChartDataLineChartRepository chartistChartDataLineChartRepository;


  @Autowired
  private GenericChartDataRepository genericChartDataRepository;

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


    ChartistChartDataBarChart data1, data2, data3;
    data1 = new ChartistChartDataBarChart("Monday", Arrays.asList(10.0, 11.0, 12.0));
    data2 = new ChartistChartDataBarChart("Tuesday", Stream.of(13.0, 14.0, 15.0).collect(Collectors.toList()));
    data3 = new ChartistChartDataBarChart("Wednesday", Stream.of(16.0, 17.0, 18.0).collect(Collectors.toList()));
    chartistChartDataBarChartRepository.save(data1);
    chartistChartDataBarChartRepository.save(data2);
    chartistChartDataBarChartRepository.save(data3);


    ChartistChartDataPieChart pie1, pie2, pie3, pie4, pie5, pie6, pie7;
    pie1 = new ChartistChartDataPieChart("Google Chrome", generateRandomDouble());
    pie2 = new ChartistChartDataPieChart("Mozilla Firefox", generateRandomDouble());
    pie3 = new ChartistChartDataPieChart("Microsoft IE", generateRandomDouble());
    pie4 = new ChartistChartDataPieChart("Microsoft Edge", generateRandomDouble());
    pie5 = new ChartistChartDataPieChart("Apple Safari", generateRandomDouble());
    pie6 = new ChartistChartDataPieChart("Opera", generateRandomDouble());
    pie7 = new ChartistChartDataPieChart("Blisk", generateRandomDouble());
    chartistChartDataPieChartRepository.create(pie1);
    chartistChartDataPieChartRepository.create(pie2);
    chartistChartDataPieChartRepository.create(pie3);
    chartistChartDataPieChartRepository.create(pie4);
    chartistChartDataPieChartRepository.create(pie5);
    chartistChartDataPieChartRepository.create(pie6);
    chartistChartDataPieChartRepository.create(pie7);


    ChartistChartDataLineChart l1, l2, l3, l4, l5, l6,l7, l8;
    l1 = new ChartistChartDataLineChart("One", Collections.singletonList(5.0));
    l2 = new ChartistChartDataLineChart("Two", Collections.singletonList(9.0));
    l3 = new ChartistChartDataLineChart("Three", Collections.singletonList(7.0));
    l4 = new ChartistChartDataLineChart("Four", Collections.singletonList(8.0));
    l5 = new ChartistChartDataLineChart("Five", Collections.singletonList(5.0));
    l6 = new ChartistChartDataLineChart("Six", Collections.singletonList(3.0));
    l7 = new ChartistChartDataLineChart("Seven", Collections.singletonList(5.0));
    l8 = new ChartistChartDataLineChart("Eight", Collections.singletonList(4.0));
    chartistChartDataLineChartRepository.create(l1);
    chartistChartDataLineChartRepository.create(l2);
    chartistChartDataLineChartRepository.create(l3);
    chartistChartDataLineChartRepository.create(l4);
    chartistChartDataLineChartRepository.create(l5);
    chartistChartDataLineChartRepository.create(l6);
    chartistChartDataLineChartRepository.create(l7);
    chartistChartDataLineChartRepository.create(l8);



    GenericChartData gcd1 = new GenericChartData("bar", Arrays.asList("W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10"),
            Arrays.asList(new GenericSerie(Arrays.asList(1.0, 2.0, 4.0, 8.0, 6.0, -2.0, -1.0, -4.0, -6.0, -2.0))));

//    Map<String,Object> gcd1Options = new HashMap<String,Object>() {
//      {
//        put("high", 10);
//        put("low", 0);
//      }
//    };
    List<GenericOption> gcd1Options = Arrays.asList(new GenericOption("high","10"),
            new GenericOption("low", "0"));
    gcd1.setOptions(gcd1Options);
    gcd1.setResponsiveOptions(new ArrayList<>());

    genericChartDataRepository.create(gcd1);


  }

  private double generateRandomDouble() {
      return new Random().nextInt(100);
  }

}
