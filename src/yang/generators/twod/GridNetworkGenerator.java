package yang.generators.twod;

import yang.generators.twod.grid.GridCell;
import yang.generators.twod.grid.LogicalGridCell;
import yang.nodes.SpaceNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GridNetworkGenerator extends  NetworkGenerator2DCore {

    private final int networkWidth;
    private final int nodeMinRadius;
    private final int networkHeight;
    private final int nodeMaxRadius;
    private final double nodeMinSquareRadiusWithCirconference;
    private final double nodeMaxSquareRadiusWithCirconference;

    private final Random r = new Random();

    public GridNetworkGenerator(int nodes, int networkHeight, int networkWidth, int nodeMinRadius, int nodeMaxRadius){
        super(nodes);
        this.networkHeight = networkHeight;
        this.networkWidth = networkWidth;
        this.nodeMinRadius = nodeMinRadius;
        this.nodeMaxRadius = nodeMaxRadius;
        this.nodeMinSquareRadiusWithCirconference = nodeMinRadius;
        this.nodeMaxSquareRadiusWithCirconference = nodeMaxRadius * (Math.sqrt(2)/2);
        this.createNodes(nodes);
    }

    @Override
    protected void createNodes(int n) {
        // construct the matrix.
        double radius;
        ArrayList<Double> xPositions = new ArrayList<Double>();
        ArrayList<Double> yPositions = new ArrayList<Double>();
        HashMap<LogicalGridCell, GridCell> grid = new HashMap<LogicalGridCell, GridCell>();

        double xbase = 0;
        do {
            radius = nodeMinSquareRadiusWithCirconference + (this.r.nextDouble()*(nodeMaxSquareRadiusWithCirconference - nodeMinSquareRadiusWithCirconference));
            xbase += radius;
            if (xbase < this.networkWidth ) {
                xPositions.add(xbase);
            }
        }while(xbase < this.networkWidth );

        double ybase = 0;

        do {
            radius = nodeMinSquareRadiusWithCirconference + (this.r.nextDouble()*(nodeMaxSquareRadiusWithCirconference - nodeMinSquareRadiusWithCirconference));
            ybase += radius;
            if (ybase < this.networkHeight ) {
                yPositions.add(ybase);
            }
        }while(ybase < this.networkHeight);
        // Create matrix.
        for (int xpos = 0; xpos < xPositions.size(); xpos++) {
            for (int ypos = 0; ypos < yPositions.size(); ypos++) {
                LogicalGridCell mPos =  new LogicalGridCell(xpos, ypos);
                GridCell mPosXY =  new GridCell(xPositions.get(xpos), yPositions.get(ypos));
                grid.put(mPos,mPosXY);
            }
        }

        int xMaster = xPositions.size()/2;
        int yMaster = yPositions.size()/2;
        LogicalGridCell logicalMasterPosition = new LogicalGridCell(xMaster, yMaster);
        GridCell phMasterPosition  = grid.get(logicalMasterPosition);
        SpaceNode master = new SpaceNode(0,phMasterPosition.x,phMasterPosition.y);
        phMasterPosition.node = master;
        // Update the position.
        grid.put(logicalMasterPosition, phMasterPosition);
        // add nodes.
        this.nodes.add(master);
        ArrayList<LogicalGridCell> usedGridCell = new ArrayList<LogicalGridCell>();
        usedGridCell.add(logicalMasterPosition);
        // x(+1 0 -1), y(+1 0 -1)
        int nodeID = 1;
        while (this.nodes.size()!=this.nodes_count) {
            // take a random node.
            LogicalGridCell nodeCell = usedGridCell.get(this.r.nextInt(usedGridCell.size()));
            int nextPosition = r.nextInt(4);
            int xMove = 0;
            int yMove = 0;

            if (nextPosition  == 0){
                xMove = 1;
                yMove = 0;
            } else if (nextPosition  == 1){
                xMove = 0;
                yMove = 1;
            } else if (nextPosition  == 2){
                xMove = 0;
                yMove = -1;
            } else{
                xMove = -1;
                yMove = 0;
            }
            // avoid go outside the range.
            if (nodeCell.x + xMove > xPositions.size() -1){
                continue;
            } else if (nodeCell.x + xMove < 0){
                continue;
            } else if (nodeCell.y + yMove > yPositions.size() - 1){
                continue;
            } else if (nodeCell.y + yMove < 0){
                continue;
            }
            LogicalGridCell logicalNextCell = new LogicalGridCell(nodeCell.x + xMove, nodeCell.y + yMove);
            GridCell phNextCell = grid.get(logicalNextCell);

            if (phNextCell.isBusy()){
                continue;
            }
            phNextCell.node = new SpaceNode(nodeID, phNextCell.x, phNextCell.y);
            this.nodes.add(phNextCell.node);
            nodeID++;

            usedGridCell.add(logicalNextCell);
            grid.put(logicalNextCell,phNextCell);
        }


    }
}
