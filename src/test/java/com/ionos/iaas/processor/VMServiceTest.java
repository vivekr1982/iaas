package com.ionos.iaas.processor;

import com.ionos.iaas.model.Action;
import com.ionos.iaas.model.ItemType;
import com.ionos.iaas.model.Request;
import com.ionos.iaas.service.VMService;
import com.ionos.iaas.data.DataSet;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;


public class VMServiceTest {

    private Request mockRequest;
    private VMService vmServiceUnderTest;

    @Before
    public void setUp() {
        String path = VMServiceTest.class.getClassLoader().getResource("logging.properties").getFile();
        System.setProperty("java.util.logging.config.file", path);
        initMocks(this);
        mockRequest = new Request();
        mockRequest.setDatacenterId(1L);
        mockRequest.setItemId(1L);
        mockRequest.setAction(Action.CREATE);
        mockRequest.setItemType(ItemType.DATACENTER);
        vmServiceUnderTest = new VMService(mockRequest, new MockedBackendProcessor());
    }

    @Test
    public void testRun() {
        vmServiceUnderTest.run();
        // Verify the results
        assertEquals(DataSet.datacenterMap.get(1L).getLock().getHoldCount(), 0);
    }

    @Test
    public void doTest() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Request request1 = new Request();

        request1.setItemId(1L);
        request1.setAction(Action.CREATE);
        request1.setItemType(ItemType.DATACENTER);

        Request request2 = new Request();
        request2.setItemId(2L);
        request2.setAction(Action.UPDATE);
        request2.setItemType(ItemType.SERVER);

        Request request3 = new Request();
        request3.setDatacenterId(null);
        request3.setAttachToServerId(null);
        request3.setItemId(5L);
        request3.setAction(Action.MAKESNAPSHOT);
        request3.setItemType(ItemType.STORAGE);

        Runnable w1 = new VMService(request1, new MockedBackendProcessor());
        Runnable w2 = new VMService(request2, new MockedBackendProcessor());
        Runnable w3 = new VMService(request3, new MockedBackendProcessor());
        Runnable w4 = new VMService(request1, new MockedBackendProcessor());

        pool.submit(w1);
        pool.submit(w2);
        pool.submit(w3);
        pool.submit(w4);

        pool.shutdown();
    }
}
