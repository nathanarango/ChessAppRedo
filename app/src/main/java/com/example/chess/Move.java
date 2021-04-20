package com.example.chess;

import java.util.ArrayList;

public class Move {

    private Piece piece;
    private int startPosition;
    private int endPosition;
    private boolean isPieceOnEndPosition;
    private Piece pieceOnEndPosition;
    private boolean isPawnJump;
    private int previousEnPassantTile;
    private boolean isCastle;
    private int lastWk;
    private int lastWq;
    private int lastBk;
    private int lastBq;
    private boolean isPromotion;
    private int promotionPiece;

    public Move(Piece piece, int startPosition, int endPosition, boolean isPieceOnEndPosition,
                Piece pieceOnEndPosition, boolean isPawnJump, int previousEnPassantTile, boolean isCastle,
                int lastWk, int lastWq, int lastBk, int lastBq, boolean isPromotion, int promotionPiece){

        this.piece = piece;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.isPieceOnEndPosition = isPieceOnEndPosition;
        this.pieceOnEndPosition = pieceOnEndPosition;
        this.isPawnJump = isPawnJump;
        this.previousEnPassantTile = previousEnPassantTile;
        this.isCastle = isCastle;
        this.lastWk = lastWk;
        this.lastWq = lastWq;
        this.lastBk = lastBk;
        this.lastBq = lastBq;
        this.isPromotion = isPromotion;
        this.promotionPiece = promotionPiece;
    }

    public int getPromotionPiece() {
        return promotionPiece;
    }

    public boolean isPromotion() {
        return isPromotion;
    }

    public boolean isCastle() {
        return isCastle;
    }

    public int getLastWk(){
        return lastWk;
    }

    public int getLastWq(){
        return lastWq;
    }

    public int getLastBk(){
        return lastBk;
    }

    public int getLastBq(){
        return lastBq;
    }

    public int getEndPosition(){
        return endPosition;
    }

    public int getPreviousEnPassantTile(){
        return previousEnPassantTile;
    }

    public boolean isPawnJump(){
        return isPawnJump;
    }

    public int getStartPosition(){
        return startPosition;
    }

    public boolean isPieceOnEndPosition(){
        return isPieceOnEndPosition;
    }

    public Piece getPieceOnEndPosition(){
        return pieceOnEndPosition;
    }

    public Piece getPiece(){
        return piece;
    }
}
