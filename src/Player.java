public class Player {
    public String Name;
    public int Position;

    public Player(String name) {
        this.Name = name;
        Position = 1;
    }

    public Player(){
        Position = -1;
        this.Name = "";
    }
}
