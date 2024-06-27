public class Case {
    public int SendTo;
    public int Position;

    //Special = 0 => nothing special
    //Special = 1 => question case
    //Special = 2 => forbidden case
    public int Special;

    public Case(int SendTo, int Position, int Special) {
        this.SendTo = SendTo;
        this.Position = Position;
        this.Special = Special;
    }
}
