package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final int[] TILE_IDS = fillTileIds();
    public static final Map<String, Integer> PIECE_IDS = fillPieceIds();
    public static final boolean[] IS_DARK_SQUARE = fillDarkSquare();
    public static final String[] TILE_NAMES = fillTileNames();

    private static Board currentBoard;
    private boolean promotionInProgress = false;
    private int promotionTile = -1;
    private Move promotionMove = null;

    private static boolean[] fillDarkSquare() {

        final boolean[] isDark = new boolean[]{false, true, false, true, false, true, false, true,
                true, false, true, false, true, false, true, false,
                false, true, false, true, false, true, false, true,
                true, false, true, false, true, false, true, false,
                false, true, false, true, false, true, false, true,
                true, false, true, false, true, false, true, false,
                false, true, false, true, false, true, false, true,
                true, false, true, false, true, false, true, false,};
        return isDark;
    }

    private static int[] fillTileIds() {

        final int[] ids = new int[]{R.id.a8, R.id.b8, R.id.c8, R.id.d8, R.id.e8, R.id.f8, R.id.g8, R.id.h8,
                R.id.a7, R.id.b7, R.id.c7, R.id.d7, R.id.e7, R.id.f7, R.id.g7, R.id.h7,
                R.id.a6, R.id.b6, R.id.c6, R.id.d6, R.id.e6, R.id.f6, R.id.g6, R.id.h6,
                R.id.a5, R.id.b5, R.id.c5, R.id.d5, R.id.e5, R.id.f5, R.id.g5, R.id.h5,
                R.id.a4, R.id.b4, R.id.c4, R.id.d4, R.id.e4, R.id.f4, R.id.g4, R.id.h4,
                R.id.a3, R.id.b3, R.id.c3, R.id.d3, R.id.e3, R.id.f3, R.id.g3, R.id.h3,
                R.id.a2, R.id.b2, R.id.c2, R.id.d2, R.id.e2, R.id.f2, R.id.g2, R.id.h2,
                R.id.a1, R.id.b1, R.id.c1, R.id.d1, R.id.e1, R.id.f1, R.id.g1, R.id.h1};
        return ids;
    }

    private static String[] fillTileNames() {

        final String[] names = new String[]{"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
                "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
                "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
                "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
                "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
                "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
                "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
                "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1",};
        return names;
    }

    private static Map<String, Integer> fillPieceIds() {

        final Map<String, Integer> ids = new HashMap<>();

        ids.put("empty", R.drawable.empty);
        ids.put("white_king", R.drawable.white_king);
        ids.put("white_queen", R.drawable.white_queen);
        ids.put("white_rook", R.drawable.white_rook);
        ids.put("white_knight", R.drawable.white_knight);
        ids.put("white_bishop", R.drawable.white_bishop);
        ids.put("white_pawn", R.drawable.white_pawn);
        ids.put("black_king", R.drawable.black_king);
        ids.put("black_queen", R.drawable.black_queen);
        ids.put("black_rook", R.drawable.black_rook);
        ids.put("black_knight", R.drawable.black_knight);
        ids.put("black_bishop", R.drawable.black_bishop);
        ids.put("black_pawn", R.drawable.black_pawn);

        return ids;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createStartBoard();
    }

    public void onTileClick(View view) {

        unHighlightAllTiles();

        int tileNum = Integer.parseInt(view.getTag().toString());

        boolean continueTurn = true;

        if(promotionInProgress){


            int mult = -1;
            if(promotionTile < 8){
                mult = 1;
            }

            boolean doMove = false;
            Move move = null;
            if(tileNum == promotionTile){
                doMove = true;
                move = new Move(promotionMove.getPiece(), promotionMove.getStartPosition(), promotionMove.getEndPosition(),
                        promotionMove.isPieceOnEndPosition(), promotionMove.getPieceOnEndPosition(), false,
                        promotionMove.getPreviousEnPassantTile(), false, promotionMove.getLastWk(), promotionMove.getLastWq(),
                        promotionMove.getLastBk(), promotionMove.getLastBq(), true, 1);
            }
            else if(tileNum == promotionTile + (8 * mult)){
                doMove = true;
                move = new Move(promotionMove.getPiece(), promotionMove.getStartPosition(), promotionMove.getEndPosition(),
                        promotionMove.isPieceOnEndPosition(), promotionMove.getPieceOnEndPosition(), false,
                        promotionMove.getPreviousEnPassantTile(), false, promotionMove.getLastWk(), promotionMove.getLastWq(),
                        promotionMove.getLastBk(), promotionMove.getLastBq(), true, 2);
            }
            else if(tileNum == promotionTile + (16 * mult)){
                doMove = true;
                move = new Move(promotionMove.getPiece(), promotionMove.getStartPosition(), promotionMove.getEndPosition(),
                        promotionMove.isPieceOnEndPosition(), promotionMove.getPieceOnEndPosition(), false,
                        promotionMove.getPreviousEnPassantTile(), false, promotionMove.getLastWk(), promotionMove.getLastWq(),
                        promotionMove.getLastBk(), promotionMove.getLastBq(), true, 3);
            }
            else if(tileNum == promotionTile + (24 * mult)){
                doMove = true;
                move = new Move(promotionMove.getPiece(), promotionMove.getStartPosition(), promotionMove.getEndPosition(),
                        promotionMove.isPieceOnEndPosition(), promotionMove.getPieceOnEndPosition(), false,
                        promotionMove.getPreviousEnPassantTile(), false, promotionMove.getLastWk(), promotionMove.getLastWq(),
                        promotionMove.getLastBk(), promotionMove.getLastBq(), true, 4);
            }
            if(doMove){

                currentBoard.makeMove(move);

                updateDisplay(currentBoard);

                currentBoard = new Board(currentBoard.getPieces(), 1 - currentBoard.getColorToMove(),
                        currentBoard.getEnPassantTile(), currentBoard.getWk(), currentBoard.getWq(),
                        currentBoard.getBk(), currentBoard.getBq());

                if (countMoves(1, currentBoard.getColorToMove()) == 0) {
                    if (currentBoard.getColorToMove() == 0) {
                        displayText("Black Wins!");
                    } else {
                        displayText("White Wins!");
                    }
                }
            }
            else{
                updateDisplay(currentBoard);
            }

            promotionInProgress = false;
            promotionTile = -1;
            promotionMove = null;
            currentBoard.setCurrentPossibleMoves(new ArrayList<Move>());
        }
        else {

            for (Move possibleMove : currentBoard.getCurrentPossibleMoves()) {

                if (tileNum == possibleMove.getEndPosition()) {

                    if (possibleMove.isPromotion()) {
                        promotionInProgress = true;
                        promotionTile = tileNum;
                        promotionMove = possibleMove;
                        clearDisplay();

                        int mult = 1;
                        String color = "white_";
                        if(possibleMove.getPiece().getColor() == 1){
                            mult = -1;
                            color = "black_";
                        }

                        ImageButton tile1 = findViewById(MainActivity.TILE_IDS[tileNum]);
                        tile1.setImageResource(MainActivity.PIECE_IDS.get(color + "queen"));
                        ImageButton tile2 = findViewById(MainActivity.TILE_IDS[tileNum + (8 * mult)]);
                        tile2.setImageResource(MainActivity.PIECE_IDS.get(color + "knight"));
                        ImageButton tile3 = findViewById(MainActivity.TILE_IDS[tileNum + (16 * mult)]);
                        tile3.setImageResource(MainActivity.PIECE_IDS.get(color + "rook"));
                        ImageButton tile4 = findViewById(MainActivity.TILE_IDS[tileNum + (24 * mult)]);
                        tile4.setImageResource(MainActivity.PIECE_IDS.get(color + "bishop"));

                        continueTurn = false;
                        break;
                    }
                    else {
                        currentBoard.makeMove(possibleMove);

                        currentBoard = new Board(currentBoard.getPieces(), 1 - currentBoard.getColorToMove(),
                                currentBoard.getEnPassantTile(), currentBoard.getWk(), currentBoard.getWq(),
                                currentBoard.getBk(), currentBoard.getBq());

                        if (countMoves(1, currentBoard.getColorToMove()) == 0) {
                            if (currentBoard.getColorToMove() == 0) {
                                displayText("Black Wins!");
                            } else {
                                displayText("White Wins!");
                            }
                        }
                        else{
                            Search search = new Search(currentBoard);
                            Move computerMove = search.searchMoves();
                            currentBoard.makeMove(computerMove);
                            updateDisplay(currentBoard);
                            currentBoard = new Board(currentBoard.getPieces(), 1 - currentBoard.getColorToMove(),
                                    currentBoard.getEnPassantTile(), currentBoard.getWk(), currentBoard.getWq(),
                                    currentBoard.getBk(), currentBoard.getBq());
                        }

                        continueTurn = false;
                        break;
                    }
                }
            }

            if (continueTurn) {

                if (currentBoard.isPieceOnTile(tileNum) && currentBoard.getPieceOnTile(tileNum).getColor() == currentBoard.getColorToMove()) {

                    ArrayList<Move> legalMoves = currentBoard.getPieceOnTile(tileNum).generateLegalMoves(currentBoard);
                    currentBoard.setCurrentPossibleMoves(legalMoves);

                    if (legalMoves.isEmpty()) {
                        highlightTileRed(tileNum);
                    } else {
                        highlightTileGreen(tileNum);
                    }

                    for (Move move : legalMoves) {
                        highlightTile(move.getEndPosition());
                    }
                }
            }
        }

        //int score = Evaluation.evaluate(currentBoard);
        //displayText(String.valueOf(score));

/*
        int moveCount = countMoves(3, 0);

        displayText(String.valueOf(moveCount));
*/
    }

    int countMoves(int depth, int color){

        if(depth == 0){
            return 1;
        }

        int moveCount = 0;

        ArrayList<Move> legalMoves = getAllMoves(color);

        for(Move move: legalMoves){

            currentBoard.makeMove(move);

            moveCount += countMoves(depth - 1, 1 - color);

            currentBoard.unMakeMove(move);
        }

        return moveCount;
    }

    static ArrayList<Move> getAllMoves(int color){

        ArrayList<Move> allLegalMoves = new ArrayList<Move>();

        for(int i = 0; i < 64; i ++){
            if(currentBoard.isPieceOnTile(i) && currentBoard.getPieceOnTile(i).getColor() == color) {

                allLegalMoves.addAll(currentBoard.getPieceOnTile(i).generateLegalMoves(currentBoard));
            }
        }
        return allLegalMoves;
    }

    public void createTestBoard(){

        ArrayList<Piece> newBoardPieceArray = new ArrayList<Piece>();

        newBoardPieceArray.add(new King(4, 1));
        newBoardPieceArray.add(new Rook(0, 1));
        newBoardPieceArray.add(new Rook(7, 1));

        newBoardPieceArray.add(new King(60, 0));
        newBoardPieceArray.add(new Rook(63, 0));
        newBoardPieceArray.add(new Rook(56, 0));

        Board board = new Board(newBoardPieceArray, 0, -1, 1, 1, 1, 1);

        currentBoard = board;

        updateDisplay(board);
    }

    public void createStartBoard(){

        ArrayList<Piece> newBoardPieceArray = new ArrayList<Piece>();
        // black pieces
        newBoardPieceArray.add(new Rook(0, 1));
        newBoardPieceArray.add(new Knight(1, 1));
        newBoardPieceArray.add(new Bishop(2, 1));
        newBoardPieceArray.add(new Queen(3, 1));
        newBoardPieceArray.add(new King(4, 1));
        newBoardPieceArray.add(new Bishop(5, 1));
        newBoardPieceArray.add(new Knight(6, 1));
        newBoardPieceArray.add(new Rook(7, 1));
        newBoardPieceArray.add(new Pawn(8, 1));
        newBoardPieceArray.add(new Pawn(9, 1));
        newBoardPieceArray.add(new Pawn(10, 1));
        newBoardPieceArray.add(new Pawn(11, 1));
        newBoardPieceArray.add(new Pawn(12, 1));
        newBoardPieceArray.add(new Pawn(13, 1));
        newBoardPieceArray.add(new Pawn(14, 1));
        newBoardPieceArray.add(new Pawn(15, 1));
        // white pieces
        newBoardPieceArray.add(new Rook(56, 0));
        newBoardPieceArray.add(new Knight(57, 0));
        newBoardPieceArray.add(new Bishop(58, 0));
        newBoardPieceArray.add(new Queen(59, 0));
        newBoardPieceArray.add(new King(60, 0));
        newBoardPieceArray.add(new Bishop(61, 0));
        newBoardPieceArray.add(new Knight(62, 0));
        newBoardPieceArray.add(new Rook(63, 0));
        newBoardPieceArray.add(new Pawn(48, 0));
        newBoardPieceArray.add(new Pawn(49, 0));
        newBoardPieceArray.add(new Pawn(50, 0));
        newBoardPieceArray.add(new Pawn(51, 0));
        newBoardPieceArray.add(new Pawn(52, 0));
        newBoardPieceArray.add(new Pawn(53, 0));
        newBoardPieceArray.add(new Pawn(54, 0));
        newBoardPieceArray.add(new Pawn(55, 0));

        Board board = new Board(newBoardPieceArray, 0, -1, 1, 1, 1, 1);

        currentBoard = board;

        updateDisplay(board);
    }

    public void highlightTile(int tileNum){

        ImageButton tile = findViewById(MainActivity.TILE_IDS[tileNum]);
        if(IS_DARK_SQUARE[tileNum]){
            tile.setBackgroundColor(getResources().getColor(R.color.dark_square_highlight));
        }
        else {
            tile.setBackgroundColor(getResources().getColor(R.color.light_square_highlight));
        }
    }

    public void highlightTileGreen(int tileNum){

        ImageButton tile = findViewById(MainActivity.TILE_IDS[tileNum]);
        tile.setBackgroundColor(getResources().getColor(R.color.green_highlight));
    }

    public void highlightTileRed(int tileNum){

        ImageButton tile = findViewById(MainActivity.TILE_IDS[tileNum]);
        tile.setBackgroundColor(getResources().getColor(R.color.red_highlight));
    }

    public void unHighlightTile(int tileNum){

        ImageButton tile = findViewById(MainActivity.TILE_IDS[tileNum]);
        if(IS_DARK_SQUARE[tileNum]){
            tile.setBackgroundColor(getResources().getColor(R.color.dark_square));
        }
        else {
            tile.setBackgroundColor(getResources().getColor(R.color.light_square));
        }
    }

    public void unHighlightAllTiles(){

        for(int i = 0; i < 64; i ++){
            unHighlightTile(i);
        }
    }

    public void updateDisplay(Board board){

        clearDisplay();

        final ArrayList<Piece> pieces = board.getPieces();

        for(Piece piece : pieces){

            ImageButton tile = findViewById(MainActivity.TILE_IDS[piece.getPosition()]);
            tile.setImageResource(MainActivity.PIECE_IDS.get(piece.getPieceName()));
        }
    }

    public void clearDisplay(){
        for(int i = 0; i < 64; i ++){
            ImageButton tile = findViewById(MainActivity.TILE_IDS[i]);
            tile.setImageResource(MainActivity.PIECE_IDS.get("empty"));
        }
    }

    public void displayText(String text){
        TextView textV = findViewById(R.id.textView);
        textV.setText(text);
    }
}