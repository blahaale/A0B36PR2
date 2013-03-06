package chess;

import java.util.Scanner;

public class Pawn extends Chessman {
    
    
    Pawn(ChessBoard boardRef,boolean color, int row, int column,boolean moved) { //konstruktor pro nacteni nove hry/hry ze souboru
        super(boardRef, color, row, column,moved);
    }
    Pawn(Chessman copy, int column, int row) {//konstruktor k presouvani po sachovnici
        super();
        this.color = copy.color;
        this.row = row;
        this.column = column;
        this.ref = copy.ref;
    }
    @Override public boolean move(boolean really,int column, int row){ //vrati true, pokud je tah na pole [column][row] mozny. pokud je navic argument really = true, tah se i provede
        if(inRange(column, row)&& ref.isNotMine(column, row)){
            Pawn backUp;
            Chessman ten;
            backUp = this;
            ten = ref.board[column][row];
            if(row == 7 || row == 0) {
                Scanner inp = new Scanner(System.in);
                String piece;
                System.out.println("Povyseni pesce.");
                System.out.println("Zadej figurku, na kterou chces povysit.");
                boolean a = true;
                while(a) {
                    piece = inp.nextLine();
                    switch(piece) {
                        case "queen":
                            ref.board[column][row] = new Queen(this,column,row);
                            a = false;
                            break;
                        case "bishop":
                            ref.board[column][row] = new Bishop(this,column,row);
                            a = false;
                            break;
                        case "rook":
                            ref.board[column][row] = new Rook(this,column,row);
                            a = false;
                            break;    
                        case "knight":
                            ref.board[column][row] = new Knight(this,column,row);
                            a = false;
                            break;    
                        default:
                            System.out.println("Chybny prikaz, opakuj.");
                    }
                }
            }
            else {
                ref.board[column][row] = new Pawn(this,column,row);
            }
            
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
                //System.out.println("pr " +!b);
                return !b;
            }
        }
        else {
            //System.out.println("vracim false");
            return false;
        }
    }
    @Override public boolean inRange(int column, int row) { //vrati true, pokud je pole [row][column] v dosahu figury
        if (this.color == true) {
            //System.out.println("otrokar");
            //System.out.println("oki");
            if((this.column == column) && (this.ref.isFree(column,row)) && (row == this.row + 1)) { //jdu bilym dopredu
                //System.out.println("ok1");
                return true;
            }
            if((this.column == column) && (this.ref.isFree(column,row)) && (this.ref.isFree(this.column,this.row+1)) && (row == this.row + 2) && (this.row == 1)) { //jdu bilym dopredu
                //System.out.println("ok2");
                return true;
            }
            //System.out.println(this.ref.isEnemy(column, row));
            if((Math.abs(this.column - column) == 1) && (this.ref.isEnemy(this.color,column, row)) && (row-1 == this.row)) {
                //System.out.println("ok3");
                return true;
            }
        }
        else {
            //System.out.println("negr");
            if((this.column == column) && (this.ref.isFree(column,row)) && (row == this.row - 1)) { //jdu cernym dopredu
                return true;
            }
            if((this.column == column) && (this.ref.isFree(column,row)) && (this.ref.isFree(this.column,this.row-1)) && (row == this.row - 2) && (this.row == 6)) { //jdu bilym dopredu
                return true;
            }
            //System.out.println(this.ref.isEnemy(column, row));
            if((Math.abs(this.column - column) == 1) && (this.ref.isEnemy(this.color,column, row)) && (row+1 == this.row)) {
                return true;
            }
            
        }
        return false;
    }
    @Override public String toString() {
        if (this.color == true) {
            return "P";
        }
        else {
            return "p";
        }
    }
}
