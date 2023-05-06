package in.vini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vini.entitiy.UserEntity;

public interface UserDetailsRepo extends JpaRepository<UserEntity, Integer> {

	public UserEntity findByMailAndPassword(String mail,String password); 
}
