package com.jaydonga.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText player1,Player2;
    Button startBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = findViewById(R.id.edtPlayer1);
        Player2 = findViewById(R.id.edtPlayer2);
        startBtn = findViewById(R.id.startBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = true;
                if (player1.length() == 0 || Player2.length() == 0) {
                    isValid = false;
                    Toast.makeText(getApplicationContext(), "Please enter value", Toast.LENGTH_SHORT).show();
                    return;
                }
                int player_1 = Integer.parseInt(player1.getText().toString());
                int player_2 = Integer.parseInt(Player2.getText().toString());
                if (player_1 == player_2) {
                    Toast.makeText(getApplicationContext(), "Please enter different values", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent =  new Intent(MainActivity.this,GameActivty.class);
                intent.putExtra("player1",player_1);
                intent.putExtra("player2",player_2);
                startActivity(intent);
            }
        });

    }
}
