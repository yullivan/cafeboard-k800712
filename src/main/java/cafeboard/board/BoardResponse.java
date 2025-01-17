package cafeboard.board;

import java.time.LocalDateTime;

public record BoardResponse(
        Long id,
        String name,
        String description,
        int postCount,
        LocalDateTime createdAt, // 생성 시간 추가
        LocalDateTime updatedAt   // 업데이트 시간 추가
) {
    public static BoardResponse from(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getName(),
                board.getDescription(),
                board.getPostCount(),
                board.getCreatedAt(), // 생성 시간 추가
                board.getUpdatedAt()  // 업데이트 시간 추가
        );
    }
}
