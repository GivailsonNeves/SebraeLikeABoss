package br.com.givailson.sebraelikeaboss;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;
import java.util.List;

import br.com.givailson.sebraelikeaboss.database.Participant;
import br.com.givailson.sebraelikeaboss.database.ParticipantViewModel;

public class MainActivity extends AppCompatActivity {

    private ParticipantViewModel participantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareDatabase();

        Participant participant = new Participant("Givailson", "givailson@gmail.com", "TI", "xii", new Date());

        participantViewModel.insert(participant);
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
