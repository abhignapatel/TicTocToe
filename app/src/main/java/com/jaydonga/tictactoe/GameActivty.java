package com.jaydonga.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivty extends AppCompatActivity implements CallBack {

    Button resetBtn;
    TextView turn;
    Player player1;
    Player player2;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activty);

        resetBtn = findViewById(R.id.resetBtn);
        turn = findViewById(R.id.turn);
        TextView player1Edt = findViewById(R.id.player1);
        TextView player2Edt = findViewById(R.id.player2);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);


        intent = getIntent();

        player1 = new Player(Player.PlayerNumber.Player1, intent.getIntExtra("player1", -1));
        player2 = new Player(Player.PlayerNumber.Player2, intent.getIntExtra("player2", -1));

        player1Edt.setText("player1 symbol:" + player1.getSymbol());
        player2Edt.setText("player2 symbol:" + player2.getSymbol());

        GameAdapter adapter = new GameAdapter(this, player1, player2, this);

        recyclerView.setAdapter(adapter);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.setAction(Intent.ACTION_PACKAGE_DATA_CLEARED);
                startActivity(intent);
            }
        });
    }

    @Override
    public void clicked(int count) {

        if (count % 2 == 1) {
          turn.setText("player1 turn");
        } else {
            turn.setText("player2 turn");
        }

    }

    @Override
    public void won(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

}
