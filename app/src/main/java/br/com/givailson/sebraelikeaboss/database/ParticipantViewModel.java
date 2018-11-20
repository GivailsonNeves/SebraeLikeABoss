package br.com.givailson.sebraelikeaboss.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ParticipantViewModel extends AndroidViewModel {

    private ParticipantRepository repository;

    private LiveData<List<Participant>> participantes;

    public ParticipantViewModel (Application application) {
        super(application);
        repository = new ParticipantRepository(application);
        participantes = repository.listParticipantes();
    }

    public LiveData<List<Participant>> listParticipants () { return participantes; }

    public void insert (Participant participant) { repository.insert(participant); }
}
