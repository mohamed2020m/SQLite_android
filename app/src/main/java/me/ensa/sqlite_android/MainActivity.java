package me.ensa.sqlite_android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import me.ensa.sqlite_android.classes.Etudiant;
import me.ensa.sqlite_android.service.EtudiantService;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText prenom, nom;
    private Button add;

    private TextInputEditText id;
    private Button rechercher, delete;
    private TextView res, rr;

    //Méthode pour vider les champs après l’ajout
    void clear(){
        nom.setText("");
        prenom.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EtudiantService es = new EtudiantService(this);

        //Insertion des étudiants
//        es.create(new Etudiant("ALAMI", "ALI"));
//        es.create(new Etudiant("RAMI", "AMAL"));
//        es.create(new Etudiant("SAFI", "MHMED"));
//        es.create(new Etudiant("SELAOUI", "REDA"));
//        es.create(new Etudiant("ALAMI", "WAFA"));
//
//        //Parcourir la liste des étudiants
//        for(Etudiant e : es.findAll()){
//            Log.d(e.getId()+"", e.getNom()+" "+e.getPrenom());
//        }
//
//        //Supprimer l'élement dont id = 3
//        es.delete(es.findById(3));
//
//        //Liste après suppression
//        Log.d("delete","Après suppression");
//        for(Etudiant e : es.findAll()){
//            Log.d(e.getId()+"", e.getNom()+" "+e.getPrenom());
//        }


        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        add = (Button)findViewById(R.id.bn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insertion des étudiants
                es.create(new Etudiant(nom.getText().toString(), prenom.getText().toString()));
                clear();
                //Parcourir la liste des étudiants
                for(Etudiant e : es.findAll()){
                    Log.d(e.getId()+"", e.getNom()+" "+e.getPrenom());
                }
            }
        });

        id = findViewById(R.id.id);
        rechercher = (Button)findViewById(R.id.load);
        delete = (Button) findViewById(R.id.delete);
        res = (TextView)findViewById(R.id.res);
        rr = findViewById(R.id.rr);
        rr.setVisibility(View.GONE);

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rr.setVisibility(View.VISIBLE);
                try {
                    Etudiant e = es.findById(Integer.parseInt(id.getText() + ""));
                    res.setText(e.getNom()+" "+e.getPrenom());
                }catch (Exception e) {
                    res.setText("Not Found!");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    es.delete(es.findById(Integer.parseInt(id.getText() + "")));
                    Toast.makeText(MainActivity.this, "Etudiant ete Supprimer avec success!", Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    res.setText("Can't delete!");
                }
            }
        });

    }
}

