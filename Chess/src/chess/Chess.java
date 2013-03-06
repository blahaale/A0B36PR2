package chess;
public class Chess {
    public static void main(String[] args) { //hlavni trida s jednoduchou smyckou
        ChessBoard a = new ChessBoard();
        a.newGame();
        a.changePlayer();
        while(a.stillPlaying()) {
            a.print();
            if(a.command()){
                a.changePlayer();
                //System.out.println("endgame");
                a.isEndGame(a.getKing());
            }
                
        }
    }
}
