package _four_java_assessment._four_java_assessment.controller;

import _four_java_assessment._four_java_assessment.models.Node_A;
import _four_java_assessment._four_java_assessment.repos.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/_nodes")
public class NodeController {
    @Autowired
    private NodeRepository nodeRepository;
    @GetMapping
    public List<Node_A> findAllNodes(){
        return nodeRepository.findAll();
    }
    @PostMapping
    public Node_A  createNode(@RequestBody Node_A nodeA){
        return nodeRepository.save(nodeA);
    }
    @GetMapping("/{id}")
    public Node_A getNode(@PathVariable String id){
        return nodeRepository.findById(id).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void deleteNode(@PathVariable String id){
        nodeRepository.deleteById(id);
    }
}
