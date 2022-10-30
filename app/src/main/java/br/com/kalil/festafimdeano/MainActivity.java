package br.com.kalil.festafimdeano;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.confirm = findViewById(R.id.button_confirm);

        this.mViewHolder.confirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_confirm){
            Intent intent = new Intent(this, DatailsActivity.class);
            startActivity(intent);
        }
    }

    private static class ViewHolder{

        TextView textToday;
        TextView textDaysLeft;
        Button confirm;

    }

}