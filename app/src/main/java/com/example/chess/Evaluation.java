package com.example.chess;

public class Evaluation {

    public static final int QUEEN_VALUE = 900;
    public static final int ROOK_VALUE = 500;
    public static final int BISHOP_VALUE = 300;
    public static final int KNIGHT_VALUE = 300;
    public static final int PAWN_VALUE = 100;


    public static int evaluate(Board board){

        int whiteScore = 0;
        int blackScore = 0;

        for(Piece piece : board.getPieces()){

            if(piece.getColor() == 0){

                switch (piece.getName()) {
                    case "queen":
                        whiteScore += QUEEN_VALUE;
                        break;
                    case "rook":
                        whiteScore += ROOK_VALUE;
                        break;
                    case "bishop":
                        whiteScore += BISHOP_VALUE;
                        break;
                    case "knight":
                        whiteScore += KNIGHT_VALUE;
                        break;
                    case "pawn":
                        whiteScore += PAWN_VALUE;
                        break;
                }
            }
            else{

                switch (piece.getName()) {
                    case "queen":
                        blackScore += QUEEN_VALUE;
                        break;
                    case "rook":
                        blackScore += ROOK_VALUE;
                        break;
                    case "bishop":
                        blackScore += BISHOP_VALUE;
                        break;
                    case "knight":
                        blackScore += KNIGHT_VALUE;
                        break;
                    case "pawn":
                        blackScore += PAWN_VALUE;
                        break;
                }
            }
        }

        int sideToMove = 1;
        if(board.getColorToMove() == 1){
            sideToMove = -1;
        }
        return (whiteScore - blackScore) * sideToMove;
    }

}