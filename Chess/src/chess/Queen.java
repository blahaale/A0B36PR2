package chess;

public class Queen extends Chessman {
    
    Queen(ChessBoard boardRef,boolean color, int row, int column,boolean moved) {//konstruktor pro nacteni nove hry/hry ze souboru
        super(boardRef, color, row, column, moved);
    }
    Queen(Chessman copy, int column, int row) {//konstruktor k presouvani po sachovnici
        super();
        this.color = copy.color;
        this.row = row;
        this.column = column;
        this.ref = copy.ref;
    }
    @Override public boolean move(boolean really,int column, int row){//vrati true, pokud je tah na pole [column][row] mozny. pokud je navic argument really = true, tah se i provede
        if(inRange(column, row)&& ref.isNotMine(column, row)){
            Queen backUp;
            Chessman ten;
            backUp = this;
            ten = ref.board[column][row];
            ref.board[column][row] = new Queen(this,column,row);
            ref.board[this.column][this.row] = null;
            //ref.print();
            boolean b = ref.isChecked();
            if(really) {
                if(b) {
                    ref.board[backUp.column][backUp.row] = backUp;
                    ref.board[column][row] = ten;
                    //System.out.println("vracim false bo by to byl sach");
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
                //System.out.println("jen zjistuju, nehejbu");
                return !b;
            }
        }
        else {
            //System.out.println("neni range nebo je moje");
            return false;
        }
    }
    @Override public boolean inRange(int column, int row) { //vrati true, pokud je pole [row][column] v dosahu figury
        
        if(column == this.column) {
            if(row > this.row) {
                for(int i = 1;i < row-this.row; i++) {
                    if(!ref.isFree(this.column, this.row+i)) {
                        return false;
                    }
                }
                return true;
            }
            if(row < this.row) {
                for(int i = 1;i < this.row-row; i++) {
                    if(!ref.isFree(this.column, this.row-i)) {
                        return false;
                    }
                }
                return true;
            }
        }
        if(row == this.row) {
            if(column > this.column) {
                for(int i = 1;i < column-this.column; i++) {
                    //System.out.println("i = " +i +"...do: " + (column-this.column-1) + " ....momentalne testuju: " + (this.column+i) + " " + this.row );
                    if(!ref.isFree(this.column+i, this.row)) {
                        return false;
                    }
                }
                return true;
            }
            if(column < this.column) {
                for(int i = 1;i < this.column-column; i++) {
                    if(!ref.isFree(this.column-i, this.row)) {
                        return false;
                    }
                }
                return true;
            }
        }
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
            return "Q";
        }
        else {
            return "q";
        }
    }
    
}
