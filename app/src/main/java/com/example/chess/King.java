package com.example.chess;

import java.util.ArrayList;

public class King extends Piece {

    public static final int[] MOVE_VECTORS = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(int position, int color) {

        super(position, color, "king");
    }

    @Override
    public ArrayList<Move> findMoves(Board board, boolean normal) {

        ArrayList<Move> legalMoves = new ArrayList<Move>();

        for(int vector : MOVE_VECTORS){

            int position = this.getPosition();

            if(!((vector == -1 && position % 8 == 0) || (vector == 1 && position % 8 == 7) ||
                    ((vector == 7 || vector == -9) && position % 8 == 0) ||
                    ((vector == -7 || vector == 9) && position % 8 == 7)) &&
                    (position + vector >=0 && position + vector < 64)){

                if(!(board.isPieceOnTile(position + vector) &&
                        board.getPieceOnTile(position + vector).getColor() == this.getColor())) {

                    legalMoves.add(new Move(this, position, position + vector,
                            board.isPieceOnTile(position + vector), board.getPieceOnTile(position + vector),
                            false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                            board.getBk(), board.getBq(), false, 0));
                }
            }
        }
        if(normal){

            if(board.getColorToMove() == 0){

                if(board.getWk() == 1 && !board.isPieceOnTile(61) && !board.isPieceOnTile(62)){

                    boolean moveIsLegal = true;
                    boolean stop = false;

                    if(board.isPieceOnTile(52)){
                        if(board.getPieceOnTile(52).getColor() == 1 && board.getPieceOnTile(52).getName().equals("pawn")){
                            moveIsLegal = false;
                            stop = true;
                        }
                    }

                    for(Piece piece : board.getPieces()){

                        if(stop){
                            break;
                        }
                        if(piece.getColor() == 1){

                            ArrayList<Move> opponentMoves = piece.findMoves(board, false);

                            for(Move opponentMove : opponentMoves){

                                if (opponentMove.getEndPosition() == 60 || opponentMove.getEndPosition() == 61 || opponentMove.getEndPosition() == 62) {

                                    moveIsLegal = false;
                                    stop = true;
                                    break;
                                }
                            }
                        }
                    }

                    if(moveIsLegal){
                        legalMoves.add(new Move(this, 60, 62, false,
                                null, false, board.getEnPassantTile(), true,
                                board.getWk(), board.getWq(), board.getBk(), board.getBq(), false, 0));
                    }
                }
                if(board.getWq() == 1 && !board.isPieceOnTile(59) && !board.isPieceOnTile(58) && !board.isPieceOnTile(57)){

                    boolean moveIsLegal = true;
                    boolean stop = false;

                    if(board.isPieceOnTile(52)){
                        if(board.getPieceOnTile(52).getColor() == 1 && board.getPieceOnTile(52).getName().equals("pawn")){
                            moveIsLegal = false;
                            stop = true;
                        }
                    }

                    for(Piece piece : board.getPieces()){

                        if(stop){
                            break;
                        }
                        if(piece.getColor() == 1){

                            ArrayList<Move> opponentMoves = piece.findMoves(board, false);

                            for(Move opponentMove : opponentMoves){

                                if (opponentMove.getEndPosition() == 60 || opponentMove.getEndPosition() == 59 || opponentMove.getEndPosition() == 58) {

                                    moveIsLegal = false;
                                    stop = true;
                                    break;
                                }
                            }
                        }
                    }

                    if(moveIsLegal){
                        legalMoves.add(new Move(this, 60, 58, false,
                                null, false, board.getEnPassantTile(), true,
                                board.getWk(), board.getWq(), board.getBk(), board.getBq(), false, 0));
                    }
                }
            }
            else{

                if(board.getBk() == 1 && !board.isPieceOnTile(5) && !board.isPieceOnTile(6)){

                    boolean moveIsLegal = true;
                    boolean stop = false;

                    if(board.isPieceOnTile(12)){
                        if(board.getPieceOnTile(12).getColor() == 0 && board.getPieceOnTile(12).getName().equals("pawn")){
                            moveIsLegal = false;
                            stop = true;
                        }
                    }

                    for(Piece piece : board.getPieces()){

                        if(stop){
                            break;
                        }
                        if(piece.getColor() == 0){

                            ArrayList<Move> opponentMoves = piece.findMoves(board, false);

                            for(Move opponentMove : opponentMoves){

                                if (opponentMove.getEndPosition() == 4 || opponentMove.getEndPosition() == 5 || opponentMove.getEndPosition() == 6) {

                                    moveIsLegal = false;
                                    stop = true;
                                    break;
                                }
                            }
                        }
                    }

                    if(moveIsLegal){
                        legalMoves.add(new Move(this, 4, 6, false,
                                null, false, board.getEnPassantTile(), true,
                                board.getWk(), board.getWq(), board.getBk(), board.getBq(), false, 0));
                    }
                }
                if(board.getBq() == 1 && !board.isPieceOnTile(1) && !board.isPieceOnTile(2) && !board.isPieceOnTile(3)){

                    boolean moveIsLegal = true;
                    boolean stop = false;

                    if(board.isPieceOnTile(12)){
                        if(board.getPieceOnTile(12).getColor() == 0 && board.getPieceOnTile(12).getName().equals("pawn")){
                            moveIsLegal = false;
                            stop = true;
                        }
                    }

                    for(Piece piece : board.getPieces()){

                        if(stop){
                            break;
                        }
                        if(piece.getColor() == 0){

                            ArrayList<Move> opponentMoves = piece.findMoves(board, false);

                            for(Move opponentMove : opponentMoves){

                                if (opponentMove.getEndPosition() == 2 || opponentMove.getEndPosition() == 3 || opponentMove.getEndPosition() == 4) {

                                    moveIsLegal = false;
                                    stop = true;
                                    break;
                                }
                            }
                        }
                    }

                    if(moveIsLegal){
                        legalMoves.add(new Move(this, 4, 2, false,
                                null, false, board.getEnPassantTile(), true,
                                board.getWk(), board.getWq(), board.getBk(), board.getBq(), false, 0));
                    }
                }
            }
        }
        return legalMoves;
    }
}
