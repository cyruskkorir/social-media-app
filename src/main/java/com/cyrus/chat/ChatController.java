package com.cyrus.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;






@Controller
public class ChatController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(User user ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        List<Post> list = postRepository.findAll();
        model.addAttribute("posts", list);
        model.addAttribute("post", new Post());
        return "post";
    }
    @GetMapping("/posts/{id}")
    public String getPost(Model model) {
        return "post";
    }
    @PostMapping("/posts")
    public String postStatus(Post post) {
        postRepository.save(post);
        return "redirect:/posts";
    } 
}
