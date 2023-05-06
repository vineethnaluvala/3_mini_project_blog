package in.vini.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.vini.binding.LoginForm;
import in.vini.binding.SignUpForm;
import in.vini.binding.savePostForm;
import in.vini.entitiy.PostEntity;
import in.vini.service.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
	private HttpSession session;

	@Autowired
	private UserServiceImpl service;

	@GetMapping("/")
	public String indexPage(Model model) {
		
		List<PostEntity> indexPosts = service.indexPosts();

		model.addAttribute("msg", indexPosts);
		return "index";
	}

	@GetMapping("/signup")
	public String registerPage(Model model) {

		model.addAttribute("user", new SignUpForm());
		return "signup";

	}

	@PostMapping("/signup")
	public String register(@ModelAttribute("user") SignUpForm form, Model model) {

		boolean status = service.registerUser(form);
		if (status) {
			model.addAttribute("msg", "account created successfully");
		} else {
			model.addAttribute("errmsg", "failed to create account");
		}

		return "signup";

	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("login", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("login") LoginForm loginForm, Model model) {

		String loginUser = service.loginUser(loginForm);

		if (loginUser.contains("success")) {
			return "redirect:/viewposts";
		}
		model.addAttribute("errmsg", loginUser);

		return "login";
	}

	@GetMapping("/viewposts")
	public String viewPosts(Model model) {

		Integer user = (Integer) session.getAttribute("userId");

		List<savePostForm> viewPosts = service.viewPosts(user);

		model.addAttribute("viewposts", viewPosts);

		return "viewposts";
	}

	@GetMapping("/content")
	public String viewContent(Model model) {

		List<PostEntity> indexPosts = service.indexPosts();

		model.addAttribute("msg", indexPosts);
		
		return "content";
	}

	@GetMapping("/comments")
	public String postComments(Model model) {

		return "comments";
	}

	@GetMapping("/createpost")
	public String newPostPage(Model model) {
		model.addAttribute("form", new savePostForm());
		return "createpost";
	}

	@PostMapping("/createpost")
	public String newPost(@ModelAttribute("form") savePostForm saveForm, Model model) {

		Integer user = (Integer) session.getAttribute("userId");

		String savePost = service.savePost(saveForm);

		if (savePost.contains("success")) {
			model.addAttribute("msg", "post saved succesfully");
		} else {
			model.addAttribute("errMsg", "error occured while posting...");
		}

		return "createpost";
	}

}
