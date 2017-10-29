package ru.gdgkazan.pictureofdaymvvm.content;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * @author Alexey Pakhtusov
 */
public class DayPicture extends BaseObservable implements Parcelable {

    @SerializedName("date")
    private String mDate;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("explanation")
    private String mExplanation;

    @SerializedName("url")
    private String mUrl;

    public DayPicture() { }

    public DayPicture(@NonNull String date, @NonNull String title, @NonNull String explanation,
                      @NonNull String url) {
        mDate = date;
        mTitle = title;
        mExplanation = explanation;
        mUrl = url;
    }


    protected DayPicture(Parcel in) {
        mDate = in.readString();
        mTitle = in.readString();
        mExplanation = in.readString();
        mUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDate);
        dest.writeString(mTitle);
        dest.writeString(mExplanation);
        dest.writeString(mUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DayPicture> CREATOR = new Creator<DayPicture>() {
        @Override
        public DayPicture createFromParcel(Parcel in) {
            return new DayPicture(in);
        }

        @Override
        public DayPicture[] newArray(int size) {
            return new DayPicture[size];
        }
    };

    @Bindable
    @NonNull
    public String getDate() {
        return mDate;
    }

    public void setDate(@NonNull String date) {
        mDate = date;
    }

    @Bindable
    @NonNull
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(@NonNull String title) {
        mTitle = title;
    }

    @Bindable
    @NonNull
    public String getExplanation() {
        return mExplanation;
    }

    public void setExplanation(@NonNull String explanation) {
        mExplanation = explanation;
    }

    @Bindable
    @NonNull
    public String getUrl() {
        return mUrl;
    }

    public void setUrl(@NonNull String url) {
        mUrl = url;
    }
}
