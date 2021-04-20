package com.example.chess;

import java.util.ArrayList;

public class Bishop extends Piece {

    public static final int[] MOVE_VECTORS = {-7, -9, 7, 9};

    public Bishop(int position, int color) {
        super(position, color, "bishop");
    }

    @Override
    public ArrayList<Move> findMoves(Board board, boolean useless) {

        ArrayList<Move> legalMoves = new ArrayList<Move>();

        for(int vector : MOVE_VECTORS){

            int position = this.getPosition();

            while(position + vector >=0 && position + vector < 64){

                if(((vector == 7 || vector == -9) && position % 8 == 0) ||
                   ((vector == -7 || vector == 9) && position % 8 == 7)){
                    break;
                }
                if(board.isPieceOnTile(position + vector)){

                    if(board.getPieceOnTile(position + vector).getColor() != this.getColor()){
                        legalMoves.add(new Move(this, this.getPosition(), position + vector,
                            board.isPieceOnTile(position + vector), board.getPieceOnTile(position + vector),
                                false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                board.getBk(), board.getBq(), false, 0));
                    }
                    break;
                }
                legalMoves.add(new Move(this, this.getPosition(), position + vector,
                    board.isPieceOnTile(position + vector), board.getPieceOnTile(position + vector),
                        false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                        board.getBk(), board.getBq(), false, 0));
                position += vector;
            }
        }
        return legalMoves;
    }
}
