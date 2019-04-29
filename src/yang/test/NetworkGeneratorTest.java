package yang.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yang.NetworkGenerator;

import static org.junit.Assert.*;

@DisplayName("Network generator test")
public class NetworkGeneratorTest {




    @Test
    public void testGeneratedNetworkSize() {
        NetworkGenerator ng = new NetworkGenerator(100,10,1,30,30,false);
        int size = ng.getNodes().size();
        assertEquals("Dimensione errata",size,0);
        ng.generateNetwork();
        assertEquals("Dimensione errata",ng.getNodes().size(),100);
    }



    /*@BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }*/

}