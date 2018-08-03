package org.rtassembly.npgraph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

public class SimpleBinner {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleBinner.class);
	public static volatile int 	UNQ_CTG_LEN=10000,
								SIG_CTG_LEN=1000;
	
	BidirectedGraph graph;
	ArrayList<PopBin> binList;
	HashMap<Edge, HashMap<PopBin,Integer>> edge2BinMap;
	HashMap<Node, HashMap<PopBin, Integer>> node2BinMap;
	ArrayList<Edge> unresolvedEdges;
	SimpleBinner(BidirectedGraph graph){
		this.graph = graph;
		binList = new ArrayList<PopBin>();
		edge2BinMap = new HashMap<Edge, HashMap<PopBin,Integer>>();
		node2BinMap = new HashMap<Node, HashMap<PopBin, Integer>>();
		unresolvedEdges = new ArrayList<Edge>(graph.edges().collect(Collectors.toList()));
	}

	/*
	 * When a node bin is set, traverse the graph to assign edges if possible
	 */
	private void exploringFromNode(Node node){
		HashMap<PopBin, Integer> nbins = node2BinMap.get(node);
		if(nbins == null || nbins.isEmpty())
			return;
		
		ArrayList<Edge> unknownLeavingEdges = new ArrayList<>(),
						unknownEnteringEdges = new ArrayList<>();
		HashMap<PopBin,Integer> 	leavingEdgeBinCount=new HashMap<PopBin,Integer>(),
									enteringEdgeBinCount=new HashMap<PopBin,Integer>(),
									induceEdgeBin=new HashMap<PopBin, Integer>();
		// Leaving edges
		node.leavingEdges().forEach(e -> 
		{
			HashMap<PopBin, Integer> ebins = edge2BinMap.get(e); 
			if(ebins == null || ebins.size()==0)
				unknownLeavingEdges.add(e);
			else{
				for(PopBin b:ebins.keySet()) {
					leavingEdgeBinCount.put(b, ebins.get(b)+ (leavingEdgeBinCount.get(b)==null?0:leavingEdgeBinCount.get(b)));
				}
			}
		});
		if(unknownLeavingEdges.size()==1){
			Edge e = unknownLeavingEdges.get(0);
			induceEdgeBin=substract(nbins, leavingEdgeBinCount);
			if(induceEdgeBin.values().stream().mapToInt(Integer::intValue).sum() > 0){
				edge2BinMap.put(e, induceEdgeBin);
				System.out.printf("From node %s%s firing edge %s%s\n",node.getId(),getBinsOfNode(node), e.getId(), getBinsOfEdge(e));
				exploringFromEdge(e);
			}
		}

		// Entering edges
		node.enteringEdges().forEach(e -> 
		{
			HashMap<PopBin, Integer> ebins = edge2BinMap.get(e); 
			if(ebins == null || ebins.size()==0)
				unknownEnteringEdges.add(e);
			else{
				for(PopBin b:ebins.keySet()) {
					enteringEdgeBinCount.put(b, ebins.get(b)+ (enteringEdgeBinCount.get(b)==null?0:enteringEdgeBinCount.get(b)));
				}
			}
		});
		if(unknownEnteringEdges.size()==1){
			Edge e = unknownEnteringEdges.get(0);
			induceEdgeBin=substract(nbins, enteringEdgeBinCount);
			if(induceEdgeBin.values().stream().mapToInt(Integer::intValue).sum() > 0){
				edge2BinMap.put(e, induceEdgeBin);
				System.out.printf("From node %s%s firing edge %s%s\n",node.getId(),getBinsOfNode(node), e.getId(), getBinsOfEdge(e));
				exploringFromEdge(e);
			}
		}
		
	}
	/*
	 * When an edge bin is set, traverse the graph to assign nodes if possible
	 */
	private void exploringFromEdge(Edge edge){
		if(!edge2BinMap.containsKey(edge))
			return;
		unresolvedEdges.remove(edge);
		Node 	n0 = edge.getNode0(),
				n1 = edge.getNode1();
		
		if(!node2BinMap.containsKey(n0)){
			boolean dir0 = ((BidirectedEdge)edge).getDir0();
			Stream<Edge> edgeSet0 = dir0?n0.leavingEdges():n0.enteringEdges();		
			
			ArrayList<Edge> edgeList0 = (ArrayList<Edge>) edgeSet0.collect(Collectors.toList());
			HashMap<PopBin,Integer> binCounts0 = new HashMap<PopBin,Integer>();
			boolean fully = true;
			for(Edge e:edgeList0){
				if(edge2BinMap.containsKey(e)){
					binCounts0=sum(binCounts0, edge2BinMap.get(e));
				}else{
					fully = false;
					break;
				}
			}
			//check if the total cov of inferred pop bins agree with its original coverage or not
			double covSum=0.0;
			for(PopBin b:binCounts0.keySet()){
				covSum+=binCounts0.get(b)*b.estCov;
			}
			
			if(fully && Math.abs(covSum-n0.getNumber("cov"))/n0.getNumber("cov") < .2){
				node2BinMap.put(n0, binCounts0);
//				System.out.println("From edge " + edge.getId() + " updating node " + n0.getId());
				System.out.printf("From edge %s%s completing node %s%s\n", edge.getId(), getBinsOfEdge(edge), n0.getId(),getBinsOfNode(n0));

				exploringFromNode(n0);
			}
		}else{
			//TODO: check consistent here			
			System.out.printf("From edge %s%s firing node %s%s\n",edge.getId(), getBinsOfEdge(edge), n0.getId(),getBinsOfNode(n0));
			exploringFromNode(n0);
		}
		
		if(!node2BinMap.containsKey(n1)){
			boolean dir1 = ((BidirectedEdge)edge).getDir1();
			Stream<Edge> edgeSet1 = dir1?n1.leavingEdges():n1.enteringEdges();
			
			ArrayList<Edge> edgeList1 = (ArrayList<Edge>) edgeSet1.collect(Collectors.toList());
			HashMap<PopBin,Integer> binCounts1 = new HashMap<PopBin,Integer>();
			boolean fully = true;
			for(Edge e:edgeList1){
				if(edge2BinMap.containsKey(e)){
					binCounts1=sum(binCounts1, edge2BinMap.get(e));
				}else{
					fully = false;
//					System.out.println("...node " + n1.getId() + " not yet fully solved at: " + e.getId());
					break;
				}
			}
			//check if the total cov of inferred pop bins agree with its original coverage or not
			double covSum=0.0;
			for(PopBin b:binCounts1.keySet()){
				covSum+=binCounts1.get(b)*b.estCov;
			}
			
			if(fully && Math.abs(covSum-n1.getNumber("cov"))/n1.getNumber("cov") < .2){				
				node2BinMap.put(n1, binCounts1);
				System.out.printf("From edge %s%s completing node %s%s\n", edge.getId(), getBinsOfEdge(edge), n1.getId(),getBinsOfNode(n1));
				exploringFromNode(n1);
			}
			
		}else{ 
			//TODO: check consistent here also
			System.out.printf("From edge %s%s firing node %s%s\n",edge.getId(), getBinsOfEdge(edge), n1.getId(),getBinsOfNode(n1));
			exploringFromNode(n1);
		}
	}
	
	private static HashMap<PopBin, Integer> substract(HashMap<PopBin, Integer> minuend, HashMap<PopBin, Integer> subtrahend){
		HashMap<PopBin, Integer> difference = new HashMap<PopBin, Integer>();
		for(PopBin b:minuend.keySet()){
			int _minuend = minuend.get(b),
				_diff = _minuend - (subtrahend.containsKey(b)?subtrahend.get(b):0);	
			if(_diff > 0)
				difference.put(b, _diff);
			else
				difference.put(b, 0);
		}
		
		return difference;
	}
	
	private static HashMap<PopBin, Integer> sum(HashMap<PopBin, Integer> augend, HashMap<PopBin, Integer> addend){
		if(augend==null||addend==null)
			return null;
//		if(augend==null) return addend;
//		if(addend==null) return augend;
		
		HashMap<PopBin, Integer> retval = new HashMap<PopBin, Integer>();
		for(PopBin b:Sets.union(augend.keySet(), addend.keySet()))
			retval.put(b, (augend.containsKey(b)?augend.get(b):0) + (addend.containsKey(b)?addend.get(b):0));
		
		return retval;
	}
	
	private PopBin scanAndGuess(double cov) {
		PopBin target=null;
		double 	tmp=Double.MAX_VALUE,
				lowestPopAbundance=Double.MAX_VALUE;
		for(PopBin b:binList) {
			double distance=GraphUtil.metric(cov, b.estCov);
			if(distance<tmp) {
				tmp=distance;
				target=b;
			}
			if(lowestPopAbundance > b.estCov)
				lowestPopAbundance=b.estCov;
		}
			
		if(cov > lowestPopAbundance && tmp>GraphUtil.DISTANCE_THRES) {
			target = null;
		}
		
		return target;
	}

	//A simple clustering with DBSCAN. used internal
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void nodesClustering() {
		binList = new ArrayList<PopBin>();
		List<DoublePoint> points = new ArrayList<DoublePoint>();
		
		for(Node n:graph) {
			if(n.getNumber("len") >= UNQ_CTG_LEN && Math.max(n.getInDegree(), n.getOutDegree()) <= 1) {
				points.add(new DoublePoint(new double[]{n.getNumber("cov"), new Double(n.getId())}));
			}
		}
		DBSCANClusterer dbscan = new DBSCANClusterer(GraphUtil.DISTANCE_THRES, 0, (a,b)->GraphUtil.metric(a[0], b[0]));

		List<Cluster<DoublePoint>> cluster = dbscan.cluster(points);
		for(Cluster<DoublePoint> c:cluster) {
			PopBin bin=new PopBin();
			for(DoublePoint p:c.getPoints()) {
				Node tmp = graph.getNode(((int)p.getPoint()[1])+"");
				bin.addCoreNode(tmp);
				HashMap<PopBin, Integer> entry = new HashMap<PopBin, Integer>();
				entry.put(bin, 1);
				node2BinMap.put(tmp, entry);
				
			}
			if(bin.getCoreNodes().size() > 0){
				binList.add(bin);
				System.out.println("Bin " + bin.binID + ": " + bin.estCov);
				for(Node n:bin.getCoreNodes())
					System.out.println("...core node " + n.getAttribute("name"));
			}
			
		}

	}
	
	
	//Now traverse and clustering the edges (+assign cov)
	public void estimatePathsByCoverage() {
		//1.first clustering the biggest nodes into populations
		nodesClustering();
		//2. assign edges and nodes coverage based on original nodes coverage and graph topo
		GraphUtil.gradientDescent(graph);
		graph.edges().forEach(e->System.out.println("Edge " + e.getId() + " cov=" + e.getNumber("cov")));
		//3.1.First round of assigning unit cov: from binned significant nodes
		for(PopBin b:binList) {
			for(Node n:b.getCoreNodes()) {
				exploringFromNode(n);
			}			
		}

		//3.2. Second round of thorough assignment: suck it deep!
		HashMap<PopBin, ArrayList<Edge>> highlyPossibleEdges = new HashMap<PopBin, ArrayList<Edge>>();
		unresolvedEdges.sort((a,b)->Double.compare(a.getNumber("cov"),b.getNumber("cov")));

		for(Edge e:unresolvedEdges){
				System.out.print("...scanning edge " + e.getId() + "cov=" + e.getNumber("cov"));
				PopBin tmp = scanAndGuess(e.getNumber("cov"));
				if(tmp!=null){
					if(!highlyPossibleEdges.containsKey(tmp))
						highlyPossibleEdges.put(tmp, new ArrayList<Edge>());
					
					highlyPossibleEdges.get(tmp).add(e);
					System.out.printf(": highly possible in bin %d!", tmp.getId());
				}else
					System.out.print(": none!");
				System.out.println();
		}

		while(!unresolvedEdges.isEmpty()) {
			LOG.info("Starting assigning " + unresolvedEdges.size() + " unresolved edges");
			//sort the unresolved edges based on abundance and guess until all gone...
			if(!highlyPossibleEdges.keySet().isEmpty()){
				for(PopBin b:binList){
					if(highlyPossibleEdges.containsKey(b)){
						while(!highlyPossibleEdges.get(b).isEmpty()) {
							Edge guess = highlyPossibleEdges.get(b).remove(0);
							if(unresolvedEdges.contains(guess)){
								HashMap<PopBin, Integer> bc = new HashMap<>();
								bc.put(b, 1);
								edge2BinMap.put(guess, bc);
								exploringFromEdge(guess);
							}else{
								//TODO: check consistent here or just take it?
							}
						
						}
						highlyPossibleEdges.remove(b);
					}
				}

			}
			else{
				//we can go further with random guessing but let's stop here for now
				LOG.info("GUESS NO MORE!!!");
				break;
			}
		}
		
		//3.3 Assign unique nodes here: need more tricks
		for(Node node:graph)
			if(	node2BinMap.containsKey(node) && node.getNumber("len") > SIG_CTG_LEN 
				&& Math.max(node.getInDegree(), node.getOutDegree()) <= 1){
				HashMap<PopBin, Integer> bc = node2BinMap.get(node);
				ArrayList<PopBin> counts = new ArrayList<PopBin>(bc.keySet());
//				if(counts.size()==1 && bc.get(counts.get(0))==1){ //and should check for any conflict???
				if(bc.values().stream().mapToInt(Integer::intValue).sum()==1){ //and should check for any conflict???
					node.setAttribute("unique", counts.get(0));
				}
			}
		
	}
	static public PopBin getUniqueBin(Node node){
		return (PopBin)node.getAttribute("unique");
	}
	
	
	//Traversal along a unique path (unique ends) and return list of unique edges
	//Must only be called from BidirectedGraph.reduce()
	synchronized public ArrayList<BidirectedEdge> walkAlongUniquePath(BidirectedPath path) {
		LOG.info("Path {} being processed based on binning info...", path.getId());
		Node  	curNode = path.getRoot(), nextNode;
		PopBin 	uniqueBin=path.getConsensusUniqueBinOfPath(),
				startBin=getUniqueBin(path.getRoot()),
				endBin=getUniqueBin(path.peekNode());
		if(uniqueBin==null||startBin==null||endBin==null){
			LOG.info("Ignored: population bin of the path (either at ending node or global) is not known!");
			return null;
		}else if(uniqueBin!=startBin && uniqueBin!=endBin){//at least one end must agree with the wholepath bin
			LOG.info("Ignored: consensus bin must be one of the endings bin: Ignored!");
			//clean from bin map here...
			node2BinMap.remove(path.getRoot());
			node2BinMap.remove(path.peekNode());
			return null;
		}else if(!uniqueBin.isCloseTo(startBin)){
			LOG.info("Ignored: consensus bin {} doesn't agree with one of the endings bin {} at node {}", uniqueBin, startBin, path.getRoot());
			node2BinMap.remove(path.getRoot());
			//clean from bin map here...
			return null;
		}else if(!uniqueBin.isCloseTo(endBin)){
			LOG.info("Ignored: consensus bin {} doesn't agree with one of the endings bin {} at node {}", uniqueBin, endBin, path.peekNode());
			node2BinMap.remove(path.peekNode());
			//clean from bin map here...
			return null;
		}
		PopBin other=(uniqueBin==startBin?endBin:startBin);
		HashMap<PopBin, Integer> 	oneBin=new HashMap<>(),
									otherBin=new HashMap<>();
		oneBin.put(uniqueBin, 1);
		otherBin.put(other, 1);
		
		ArrayList<BidirectedEdge> retval = new ArrayList<BidirectedEdge>();	
		double aveCov=uniqueBin.estCov;
	
		for(Edge ep:path.getEdgePath()){
			nextNode=ep.getOpposite(curNode);
			HashMap<PopBin, Integer> edgeBinsCount, bcMinusOne=null,  
										nodeBinsCount;	
			if(getUniqueBin(ep.getNode0())!=null || getUniqueBin(ep.getNode1())!=null)
				retval.add((BidirectedEdge)ep);
				
			if(edge2BinMap.containsKey(ep)) {
				edgeBinsCount=edge2BinMap.get(ep);
				if(edgeBinsCount.containsKey(uniqueBin)) {
					bcMinusOne=substract(edgeBinsCount, oneBin);
					edge2BinMap.replace(ep, bcMinusOne);

				}else if(edgeBinsCount.containsKey(other)){
				//E.g. b2 vs b1 =>  b2==b1							//...
					bcMinusOne=substract(edgeBinsCount, otherBin);
					edge2BinMap.replace(ep, bcMinusOne);				

				}else {
					LOG.error("Conflict binning information on path {}, at edge {}: {}!,", path.getId(), ep.getId(), getBinsOfEdge(ep));
//					edge2BinMap.remove(ep);
				}
		
				
			}
			
//			LOG.info("--edge {} coverage:{} to {}",ep.getId(),ep.getNumber("cov"),ep.getNumber("cov") - aveCov);
			ep.setAttribute("cov", ep.getNumber("cov")>aveCov?ep.getNumber("cov")-aveCov:0);	

			if(bcMinusOne!=null && bcMinusOne.values().stream().mapToInt(Integer::intValue).sum() == 0) {
//				edge2BinMap.remove(ep);
				//delete here???
				retval.add((BidirectedEdge) ep);
			}
			
//			if(ep.getNumber("cov") < 0  && !unresolvedEdges.contains(ep)) //plasmid coverage is different!!!
//			if(ep.getNumber("cov") <= aveCov*0.5  && !edge2BinMap.containsKey(ep)) //plasmid coverage is different!!!
//				retval.add((BidirectedEdge) ep);
			bcMinusOne=null;
			if(curNode!=path.getRoot() && curNode!=path.peekNode()) {
				if(node2BinMap.containsKey(curNode)) {
					if(node2BinMap.get(curNode).containsKey(uniqueBin)) {
						nodeBinsCount=node2BinMap.get(curNode);
						bcMinusOne=substract(nodeBinsCount, oneBin);
						node2BinMap.replace(curNode, bcMinusOne);

					}else if(node2BinMap.get(curNode).containsKey(other)) {
						nodeBinsCount=node2BinMap.get(curNode);
						bcMinusOne=substract(nodeBinsCount, otherBin);
						node2BinMap.replace(curNode, bcMinusOne);
//						if(!bcMinusOne.isEmpty()) {
//							node2BinMap.replace(curNode, bcMinusOne);
//						}
//						else {
//							node2BinMap.remove(curNode);
//						}
					}
					
				}				
				
				curNode.setAttribute("cov", curNode.getNumber("cov")>aveCov?curNode.getNumber("cov")-aveCov:0);
//				if(	(bcMinusOne!=null && bcMinusOne.values().stream().mapToInt(Integer::intValue).sum() == 0)
//					|| curNode.getNumber("cov") < .1*aveCov) 
//					curNode.edges().forEach(e->retval.add((BidirectedEdge) e));
				
			}
			
			curNode=nextNode;
		}
		
		//check again
		retval.removeIf(e->	getUniqueBin(e.getNode0())==null && getUniqueBin(e.getNode0())==null &&
							!(checkEdgeSafeToRemove(e)));
		return retval;
	}

	
	private boolean checkEdgeSafeToRemove(BidirectedEdge edge) {
		boolean retval=true;
		BidirectedNode 	n0=(BidirectedNode) edge.getNode0(),
						n1=(BidirectedNode) edge.getNode1();
		boolean dir0=edge.getDir0(),
				dir1=edge.getDir1();
		double remainCov=0.0;
		Optional<Double> tmp;
		System.out.printf("Checking edge " + edge.getId() + ": remainCov=");
		boolean onlyFlag=false;
		if(getUniqueBin(n0)==null && getUniqueBin(n1)==null){
			if((dir0?n0.getOutDegree():n0.getInDegree()) == 1){//the only in/out edge for n0
				tmp=(dir0?n0.enteringEdges():n0.leavingEdges()).map(e->(e.getNumber("cov"))).reduce(Double::sum);
				if(tmp.isPresent())
					remainCov+=tmp.get();
				onlyFlag=true;
			}
			if((dir1?n1.getOutDegree():n1.getInDegree()) == 1){//the only in/out edge for n1
				tmp=(dir1?n1.enteringEdges():n1.leavingEdges()).map(e->(e.getNumber("cov"))).reduce(Double::sum);
				if(tmp.isPresent())
					remainCov+=tmp.get();			
				onlyFlag=true;
			}
			if(onlyFlag && remainCov > edge.getNumber("cov"))
				retval=false;
		}
		System.out.println(remainCov + " => " + retval);
		return retval;
	}
	public String getBinsOfNode(Node node) {
		String retval="[";
		HashMap<PopBin, Integer> binCount=node2BinMap.get(node);
		if(binCount==null)
			retval+="unknown";
		else {
			for(PopBin b:binCount.keySet()) {
				int count=binCount.get(b);
				retval+=b.getId()+":"+count+"; ";
			}
		}
		retval+="]";
		return retval;
	}
	public String getBinsOfEdge(Edge edge) {
		String retval="[";
		HashMap<PopBin, Integer> binCount=edge2BinMap.get(edge);
		if(binCount==null)
			retval+="unknown";
		else {
			for(PopBin b:binCount.keySet()) {
				int count=binCount.get(b);
				retval+=b.getId()+":"+count+"; ";
			}
		}
		retval+="]";
		return retval;
	}
	public static void main(String[] args) throws IOException {
		HybridAssembler hbAss = new HybridAssembler();
		hbAss.setShortReadsInput("/home/sonhoanghguyen/Projects/scaffolding/data/spades_3.7/Kp13883-careful/assembly_graph.fastg");
		hbAss.setShortReadsInputFormat("fastg");
		hbAss.prepareShortReadsProcess(true);
		
		SimpleBinner binner = hbAss.simGraph.binner;
		//binner.estimatePathsByCoverage();
		System.out.println("=> number of bin = " + binner.binList.size());
		for(PopBin b:binner.binList) {
			System.out.println("Bin " + b.binID + " estCov=" + b.estCov + " totLen=" + b.totLen);
			for(Node n:b.getCoreNodes())
				System.out.println(n.getAttribute("name"));
		}
			
			
		for(Node n:hbAss.simGraph) {
			Iterator<Edge> ite = n.edges().iterator();
			while(ite.hasNext()) {
				Edge e = ite.next();
				System.out.println("Edge "+e.getId() + " cov=" + e.getNumber("cov") );
				HashMap<PopBin,Integer> tmp = binner.edge2BinMap.get(e);
				if(tmp==null) {
					System.out.println("...has not yet assigned!");
					continue;
				}
				for(PopBin b:tmp.keySet()) {
					System.out.printf(" bin %d : %d, ", b.binID, tmp.get(b));
				}
				System.out.println();
			}
		}
	}
}
