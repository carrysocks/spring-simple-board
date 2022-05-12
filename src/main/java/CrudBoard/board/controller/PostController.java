package CrudBoard.board.controller;

import CrudBoard.board.domain.Member;
import CrudBoard.board.domain.Post;
import CrudBoard.board.domain.PostSearch;
import CrudBoard.board.service.MemberService;
import CrudBoard.board.service.PostService;
import CrudBoard.board.web.MemberForm;
import CrudBoard.board.web.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    //postSearch.getSearchStatus().name()
    @GetMapping(value = "/posts")
    public String list(@ModelAttribute("postSearch") PostSearch postSearch, Model model){
        List<Post> posts = postService.findPosts(postSearch.getTitle(), postSearch.getTitle());
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping(value = "/posts/new")
    public String createForm(Model model){

        List<Member> members = memberService.findAll();

        model.addAttribute("members", members);
        model.addAttribute("postForm",new PostForm());
        return "posts/createPostForm";
    }

    @PostMapping(value = "/posts/new")
    public String create(@RequestParam("memberId") Long memberId, @Valid PostForm form, BindingResult result){
        if(result.hasErrors()){
            return "posts/new";
        }

        postService.post(memberId, form.getTitle(), form.getContent());
        return "redirect:/";
    }

    @PostMapping(value = "/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        System.out.println("delete post!");
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}
