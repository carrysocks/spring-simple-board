package CrudBoard.board.service;

import CrudBoard.board.domain.Member;
import CrudBoard.board.domain.Post;
import CrudBoard.board.repository.MemberRepository;
import CrudBoard.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    
    /** 글 작성 **/
    @Transactional
    public Long post(Long memberId, String title, String content){
        Member member = memberRepository.findOne(memberId);
        Post post = Post.createPost(member, title, content);
        postRepository.save(post);
        return post.getId();
    }

    /** 글 삭제 **/
    @Transactional
    public void deletePost(Long postId){
        Post post = postRepository.findOne(postId);
        postRepository.delete(post);
        post.deletePost();
    }

    /** 글 검색 **/
    public List<Post> findPosts(String title, String status){
        return postRepository.findPostsBySearch(title, status);
    }

    public Post findOne(Long postId){
        return postRepository.findOne(postId);
    }

    public List<Post> findPostsAll(){
        return postRepository.findAllPosts();
    }
}
