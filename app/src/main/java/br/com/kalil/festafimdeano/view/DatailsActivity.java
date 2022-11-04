package br.com.kalil.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import br.com.kalil.festafimdeano.R;
import br.com.kalil.festafimdeano.constant.FimDeAnoConstants;
import br.com.kalil.festafimdeano.data.SecurityPereference;

public class DatailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPereference mSecurityPereference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datails);

        this.mSecurityPereference = new SecurityPereference(this);

        this.mViewHolder.checkJoin = findViewById(R.id.check_join);
        this.mViewHolder.checkJoin.setOnClickListener(this);

        this.loadDataFromAcitivity();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.check_join){
            if(this.mViewHolder.checkJoin.isChecked()){
                this.mSecurityPereference.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_YES);
            }else{
                this.mSecurityPereference.storeString(FimDeAnoConstants.PRESENCE_KEY, FimDeAnoConstants.CONFIRMATION_NO);
            }
        }
    }

    private void loadDataFromAcitivity(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String presence = extras.getString(FimDeAnoConstants.PRESENCE_KEY);
            if(presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
                this.mViewHolder.checkJoin.setChecked(true);
            }else{
                this.mViewHolder.checkJoin.setChecked(false);
            }
        }
    }

    private static class ViewHolder{
        CheckBox checkJoin;
    }

}