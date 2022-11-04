package br.com.kalil.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.kalil.festafimdeano.R;
import br.com.kalil.festafimdeano.constant.FimDeAnoConstants;
import br.com.kalil.festafimdeano.data.SecurityPereference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public ViewHolder mViewHolder = new ViewHolder();
    private SecurityPereference mSecurityPereference;
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPereference = new SecurityPereference(this);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.confirm = findViewById(R.id.button_confirm);

        this.mViewHolder.confirm.setOnClickListener(this);

        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(daysLeft);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_confirm){
            String presence = this.mSecurityPereference.getStoredString(FimDeAnoConstants.PRESENCE_KEY);

            Intent intent = new Intent(this, DatailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE_KEY, presence);
            startActivity(intent);
        }
    }

    private int getDaysLeft(){

        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar lastDay = Calendar.getInstance();
        int daymax = lastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return daymax-today;

    }

    private void verifyPresence(){
        String presence = this.mSecurityPereference.getStoredString(FimDeAnoConstants.PRESENCE_KEY);
        if(presence == ""){
            this.mViewHolder.confirm.setText(getString(R.string.nao_confirmado));
        } else if(presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
            this.mViewHolder.confirm.setText(getString(R.string.sim));
        }else{
            this.mViewHolder.confirm.setText(getString(R.string.nao));
        }
    }

    private static class ViewHolder{

        TextView textToday;
        TextView textDaysLeft;
        Button confirm;

    }

}