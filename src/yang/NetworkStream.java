package yang;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class NetworkStream {
    /**
     * Save the network method
     * @param netGenerator
     * @param path
     */
    public static void saveNetwork(NetworkGenerator netGenerator, String path) throws IOException {
        File fpath;
        FileWriter fw;
        BufferedWriter bw;
        try {
            fpath = new File(path);
            fw = new FileWriter(fpath);
            bw = new BufferedWriter(fw);
        }
        catch (IOException e)
        {
            throw e;
        }


        ArrayList<Node> nodes = netGenerator.getNodes();
        bw.write("Name;x;y");
        for (int ni = 0; ni < nodes.size(); ni++){
            bw.write(nodes.get(ni).toString());
        }

        bw.close();
    }

    /**
     * recoverNetwork
     * @param file file path.
     * @return NetworkGenerator
     */
    public static ArrayList<Node>  recoverNetwork(String file)  throws IOException{
        BufferedReader reader;

        reader = new BufferedReader(new FileReader(
                file));
        reader.readLine(); // header string ... useless
        Node node;
        ArrayList<Node> nodes = new ArrayList<Node>();
        String line = reader.readLine();

        while (line != null) {
            // avoid header


            String[] data = line.split(";");
            node = new Node(Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[0]));
            nodes.add(node);
            line = reader.readLine();
            // read next line

        }
        reader.close();
        return nodes;
    }
}
