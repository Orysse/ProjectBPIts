import java.util.Random;

public class Grid {
    public Case[] TheGrid;
    public int Size;
    Random rd;

    public Grid(Random random) {
        Size = 100;
        TheGrid = new Case[Size];
        rd = random;
    }

    public void FillGrid() {
        for (int i = 0; i < Size; i++) {
            switch (i + 1) {
                case 2: TheGrid[i] = new Case(23,i+1,0); break;
                case 8: TheGrid[i] = new Case(34,i+1,0); break;
                case 20: TheGrid[i] = new Case(77,i+1,0); break;
                case 29: TheGrid[i] = new Case(9,i+1,0); break;
                case 32: TheGrid[i] = new Case(68,i+1,0); break;
                case 37: TheGrid[i] = new Case(i+1,i+1,1); break;
                case 38: TheGrid[i] = new Case(15,i+1,0); break;
                case 41: TheGrid[i] = new Case(79,i+1,0); break;
                case 47: TheGrid[i] = new Case(5,i+1,0); break;
                case 73: TheGrid[i] = new Case(i+1,i+1,1); break;
                case 74: TheGrid[i] = new Case(88,i+1,0); break;
                case 82: TheGrid[i] = new Case(100,i+1,0); break;
                case 85: TheGrid[i] = new Case(95,i+1,0); break;
                case 86: TheGrid[i] = new Case(54,i+1,0); break;
                case 93: TheGrid[i] = new Case(70,i+1,0); break;
                case 97: TheGrid[i] = new Case(25,i+1,0); break;
                default: TheGrid[i] = new Case(i + 1,i + 1,0);
            }
        }
        int n;
        for (int i = 0; i < 3; i++) {
            do {
                n = rd.nextInt(100);
            } while (TheGrid[n].SendTo != TheGrid[n].Position && TheGrid[n].Special != 0);
            TheGrid[n].Special = 2;
            System.out.print((n+1));
            if (i < 1) System.out.print(", ");
            if (i < 2) System.out.print("and ");
        }
        System.out.println(" are forbidden numbers, make sure not to step on it.\n");
        System.out.print("Case number ");
        for (int i = 0; i < 3; i++) {
            do {
                n = rd.nextInt(100);
            } while (TheGrid[n].SendTo != TheGrid[n].Position && TheGrid[n].Special != 0);
            TheGrid[n].Special = 3;
            System.out.print((n+1));
            if (i < 1) System.out.print(", ");
            if (i < 2) System.out.print("and ");
        }
        System.out.println(" are target cases.\n");

    }

    public void PrintGrid(){
        System.out.println("-----------------------");
        for (int i = 0; i < 5; i++) {
            System.out.print("| ");
            for (int j = 0; j < 10; j++) {
                Case c = TheGrid[(5-i) * 20 - j - 1];
                printGridSymbol(c);
            }
            System.out.println("|");
            System.out.print("| ");
            for (int j = 0; j < 10; j++) {
                Case c = TheGrid[(4-i) * 20 + j];
                printGridSymbol(c);
            }
            System.out.println("|");
        }
        System.out.println("-----------------------");
        System.out.println("Legend :\nO => Normal case\nH => Ladder\nS => Snake\n? => Question case\nX => Forbidden case\nF => Target case\n");
    }

    public void printGridSymbol(Case c) {
        if (c.Special == 3) {
            System.out.print("F ");
        }
        else if (c.Special == 2) {
            System.out.print("X ");
        }
        else if (c.Special == 1) {
            System.out.print("? ");
        }
        else if (c.SendTo < c.Position) {
            System.out.print("S ");
        }
        else if (c.SendTo > c.Position) {
            System.out.print("H ");
        }
        else System.out.print("O ");
    }
}
