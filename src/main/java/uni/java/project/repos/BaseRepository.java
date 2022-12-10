package uni.java.project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BaseRepository<TEntity> extends JpaRepository<TEntity, Integer> {

}
