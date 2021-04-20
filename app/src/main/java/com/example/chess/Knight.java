package com.example.chess;

import java.util.ArrayList;

public class Knight extends Piece {

    public static final int[] MOVE_VECTORS = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(int position, int color) {
        super(position, color, "knight");
    }

    @Override
    public ArrayList<Move> findMoves(Board board, boolean useless) {

        ArrayList<Move> legalMoves = new ArrayList<Move>();

        int position = this.getPosition();

        for(int vector : MOVE_VECTORS){

            if(!(((vector == -6 || vector == 10) && position % 8 == 6) ||
                ((vector == -6 || vector == 10 || vector == 17 || vector == -15) && position % 8 == 7) ||
                ((vector == 6 || vector == -10 || vector == -17 || vector == 15) && position % 8 == 0) ||
                ((vector == 6 || vector == -10) && position % 8 == 1)) &&
                (position + vector >=0 && position + vector < 64)){

                if(!(board.isPieceOnTile(position + vector) &&
                    board.getPieceOnTile(position + vector).getColor() == this.getColor())) {

                    legalMoves.add(new Move(this, this.getPosition(), position + vector,
                        board.isPieceOnTile(position + vector), board.getPieceOnTile(position + vector),
                            false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                            board.getBk(), board.getBq(), false, 0));
                }
            }
        }
        return legalMoves;
    }
}
