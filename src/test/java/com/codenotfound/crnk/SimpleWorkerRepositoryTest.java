package com.codenotfound.crnk;

import com.codenotfound.crnk.domain.model.SimpleWorker;
import com.codenotfound.crnk.domain.repository.SimpleWorkerRepository;
import com.codenotfound.crnk.domain.repository.SimpleWorkerRepositoryImpl;
import io.crnk.core.queryspec.FilterOperator;
import io.crnk.core.queryspec.FilterSpec;
import io.crnk.core.queryspec.QuerySpec;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class SimpleWorkerRepositoryTest extends TestCase  {
    private SimpleWorkerRepository repo;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        repo = new SimpleWorkerRepositoryImpl();
        SimpleWorker w1, w2, w3;
        w1 = new SimpleWorker("foo", "bar", "Germany", "Bochum", "44801", "foo@bar", "foobar company",
                10, 100.0, true, Arrays.asList("Reading", "Writing"));

        w2 = new SimpleWorker("leo", "messi", "Germany", "Essen", "43214", "leo@messi", "leomessi company",
                20, 200.0, false, Arrays.asList("Listening", "Speaking"));
        w3 = new SimpleWorker("cris", "tiano", "Italy", "Juventus", "12345", "cris@tiano", "cristiano company",
                30, 300.0, true, Arrays.asList("Reading", "Speaking"));
        repo.create(w1);
        repo.create(w2);
        repo.create(w3);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        ((SimpleWorkerRepositoryImpl)repo).closeFactory();
    }


    public void testCreate() {
        List<SimpleWorker> all = repo.findAll(new QuerySpec(SimpleWorker.class));
        assertThat(all.size(), is(3));
        assertThat(all.get(0).getFname(), is("foo"));
        assertThat(all.get(0).getLname(), is("bar"));
        assertThat(all.get(0).getCountry(), is("Germany"));
        assertThat(all.get(0).getCity(), is("Bochum"));
        assertThat(all.get(0).getPlz(), is("44801"));
        assertThat(all.get(0).getEmail(), is("foo@bar"));
        assertThat(all.get(0).getCompany(), is("foobar company"));
        assertThat(all.get(0).getAge(), is(10));
        assertThat(all.get(0).getWage(), is(100.0));
        assertThat(all.get(0).isActive(), is(true));
        assertThat(all.get(0).getActivities().size(), is(2));
    }

    public void testUpdate() {
        repo.save( new SimpleWorker(1L, "new foo", "new bar", "Germany", "AltBochum", "44801", "foo@bar", "foobar company",
                10, 100.0, true, Arrays.asList("Reading", "Writing")));
        List<SimpleWorker> all = repo.findAll(new QuerySpec(SimpleWorker.class));
        assertThat(all.size(), is(3));
        assertThat(all.get(0).getFname(), is("new foo"));
        assertThat(all.get(0).getLname(), is("new bar"));
        assertThat(all.get(0).getCountry(), is("Germany"));
        assertThat(all.get(0).getCity(), is("AltBochum"));
        assertThat(all.get(0).getPlz(), is("44801"));
        assertThat(all.get(0).getEmail(), is("foo@bar"));
        assertThat(all.get(0).getCompany(), is("foobar company"));
        assertThat(all.get(0).getAge(), is(10));
        assertThat(all.get(0).getWage(), is(100.0));
        assertThat(all.get(0).isActive(), is(true));
        assertThat(all.get(0).getActivities().size(), is(2));
    }

    public void testDelete() {
        repo.delete(1L);
        List<SimpleWorker> all = repo.findAll(new QuerySpec(SimpleWorker.class));
        assertThat(all.size(), is(2));
    }

    public void testFindX() {
        //by fname
        QuerySpec querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("fname"), FilterOperator.EQ, "foo"));
        List<SimpleWorker> some = repo.findAll(querySpec);
        assertThat(some.size(), is(1));

        //by lname
        querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("lname"), FilterOperator.EQ, "bar"));
        some = repo.findAll(querySpec);
        assertThat(some.size(), is(1));

        //by country
        querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("country"), FilterOperator.EQ, "Germany"));
        some = repo.findAll(querySpec);
        assertThat(some.size(), is(2));

        //by city
        querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("city"), FilterOperator.EQ, "Bochum"));
        some = repo.findAll(querySpec);
        assertThat(some.size(), is(1));

        //by plz
        querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("plz"), FilterOperator.EQ, "44801"));
        some = repo.findAll(querySpec);
        assertThat(some.size(), is(1));

        //by email
        querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("email"), FilterOperator.EQ, "foo@bar"));
        some = repo.findAll(querySpec);
        assertThat(some.size(), is(1));

        //by company
        querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("company"), FilterOperator.EQ, "foobar company"));
        some = repo.findAll(querySpec);
        assertThat(some.size(), is(1));

        //by age
        querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("age"), FilterOperator.EQ, 10));
        some = repo.findAll(querySpec);
        assertThat(some.size(), is(1));

        //by wage
        querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("wage"), FilterOperator.EQ, 100.0));
        some = repo.findAll(querySpec);
        assertThat(some.size(), is(1));

        //by active
        querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("active"), FilterOperator.EQ, true));
        some = repo.findAll(querySpec);
        assertThat(some.size(), is(2));

        //by minAge and maxAge
        querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("minAge"), FilterOperator.EQ, 10));
        querySpec.addFilter(new FilterSpec(Collections.singletonList("maxAge"), FilterOperator.EQ, 20));
        some = repo.findAll(querySpec);
        assertThat(some.size(), is(2));

        //by minWage and maxWage
        querySpec = new QuerySpec(SimpleWorker.class);
        querySpec.addFilter(new FilterSpec(Collections.singletonList("minWage"), FilterOperator.EQ, 100.0));
        querySpec.addFilter(new FilterSpec(Collections.singletonList("maxWage"), FilterOperator.EQ, 300.0));
        some = repo.findAll(querySpec);
        assertThat(some.size(), is(3));
    }
}
