package chess;

public class Knight extends Chessman {
    
    Knight(ChessBoard boardRef,boolean color, int row, int column,boolean moved) { //konstruktor pro nacteni nove hry/hry ze souboru
        super(boardRef, color, row, column, moved);
    }
    Knight(Chessman copy, int column, int row) {  //konstruktor k presouvani po sachovnici
        super();
        this.color = copy.color;
        this.row = row;
        this.column = column;
        this.ref = copy.ref;
    }
    @Override public boolean inRange(int column, int row) { //vrati true, pokud je pole [row][column] v dosahu figury
        if (Math.abs(column - this.column) == 2 && Math.abs(row - this.row) == 1) {             
                return true;
            }
        if (Math.abs(row - this.row) == 2 && Math.abs(column - this.column) == 1) {
            return true;
        }
        return false;
    }
    @Override public boolean move(boolean really,int column, int row){ //vrati true, pokud je tah na pole [column][row] mozny. pokud je navic argument really = true, tah se i provede
        if(inRange(column, row)&& ref.isNotMine(column, row)){
            Knight backUp;
            Chessman ten;
            backUp = this;
            ten = ref.board[column][row];
            ref.board[column][row] = new Knight(this,column,row);
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
    @Override public String toString() {
        if (this.color == true) {
            return "K";
        }
        else {
            return "k";
        }
    }
}
