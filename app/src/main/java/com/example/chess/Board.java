package com.example.chess;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Board {

    private ArrayList<Piece> pieces;
    private int colorToMove;
    private ArrayList<Move> currentPossibleMoves;
    private int enPassantTile;
    private int wk;
    private int wq;
    private int bk;
    private int bq;

    public Board(ArrayList<Piece> pieces, int colorToMove, int enPassantTile, int wk, int wq, int bk, int bq){

        this.pieces = pieces;
        this.colorToMove = colorToMove;
        this.currentPossibleMoves = new ArrayList<Move>();
        this.enPassantTile = enPassantTile;
        this.wk = wk;
        this.wq = wq;
        this.bk = bk;
        this.bq = bq;
    }

    public void setCurrentPossibleMoves(ArrayList<Move> currentPossibleMoves){
        this.currentPossibleMoves = currentPossibleMoves;
    }

    public int getWk(){
        return wk;
    }

    public int getWq(){
        return wq;
    }

    public int getBk(){
        return bk;
    }

    public int getBq(){
        return bq;
    }

    public void setWk(int wk){
        this.wk = wk;
    }

    public void setWq(int wq){
        this.wq = wq;
    }

    public void setBk(int bk){
        this.bk = bk;
    }

    public void setBq(int bq){
        this.bq = bq;
    }

    public void setEnPassantTile(int tile){
        this.enPassantTile = tile;
    }

    public int getEnPassantTile(){
        return enPassantTile;
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
    }

    public int getColorToMove() {
        return colorToMove;
    }

    public ArrayList<Move> getCurrentPossibleMoves(){
        return currentPossibleMoves;
    }

    public boolean isPieceOnTile(int tileNum){

        for(Piece piece : pieces){

            if(piece.getPosition() == tileNum){

                return true;
            }
        }
        return false;
    }

    public Piece getPieceOnTile(int tileNum){

        for(Piece piece : pieces){

            if(piece.getPosition() == tileNum){

                return piece;
            }
        }
        return null;
    }

    public void makeMove(Move move){

        Piece pieceToMove = move.getPiece();

        if(move.isPawnJump()){
            if(move.getPiece().getColor() == 0){
                this.setEnPassantTile(move.getStartPosition() - 8);
            }
            else{
                this.setEnPassantTile(move.getStartPosition() + 8);
            }
        }
        else{
            this.setEnPassantTile(-1);
        }

        if(move.isCastle()){

            if(pieceToMove.getColor() == 0){
                this.setWk(0);
                this.setWq(0);

                this.pieces.remove(pieceToMove);

                if(move.getEndPosition() == 62){

                    this.pieces.remove(this.getPieceOnTile(63));

                    this.pieces.add(new King(62, 0));
                    this.pieces.add(new Rook(61, 0));
                }
                else{

                    this.pieces.remove(this.getPieceOnTile(56));

                    this.pieces.add(new King(58, 0));
                    this.pieces.add(new Rook(59, 0));
                }
            }
            else{
                this.setBk(0);
                this.setBq(0);

                this.pieces.remove(pieceToMove);

                if(move.getEndPosition() == 6){

                    this.pieces.remove(this.getPieceOnTile(7));

                    this.pieces.add(new King(6, 1));
                    this.pieces.add(new Rook(5, 1));
                }
                else{

                    this.pieces.remove(this.getPieceOnTile(0));

                    this.pieces.add(new King(2, 1));
                    this.pieces.add(new Rook(3, 1));
                }
            }
        }
        else{

            if(move.isPieceOnEndPosition()){

                this.pieces.remove(move.getPieceOnEndPosition());
            }

            this.pieces.remove(pieceToMove);

            if(pieceToMove.getName().equals("king")){
                this.pieces.add(new King(move.getEndPosition(), pieceToMove.getColor()));

                if(pieceToMove.getColor() == 0){
                    this.setWk(0);
                    this.setWq(0);
                }
                else{
                    this.setBk(0);
                    this.setBq(0);
                }
            }
            else if(pieceToMove.getName().equals("queen")){
                this.pieces.add(new Queen(move.getEndPosition(), pieceToMove.getColor()));
            }
            else if(pieceToMove.getName().equals("rook")){
                this.pieces.add(new Rook(move.getEndPosition(), pieceToMove.getColor()));

                if(pieceToMove.getPosition() == 0){
                    this.setBq(0);
                }
                else if(pieceToMove.getPosition() == 7){
                    this.setBk(0);
                }
                else if(pieceToMove.getPosition() == 56){
                    this.setWq(0);
                }
                else if(pieceToMove.getPosition() == 63){
                    this.setWk(0);
                }
            }
            else if(pieceToMove.getName().equals("bishop")){
                this.pieces.add(new Bishop(move.getEndPosition(), pieceToMove.getColor()));
            }
            else if(pieceToMove.getName().equals("knight")){
                this.pieces.add(new Knight(move.getEndPosition(), pieceToMove.getColor()));
            }
            else if(pieceToMove.getName().equals("pawn")){

                if(move.isPromotion()){
                    if(move.getPromotionPiece() == 1){
                        this.pieces.add(new Queen(move.getEndPosition(), pieceToMove.getColor()));
                    }
                    else if(move.getPromotionPiece() == 2){
                        this.pieces.add(new Knight(move.getEndPosition(), pieceToMove.getColor()));
                    }
                    else if(move.getPromotionPiece() == 3){
                        this.pieces.add(new Rook(move.getEndPosition(), pieceToMove.getColor()));
                    }
                    else{
                        this.pieces.add(new Bishop(move.getEndPosition(), pieceToMove.getColor()));
                    }
                }
                else {
                    this.pieces.add(new Pawn(move.getEndPosition(), pieceToMove.getColor()));
                }
            }

            if(move.getEndPosition() == 0){
                this.setBq(0);
            }
            else if(move.getEndPosition() == 7){
                this.setBk(0);
            }
            else if(move.getEndPosition() == 56){
                this.setWq(0);
            }
            else if(move.getEndPosition() == 63){
                this.setWk(0);
            }
        }
    }

    public void unMakeMove(Move move){

        Piece pieceToMove = move.getPiece();

        this.setEnPassantTile(move.getPreviousEnPassantTile());

        this.setWk(move.getLastWk());
        this.setWq(move.getLastWq());
        this.setBk(move.getLastBk());
        this.setBq(move.getLastBq());

        if(move.isCastle()){

            if(pieceToMove.getColor() == 0){

                this.pieces.add(pieceToMove);

                if(move.getEndPosition() == 62){

                    this.pieces.add(new Rook(63, 0));

                    this.pieces.remove(this.getPieceOnTile(61));
                    this.pieces.remove(this.getPieceOnTile(62));
                }
                else{

                    this.pieces.add(new Rook(56, 0));

                    this.pieces.remove(this.getPieceOnTile(58));
                    this.pieces.remove(this.getPieceOnTile(59));
                }
            }
            else{

                this.pieces.add(pieceToMove);

                if(move.getEndPosition() == 6){

                    this.pieces.add(new Rook(7, 1));

                    this.pieces.remove(this.getPieceOnTile(6));
                    this.pieces.remove(this.getPieceOnTile(5));
                }
                else{

                    this.pieces.add(new Rook(0, 1));

                    this.pieces.remove(this.getPieceOnTile(2));
                    this.pieces.remove(this.getPieceOnTile(3));
                }
            }
        }
        else{

            this.pieces.remove(this.getPieceOnTile(move.getEndPosition()));

            if(move.isPieceOnEndPosition()){

                this.pieces.add(move.getPieceOnEndPosition());
            }

            this.pieces.add(pieceToMove);
        }
    }
}
