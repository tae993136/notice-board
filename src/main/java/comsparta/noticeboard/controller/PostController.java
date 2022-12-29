package comsparta.noticeboard.controller;

import comsparta.noticeboard.dto.PostRequestDto;
import comsparta.noticeboard.dto.PostResponseDto;
import comsparta.noticeboard.service.PostService;
import comsparta.noticeboard.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    //전체 게시글 목록 조회
    @GetMapping("/posts")
    public List<PostResponseDto> totalPostListResponseDto() {
        return postService.getPostList();
    }

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request){
        return postService.createPost(postRequestDto, request);
    }

    @GetMapping("/posts/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }

    @PutMapping("/posts/{postId}")
    public PostResponseDto updataPost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto, HttpServletRequest request){
        return postService.updatePost(postId, postRequestDto, request);
    }

    @DeleteMapping("/posts/{postId}")
    public PostResponseDto deletePost(@PathVariable Long postId, HttpServletRequest request){
        return postService.deletePost(postId, request);
    }
}
