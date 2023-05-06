package in.vini.service;

import java.util.List;

import in.vini.binding.LoginForm;
import in.vini.binding.SignUpForm;
import in.vini.binding.savePostForm;
import in.vini.entitiy.PostEntity;

public interface UserService {

	public boolean registerUser(SignUpForm signUpForm);

	public String loginUser(LoginForm loginForm);
	
	public String savePost(savePostForm saveForm);
	
	public List<savePostForm> viewPosts(Integer userId);
	
	public List<PostEntity> indexPosts();
	
	public List<PostEntity> contentView(PostEntity postId);
	
}
