package co.infinum.skliba.zadatak5.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.infinum.skliba.zadatak5.R;
import co.infinum.skliba.zadatak5.helpers.MvpFactory;
import co.infinum.skliba.zadatak5.models.Post;
import co.infinum.skliba.zadatak5.mvp.presenter.CommentPresenter;
import co.infinum.skliba.zadatak5.mvp.view.CommentsView;

public class CommentActivity extends AppCompatActivity implements CommentsView {

    public static final String BOAT_INFO = "BOAT INFO";
    public static final String TOKEN = "TOKEN";

    @Bind(R.id.new_comment_content)
    EditText newCommentContent;

    private CommentPresenter presenter;
    private Post post;
    private String token;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        post = intent.getParcelableExtra(BOAT_INFO);

        token = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(TOKEN, "");



        presenter = MvpFactory.getPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_comment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.btn_save) {
            content = newCommentContent.getText().toString();
            if(!content.isEmpty()){
                presenter.addComment(post, token, content);
                return true;
            }
            else return false;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCommentsRecieved() {

    }

    @Override
    public void onTokenExpired() {

    }

    @Override
    public void onCommentCreated() {
        Toast.makeText(getApplicationContext(), "super", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateFailed() {
        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();

    }

    @Override
    public void showError(@StringRes int error) {

    }
}
