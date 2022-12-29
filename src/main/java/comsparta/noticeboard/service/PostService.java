package comsparta.noticeboard.service;

import comsparta.noticeboard.dto.PostRequestDto;
import comsparta.noticeboard.dto.PostResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class PostService {
    private List<PostResponseDto> postList;
    private  PostResponseDto post;

    public PostResponseDto createPost(PostRequestDto postRequestDto, HttpServletRequest request) {
    }



    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto, HttpServletRequest request) {
    }

    public PostResponseDto deletePost(Long postId, HttpServletRequest request) {
    }

    public PostResponseDto getPost(Long postId) {
    }
}
