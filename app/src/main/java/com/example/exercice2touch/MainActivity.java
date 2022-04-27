package com.example.exercice2touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button b1, b2, b3, b4;
    EditText e1, e2;
    RadioGroup grp;
    ImageView image;
    ArrayList<Stagiaire> stgs;
    int position = 0;
    float xInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        grp = findViewById(R.id.rgp);
        image = findViewById(R.id.img);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        stgs= new ArrayList<>();
        stgs.add(new Stagiaire("Nom 1", "Prenom 1", "H", R.drawable.stg1));
        stgs.add(new Stagiaire("Nom 2", "Prenom 2", "F", R.drawable.stg2));
        stgs.add(new Stagiaire("Nom 3", "Prenom 3", "H", R.drawable.stg3));
        stgs.add(new Stagiaire("Nom 4", "Prenom 4", "F", R.drawable.stg4));
        stgs.add(new Stagiaire("Nom 5", "Prenom 5", "F", R.drawable.stg5));
        stgs.add(new Stagiaire("Nom 6", "Prenom 6", "H", R.drawable.stg6));
        stgs.add(new Stagiaire("Nom 7", "Prenom 7", "H", R.drawable.stg1));
        stgs.add(new Stagiaire("Nom 8", "Prenom 8", "F", R.drawable.stg2));

        position = 0;
        afficheDonneesStagiaire();
        image.setOnTouchListener(new View.OnTouchListener( ) {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        xInitial = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        float xFinal = motionEvent.getY();

                        if(xInitial<xFinal)
                            position--;
                        else if(xInitial>xFinal)
                            position ++;
                        afficheDonneesStagiaire();
                        break;
                }
                return true;
            }
        });
    }

    public void afficheDonneesStagiaire() {

        if (position < 0)
            position = stgs.size() - 1;
        else if (position >= stgs.size())
            position = 0;

        e1.setText(stgs.get(position).getNom());
        e2.setText(stgs.get(position).getPrenom());
        if (stgs.get(position).getSexe().equals("H"))
            grp.check(R.id.rdh);
        else
            grp.check(R.id.rdf);
        image.setImageResource(stgs.get(position).getImage());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b1:
                position = 0;
                break;
            case R.id.b2:
                position--;
                break;
            case R.id.b3:
                position++;
                break;
            case R.id.b4:
                position = stgs.size() - 1;
                break;
        }

        afficheDonneesStagiaire();
    }
}