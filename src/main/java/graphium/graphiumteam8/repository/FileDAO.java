package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.File;
import org.springframework.data.repository.CrudRepository;

// File DAO (Data Access Object) provides CrudRepository, which has built-in methods for a CRUD application

public interface FileDAO extends CrudRepository<File, Long> {

}
