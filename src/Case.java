public class Case {
    public int SendTo;
    public int Position;

    public Case(int SendTo, int Position) {
        this.SendTo = SendTo;
        this.Position = Position;
    }

    public int getSendTo() {
        return SendTo;
    }
}
