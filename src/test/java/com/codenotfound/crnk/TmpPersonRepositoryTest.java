package com.codenotfound.crnk;

import com.codenotfound.crnk.client.BlogClient;
import com.codenotfound.crnk.domain.model.TmpPerson;
import com.codenotfound.crnk.domain.model.TmpWorker;
import junit.framework.TestCase;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class TmpPersonRepositoryTest extends TestCase {
    private BlogClient client;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        client = new BlogClient();
        client.init();
        client.createTmpPerson(new TmpPerson("foo"));
        client.createTmpPerson(new TmpPerson("bar"));
        client.createTmpPerson(new TmpPerson("bim"));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        List<TmpPerson> all = client.findAllTmpPerson();
        all.forEach(person-> {
            client.deleteTmpPerson(person.getId());
        });
    }



    public void testCreate() {
        List<TmpPerson> all = client.findAllTmpPerson();
        all.forEach(System.out::println);
        assertThat(all.size(), is(3));
    }

    public void testSave() {
        long id = client.findAllTmpPerson().get(0).getId();
        client.saveTmpPerson(new TmpPerson(id, "new-foo"));
        TmpPerson one = client.findOneTmpPerson(id);
        assertThat(one.getName(), is("new-foo"));
    }

    public void testDelete() {
        long id = client.findAllTmpPerson().get(0).getId();
        client.deleteTmpPerson(id);
        List<TmpPerson> all = client.findAllTmpPerson();
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getName(), is("bar"));
    }
}
