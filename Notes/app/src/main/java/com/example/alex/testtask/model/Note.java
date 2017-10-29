package com.example.alex.testtask.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject implements Serializable {

    @PrimaryKey
    private String mId;

    private String mTitle;

    private String mDescription;

    private String mDateCreated;

    public Note() {
    }

    public Note(String title, String description){
        mId = null;
        mTitle = title;
        mDescription = description;
        mDateCreated = null;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDateCreated() {
        return mDateCreated;
    }

    public void setDateCreated(String dateCreated) {
        mDateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Note note = (Note) o;

        return mId != null ? mId.equals(note.mId) : note.mId == null ||
               mTitle != null ? mTitle.equals(note.mTitle) : note.mTitle == null ||
               mDescription != null ? mDescription.equals(note.mDescription) :
                                      note.mDescription == null ||
               mDateCreated != null ? mDateCreated.equals(note.mDateCreated) :
                                      note.mDateCreated == null;
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        result = 31 * result + (mDateCreated != null ? mDateCreated.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Note{")
                .append("id='").append(mId).append('\'')
                .append(", title='").append(mTitle).append('\'')
                .append(", description='").append(mDescription).append('\'')
                .append(", dateCreated='").append(mDateCreated).append('\'')
                .append('}')
                .toString();
    }
}
