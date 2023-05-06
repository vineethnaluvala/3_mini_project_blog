package in.vini.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.vini.entitiy.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{

}
