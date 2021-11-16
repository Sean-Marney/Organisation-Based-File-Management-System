package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// File DAO (Data Access Object) provides CrudRepository, which has built-in methods for a CRUD application

@Repository
public interface FileDAO extends CrudRepository<File, Long> {

    // Finds entry using keywords 'findBy' and then will find that column in the database
    File findByFileName(String fileName);
}
