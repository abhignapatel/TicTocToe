package com.jaydonga.tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private Context context;
    private final Player mPlayer1;
    private final Player mPlayer2;
    private CallBack mCallBack;
    private int[][] array = new int[3][3];
    private int counter = 0;

    private int[] symobolArray = new int[9];
    private static final int INVALID = -1;
    private boolean continueGame = true;

    GameAdapter(Context context, Player player1, Player player2, CallBack callBack) {
        this.context = context;
        mPlayer1 = player1;
        mPlayer2 = player2;
        mCallBack = callBack;
        for (int i = 0; i < symobolArray.length; i++) {
            symobolArray[i] = INVALID;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = INVALID;
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (symobolArray[position] == INVALID) {
            holder.gameTxt.setText("");
        } else {
            holder.gameTxt.setText(String.valueOf(symobolArray[position]));
        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView gameTxt;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            gameTxt = itemView.findViewById(R.id.itemTxt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (symobolArray[position] == INVALID && continueGame) {
                        counter++;
                        int symbol;
                        if (counter % 2 == 1) {
                            symbol = mPlayer1.getSymbol();
                        } else {
                            symbol = mPlayer2.getSymbol();
                        }

                        int row = position / 3;
                        int column = position % 3;

                        array[row][column] = symbol;
                        symobolArray[position] = symbol;
                        notifyDataSetChanged();

                        //player changed
                        mCallBack.clicked(counter);

                        if (hasPlayerWon(mPlayer1)) {
                            mCallBack.won("Player 1 has won");
                            continueGame = false;
                        } else if (hasPlayerWon(mPlayer2)) {
                            mCallBack.won("Player 2 has won");
                            continueGame = false;
                        }
                    }
                }
            });
        }
    }

    private boolean hasPlayerWon(Player player) {
        int symbol = player.getSymbol();
        int countOfSymbols = 0;
        for (int i : symobolArray) {
            if (i == symbol) {
                countOfSymbols++;
            }
        }
        if (countOfSymbols < 3) {
            return false;
        }
        if (array[0][0] == symbol) {

            //checking horizontal direction
            if (array[0][1] == symbol && array[0][2] == symbol) {
                return true;
            }
            //checking vertical direction

            if (array[1][0] == symbol && array[2][0] == symbol) {
                return true;
            }
            //checking diagonal direction
            if (array[1][1] == symbol && array[2][2] == symbol) {
                return true;
            }

        }
        if (array[1][0] == symbol) {

            //checking horizontal direction
            if (array[1][1] == symbol && array[1][2] == symbol) {
                return true;
            }

        }
        if (array[2][0] == symbol) {

            //checking horizontal direction
            if (array[2][1] == symbol && array[2][2] == symbol) {
                return true;
            }

        }
        if (array[0][1] == symbol) {

            //checking vertical direction
            if (array[1][1] == symbol && array[2][1] == symbol) {
                return true;
            }

        }
        if (array[0][2] == symbol) {

            //checking vertical direction
            if (array[1][2] == symbol && array[2][2] == symbol) {
                return true;
            }
            //checking diagonal direction
            if (array[1][1] == symbol && array[2][0] == symbol) {
                return true;
            }

        }
        return false;
    }
}
