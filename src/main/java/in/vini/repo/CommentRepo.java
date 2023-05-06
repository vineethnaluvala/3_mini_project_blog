package in.vini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vini.entitiy.CommentEntity;

public interface CommentRepo extends JpaRepository<CommentEntity, Integer>{

}
