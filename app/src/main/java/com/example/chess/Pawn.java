package com.example.chess;

import java.util.ArrayList;

public class Pawn extends Piece {

    public static final int[] MOVE_VECTORS = {7, 8, 9};

    public Pawn(int position, int color) {
        super(position, color, "pawn");
    }

    @Override
    public ArrayList<Move> findMoves(Board board, boolean useless) {

        ArrayList<Move> legalMoves = new ArrayList<Move>();

        for(int vector : MOVE_VECTORS){

            int position = this.getPosition();

            if(this.getColor() == 0){
                vector = vector * -1;
            }

            if(!(((vector == -7 || vector == 9) && position % 8 == 7) ||
                ((vector == 7 || vector == -9) && position % 8 == 0)) &&
                (position + vector >=0 && position + vector < 64)){

                if(vector % 8 == 0) {

                    boolean isPromotion = false;
                    if((position + vector < 8 && this.getColor() == 0) || (position + vector > 55 && this.getColor() == 1)){
                        isPromotion = true;
                    }

                    if(!(board.isPieceOnTile(position + vector))){

                        if((position + vector < 8 && this.getColor() == 0) || (position + vector > 55 && this.getColor() == 1)){
                            legalMoves.add(new Move(this, this.getPosition(), position + vector,
                                    board.isPieceOnTile(position + vector), board.getPieceOnTile(position + vector),
                                    false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                    board.getBk(), board.getBq(), true, 1));
                            legalMoves.add(new Move(this, this.getPosition(), position + vector,
                                    board.isPieceOnTile(position + vector), board.getPieceOnTile(position + vector),
                                    false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                    board.getBk(), board.getBq(), true, 2));
                            legalMoves.add(new Move(this, this.getPosition(), position + vector,
                                    board.isPieceOnTile(position + vector), board.getPieceOnTile(position + vector),
                                    false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                    board.getBk(), board.getBq(), true, 3));
                            legalMoves.add(new Move(this, this.getPosition(), position + vector,
                                    board.isPieceOnTile(position + vector), board.getPieceOnTile(position + vector),
                                    false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                    board.getBk(), board.getBq(), true, 4));
                        }
                        else {
                            legalMoves.add(new Move(this, this.getPosition(), position + vector,
                                    board.isPieceOnTile(position + vector), board.getPieceOnTile(position + vector),
                                    false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                    board.getBk(), board.getBq(), false, 0));

                            if(!(board.isPieceOnTile(position + vector + vector))){

                                if((this.getColor() == 0 && position > 47 && position < 56) ||
                                        (this.getColor() == 1 && position > 7 && position < 16)){

                                    legalMoves.add(new Move(this, this.getPosition(), position + vector + vector,
                                            board.isPieceOnTile(position + vector + vector), board.getPieceOnTile(position + vector + vector),
                                            true, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                            board.getBk(), board.getBq(), false, 0));
                                }
                            }
                        }
                    }
                }
                else if(board.isPieceOnTile(position + vector) &&
                    board.getPieceOnTile(position + vector).getColor() != this.getColor()) {

                    if((position + vector < 8 && this.getColor() == 0) || (position + vector > 55 && this.getColor() == 1)){
                        legalMoves.add(new Move(this, position, position + vector,
                                true, board.getPieceOnTile(position + vector),
                                false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                board.getBk(), board.getBq(), true, 1));
                        legalMoves.add(new Move(this, position, position + vector,
                                true, board.getPieceOnTile(position + vector),
                                false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                board.getBk(), board.getBq(), true, 2));
                        legalMoves.add(new Move(this, position, position + vector,
                                true, board.getPieceOnTile(position + vector),
                                false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                board.getBk(), board.getBq(), true, 3));
                        legalMoves.add(new Move(this, position, position + vector,
                                true, board.getPieceOnTile(position + vector),
                                false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                board.getBk(), board.getBq(), true, 4));
                    }
                    else {
                        legalMoves.add(new Move(this, position, position + vector,
                                true, board.getPieceOnTile(position + vector),
                                false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                board.getBk(), board.getBq(), false, 0));
                    }
                }
                else if(board.getEnPassantTile() == position + vector){
                    if(this.getColor() == 0){
                        legalMoves.add(new Move(this, position, position + vector,
                            true, board.getPieceOnTile(position + vector + 8),
                                false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                board.getBk(), board.getBq(), false, 0));
                    }
                    else{
                        legalMoves.add(new Move(this, this.getPosition(), position + vector,
                            true, board.getPieceOnTile(position + vector - 8),
                                false, board.getEnPassantTile(), false, board.getWk(), board.getWq(),
                                board.getBk(), board.getBq(), false, 0));
                    }
                }
            }
        }
        return legalMoves;
    }
}
