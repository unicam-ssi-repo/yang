package yang;

import org.apache.commons.cli.*;
import yang.helpers.NetworkGeneratorHelper;
import yang.nodes.YangNetwork;

public class CliMain {
    public static void main(String[] args) {
        Options options = new Options();
        /*

             GlobalConfiguration.NETWORK_SIZE_METERS,
                    GlobalConfiguration.MIN_NODE_RADIUS,
                    GlobalConfiguration.MAX_NODE_RADIUS,
                    GlobalConfiguration.MASTER_X,
                    GlobalConfiguration.MASTER_Y,
         */
        Option nodes = new Option("n", "nodes", true, "Nodes in a network (>2) ");
        nodes.setType(Integer.class);

        options.addOption(nodes);

        Option networkSize = new Option("s", "size", true, "Network size in meters");
        networkSize.setType(Integer.class);
        options.addOption(networkSize);

        Option minRadius = new Option("minr", "minradius", true, "Mininum radius for a node");
        minRadius.setType(Integer.class);
        options.addOption(minRadius);

        Option maxRadius = new Option("maxr", "maxradius", true, "Max radius for a node");
        maxRadius.setType(Integer.class);
        options.addOption(maxRadius);

        Option masterX = new Option("mx", "masterx", true, "Master x position");
        masterX.setType(Integer.class);
        options.addOption(masterX);

        Option masterY = new Option("my", "mastery", true, "Master y position");
        masterY.setType(Integer.class);
        options.addOption(masterY);


        // Parsing stuff.
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        int nodesNetwork = Integer.parseInt(cmd.getOptionValue("nodes","3"));
        int sizeNetwork = Integer.parseInt(cmd.getOptionValue("size" ,"10000"));
        int nodeMinRadius  = Integer.parseInt(cmd.getOptionValue("minradius", "100"));
        int nodeMaxRadius  = Integer.parseInt(cmd.getOptionValue("maxradius", "1500"));
        int masterXValue  = Integer.parseInt(cmd.getOptionValue("masterx", "5000"));
        int masterYValue  = Integer.parseInt(cmd.getOptionValue("mastery", "5000"));
        YangNetwork yang = NetworkGeneratorHelper.generateInterconnectedRadiusNetwork(
                nodesNetwork, sizeNetwork, nodeMinRadius, nodeMaxRadius, masterXValue, masterYValue, -1
        );
        System.out.println(yang.withSpace.toString());
        System.out.println(yang.withNeighbor.toString());

    }
}
