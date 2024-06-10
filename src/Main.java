import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println();
        System.out.println("Hello, let's play a game of snakes and ladders!");
        System.out.println("How many players will be playing the game?");
        int n;
        Scanner sc = new Scanner(System.in);
        do {
            n = sc.nextInt();
            if (n > 6 || n < 1){
                System.out.println("Please enter a number between 1 and 6");
            }
        }
        while (n > 6 || n < 1);
        System.out.println();

        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        Player player5 = new Player();
        Player player6 = new Player();

        Player[] players = {player1, player2, player3, player4, player5, player6};

        for (int i = 0; i < n; i++) {
            switch (i) {
                case 0: System.out.println("Please enter the first player name."); break;
                case 1: System.out.println("Please enter the second player name."); break;
                case 2: System.out.println("Please enter the third player name."); break;
                case 3: System.out.println("Please enter the fourth player name."); break;
                case 4: System.out.println("Please enter the fifth player name."); break;
                case 5: System.out.println("Please enter the sixth player name."); break;
            }

            do {
                String name = sc.next();
                if (!Objects.equals(name, "Random") && !name.isEmpty()) {
                    players[i].Name = name;
                    players[i].Position = 1;
                }
                else
                    System.out.println("Please enter a name that is different from \"Random\".");
            } while (players[i].Position == -1);
        }
        System.out.println();

        System.out.println("Who will be the first to play?\nYou can enter the name of the player or \"Random\" to randomize the player who start.");
        int startPosition = -1;
        Random r = new Random();
        do {
            String first = sc.next();
            if (first.equals("Random")) {
                startPosition = r.nextInt(n);
            }
            else {
                for (int i = 0; i < n; i++) {
                    if (Objects.equals(first, players[i].Name)) {
                        startPosition = i;
                    }
                }
            }
            if (startPosition == -1){
                System.out.println("Please enter one of the name you gave above or \"Random\".");
            }
        } while (startPosition == -1);

        System.out.println("Ok, " + players[startPosition].Name + " will be the one starting.");
        System.out.println();

        Game game = new Game(n, startPosition, r);
        game.PlayGame(players);
    }
}