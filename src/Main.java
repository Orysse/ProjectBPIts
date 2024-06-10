import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
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

            players[i].Name = sc.next();
            players[i].Position = 1;
        }

        System.out.println("Who will be the first to play?");
        int startPosition = -1;
        do {
            String first = sc.next();
            for (int i = 0; i < n; i++) {
                if (Objects.equals(first, players[i].Name)){
                    startPosition = i;
                }
            }
            if (startPosition == -1){
                System.out.println("Please enter one of the name you gave above.");
            }
        } while (startPosition == -1);


        Game game = new Game(n, startPosition);
        game.PlayGame(players);
    }
}