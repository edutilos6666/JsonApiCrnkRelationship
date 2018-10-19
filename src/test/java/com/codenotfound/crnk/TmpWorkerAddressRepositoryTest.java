package com.codenotfound.crnk;

import com.codenotfound.crnk.client.BlogClient;
import com.codenotfound.crnk.domain.model.TmpAddress;
import com.codenotfound.crnk.domain.model.TmpWorker;
import com.codenotfound.crnk.domain.repository.TmpAddressRepository;
import com.codenotfound.crnk.domain.repository.TmpAddressRepositoryImpl;
import com.codenotfound.crnk.domain.repository.TmpWorkerRepository;
import com.codenotfound.crnk.domain.repository.TmpWorkerRepositoryImpl;
import junit.framework.TestCase;

import java.util.List;

public class TmpWorkerAddressRepositoryTest extends TestCase {
    private BlogClient client;
    private TmpWorkerRepository tmpWorkerRepository;
    private TmpAddressRepository tmpAddressRepository;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        client = new BlogClient();
        client.init();
        tmpWorkerRepository = new TmpWorkerRepositoryImpl();
        tmpAddressRepository = new TmpAddressRepositoryImpl();
        TmpWorker w1, w2, w3;
        TmpAddress a1, a2, a3;
        w1 = new TmpWorker("foo");
        w2 = new TmpWorker("bar");
        w3 = new TmpWorker("bim");
        a1 = new TmpAddress("Essen");
        a2 = new TmpAddress("Bochum");
        a3 = new TmpAddress("Hamburg");
        w1.setAddress(a1);
        a1.setWorker(w1);
        w2.setAddress(a2);
        a2.setWorker(w2);
        w3.setAddress(a3);
        a3.setWorker(w3);

        client.createTmpWorker(w1);
        client.createTmpWorker(w2);
        client.createTmpWorker(w3);
        client.createTmpAddress(a1);
        client.createTmpAddress(a2);
        client.createTmpAddress(a3);
//        tmpWorkerRepository.create(w1);
//        tmpWorkerRepository.create(w2);
//        tmpWorkerRepository.create(w3);
//        tmpAddressRepository.create(a1);
//        tmpAddressRepository.create(a2);
//        tmpAddressRepository.create(a3);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }


    public void testCreate() {
        List<TmpWorker> workers = client.findAllTmpWorkers();
//        List<TmpWorker> workers = tmpWorkerRepository.findAll(new QuerySpec(TmpWorker.class));
        workers.forEach(w-> {
            System.out.println(w.toString());
            System.out.println(w.getAddress());
            System.out.println();
        });
    }
}
