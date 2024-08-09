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
    // @GetMapping("/login")
    // public String showLoginPage(Model model){
    //     model.addAttribute("login", new Login());
    //     return "login";
    // }
    // @PostMapping("/login")
    // public String postMethodName(Login login) {
    //     Long id = login.getId();
    //     Optional<User> user = userRepository.findById(id);
    //     if (user.isPresent()) {
    //         User user2 = user.get();
    //         if(passwordEncoder.matches(login.getPassword(), user2.getPassword()) && user2.getEmail().equals(login.getEmail())){
    //             return "/posts";
    //         }
    //     }
        
    //     return "redirect:/login";
    // }
    

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
