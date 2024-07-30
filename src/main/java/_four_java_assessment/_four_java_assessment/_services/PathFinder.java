package _four_java_assessment._four_java_assessment._services;

import _four_java_assessment._four_java_assessment.models.Node;
import _four_java_assessment._four_java_assessment.models.Route;
import _four_java_assessment._four_java_assessment.repos.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PathFinder {
    @Autowired
    private RouteRepository routeRepository;

    public List<String> smallestRoute(String start ,String end){
        Map<String ,List<Route>> gr = buildGraph();
        return dj(gr,start,end);
    }
    private Map<String,List<Route>>buildGraph (){
        List<Route> routes = routeRepository.findAll();
        Map<String ,List<Route>> graph = new HashMap<>();
        for (Route route : routes){
            graph.computeIfAbsent(route.getSource(),i -> new ArrayList<>()).add(route);
        }
        return graph;
    }
    private List<String> dj(Map<String,List<Route>> graph,String start,String end){
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingDouble(Node::getDistance));
        Map<String,Double>  distances = new HashMap<>();
        Map<String,String>  pre = new HashMap<>();
        Set<String>  visit = new HashSet<>();

        for (String node : graph.keySet()){
            if (node.equals(start)) {
                distances.put(node,0.0);
                q.add(new Node(node,0.0));
            }else{
                distances.put(node ,Double.MAX_VALUE);
            }
        }
        while(!q.isEmpty()){
            Node cur = q.poll();
            visit.add(cur.getName());

            if (cur.getName().equals(end)){
                return path(pre,end);
            }

            for (Route n :graph.getOrDefault(cur
                    .getName(),new ArrayList<>())){
                if (visit.contains(n.getDestination())){
                    continue;
                }
                double newDistance =distances.get(cur.getName() +n.getDestination());

                if (newDistance < distances.getOrDefault(n.getDestination(),Double.MAX_VALUE)){
                    distances.put(n.getDestination(),newDistance);
                    pre.put(n.getDestination(),cur.getName());
                    q.add(new Node(n.getDestination(),newDistance));
                }
            }
        }
        return Collections.emptyList();
    }
    private List<String> path (Map<String ,String>previous,String end){
        List<String> path = new ArrayList<>();

        for (String k =end;k!=null;k =previous.get(k)){
            path.add(0,k);
        }
        return path;
    }
}
