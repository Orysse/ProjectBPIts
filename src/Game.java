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

    public Game() {
        // print a text to welcome the players
        System.out.println();
        System.out.println("Hello, let's play a game of snakes and ladders!");

        r = new Random(startPos);
        sc = new Scanner(System.in);

        playerNumber = getPlayerNumber();
        players = instantiatePlayers();
        getPlayerNames();
        startPos = getPlayerOrder();

        grid = new Grid(r);
        grid.FillGrid();
        System.out.println("This is the grid you will be playing on :");
        grid.PrintGrid();
    }

    private int getPlayerNumber() {// ask for the number of player
        System.out.println("How many players will be playing the game?");
        int n;
        do { // this do-while ensure that the answer is a valid number
            String s = sc.next();
            try { // this try catch make sure it is an integer
                n = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                    n = 0;
            }
            if (n > 6 || n < 1) { // this is a part that ensure the integer is in the correct range
                System.out.println("Please enter a number between 1 and 6");
            }
        }
        while (n > 6 || n < 1); // as long as the answer isn't a correct value the question will be asked
        // again and the user will be able to provide a new answer.
        System.out.println();
        return n;
    }

    private Player[] instantiatePlayers() {
        // as the player class is pretty light, we create the max amount of player beforehand to avoid
        // having to create a bunch of variables when getting different amount of player.
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        Player player5 = new Player();
        Player player6 = new Player();

        // we then put all the players into an array and return it
        return new Player[]{player1, player2, player3, player4, player5, player6};
    }

    private void getPlayerNames() {
        // here we ask for all the players names.
        for (int i = 0; i < playerNumber; i++) { // we loop through the first n player instantiated with n being
            switch (i) {              // the number of player the user ask for
                case 0: System.out.println("Please enter the first player name."); break;
                case 1: System.out.println("Please enter the second player name."); break;
                case 2: System.out.println("Please enter the third player name."); break;
                case 3: System.out.println("Please enter the fourth player name."); break;
                case 4: System.out.println("Please enter the fifth player name."); break;
                case 5: System.out.println("Please enter the sixth player name."); break;
            }// the switch statement is only here for verbose purposes and having better questions

            do { // again here we have a do-while ensuring the name of the player won't be "Random"
                String name = sc.next(); // as it would be in conflict with another function later
                if (!Objects.equals(name, "random") && !name.isEmpty()) {
                    players[i].Name = name; // when the player is given a name, it gets is position
                    players[i].Position = 1;// meaning he can play. if the position is = to -1 the game
                }                           // will throw an error if this object is moved.
                else
                    System.out.println("Please enter a name that is different from \"random\".");
            } while (players[i].Position == -1);
        }
        System.out.println();
    }

    private int getPlayerOrder() {
        //asking the player who will be the first to play
        System.out.println("Who will be the first to play?\nYou can enter the name of the player or \"random\" to randomize the player who start.");
        int startPosition = -1;
        Random r = new Random();// creating a new random that will be passed as a parameter to other
        do {                    // classes.
            String first = sc.next();
            if (first.equals("random")) { // check if the input is "random" and assign a random start
                startPosition = r.nextInt(playerNumber); // number, this number is the position of the player
            }                                 // that start in the array
            else {
                for (int i = 0; i < playerNumber; i++) {
                    if (Objects.equals(first, players[i].Name)) {
                        startPosition = i;
                    } // if a name is given we loop through the player array to find a player with
                }     // that name.
            }
            if (startPosition == -1){ // if the given input is incorrect, the do-while will repeat the question.
                System.out.println("Please enter one of the name you gave above or \"random\".");
            }
        } while (startPosition == -1);

        // prompt a message to tell who will be starting
        System.out.println("Ok, " + players[startPosition].Name + " will be the one starting.");
        System.out.println();

        return startPosition;
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

    private int Dice() {
        return r.nextInt(6) + 1;
    }

    private void MovePlayer(Player player, Player[] players) {
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
            System.out.println("Too bad, this is one of the numbers you must not step on... " + player.Name + " wont be able to play next turn.");
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

    private void FinishGame(Player winner, int count) {
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
