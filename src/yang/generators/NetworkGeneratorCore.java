package yang.generators;

import yang.nodes.Node;

import java.util.ArrayList;

public abstract class NetworkGeneratorCore {
    protected int nodes_count;


    protected ArrayList<Node> nodes;


    public NetworkGeneratorCore(int n){
        this.nodes_count = n;
        this.nodes = new ArrayList<Node>();
    }



    protected abstract void createNodes(int n);

    public String toString(){
        String result = "";
        for (int i = 0; i < nodes.size(); i++) {
            result+= nodes.get(i).toString()+'\n';
        }
        return result;
    }

    protected  void setNodes(ArrayList<Node> nodes){
        this.nodes = nodes;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
}
