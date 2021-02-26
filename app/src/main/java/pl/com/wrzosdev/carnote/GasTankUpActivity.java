package pl.com.wrzosdev.carnote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import pl.com.wrzosdev.carnote.model.AutoData;
import pl.com.wrzosdev.carnote.model.TankUpRecord;

/**
 * Okno formularza nowego tankowania
 */

public class GasTankUpActivity extends AppCompatActivity
{
    // TODO: 17.06.2017 zostawiamy tytuł okna w stałej, żeby zobaczyć jak wspaniała jest internacjonalizacja zasobów
    public static final String NOWE_TANKOWANIE = "Nowe tankowanie";
    private EditText dateEditText;
    private EditText mileageEditText;
    private EditText litersEditText;
    private EditText costEditText;

    private Button confirmButton;
    private AutoData autoData;
    private DateFormat dateFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gas_tank_up_layout);
        obtainExtras();
        viewInit();
        setTitle(NOWE_TANKOWANIE);
    }

    private void obtainExtras()
    {
        autoData = (AutoData) getIntent().getExtras().getSerializable(MainMenuActivity.SPECIAL_DATA);
    }

    private void viewInit()
    {
        dateEditText = (EditText) findViewById(R.id.date);
        mileageEditText = (EditText) findViewById(R.id.mileage);
        litersEditText = (EditText) findViewById(R.id.liters);
        costEditText = (EditText) findViewById(R.id.cost);
        confirmButton = (Button) findViewById(R.id.confirm);

        dateEditText.setText(getCurrentDate());
        confirmButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TankUpRecord tank = new TankUpRecord(getDateEditTextDate(), getMileageInteger(), getLitersInteger(), getCostInteger());
                autoData.getTankUpRecord().add(tank);
            }
        });
    }

    private Date getDateEditTextDate()
    {
        try
        {
            return dateFormat.parse(dateEditText.getText().toString());
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        // TODO: 17.06.2017 Pamiętaj, aby dodać tu walidację!
        dateFormat = DateFormat.getDateInstance();
        Date date = new Date();
        return date;
    }

    // TODO: 17.06.2017 Pamiętaj, aby dodać tu walidację!
    private Integer getCostInteger()
    {
        return Integer.valueOf(costEditText.getText().toString());
    }

    // TODO: 17.06.2017 Pamiętaj, aby dodać tu walidację!
    private Integer getLitersInteger()
    {
        return Integer.valueOf(litersEditText.getText().toString());
    }

    // TODO: 17.06.2017 Pamiętaj, aby dodać tu walidację!
    private Integer getMileageInteger()
    {
        return Integer.valueOf(mileageEditText.getText().toString());
    }

    private String getCurrentDate()
    {
        dateFormat = DateFormat.getDateInstance();
        Date date = new Date();
        return dateFormat.format(date);
    }

}
