import java.util.Random;

public class Game {
    public boolean IsFinished = false;
    private Random _r = new Random();
    Grid grid;

    public Game() {
        grid = new Grid();
        grid.FillGrid();
    }

    public void PlayGame(Player player1, Player player2) {

        while (!IsFinished) {
            MovePlayer(player1);
            if (!IsFinished) {
                MovePlayer(player2);
            }
        }
    }

    public int Dice() {
        return _r.nextInt(6) + 1;
    }

    public void MovePlayer(Player player) {
        player.Position += Dice();
        System.out.println(player.name + " moved to " + player.Position);
        Case next = grid.TheGrid[player.Position -1];
        if (next.SendTo < next.Position) {
            System.out.println("This case has a snake, " + player.name + " as fallen to " + next.SendTo);
            player.Position = next.SendTo;
        }
        if (next.SendTo > next.Position) {
            System.out.println("This case has a ladder, " + player.name + " as climbed to " + next.SendTo);
            player.Position = next.SendTo;
        }
        if (player.Position == 100) {
            FinishGame(player);
        }
    }

    public void FinishGame(Player winner) {
        IsFinished = true;
        System.out.println(winner.name + " won the game!");
    }
}
