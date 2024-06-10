import java.security.InvalidParameterException;
import java.util.Random;

public class Game {
    public boolean IsFinished = false;
    private Random _r = new Random();
    Grid grid;
    int playerNumber;
    int startPos;

    public Game(int n, int start) {
        grid = new Grid();
        grid.FillGrid();
        if (n > 6) throw new InvalidParameterException("Players must be less than 6");
        playerNumber = n;
        startPos = start;
    }

    public void PlayGame(Player[] players) {
        while (!IsFinished) {
            for (int i = 0; i < playerNumber; i++) {
                if (!IsFinished) {
                    MovePlayer(players[(i + startPos) % playerNumber]);
                }
                else break;
            }
        }
    }

    public int Dice() {
        return _r.nextInt(6) + 1;
    }

    public void MovePlayer(Player player) {
        player.Position += Dice();
        if (player.Position > 100)
            player.Position = 100;

        System.out.println(player.Name + " moved to case number " + player.Position);
        Case next = grid.TheGrid[player.Position -1];

        if (next.SendTo < next.Position) {
            System.out.println("This case had a snake hiding on it, " + player.Name + " fell to case number " + next.SendTo);
            player.Position = next.SendTo;
        }
        if (next.SendTo > next.Position) {
            System.out.println("This case had a ladder on it, " + player.Name + " climbed to case number " + next.SendTo);
            player.Position = next.SendTo;
        }
        if (player.Position >= 100) {
            FinishGame(player);
        }
    }

    public void FinishGame(Player winner) {
        IsFinished = true;
        System.out.println(winner.Name + " won the game!");
    }
}
