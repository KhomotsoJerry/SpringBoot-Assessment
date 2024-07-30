package _four_java_assessment._four_java_assessment.controller;

import _four_java_assessment._four_java_assessment.models.Route;
import _four_java_assessment._four_java_assessment.repos.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/routes")
public class RouteController {
    @Autowired
    private RouteRepository routeRepository;
    @GetMapping
    public List<Route> findAllRoutes(){
        return routeRepository.findAll();
    }
    @PostMapping
    public Route  createRoute(@RequestBody Route route){
        return routeRepository.save(route);
    }
    @GetMapping("/{id}")
    public Route getNode(@PathVariable Long id){
        return routeRepository.findById(id).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void deleteNode(@PathVariable Long id){
        routeRepository.deleteById(id);
    }
}
