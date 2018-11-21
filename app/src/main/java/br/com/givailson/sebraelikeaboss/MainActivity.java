package br.com.givailson.sebraelikeaboss;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import br.com.givailson.sebraelikeaboss.database.Participant;
import br.com.givailson.sebraelikeaboss.database.ParticipantViewModel;
import br.com.givailson.sebraelikeaboss.views.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private ParticipantViewModel participantViewModel;
    private View btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareDatabase();
        btRegister = findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegistry();
            }
        });

//        Participant participant = new Participant("Givailson", "givailson@gmail.com", "TI", "xii", new Date());
//        participantViewModel.insert(participant);
    }

    private void startRegistry() {

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void prepareDatabase() {
        participantViewModel = ViewModelProviders
                .of(this).get(ParticipantViewModel.class);

        participantViewModel.listParticipants()
                .observe(this, new Observer<List<Participant>>() {
                    @Override
                    public void onChanged(@Nullable List<Participant> participants) {
                        Log.v(MainActivity.class.getName(), participants.size() + "");
                    }
                });
    }
}
