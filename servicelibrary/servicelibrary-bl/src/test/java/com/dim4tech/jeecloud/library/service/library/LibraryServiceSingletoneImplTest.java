package com.dim4tech.jeecloud.library.service.library;

import com.dim4tech.jeecloud.library.domain.ServiceDescription;
import com.dim4tech.jeecloud.library.service.LibraryService;
import com.dim4tech.jeecloud.library.service.LibraryServiceSingletoneImpl;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.Set;

import static org.junit.Assert.*;

public class LibraryServiceSingletoneImplTest {

    private LibraryService libraryService;

    @Before
    public void setUp() throws Exception {
        libraryService = new LibraryServiceSingletoneImpl();

        ServiceDescription serviceDescriptionTest50Load;
        ServiceDescription serviceDescriptionTest80Load;
        ServiceDescription serviceDescriptionDummy20Load;

        serviceDescriptionTest50Load = new ServiceDescription();
        serviceDescriptionTest50Load.setName("Test");
        serviceDescriptionTest50Load.setUrl(new URL("http://example.com/rest/test50"));
        serviceDescriptionTest50Load.setLoad(50);

        serviceDescriptionTest80Load = new ServiceDescription();
        serviceDescriptionTest80Load.setName("Test");
        serviceDescriptionTest80Load.setUrl(new URL("http://example.com/rest/test80"));
        serviceDescriptionTest80Load.setLoad(80);

        serviceDescriptionDummy20Load = new ServiceDescription();
        serviceDescriptionDummy20Load.setName("Dummy");
        serviceDescriptionDummy20Load.setUrl(new URL("http://example.com/rest/dummy20"));
        serviceDescriptionDummy20Load.setLoad(20);

        libraryService.add(serviceDescriptionTest50Load);
        libraryService.add(serviceDescriptionTest80Load);
        libraryService.add(serviceDescriptionDummy20Load);
    }

    @Test
    public void testDelete() throws Exception {
        libraryService.delete("Test", new URL("http://example.com/rest/test50"));
        assertTrue(libraryService.getAvailableServices().contains("Test"));
        assertTrue(libraryService.getAvailableServices().contains("Dummy"));

        ServiceDescription serviceDescriptionTest80Load = new ServiceDescription();
        serviceDescriptionTest80Load.setName("Test");
        serviceDescriptionTest80Load.setUrl(new URL("http://example.com/rest/test80"));
        serviceDescriptionTest80Load.setLoad(80);
        libraryService.delete(serviceDescriptionTest80Load);
        assertFalse(libraryService.getAvailableServices().contains("Test"));
        assertTrue(libraryService.getAvailableServices().contains("Dummy"));
    }

    @Test
    public void testGet() throws Exception {
        ServiceDescription serviceDescription = libraryService.get("Test", new URL("http://example.com/rest/test50"));
        assertEquals("Test", serviceDescription.getName());
        assertEquals(new URL("http://example.com/rest/test50"), serviceDescription.getUrl());
        assertEquals(50, serviceDescription.getLoad());

        serviceDescription = libraryService.get("Test", new URL("http://example.com/rest/test80"));
        assertEquals("Test", serviceDescription.getName());
        assertEquals(new URL("http://example.com/rest/test80"), serviceDescription.getUrl());
        assertEquals(80, serviceDescription.getLoad());

        serviceDescription = libraryService.get("Dummy", new URL("http://example.com/rest/dummy20"));
        assertEquals("Dummy", serviceDescription.getName());
        assertEquals(new URL("http://example.com/rest/dummy20"), serviceDescription.getUrl());
        assertEquals(20, serviceDescription.getLoad());

    }

    @Test
    public void testGetServiceDescriptionWithMinimalLoad() throws Exception {
        ServiceDescription serviceDescription = libraryService.getServiceDescriptionWithMinimalLoad("Test");
        assertEquals("Test", serviceDescription.getName());
        assertEquals(new URL("http://example.com/rest/test50"), serviceDescription.getUrl());
        assertEquals(50, serviceDescription.getLoad());

        serviceDescription = libraryService.getServiceDescriptionWithMinimalLoad("Dummy");
        assertEquals("Dummy", serviceDescription.getName());
        assertEquals(new URL("http://example.com/rest/dummy20"), serviceDescription.getUrl());
        assertEquals(20, serviceDescription.getLoad());
    }

    @Test
    public void testGetAvailableServices() throws Exception {
        Set<String> services = libraryService.getAvailableServices();
        assertEquals(2, services.size());
        assertTrue(services.contains("Test"));
        assertTrue(services.contains("Dummy"));
    }
}