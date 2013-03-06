package chess;

public class Bishop extends Chessman {
    
    Bishop(ChessBoard boardRef,boolean color, int row, int column,boolean moved) {//konstruktor pro nacteni nove hry/hry ze souboru
        super(boardRef, color, row, column,moved);
    }
    Bishop(Chessman copy, int column, int row) {//konstruktor k presouvani po sachovnici
        super();
        this.color = copy.color;
        this.row = row;
        this.column = column;
        this.ref = copy.ref;
    }
    @Override public boolean move(boolean really,int column, int row){//vrati true, pokud je tah na pole [column][row] mozny. pokud je navic argument really = true, tah se i provede
        if(inRange(column, row)&& ref.isNotMine(column, row)){
            Bishop backUp;
            Chessman ten;
            backUp = this;
            ten = ref.board[column][row];
            ref.board[column][row] = new Bishop(this,column,row);
            ref.board[this.column][this.row] = null;
            //ref.print();
            boolean b = ref.isChecked();
            if(really) {
                if(b) {
                    ref.board[backUp.column][backUp.row] = backUp;
                    ref.board[column][row] = ten;
                    //System.out.println("vracim false tudyma");
                    return false;
                }
                else {
                    //System.out.println("ok, vracim true");
                    ref.board[column][row].moved = true;
                    return true;
                }
            }
            else{
                ref.board[backUp.column][backUp.row] = backUp;
                ref.board[column][row] = ten;
                return !b;
            }
        }
        else {
            //System.out.println("vracim false");
            return false;
        }
    }
    @Override public boolean inRange(int column, int row) { //vrati true, pokud je pole [row][column] v dosahu figury
        if(Math.abs(this.column - column) == Math.abs(this.row - row)) {
            //System.out.println("ok");
            if(this.column < column && this.row < row) {
                for(int i = 1; i < (column - this.column);i++) {
                    //System.out.println(i);
                    if(!ref.isFree(this.column+i, this.row+i)) {
                        return false;
                    }
                }
                return true;
            }
            if(this.column > column && this.row > row) {
                for(int i = 1; i < (this.column - column);i++) {
                    //System.out.println(i);
                    if(!ref.isFree(this.column-i, this.row-i)) {
                        return false;
                    }
                }
                return true;
            }
            if(this.column < column && this.row > row) {
                for(int i = 1; i < (column - this.column);i++) {
                    //System.out.println(i);
                    if(!ref.isFree(this.column+i, this.row-i)) {
                        return false;
                    }
                }
                return true;
            }
            if(this.column > column && this.row < row) {
                for(int i = 1; i < (this.column - column);i++) {
                    //System.out.println(i);
                    if(!ref.isFree(this.column-i, this.row+i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    @Override public String toString() {
        if (this.color == true) {
            return "B";
        }
        else {
            return "b";
        }
    }
    
}
