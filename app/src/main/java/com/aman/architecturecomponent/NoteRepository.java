package com.aman.architecturecomponent;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allnotes;

    public NoteRepository(Application application){

        NoteDatabase database= NoteDatabase.getInstance(application);
        noteDao=database.noteDao();
        allnotes=noteDao.getAllNotes();

    }


    public void insert(Note note){

        new InsertNoteAsyncTasck(noteDao).execute(note);
    }

    public void update(Note note){

        new UpdateNoteAsyncTasck(noteDao).execute(note);
    }

    public void delete(Note note){

        new DeleteNoteAsyncTasck(noteDao).execute(note);

    }

    public void deleteAllNotes(){
        new DeleteAllNoteAsyncTasck(noteDao).execute();
    }


    public LiveData<List<Note>> getAllnotes(){

        return allnotes;

    }


    private static class InsertNoteAsyncTasck extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private InsertNoteAsyncTasck(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);

            return null;
        }
    }

    private static class UpdateNoteAsyncTasck extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private UpdateNoteAsyncTasck(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);

            return null;
        }
    }

    private static class DeleteNoteAsyncTasck extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        private DeleteNoteAsyncTasck(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);

            return null;
        }
    }

    private static class DeleteAllNoteAsyncTasck extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;

        private DeleteAllNoteAsyncTasck(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();

            return null;
        }
    }
}
