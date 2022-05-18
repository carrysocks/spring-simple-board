package CrudBoard.board.controller;

import CrudBoard.board.domain.Comment;
import CrudBoard.board.domain.Member;
import CrudBoard.board.domain.Post;
import CrudBoard.board.domain.PostSearch;
import CrudBoard.board.service.CommentService;
import CrudBoard.board.service.MemberService;
import CrudBoard.board.service.PostService;
import CrudBoard.board.web.CommentForm;
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
    private final CommentService commentService;

    /**
     * 게시글 나열
     */

    @GetMapping(value = "/posts")
    public String list(@ModelAttribute("postSearch") PostSearch postSearch, Model model){
        List<Post> posts = postService.findPosts(postSearch.getInputString(), postSearch.getSearchStatus());
        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    /**
     * 게시글 작성 폼
     */

    @GetMapping(value = "/posts/new")
    public String createForm(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        model.addAttribute("postForm",new PostForm());
        return "posts/createPostForm";
    }

    /**
     * 게시글 작성
     */

    @PostMapping(value = "/posts/new")
    public String create(@RequestParam("memberId") Long memberId, @Valid PostForm form, BindingResult result){
        if(result.hasErrors()){
            return "posts/new";
        }

        postService.post(memberId, form.getTitle(), form.getContent());
        return "redirect:/";
    }

    /**
     * 게시글 삭제
     */

    @PostMapping(value = "/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        System.out.println("delete post!");
        postService.deletePost(postId);
        return "redirect:/posts";
    }

    /**
     * 게시글 및 댓글 상세보기
     */
    @GetMapping(value = "/posts/post/{postId}")
    public String getPost(@PathVariable("postId") Long postId, Model model){
        Post post = postService.findOne(postId);
        List<Member> members = memberService.findAll();
        List<Comment> comments = commentService.findCommentsByPostId(post.getId());
        model.addAttribute("members", members);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("commentForm",new CommentForm());
        return "posts/post.html";
    }

    /**
     * 댓글 달기
     */
    @PostMapping(value = "/posts/post/{postId}")
    public String createComment(@RequestParam("memberId") Long memberId, @PathVariable("postId") Long postId, @Valid CommentForm commentForm, BindingResult result){

        if(result.hasErrors()){
            return "/posts/post/{postId}";
        }
        commentService.create(memberId, postId, commentForm.getContent());
        return "redirect:/posts/post/{postId}";
    }
}
