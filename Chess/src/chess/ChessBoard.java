package chess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ChessBoard { //trida reprezentujici sachovnici
    protected Chessman[][] board = new Chessman[8][8]; //pole figurek
    protected char[][] fields = new char[8][8]; //pole, ve kterem jsou ulozeny barvy jednotlivych hracich policek
    protected Chessman whiteKing, blackKing; //reference na krale obou barev, aby nemusely byt kazdy tah hledani
    protected boolean stillPlaying = true, player;
    protected int castling = 0b1111; //kratka bila +1, dlouha bila +2, kratka cerna +4, dlouha cerna +8
    
    public boolean isFree(int column, int row) { //vrati true, pokud je policko [column, row] prazdne
        //System.out.println(column + " @ "+row);
        if(board[column][row] == null) {
            //System.out.println("true");
            return true;
        }
        else {
            //System.out.println("false");
            return false;
        }
    }
    public boolean isEnemy(boolean color,int column, int row) { //vrati true, pokud je na policku [column, row] souperova figurka vuci color
        if(board[column][row] != null && board[column][row].getPlayer() != color) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isNotMine(int column, int row) { //vrati true, pokud je zadane policko prazdne nebo je na nem souperova figurka
        if(isEnemy(player,column, row) || isFree(column, row)) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean castling(boolean type) { //vrati false, pokud rosada type nebyla mozna;type = true pro kratkou
        //int ij = 0;
        if(player){
            if(type){
                if(board[4][0] != null && board[4][0] instanceof King && !board[4][0].moved && board[7][0] != null && board[7][0] instanceof Rook && !board[7][0].moved && isFree(5,0) && isFree(6,0)){
                    for(int i=0;i<=3;i++) {
                        for (int _row = 0; _row < 8; _row++) { //projde celou sachovnici...
                            for (int _column = 0; _column < 8; _column++) {
                                //ij++;
                                //System.out.println("sem tu?" + ij);
                                //System.out.println("sem tu?" + ij + "  " + i);
                                if ((this.board[_column][_row] != null) && (this.board[_column][_row].getPlayer() != this.player)) {
                                    boolean a = this.board[_column][_row].inRange(i+4, 0);
                                    if(a) {
                                        //System.out.println("vracim flase");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                    this.board[6][0] = new King(this,true,6,0,true);
                    this.board[5][0] = new Rook(this,true,5,0,true);
                    this.board[4][0] = null;
                    this.board[7][0] = null;
                }
                else {
                    return false;
                }
            }
            else{
                if(board[4][0] != null && board[4][0] instanceof King && !board[4][0].moved && board[0][0] != null && board[0][0] instanceof Rook && !board[0][0].moved && isFree(1,0) && isFree(2,0) && isFree(3,0)){
                    for(int i=0;i<=4;i++) {
                        for (int _row = 0; _row < 8; _row++) { //projde celou sachovnici...
                            for (int _column = 0; _column < 8; _column++) {
                                //ij++;
                                //System.out.println("sem tu?" + ij + "  " + i);
                                if ((this.board[_column][_row] != null) && (this.board[_column][_row].getPlayer() != this.player)) {
                                    boolean a = this.board[_column][_row].inRange(i, 0);
                                    if(a) {
                                        //System.out.println("vracim flase");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                    this.board[2][0] = new King(this,true,2,0,true);
                    this.board[3][0] = new Rook(this,true,3,0,true);
                    this.board[4][0] = null;
                    this.board[0][0] = null;
                }
                else {
                    return false;
                }
            }
        }
        else {
            if(type){
                if(board[4][7] != null && board[4][7] instanceof King && !board[4][7].moved && board[7][7] != null && board[7][7] instanceof Rook && !board[7][7].moved && isFree(5,7) && isFree(6,7)){
                    for(int i=0;i<=3;i++) {
                        for (int _row = 0; _row < 8; _row++) { //projde celou sachovnici...
                            for (int _column = 0; _column < 8; _column++) {
                                //ij++;
                                //System.out.println("sem tu?" + ij);
                                //System.out.println("sem tu?" + ij + "  " + i);
                                if ((this.board[_column][_row] != null) && (this.board[_column][_row].getPlayer() != this.player)) {
                                    boolean a = this.board[_column][_row].inRange(i+4, 7);
                                    //System.out.println("muze " + this.board[_column][_row].toString() + " Z " + _column + " " + _row + " na " + (i+4) + " 7?? - " + a);
                                    if(a) {
                                        //System.out.println("vracim flase");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                    this.board[6][7] = new King(this,true,6,7,true);
                    this.board[5][7] = new Rook(this,true,5,7,true);
                    this.board[4][7] = null;
                    this.board[7][7] = null;
                }
                else {
                    return false;
                }
            }
            else{
                if(board[4][7] != null && board[4][7] instanceof King && !board[4][7].moved && board[0][7] != null && board[0][7] instanceof Rook && !board[0][7].moved && isFree(1,7) && isFree(2,7) && isFree(3,7)){
                    for(int i=0;i<=4;i++) {
                        for (int _row = 0; _row < 8; _row++) { //projde celou sachovnici...
                            for (int _column = 0; _column < 8; _column++) {
                                //ij++;
                                //System.out.println("sem tu?" + ij);
                                //System.out.println("sem tu?" + ij + "  " + i);
                                if ((this.board[_column][_row] != null) && (this.board[_column][_row].getPlayer() != this.player)) {
                                    boolean a = this.board[_column][_row].inRange(i, 7);
                                    if(a) {
                                        //System.out.println("vracim flase");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                    this.board[2][7] = new King(this,true,2,7,true);
                    this.board[3][7] = new Rook(this,true,3,7,true);
                    this.board[4][7] = null;
                    this.board[0][7] = null;
                }
                else {
                    return false;
                }
            }
               
        }
        //System.out.println("vracim nic nez pravdu");
        return true;
    }
    public boolean command() { //nacte prikaz z klavesnice a pokud je mozny, provede ho a vrati true
        Scanner inp = new Scanner(System.in);
        if(player) {
            System.out.println("Hraje bily.");
        }
        else {
            System.out.println("Hraje cerny.");
        }
        System.out.println("Zadej prikaz:");
        String rawCommand = inp.nextLine();//save, load, new,0-0,0-0-0
        switch(rawCommand) {
            case "quit":
                System.exit(1); // prikaz quit
            case "save":
                this.save(); //prikaz save
                return false;
            case "load":
                this.load(); //prikaz load
                return true;
            case "new":
                return false; //nova hra
            case "0-0":
                boolean b = this.castling(true); //prikaz rosada (strana krale)
                if(!b) {
                    System.out.println("Rosadu nelze provest.");
                }
                return b;
            case "0-0-0":
                boolean c = this.castling(false); //prikaz rosada (strana kralovny)
                if(!c) {
                    System.out.println("Rosadu nelze provest.");
                }
                return c;
            default:
                rawCommand = rawCommand.replaceAll(" ",""); //blok default odstrani mezery, zjisti, zda-li je prikaz ve spravnem formatu a vrati odpovidajici prikaz (prip. error)
                if(rawCommand.length() == 4) { //validni prikaz ma po vypusteni mezer presne 4 znaky
                    boolean checker = true;
                    int oldColumn, oldRow, newColumn, newRow;
                    for (int i = 0;i < 4;i++){
                        if (!Character.isDigit(rawCommand.charAt(i))) {
                            rawCommand = rawCommand.replace(rawCommand.charAt(i), letterToNumber((rawCommand.charAt(i)))); //nahrazeni pismen sloupcu za cislice
                        }
                        if ((Character.getNumericValue(rawCommand.charAt(i)) > 8) || (Character.getNumericValue(rawCommand.charAt(i)) < 1)){ //policka jsou od 1,1 do 8,8
                            checker = false;
                        }
                    }
                    oldColumn = Character.getNumericValue(rawCommand.charAt(0)) - 1;
                    oldRow = Character.getNumericValue(rawCommand.charAt(1)) - 1;
                    newColumn = Character.getNumericValue(rawCommand.charAt(2)) - 1;
                    newRow = Character.getNumericValue(rawCommand.charAt(3)) - 1;
                    if (checker && (board[oldColumn][oldRow] != null) && (board[oldColumn][oldRow].getPlayer() == player)) { //pokud je prikaz validni, preda se dal                        
                        boolean a = this.board[oldColumn][oldRow].move(true,newColumn, newRow);
                        if(!a) {
                            System.out.println("Nemozny tah.");
                        }
                        return a;
                    }
                    else {
                        System.out.println("Chybny prikaz.");
                        return false; //jinak je predana chyba
                    }
                }
                else
                {
                    System.out.println("Prikaz neni ve spravnem formatu.");
                    return false; //jinak je predana chyba
                }
        }
        
    }
    private char letterToNumber(char _letter) { //prevede pismena a-h na cislice 1-8
        char[] lettersLC = {'a','b','c','d','e','f','g','h'};
        char[] lettersUC = {'A','B','C','D','E','F','G','H'};
        char[] numbers = {'1','2','3','4','5','6','7','8'};
        for (int i = 0; i < 8; i++){
            if(_letter == lettersLC[i]) {
                return numbers[i];
            }
            if(_letter == lettersUC[i]) {
                return numbers[i];
            }
        }
        return '9';
    }
    private void save() { //zapise do souboru, kdo je na tahu a  pozice vsech figurek
        try{
            Scanner inp = new Scanner(System.in);
            System.out.println("Zadej jmeno souboru:");
            FileWriter fstream = new FileWriter(inp.nextLine() + ".save");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(getPlayer());
            out.newLine();
            for (int _row = 0; _row < 8; _row++) { //projde celou sachovnici...
                for (int _column = 0; _column < 8; _column++) {
                    if(this.board[_column][_row] != null){
                        String write = this.board[_column][_row].toString()+this.board[_column][_row].getColumn()+this.board[_column][_row].getRow();
                        if(this.board[_column][_row].getMoved()){
                            write += "T";
                        }
                        else {
                            write += "F";
                        }
                        out.write(write);
                        out.newLine();
                    }
                }
            }
            out.close();
        }
        catch (Exception e){
            System.err.println("Error: " + e.getMessage());
            System.exit(0);
        }
    }
    private void load() { //nacte hru ze souboru
        Scanner inp = new Scanner(System.in);
        System.out.println("Zadej jmeno souboru:");
        for (int _row = 0; _row < 8; _row++) { //projde celou sachovnici...
            for (int _column = 0; _column < 8; _column++) {
                this.board[_row][_column] = null;
            }
        }
        BufferedReader br = null;
        try {
            String object;
            br = new BufferedReader(new FileReader(inp.nextLine() + ".save"));  
            while ((object = br.readLine()) != null) {
                if(object.length() == 4 && !"true".equals(object)) {
                    loadPiece(object);
                }
                else if("true".equals(object)) {
                    player = false;
                }
                else if("false".equals(object)){
                    player = true;
                }
                else {
                    System.out.println("Chyba souboru.");
                    System.exit(0);
                }
            }
        }    
        catch (IOException e) {
        }
        finally {
            try {
                if (br != null) {
                    br.close();
                }
            }
            catch (IOException ex) {
            }
        }
    }
    private void loadPiece(String com) { //vytvori na sachovnici instanci dane figurky
        int column = Character.getNumericValue(com.charAt(1));
        int row = Character.getNumericValue(com.charAt(2));
        boolean moved;
        if(com.charAt(3) == 'T') {
            moved = true;
        }
        else {
            moved = false;
        }
        
        switch(com.charAt(0)) {
            case 'p':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new Pawn(this, false, column, row, moved);
                break;
            case 'P':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new Pawn(this, true, column, row, moved);
                break;    
            case 'k':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new Knight(this, false, column, row, moved);
                break;    
            case 'K':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new Knight(this, true, column, row, moved);
                break;    
            case 'b':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new Bishop(this, false, column, row, moved);
                break;
            case 'B':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new Bishop(this, true, column, row, moved);
                break;
            case 'r':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new Rook(this, false, column, row, moved);
                break;
            case 'R':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new Rook(this, true, column, row, moved);
                break;
            case 'q':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new Queen(this, false, column, row, moved);
                break;    
            case 'Q':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new Queen(this, true, column, row, moved);
                break;
            case 'x':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new King(this, false, column, row, moved);
                break;
            case 'X':
                //System.out.println(com.charAt(0) + "@ " + column + " " + row);
                this.board[column][row] = new King(this, true, column, row, moved);
                break;    
            default:
                System.out.println("Chyba souboru.");
                System.exit(0);
        }
    }
    public void newGame() { //zahaji novou hru
        this.player = false;
        fillBoardColor();
        for (int _row = 0; _row < 8; _row++) { //projde celou sachovnici...
            for (int _column = 0; _column < 8; _column++) {
                this.board[_row][_column] = null;
            }
        }
        this.board[0][0] = new Rook(this,true,0,0,false);
        this.board[1][0] = new Knight(this,true,1,0,false);
        this.board[2][0] = new Bishop(this,true,2,0,false);
        this.board[3][0] = new Queen(this,true,3,0,false);
        this.board[4][0] = new King(this,true,4,0,false);
        this.board[5][0] = new Bishop(this,true,5,0,false);
        this.board[6][0] = new Knight(this,true,6,0,false);
        this.board[7][0] = new Rook(this,true,7,0,false);
        for(int i = 0; i < 8;i++){
            this.board[i][1] = new Pawn(this,true,i,1,false);
        }
        this.board[0][7] = new Rook(this,false,0,7,false);
        this.board[1][7] = new Knight(this,false,1,7,false);
        this.board[2][7] = new Bishop(this,false,2,7,false);
        this.board[3][7] = new Queen(this,false,3,7,false);
        this.board[4][7] = new King(this,false,4,7,false);
        this.board[5][7] = new Bishop(this,false,5,7,false);
        this.board[6][7] = new Knight(this,false,6,7,false);
        this.board[7][7] = new Rook(this,false,7,7,false);
        for(int i = 0; i < 8;i++){
            this.board[i][6] = new Pawn(this,false,i,6,false);
        }
    }
    public void print() { //vytiskne sachovnici a figurky do konzole
        for(int row = 7; row >= 0; row--) {
            System.out.print(row+1 + "   ");
            for(int column = 0;column < 8;column++) {
                System.out.print(fields[column][row]);
                if(board[column][row] != null) {
                    System.out.print(board[column][row]);
                }
                else {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.println("     a  b  c  d  e  f  g  h");
    }
    private void fillBoardColor() { //naplni pole barev jednotlivych policek na sachovnici
        boolean switching = false; //na stridani barev v radku
        for (int _row = 0; _row < 8; _row++) { //projde celou sachovnici...
            for (int _column = 0; _column < 8; _column++) {
                if (switching == true) {
                    fields[_column][_row] = 'B'; //...a priradi barvu
                }
                else {
                    fields[_column][_row] = 'C';
                }
                    
                switching = !switching; //dalsi barva v radku bude odlisna
            }
            switching = !switching; //dalsi barva ve sloupci bude odlisna
        }
    }
    public boolean stillPlaying() {
        return this.stillPlaying;
    }
    public void changePlayer() { //zmeni hrace
        this.player = !this.player; 
    }
    public boolean isEndGame(Chessman king) { //otestuje, zda-li neni dana situace remiza pripadne mat
        boolean draw = !isChecked();
        for (int _row = 0; _row < 8; _row++) { //projde celou sachovnici...
            for (int _column = 0; _column < 8; _column++) {
                if(this.board[_column][_row] != null && this.board[_column][_row].getPlayer() == player) {
                    for (int __row = 0; __row < 8; __row++) { //projde celou sachovnici...
                        for (int __column = 0; __column < 8; __column++) {
                            //System.out.println("Ptam se "+this.board[_column][_row].toString()+" na pozici ["+_column+"]["+_row+"], jestli by mohl jit na ["+__column+"]["+__row+"] tak, aby pak nebyl sach.");
                            if(this.board[_column][_row].move(false,__column,__row)) {
                                //System.out.println("alespon jeden mozny tah tu je");
                                return false;
                            }
                            else {
                                //System.out.println("tohle zrovna ne");
                            }
                        }
                    }
                }
            }
        }
        stillPlaying = false;
        if(draw) {
            System.out.println("Remiza.");
            return true;
        }
        else {
            if(player) {
                System.out.println("Zvitezil cerny!");
            }
            else{
                System.out.println("Zvitezil bily!");
            }
            return true;
        }
    }
    public boolean isChecked() { //zkontroluje, zda-li neni kral hrace, ktery je prave na tahu, v sachu
        int columnKing = -1, rowKing = -1;
        for (int _row = 0; _row < 8; _row++) { //projde celou sachovnici...
            for (int _column = 0; _column < 8; _column++) {
                if ((this.board[_column][_row] instanceof King) && (this.board[_column][_row].color == this.player)) {
                    columnKing = _column;
                    rowKing = _row;
                    break;
                }
                if (columnKing != -1){
                    break;
                }
            }
        }
        if (columnKing != -1 && rowKing != -1) {
            if(this.player) {
                whiteKing = this.board[columnKing][rowKing];
            }
            else {
                blackKing = this.board[columnKing][rowKing];
            }
            //System.out.println("neni tenhle " +this.board[columnKing][rowKing]+"[" + columnKing + "," + rowKing + "]  moula v sachu?");
            for (int _row = 0; _row < 8; _row++) { //projde celou sachovnici...
                for (int _column = 0; _column < 8; _column++) {
                    if ((this.board[_column][_row] != null) && (this.board[_column][_row].getPlayer() != this.player)) {
                        boolean a = this.board[_column][_row].inRange(columnKing, rowKing);
                        //System.out.println("Ptam se " + this.board[_column][_row] + " na pozici " + _column+  " " + _row + " jestli muzou na krale@" + columnKing + " " + rowKing + "tedy: " + a);
                        if (a) {
                            //System.out.println("a muze tam!");
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    private String getPlayer(){
        if(player) {
            return "true";
        }
        else{
            return "false";
        }
    }
    public Chessman getKing() {
        if(player == true) {
            return whiteKing;
        }
        else {
            return blackKing;
        }
    }
}
