package in.vini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vini.binding.LoginForm;
import in.vini.binding.SignUpForm;
import in.vini.binding.savePostForm;
import in.vini.entitiy.PostEntity;
import in.vini.entitiy.UserEntity;
import in.vini.repo.PostRepository;
import in.vini.repo.UserDetailsRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsRepo repo;
	@Autowired
	private PostRepository postRepo;

	@Autowired
	private HttpSession session;

	@Override
	public boolean registerUser(SignUpForm signUpForm) {

		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(signUpForm, entity);
		repo.save(entity);

		return true;
	}

	@Override
	public String loginUser(LoginForm loginForm) {

		UserEntity findByMailAndPassword = repo.findByMailAndPassword(loginForm.getMail(), loginForm.getPassword());

		if (findByMailAndPassword == null) {
			return "invalid credentials";
		}

		session.setAttribute("userId", findByMailAndPassword.getUserId());

		return "success";
	}

	@Override
	public String savePost(savePostForm saveForm) {

		Integer userId = (Integer) session.getAttribute("userId");
		UserEntity userEntity = repo.findById(userId).get();

		PostEntity entity = new PostEntity();

		BeanUtils.copyProperties(saveForm, entity);
		entity.setUser(userEntity);

		postRepo.save(entity);

		return "success";
	}

	@Override
	public List<savePostForm> viewPosts(Integer userId) {

		

		Optional<UserEntity> findById = repo.findById(userId);

		List<savePostForm> postsList = new ArrayList<>();

		if (findById.isPresent()) {
			UserEntity userEntity = findById.get();

			List<PostEntity> postEntities = userEntity.getPosts();
			for (PostEntity postEntity : postEntities) {
				savePostForm post = new savePostForm();
				post.setId(postEntity.getPostId());
				post.setTitle(postEntity.getTitle());
				post.setDescription(postEntity.getDescription());
				post.setContent(postEntity.getContent());
				post.setCreatedDate(postEntity.getCreatedDate());
				postsList.add(post);
			}
		}
		return postsList;
	}

	@Override
	public List<PostEntity> indexPosts() {

		return postRepo.findAll();

	}

	@Override
	public List<PostEntity> contentView(PostEntity postId) {
		// TODO Auto-generated method stub
		return null;
	}

}
