package graphium.graphiumteam8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Integer id;

    private String fileName;

    @Lob // Allows file object storage in table
    private byte[] fileObject;

    @ManyToOne // todo: check this line (probably not working due to database restrictions)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private FileAccessType accessType = FileAccessType.PRIVATE;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<FileView> views;

    public enum FileAccessType {
        PRIVATE, PUBLIC, PARTNER, ORGANISATION
    }
}
