package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.File;
import org.springframework.data.repository.CrudRepository;

public interface FileDAO extends CrudRepository<File, Long> {
    
}
