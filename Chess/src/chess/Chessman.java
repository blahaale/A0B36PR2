package chess;

abstract public class Chessman { //trida obecne figurky, ze ktere dedi vsechny figurky
    protected int column,row; //pozice na sachovnici
    protected boolean color, moved = false; //hrac, kteremu figurka patri; true = bily
    protected ChessBoard ref; //reference na sachovnici, na ktere se figurka nachazi
    
    Chessman(ChessBoard boardRef,boolean color, int column, int row, boolean moved) { //konstruktor
        this.color = color;
        this.column = column;
        this.row = row;
        this.ref = boardRef;
        this.moved = moved;
    }
    Chessman() { //defaultni konstruktor
        super();
    }
    public boolean isDraw() {
        return false;
    }
    public boolean isCheckmate() {
        return false;
    }
    public abstract boolean move(boolean really,int column, int row); //provede tah na policko [column,row], nebo vrati false, pokud tah neni mozny, pokud je really = false, tah se neprovede, pozue se zjisti, zda-li je mozny
    public abstract boolean inRange(int column, int row);
    @Override public abstract String toString();
    public int getColumn() {
        return this.column;
    }
    public int getRow() {
        return this.row;
    }
    public boolean getPlayer() {
        return this.color;
    }
    public boolean getMoved(){
        return this.moved;
    }
    
}
