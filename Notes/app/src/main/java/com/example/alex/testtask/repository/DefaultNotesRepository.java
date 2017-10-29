package com.example.alex.testtask.repository;

import android.support.annotation.NonNull;

import com.example.alex.testtask.model.Note;
import com.example.alex.testtask.utils.DateUtil;
import com.example.alex.testtask.utils.RxUtil;

import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

public class DefaultNotesRepository implements NotesRepository {

    @Override
    public Observable<List<Note>> getNotes() {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                RealmResults<Note> notesInRealm = realm.where(Note.class).findAll();
                return realm.copyFromRealm(notesInRealm);
            }
        })
        .compose(RxUtil.async());
    }

    @Override
    public Observable<Note> addNote(@NonNull Note note) {
        return saveNote(UUID.randomUUID().toString(), note);
    }

    @Override
    public Observable<Note> updateNote(@NonNull Note note) {
        return saveNote(note.getId(), note);
    }

    private Observable<Note> saveNote(@NonNull String id, @NonNull Note note) {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                realm.executeTransaction(database -> {
                    note.setId(id);
                    note.setDateCreated(DateUtil.getCurrentDate());
                    database.copyToRealmOrUpdate(note);
                });
                return note;
            }
        })
        .compose(RxUtil.async());
    }

    @Override
    public Observable<Note> deleteNote(@NonNull Note note) {
        return Observable.fromCallable(() -> {
            try (Realm realm = Realm.getDefaultInstance()) {
                realm.executeTransaction(database -> {
                    RealmResults<Note> noteInRealm =
                            database.where(Note.class).equalTo("mId", note.getId()).findAll();
                    noteInRealm.deleteAllFromRealm();
                });
                return note;
            }
        })
        .compose(RxUtil.async());
    }
}
