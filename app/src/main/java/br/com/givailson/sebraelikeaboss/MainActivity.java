package br.com.givailson.sebraelikeaboss;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Date;
import java.util.List;

import br.com.givailson.sebraelikeaboss.database.Participant;
import br.com.givailson.sebraelikeaboss.database.ParticipantViewModel;
import br.com.givailson.sebraelikeaboss.models.BaseRequest;
import br.com.givailson.sebraelikeaboss.models.Register;
import br.com.givailson.sebraelikeaboss.service.SyncronizeService;
import br.com.givailson.sebraelikeaboss.utils.RetrofitConfig;
import br.com.givailson.sebraelikeaboss.views.RegisterActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ParticipantViewModel participantViewModel;
    View vMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareDatabase();
        prepareEvents();

    }

    private void prepareEvents() {
        vMain = findViewById(R.id.vMain);
        vMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegistry();
            }
        });
        startService(new Intent(this, SyncronizeService.class));
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
