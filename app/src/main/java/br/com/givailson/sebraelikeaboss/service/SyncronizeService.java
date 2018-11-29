package br.com.givailson.sebraelikeaboss.service;

import android.app.Service;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.givailson.sebraelikeaboss.MainActivity;
import br.com.givailson.sebraelikeaboss.database.Participant;
import br.com.givailson.sebraelikeaboss.database.ParticipantRepository;
import br.com.givailson.sebraelikeaboss.database.ParticipantViewModel;
import br.com.givailson.sebraelikeaboss.models.BaseRequest;
import br.com.givailson.sebraelikeaboss.models.Register;
import br.com.givailson.sebraelikeaboss.utils.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncronizeService extends Service {

    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    private static ParticipantRepository participantRepository;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        participantRepository = new ParticipantRepository(this.getApplication());
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.i("SyncronizeService", "running");
                new listParticipantesAsync().execute();
                handler.postDelayed(runnable, 10000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
    }

    private static void syncronizeParticipant(final Participant p) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Register r = new Register();
        r.email = p.email;
        r.name = p.name;
        r.actuationSector = p.actuationSector;
        r.businessPlus = p.businessPlus;
        r.enterpriseName = p.enterpriseName;

        if(p.participationDate != null)
            r.participationDate = sdf.format(p.participationDate);

        r.sellForInternet = p.sellForInternet;
        r.timeOfBusiness = p.timeOfBusiness;
        r.hasBusiness = p.hasBusiness;



        Call<BaseRequest> registerCall = new RetrofitConfig().getRegisterService().register(r);
        registerCall.enqueue(new Callback<BaseRequest>() {
            @Override
            public void onResponse(Call<BaseRequest> call, Response<BaseRequest> response) {
                Log.i("SyncronizeService", response.toString());
                if (response.code() == 200)
                    participantRepository.delete(p);
            }

            @Override
            public void onFailure(Call<BaseRequest> call, Throwable t) {
                Log.i("SyncronizeService", t.getMessage());
            }
        });
    }

    private static class listParticipantesAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            List<Participant> participants = participantRepository.listParticipantesNotSicronized();
            Log.i("SyncronizeService", participants.size() + "");

            for(Participant p : participants) {
                syncronizeParticipant(p);
            }

            return null;
        }
    }

}
