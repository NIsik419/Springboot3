import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.el.parser.Token;
import org.example.springbootdeveloper.config.jwt.TokenProvider;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFillter extends OncePerRequestFilter{
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION="Authorization";
    private final static String TOKEN_PREFIX="Bearer";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletRequest response,
            FilterChain filterChain) throws ServletException, IOException {
        //요청 헤더의 AUthorization 키의 값조회
        String authorizationHeader=request.getHeader(HEADER_AUTHORIZATION);
        // 가져온 값에서 접두사 제거
        String token = getAccessToken(authorizationHeader);

    }
}