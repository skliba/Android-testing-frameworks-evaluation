package co.infinum.skliba.BoatIt.mvp.presenter.impl;


import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import co.infinum.skliba.BoatIt.R;
import co.infinum.skliba.BoatIt.models.UpboatDownboat.UpboatDownboatResponse;
import co.infinum.skliba.BoatIt.models.comments.CommentsResponse;
import co.infinum.skliba.BoatIt.models.boats.Post;
import co.infinum.skliba.BoatIt.models.comments.CommentsResponseBody;
import co.infinum.skliba.BoatIt.mvp.interactor.DetailsInteractor;
import co.infinum.skliba.BoatIt.mvp.listeners.DetailsListener;
import co.infinum.skliba.BoatIt.mvp.presenter.DetailsPresenter;
import co.infinum.skliba.BoatIt.mvp.view.DetailsView;

/**
 * Created by noxqs on 26.07.15..
 */
public class DetailsPresenterImpl implements DetailsPresenter {

    private DetailsView view;
    private DetailsInteractor detailsInteractor;


    public DetailsPresenterImpl(DetailsView view, DetailsInteractor detailsInteractor) {
        this.view = view;
        this.detailsInteractor = detailsInteractor;
    }

    @Override
    public void getDetails(Post post) {
        detailsInteractor.getDetails(listener);
    }

    @Override
    public void getCommentsPerPost(Post post, String token) {
        detailsInteractor.getComments(listener, post, token);
    }

    @Override
    public void onUpboatClicked(Post post) {
        detailsInteractor.onUpboat(listener, post);
    }

    @Override
    public void onDownboatClicked(Post post) {
        detailsInteractor.onDownboat(listener, post);
    }


    private DetailsListener listener = new DetailsListener() {
        @Override
        public void onDetailsRecieved(Post post) {
            view.onDetailsRecieved(post);
        }

        @Override
        public void onCommentsRecieved(CommentsResponse response) {
            CommentsResponse modifiedResponse = modifyTime(response);
            view.onCommentsRecieved(modifiedResponse.getResponse());
        }

        @Override
        public void onTokenExpired() {
            view.onTokenExpired();
        }

        @Override
        public void onError(String error) {
            view.showError(R.string.PostDetailsError);
        }

        @Override
        public void onUpboat(UpboatDownboatResponse response) {
            view.onUpboatSuccess();
        }

        @Override
        public void onDownboat(UpboatDownboatResponse response) {
            view.onDownboatSuccess();
        }
    };

    private CommentsResponse modifyTime(CommentsResponse response) {
        ArrayList<CommentsResponseBody> body = response.getResponse();
        int days, hours, minutes, seconds;

        for (CommentsResponseBody responseBody : body) {
            String time = responseBody.getTime();
            Log.e("TIME", "" + responseBody.getTime());
            Log.e("CONTENT", "" + responseBody.getContent());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = sdf.parse(time);
                String formattedTime = output.format(date);
                Log.e("FORMATTED TIME", formattedTime);
                Date currDate = new Date();
                String formattedCurrTime = now.format(currDate);
                Log.e("FORMATTED CURRENT TIME", formattedCurrTime);

                days = Days.daysBetween(new DateTime(date).plusHours(2), new DateTime(currDate)).getDays();
                responseBody.setDaysPassed(days);
                if (days == 0) {
                    hours = Hours.hoursBetween(new DateTime(date).plusHours(2), new DateTime(currDate)).getHours();
                    responseBody.setHoursPassed(hours);
                    if (hours == 0) {
                        minutes = Minutes.minutesBetween(new DateTime(date).plusHours(2), new DateTime(currDate)).getMinutes();
                        responseBody.setMinutesPassed(minutes);
                        if (minutes == 0) {
                            seconds = Seconds.secondsBetween(new DateTime(date).plusHours(2), new DateTime(currDate)).getSeconds();
                            responseBody.setSecondsPassed(seconds);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return response;
    }


}
