package br.com.givailson.sebraelikeaboss.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ParticipantRepository {
    private ParticipantDao participantDao;
    private LiveData<List<Participant>> participants;

    ParticipantRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        participantDao = db.participantDao();
        participants = participantDao.getAllParticipants();
    }

    public void insert (Participant participant) {
        new insertAsyncTask(participantDao).execute(participant);
    }

    public LiveData<List<Participant>> listParticipantes() {
        return participants;
    }

    private static class insertAsyncTask extends AsyncTask<Participant, Void, Void> {
        private ParticipantDao participanteDao;

        insertAsyncTask(ParticipantDao dao) {
            participanteDao = dao;
        }

        @Override
        protected Void doInBackground(final Participant... participants) {
            participanteDao.insert(participants[0]);
            return null;
        }
    }
}
