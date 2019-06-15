package com.example.amnsh.enigmaandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;

public class MainActivity extends AppCompatActivity {

    Button doneButton, plugButton;
    EditText messageText;
    TextView encryptedText;

    ScrollableNumberPicker firstNumberPicker, secondNumberPicker, thirdNumberPicker;

    EnigmaMachine enigma;

    Rotor rotor1, rotor2, rotor3;

    String plugBoardValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plugBoardValues = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        try {
            Intent intent = getIntent();
            String gotString = intent.getExtras().getString("plugBoardValues");
            if(gotString != null){
                plugBoardValues = gotString;
            }

        }catch (Exception e){
            Toast.makeText(this, "Using default Plug-board settings", Toast.LENGTH_SHORT).show();
        }


        doneButton = findViewById(R.id.DoneButton);
        messageText = findViewById(R.id.message);
        encryptedText = findViewById(R.id.encrypted);
        plugButton = findViewById(R.id.plugBoard);

        rotor1 = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        rotor2 = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        rotor3 = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");

        View firstRotor = findViewById(R.id.firstRotor);
        firstNumberPicker = firstRotor.findViewById(R.id.numberPicker);

        View secondRotor = findViewById(R.id.secondRotor);
        secondNumberPicker = secondRotor.findViewById(R.id.numberPicker);

        View thirdRotor = findViewById(R.id.thirdRotor);
        thirdNumberPicker = thirdRotor.findViewById(R.id.numberPicker);


        enigma = new EnigmaMachine(rotor1, rotor2, rotor3,plugBoardValues);

    }

    public void callPlugBoard(View view) {


        Intent intent = new Intent(this, PlugBoard.class);
        intent.putExtra("plugBoardValues",enigma.plugBoardValues);
        startActivity(intent);
        //finish();
    }

    public void displayInstruction(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle)
                .setTitle("Instructions")
                .setMessage(R.string.Instructions)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.show();
    }

    public void encrypt(View view) {
        try {
            enigma.firstRotor.setCounter(firstNumberPicker.getValue() - 1);
            enigma.secondRotor.setCounter(secondNumberPicker.getValue() - 1);
            enigma.thirdRotor.setCounter(thirdNumberPicker.getValue() - 1);
            enigma.plugBoardValues = plugBoardValues;
            String message = messageText.getText().toString();

            message = message.toUpperCase();
            message = message.replaceAll("\\s", "");
            String encryptedMessage="";
            try {
                encryptedMessage = enigma.encryption(message);
            }catch (Exception e){
                Toast.makeText(this, "Couldn't encrypt. Please remove characters other than letters.", Toast.LENGTH_SHORT).show();
            }
            encryptedText.setText(encryptedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
