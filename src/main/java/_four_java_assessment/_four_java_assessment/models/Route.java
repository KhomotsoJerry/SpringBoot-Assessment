package _four_java_assessment._four_java_assessment.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="_route" ,
        uniqueConstraints = {
        @UniqueConstraint(name = "unique-route" , columnNames = "source")
        }
)
public class Route {
    @Id
    @GeneratedValue(generator ="router-sequence",strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "source_id",referencedColumnName = "name")
    @Column(nullable = false)
    private Node_A source;
    @ManyToOne
    @JoinColumn(name = "goal_id",referencedColumnName = "name")
    @Column(nullable = false)
    private Node_A goal;
    private double distance;
}
