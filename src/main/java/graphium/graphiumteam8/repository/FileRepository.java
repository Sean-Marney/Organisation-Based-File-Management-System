package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// File DAO (Data Access Object) provides JPARepository, which has built-in methods for a CRUD application

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

    // Finds entry using keywords 'findBy' and then will find that column in the database
    File findByFileName(String fileName);

    // Finder method - JPA deals with the SQL so no query is required (implemented in service class)
    List<File> findAll(); // Get all records from file table

}
