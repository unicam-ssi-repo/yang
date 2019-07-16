package yang.test;

import org.junit.jupiter.api.Test;
import yang.nodes.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NetworkStreamTest {

    @Test
    void saveNetwork() throws IOException {
       /* NetworkGenerator ng = new NetworkGenerator(10,10, 1,50,30,false);
        ng.generateNetwork();

        File tempFile = File.createTempFile("tempFile", ".txt");
        String firstNetwork = ng.getNodes().toString();
        NetworkStream.saveNetwork(ng,tempFile.getAbsolutePath());
        ArrayList<Node> list = NetworkStream.recoverNetwork(tempFile.getAbsolutePath());
        String secNetwork = ng.getNodes().toString();
        assertEquals(firstNetwork,secNetwork,"Salvataggio e recupero rete non riuscito.");
        tempFile.deleteOnExit();*/
    }
}