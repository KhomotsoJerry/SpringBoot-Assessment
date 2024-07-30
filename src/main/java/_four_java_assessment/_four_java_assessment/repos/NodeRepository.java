package _four_java_assessment._four_java_assessment.repos;

import _four_java_assessment._four_java_assessment.models.Node_A;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<Node_A,String> {
}
