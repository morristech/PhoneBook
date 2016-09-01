package ua.testapp.phonebook.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.testapp.phonebook.PhoneBookApplication;
import ua.testapp.phonebook.R;

/**
 * Created by alexey on 26.08.16.
 */
public abstract class AbstractBaseActivity extends AppCompatActivity {
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);

        initToolbar();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        initContent();
    }

    protected abstract void initContent();

    /** Need for bind butter knife in all activities */
    public abstract int getContentView();

    public void initToolbar(){
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            setTitle(null);
        }
    }

    public void setTitleToolBar(String title){
        if (toolbar != null && tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
