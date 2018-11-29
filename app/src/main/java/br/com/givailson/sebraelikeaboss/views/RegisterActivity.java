package br.com.givailson.sebraelikeaboss.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.givailson.sebraelikeaboss.R;
import br.com.givailson.sebraelikeaboss.database.Participant;
import br.com.givailson.sebraelikeaboss.database.ParticipantViewModel;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNome;
    private EditText etEmail;
    private EditText etEmpresa;
    private EditText etSector;
    private Button btBussineIdeia;
    private Button btBussinesNo;
    private Button btBussinesYes;
    private Button btDiferentialPrice;
    private Button btDiferentialQuality;
    private Button btDiferentialInovation;
    private Button btInternetNo;
    private Button btInternetYes;
    private Button btnSave;
    private Participant participant;
    private Button btTimeLessOne;
    private Button btTimeMoreThanTwo;
    private Button btTimeOneToTwo;
    private ParticipantViewModel participantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        participant = new Participant();
        this.config();
    }

    private void config() {

        participantViewModel = ViewModelProviders
                .of(this).get(ParticipantViewModel.class);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etEmpresa = findViewById(R.id.etEmpresa);
        etSector = findViewById(R.id.etSetor);

        btBussineIdeia = findViewById(R.id.btBussineIdeia);
        btBussinesNo = findViewById(R.id.btBussinesNo);
        btBussinesYes = findViewById(R.id.btBussinesYes);

        btDiferentialPrice = findViewById(R.id.btDiferentialPrice);
        btDiferentialQuality = findViewById(R.id.btDiferentialQuality);
        btDiferentialInovation = findViewById(R.id.btDiferentialInovation);

        btInternetNo = findViewById(R.id.btInternetNo);
        btInternetYes = findViewById(R.id.btInternetYes);

        btTimeLessOne = findViewById(R.id.btTimeLessOne);
        btTimeMoreThanTwo = findViewById(R.id.btTimeMoreThanTwo);
        btTimeOneToTwo = findViewById(R.id.btTimeOneToTwo);

        btBussineIdeia.setOnClickListener(this);
        btBussinesNo.setOnClickListener(this);
        btBussinesYes.setOnClickListener(this);

        btDiferentialInovation.setOnClickListener(this);
        btDiferentialPrice.setOnClickListener(this);
        btDiferentialQuality.setOnClickListener(this);

        btInternetNo.setOnClickListener(this);
        btInternetYes.setOnClickListener(this);

        btTimeLessOne.setOnClickListener(this);
        btTimeMoreThanTwo.setOnClickListener(this);
        btTimeOneToTwo.setOnClickListener(this);

        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( participant.timeOfBusiness == null ||
                     participant.hasBusiness == null ||
                     participant.businessPlus == null ||
                     etNome.getText().toString().isEmpty() ||
                     etEmail.getText().toString().isEmpty() ||
                     etEmpresa.getText().toString().isEmpty() ||
                     etSector.getText().toString().isEmpty() ) {

                    showAlert("Preencha todos os campos!");

                } else {
                    participant.name = etNome.getText().toString();
                    participant.email = etEmail.getText().toString();
                    participant.enterpriseName = etEmpresa.getText().toString();
                    participant.actuationSector = etSector.getText().toString();
                    saveData();
                }
            }
        });
    }

    private void saveData () {

        this.participantViewModel.insert(participant);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Dados salvos com sucesso!")
                .setPositiveButton("ok", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("ok", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Button b = (Button) v;

        switch (id) {
            case R.id.btBussineIdeia:
            case R.id.btBussinesNo:
            case R.id.btBussinesYes:
                btBussinesYes.setAlpha(0.4f);
                btBussineIdeia.setAlpha(0.4f);
                btBussinesNo.setAlpha(0.4f);
                participant.hasBusiness = b.getText().toString();
                break;
            case R.id.btDiferentialInovation:
            case R.id.btDiferentialPrice:
            case R.id.btDiferentialQuality:
                btDiferentialQuality.setAlpha(0.4f);
                btDiferentialPrice.setAlpha(0.4f);
                btDiferentialInovation.setAlpha(0.4f);
                participant.businessPlus = b.getText().toString();
                break;
            case R.id.btInternetNo:
            case R.id.btInternetYes:
                btInternetYes.setAlpha(0.4f);
                btInternetNo.setAlpha(0.4f);
                participant.sellForInternet = id == R.id.btInternetYes;
                break;
            case R.id.btTimeLessOne:
            case R.id.btTimeMoreThanTwo:
            case R.id.btTimeOneToTwo:
                btTimeLessOne.setAlpha(0.4f);
                btTimeMoreThanTwo.setAlpha(0.4f);
                btTimeOneToTwo.setAlpha(0.4f);
                participant.timeOfBusiness = b.getText().toString();
                break;

        }

        v.setAlpha(1.0f);
    }
}
