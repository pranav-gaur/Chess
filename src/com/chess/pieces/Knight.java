package com.chess.pieces;

import com.chess.Alliance;
import com.chess.board.Board;
import com.chess.board.BoardUtils;
import com.chess.board.Move;
import com.chess.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece
{
    Knight(int piecePosition, Alliance pieceAlliance)
    {
        super(piecePosition, pieceAlliance);
    }

    private static final int[] CANDIDATE_MOVE_COORDINATE = {-17,-15,-10,-6,6,10,15,17};



    @Override
    public List<Move> calculateLegalMoves(Board board)
    {
        final List<Move> legalMoves = new ArrayList<>();
        for(int offset : CANDIDATE_MOVE_COORDINATE)
        {
            final int candidateDestinationCoordinate = this.piecePosition + offset;

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
            {
                if( isFirstColumnExclusion(this.piecePosition, offset) ||
                        isSecondColumnExclusion(this.piecePosition, offset) ||
                        isSeventhColumnExclusion(this.piecePosition, offset) ||
                        isEighthColumnExclusion(this.piecePosition, offset))
                {
                    continue;
                }

                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                if(!candidateDestinationTile.isTileOccupied())
                {
                    legalMoves.add(new Move());
                }
                else
                {
                    final Piece pieceAtDestination = board.getPiece(candidateDestinationCoordinate);
                    final Alliance pieceAtDestinationAlliance = pieceAtDestination.getAlliance();
                    if(this.pieceAlliance != pieceAtDestinationAlliance)
                    {
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }


    private boolean isFirstColumnExclusion(int currentPosition, int candidateOffset)
    {
        return BoardUtils.FIRST_COULMN[currentPosition] && ((candidateOffset == -17) ||
                (candidateOffset == -10) || (candidateOffset == 6) || (candidateOffset == 15));
    }

    private static boolean isSecondColumnExclusion(final int currentPosition,
                                                   final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || (candidateOffset == 6));
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition,
                                                    final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition,
                                                   final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -15) || (candidateOffset == -6) ||
                (candidateOffset == 10) || (candidateOffset == 17));
    }



}
