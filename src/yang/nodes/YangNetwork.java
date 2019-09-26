package yang.nodes;

import yang.generators.NetworkGeneratorCore;
import yang.generators.twod.interconnected.NetworkGeneratorInterconnected2DInstance;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class YangNetwork {
    public NetworkGeneratorCore withSpace;
    public NetworkGeneratorCore withNeighbor;

    public YangNetwork(NetworkGeneratorCore withSpace, NetworkGeneratorCore withNeighbor) {
        this.withSpace = withSpace;
        this.withNeighbor = withNeighbor;
    }

    public void save(String path, boolean space){
        if (space){
            this.withSpace.save(path);
        }else{
            this.withNeighbor.save(path);
        }
    }

    public void saveNetworkImage(int nw, int nh, int nr, String path){
        try {
            this.withSpace.saveImage(nw,nh, nr, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public NetworkGeneratorInterconnected2DInstance loadNeighborFile(String path){
       return  NetworkGeneratorInterconnected2DInstance.load(this._readFile(path));
    }


    public ArrayList<String> _readFile(String path){
        List<String> list = new ArrayList<String>();
        File file = new File(path);
        if(file.exists()){
            try {
                list = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(list.isEmpty())
                return (ArrayList<String>) list;
        }
        return (ArrayList<String>) list;
    }
    public void load(String path){

    }
}
