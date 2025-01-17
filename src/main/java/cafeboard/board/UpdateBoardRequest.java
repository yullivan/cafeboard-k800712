package cafeboard.board;

public class UpdateBoardRequest {
    private String name;

    public UpdateBoardRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
