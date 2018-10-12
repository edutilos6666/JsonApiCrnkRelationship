package com.codenotfound.crnk;

import com.codenotfound.crnk.domain.model.HibernateStudent;
import com.codenotfound.crnk.domain.model.HibernateUniversity;
import com.codenotfound.crnk.domain.repository.HibernateStudentRepository;
import com.codenotfound.crnk.domain.repository.HibernateStudentRepositoryImpl;
import com.codenotfound.crnk.domain.repository.HibernateUniversityRepository;
import com.codenotfound.crnk.domain.repository.HibernateUniversityRepositoryImpl;
import io.crnk.core.queryspec.QuerySpec;
import junit.framework.TestCase;
import static org.junit.Assert.*;

import org.hibernate.Hibernate;
import org.junit.Test;
import static org.hamcrest.core.Is.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class HibernateStudentRepositoryTest extends TestCase {
    private HibernateStudentRepository hibernateStudentRepository;
    private HibernateUniversityRepository hibernateUniversityRepository;

    public HibernateStudentRepositoryTest() {

    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.hibernateStudentRepository = new HibernateStudentRepositoryImpl();
        this.hibernateUniversityRepository = new HibernateUniversityRepositoryImpl();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        ((HibernateStudentRepositoryImpl)this.hibernateStudentRepository).closeFactory();
        ((HibernateUniversityRepositoryImpl)this.hibernateUniversityRepository).closeFactory();
    }

    private void printList(List<? extends Object> list) {
        for(Object el: list)
            System.out.println(el.toString());
    }

//    @Test
    public void testSave() {
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
//        hibernateStudentRepository.save(hs1);
//        hibernateStudentRepository.save(hs2);
//        hibernateStudentRepository.save(hs3);
        hibernateUniversityRepository.save(hu1);
        hibernateUniversityRepository.save(hu2);
        List<HibernateStudent> all = hibernateStudentRepository.findAll(new QuerySpec(HibernateStudent.class));
        System.out.println(all.stream().map(HibernateStudent::toString).collect(Collectors.joining("\n")));
        assertThat(all.size(), is(3));
        assertThat(all.get(0).getFname(), is("foo"));
        assertThat(all.get(0).getLname(), is("bar"));
        assertThat(all.get(0).getHibernateUniversity().getName(), is("RUB"));
        assertThat(all.get(0).getHibernateUniversity().getCountry(), is("Germany"));
        assertThat(all.get(0).getHibernateUniversity().getCity(), is("Bochum"));
    }


    public void testDelete() {
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
//        System.out.println(hibernateUniversityRepository.findOne(1L, new QuerySpec(HibernateUniversity.class)));
//        Hibernate.initialize(hibernateUniversityRepository.findOne(1L, new QuerySpec(HibernateUniversity.class)).getHibernateStudents());
        hibernateStudentRepository.delete(3L);
        List<HibernateStudent> all = hibernateStudentRepository.findAll(new QuerySpec(HibernateStudent.class));
        System.out.println("<<after delete>>");
        System.out.println(all.stream().map(HibernateStudent::toString).collect(Collectors.joining("\n")));
        assertThat(all.size(), is(2));
    }
}
