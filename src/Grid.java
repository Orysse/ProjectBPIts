public class Grid {
    public Case[] TheGrid;
    public int Size;

    public Grid() {
        Size = 100;
        TheGrid = new Case[Size];
    }

    public void FillGrid() {
        for (int i = 0; i < Size; i++) {
            switch (i) {
                case 2: TheGrid[i] = new Case(23,i); break;
                case 8: TheGrid[i] = new Case(34,i); break;
                case 20: TheGrid[i] = new Case(77,i); break;
                case 29: TheGrid[i] = new Case(9,i); break;
                case 32: TheGrid[i] = new Case(68,i); break;
                case 38: TheGrid[i] = new Case(15,i); break;
                case 41: TheGrid[i] = new Case(79,i); break;
                case 47: TheGrid[i] = new Case(5,i); break;
                case 74: TheGrid[i] = new Case(88,i); break;
                case 82: TheGrid[i] = new Case(100,i); break;
                case 85: TheGrid[i] = new Case(95,i); break;
                case 86: TheGrid[i] = new Case(54,i); break;
                case 93: TheGrid[i] = new Case(70,i); break;
                case 97: TheGrid[i] = new Case(25,i); break;
                default: TheGrid[i] = new Case(i,i);
            }
        }
    }
}
