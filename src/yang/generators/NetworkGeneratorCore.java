package yang.generators;

import yang.nodes.Node;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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

    public void save(String path) {
        try (PrintWriter out = new PrintWriter(path)) {
            out.println(this.toString());
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveImage(int networkWidth, int networkHeight,String path) throws IOException {
        BufferedImage image = new BufferedImage(networkWidth, networkHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 10000, 10000);
        g.setColor(Color.BLACK);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).drawBackground(g);
        }
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).draw(g);
        }
        ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
        writer.setOutput(ImageIO.createImageOutputStream(new File(path)));
        writer.write(image);

    }

}
