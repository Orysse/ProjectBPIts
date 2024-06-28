public class Case {
    public int SendTo;
    public int Position;

    //Special = 0 => nothing special
    //Special = 1 => question case
    //Special = 2 => forbidden case
    //Special = 3 => target case
    public int Special;

    public Case(int sendTo, int position, int special) {
        SendTo = sendTo;
        Position = position;
        Special = special;
    }
}
