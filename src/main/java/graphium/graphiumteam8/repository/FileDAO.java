package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.File;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// File DAO (Data Access Object) provides CrudRepository, which has built-in methods for a CRUD application

@Repository
public interface FileDAO extends CrudRepository<File, Long> {

    // Finds entry using keywords 'findBy' and then will find that column in the database
    File findByFileName(String fileName);

    // SQL query to search for a file
    @Query(value = "SELECT * FROM files WHERE MATCH (file_name) AGAINST (?1);", // ?1 will be replaced by searchTerm
            nativeQuery = true)
    List<File> fileSearch(String searchTerm);
}
