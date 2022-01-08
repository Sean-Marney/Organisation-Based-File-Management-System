package graphium.graphiumteam8.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "file_views")
public class FileView {
    @Id
    @GeneratedValue
    private UUID id;

    @CreationTimestamp
    private LocalDateTime last;

    @ManyToOne
    private User user;

}
