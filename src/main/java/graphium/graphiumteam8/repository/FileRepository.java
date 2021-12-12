package graphium.graphiumteam8.repository;

import graphium.graphiumteam8.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

    Optional<File> findByFileName(String fileName);

    List<File> findAllByAccessType(File.FileAccessType accessType);

}
