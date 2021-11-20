package graphium.graphiumteam8.entity;

import lombok.Data;

import javax.persistence.*;

// Entity class is responsible for mapping data to a table in the database (similar to DTO, except DTO is not mapped to a table like entity classes are)

@Entity
@Table(name = "files")
@Data
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_object")
    @Lob // Allows file object storage in table
    private byte[] fileObject;
}
