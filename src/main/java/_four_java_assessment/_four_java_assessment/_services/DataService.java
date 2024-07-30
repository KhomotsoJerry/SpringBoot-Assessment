package _four_java_assessment._four_java_assessment._services;

import _four_java_assessment._four_java_assessment.models.Node_A;
import _four_java_assessment._four_java_assessment.models.Route;
import _four_java_assessment._four_java_assessment.repos.NodeRepository;
import _four_java_assessment._four_java_assessment.repos.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Service
public class DataService {
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private RouteRepository routeRepository;

    @PostConstruct
    public void getData() {

            InputStream inputStream = getClass().getResourceAsStream("/data.csv");
            if (inputStream == null){
                throw new RuntimeException("stream is empty");
            }
        try ( InputStreamReader inputStreamReader = new InputStreamReader(inputStream,StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader)){

               String line;
                Set<String> nodes = new HashSet<>();
                line = reader.readLine();
                while (line != null){
                    if (line.trim().isEmpty() || line.startsWith("source")){
                        continue;
                    }
                    String[] places = line.split(",");
                    if (places.length != 3){
                        System.out.println("invalid line format: "+line);
                        continue;
                    }
                   String source = places[0].trim();
                    String goal =places[1].trim();
                    double distance = Double.parseDouble(places[2].trim());
                    if(!nodes.contains(source)){
                        nodeRepository.save(new Node_A(source));
                        nodes.add(source);
                    }
                    if(!nodes.contains(goal)){
                        nodeRepository.save(new Node_A(goal));
                        nodes.add(goal);
                    }
                    // adding route
                    Route route = new Route();
                    route.setSource(source);
                    route.setDestination(goal);
                    route.setDistance(distance);
                    routeRepository.save(route);
                }
             } catch (Exception e) {
            System.out.println(e.getMessage());
          }
    }
}
