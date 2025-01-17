package cafeboard.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.posts")
    List<Board> findAllWithPosts();
}

