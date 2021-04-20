package com.example.chess;

import java.util.ArrayList;

public abstract class Piece {

    private int position;
    private int color;
    private String name;

    public Piece(int position, int color, String name){

        this.position = position;
        this.color = color;
        this.name = name;
    }

    public abstract ArrayList<Move> findMoves(Board board, boolean normal);

    public int getPosition(){
        return position;
    }

    public String getName(){
        return name;
    }

    public int getColor(){
        return color;
    }

    public String getPieceName(){

        String color;
        if(this.color == 0){
            color = "white_";
        }
        else{
            color = "black_";
        }
        return color.concat(this.name);
    }

    public ArrayList<Move> generateLegalMoves(Board board){

        ArrayList<Move> allLegalMoves = new ArrayList<Move>();

        ArrayList<Move> possibleLegalMoves = this.findMoves(board, true);

        for(Move moveToTest : possibleLegalMoves){

            boolean moveIsLegal = true;
            board.makeMove(moveToTest);

            for(Piece piece : board.getPieces()){

                if(piece.getColor() != this.getColor()){

                    ArrayList<Move> opponentMoves = piece.findMoves(board, true);

                    for(Move opponentMove : opponentMoves){

                        if(!(board.getEnPassantTile() == opponentMove.getEndPosition())) {

                            if (opponentMove.isPieceOnEndPosition() && opponentMove.getPieceOnEndPosition().getName().equals("king")) {

                                moveIsLegal = false;
                            }
                        }
                    }
                }
            }
            if(moveIsLegal){

                allLegalMoves.add(moveToTest);
            }
            board.unMakeMove(moveToTest);
        }
        return allLegalMoves;
    }
}
