import java.security.InvalidParameterException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public boolean IsFinished = false;
    private Random r;
    Grid grid;
    int playerNumber;
    int startPos;
    Scanner sc;
    Player[] players;

    public Game(int n, int start,Player[] p, Random random, Scanner scanner) {
        grid = new Grid(random);
        grid.FillGrid();
        System.out.println("This is the grid you will be playing on :");
        grid.PrintGrid();
        if (n > 6) throw new InvalidParameterException("Players must be less than 6");
        playerNumber = n;
        startPos = start;
        players = p;
        r = random;
        sc = scanner;
    }

    public void PlayGame() {
        int turnCount = 1;
        while (!IsFinished) {
            System.out.println("--------------------------------------------");
            System.out.println("                  turn " + turnCount);
            System.out.println("--------------------------------------------");
            for (int i = 0; i < playerNumber; i++) {
                if (!IsFinished) {
                    Player player = players[(i + startPos) % playerNumber];
                    if (player.Canplay == 0){
                        MovePlayer(player, players);
                        if (player.Position == 100){
                            FinishGame(player, turnCount);
                        }
                    }
                    else {
                        player.Canplay -= 1;
                        if (player.Canplay > 1){
                            System.out.println(player.Name + " isn't allowed to play this turn. He still have " + player.Canplay + " more turns of detention.");
                        }
                        if (player.Canplay == 1){
                            System.out.println(player.Name + " isn't allowed to play this turn. He still have " + player.Canplay + " more turn of detention.");
                        }
                        if (player.Canplay == 0){
                            System.out.println(player.Name + " isn't allowed to play this turn. He will be able to play next turn.");
                        }
                    }
                    System.out.println();
                }
                else break;
            }
            turnCount++;
        }
    }

    public int Dice() {
        return r.nextInt(6) + 1;
    }

    public void MovePlayer(Player player, Player[] players) {
        int dice = Dice();
        player.Position += dice;
        System.out.println(player.Name + " rolled a dice and got " + dice);
        if (player.Position > 100)
            player.Position = 100;

        System.out.println(player.Name + " moved to case number " + player.Position);
        Case next = grid.TheGrid[player.Position -1];

        if (next.SendTo < next.Position) {
            System.out.println("This case has a snake hiding on it, " + player.Name + " fell to case number " + next.SendTo);
            player.Position = next.SendTo;
        }
        if (next.SendTo > next.Position) {
            System.out.println("This case has a ladder on it, " + player.Name + " climbed to case number " + next.SendTo);
            player.Position = next.SendTo;
        }
        if (next.Special == 1) {
            int a = Dice() * Dice();
            int b = Dice() * Dice();
            System.out.println("This case is a question case, " + player.Name + " please answer the following question : " + a  + " + " + b);
            int n = sc.nextInt();
            if (n == a+b) {
                System.out.println("Since he answered the question correctly, " + player.Name + " is allowed to roll the dice once more !");
                MovePlayer(player, players);
            }
            else System.out.println("Sorry, the answer was " + (a+b));
        }
        if (next.Special == 2){
            System.out.println("Too bad, this is the number you must not step on... " + player.Name + " wont be able to play next turn.");
            player.Canplay += 1;
        }
        if (next.Special == 3){
            System.out.println(player.Name + " Stepped on a target case, he can give the name of a player to give him one turn of detention but be careful to spell that player name correctly.");
            String name = sc.next();
            int i = 0;
            while (i < playerNumber && !Objects.equals(players[i].Name, name)){
                i++;
            }
            if (i == playerNumber)
                System.out.println("To bad, either this player dont exist or you misspelled it...");
            else{
                players[i].Canplay += 1;
                System.out.println("Sorry " + players[i].Name + ", you have been targeted by " + player.Name + ".");
            }
        }
    }

    public void FinishGame(Player winner, int count) {
        IsFinished = true;
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println("                  GAME OVER");
        System.out.println("--------------------------------------------\n");
        System.out.println(winner.Name + " won the game!\n");
        System.out.println(count + " turns have been played.\n");
        System.out.println("Final positions are :");
        for (int i = 0; i < playerNumber; i++) {
            System.out.println(players[i].Name + ": " + players[i].Position);
        }
        System.out.println("\nThank you for playing!");
    }
}
