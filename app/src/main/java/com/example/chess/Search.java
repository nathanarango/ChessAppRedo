package com.example.chess;

import java.util.ArrayList;

public class Search {

    private Board currentBoard;

    public Search(Board board){
        this.currentBoard = board;
    }

    public Move searchMoves(){

        ArrayList<Move> legalMoves = getAllMoves(1);

        Move bestMove = legalMoves.get(0);
        int bestMoveEval = 10000;

        for(Move move : legalMoves){

            currentBoard.makeMove(move);
            int moveEval = getMoveEval(3, 0);
            currentBoard.unMakeMove(move);

            if(moveEval < bestMoveEval){
                bestMoveEval = moveEval;
                bestMove = move;
            }
        }
        return  bestMove;
    }

    private int getMoveEval(int depth, int color) {

        if (depth == 0){
            return Evaluation.evaluate(currentBoard);
        }

        ArrayList<Move> legalMoves = getAllMoves(color);

        if (legalMoves.size() == 0) {
            return -100000;
        }

        int max = -100000;
        for (Move move : legalMoves) {
            currentBoard.makeMove(move);
            int score = -getMoveEval(depth - 1, 1 - color);
            if(score > max){
                max = score;
            }
            currentBoard.unMakeMove(move);
        }
        return max;
    }

    ArrayList<Move> getAllMoves(int color){

        ArrayList<Move> allLegalMoves = new ArrayList<Move>();

        for(int i = 0; i < 64; i ++){
            if(currentBoard.isPieceOnTile(i) && currentBoard.getPieceOnTile(i).getColor() == color) {

                allLegalMoves.addAll(currentBoard.getPieceOnTile(i).generateLegalMoves(currentBoard));
            }
        }
        return allLegalMoves;
    }
}
