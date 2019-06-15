package com.example.amnsh.enigmaandroid;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PlugBoard extends AppCompatActivity {

    Button[] buttons = new Button[26];
    Button done;

    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String staticCharPositions = letters;
    char[] plugBoardValues = letters.toCharArray();

    final Context thisContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug_board);
        buttonInitializer();
        done = findViewById(R.id.doneButton);

        Intent intent = getIntent();
        String plugBoardChars = intent.getExtras().getString("plugBoardValues");
        plugBoardValues = plugBoardChars.toCharArray();


        nameButtons();
    }

    public void nameButtons(){
        buttonInitializer();
        for(int i=0; i<25;i++){
            Log.i("Here",buttons[i].getText().toString());
            buttons[i].setText(staticCharPositions.charAt(i)+":\n"+plugBoardValues[i]);
        }
    }

    public void buttonInitializer(){

        buttons[0] = findViewById(R.id.A);
        buttons[1] = findViewById(R.id.B);
        buttons[2] = findViewById(R.id.C);
        buttons[3] = findViewById(R.id.D);
        buttons[4] = findViewById(R.id.E);
        buttons[5] = findViewById(R.id.F);
        buttons[6] = findViewById(R.id.G);
        buttons[7] = findViewById(R.id.H);
        buttons[8] = findViewById(R.id.I);
        buttons[9] = findViewById(R.id.J);
        buttons[10] = findViewById(R.id.K);
        buttons[11] = findViewById(R.id.L);
        buttons[12] = findViewById(R.id.M);
        buttons[13] = findViewById(R.id.N);
        buttons[14] = findViewById(R.id.O);
        buttons[15] = findViewById(R.id.P);
        buttons[16] = findViewById(R.id.Q);
        buttons[17] = findViewById(R.id.R);
        buttons[18] = findViewById(R.id.S);
        buttons[19] = findViewById(R.id.T);
        buttons[20] = findViewById(R.id.U);
        buttons[21] = findViewById(R.id.V);
        buttons[22] = findViewById(R.id.W);
        buttons[23] = findViewById(R.id.X);
        buttons[24] = findViewById(R.id.Y);
        buttons[25] = findViewById(R.id.Z);

    }

    Button pressed;
    public void buttonPressed(View view) {
        try {
            final String[] letssee = {"A", "B","C","D","E","F","G","H","I","J","K",
                    "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
            pressed = (Button) view;
            buttonInitializer();

            new AlertDialog.Builder(thisContext, R.style.AlertDialogStyle)
                    .setTitle("Choose a letter to swap with")
                    .setItems(letssee, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String buttonText = pressed.getText().toString();
                            String values[] = buttonText.split(":\n");

                            char first = values[0].charAt(0);
                            char second = values[1].charAt(0);
                            char third = staticCharPositions.charAt(which);
                            char fourth = plugBoardValues[staticCharPositions.indexOf(third)];


                            plugBoardValues[staticCharPositions.indexOf(second)] = second;
                            plugBoardValues[staticCharPositions.indexOf(fourth)] = fourth;


                            plugBoardValues[staticCharPositions.indexOf(first)] = third;
                            plugBoardValues[staticCharPositions.indexOf(third)] = first;


                            buttons[staticCharPositions.indexOf(second)].setText(second + ":\n" + second);
                            buttons[staticCharPositions.indexOf(fourth)].setText(fourth + ":\n" + fourth);


                            buttons[staticCharPositions.indexOf(second)].setText(second + ":\n" + second);
                            buttons[staticCharPositions.indexOf(fourth)].setText(fourth + ":\n" + fourth);

                            buttons[staticCharPositions.indexOf(first)].setText(first + ":\n" + third);

                            buttons[staticCharPositions.indexOf(third)].setText(third + ":\n" + first);


                        }
                    }).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doneWithPlugBoard(View view){
        Intent intent = new Intent(this, MainActivity.class);
        String toReturn = new String(plugBoardValues);
        intent.putExtra("plugBoardValues",toReturn);
        startActivity(intent);
        finish();
    }


}





