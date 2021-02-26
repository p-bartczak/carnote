package pl.com.wrzosdev.carnote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import pl.com.wrzosdev.carnote.model.AutoData;

/**
 * Okno menu głownego
 */
public class MainMenuActivity extends AppCompatActivity
{
    public static final String SPECIAL_DATA = "SPECIAL_DATA";
    public static final String ADD_CAR_REQUEST_CODE = "ADD_CAR_REQUEST_CODE";
    public static final int REQUEST_CODE = 12346;

    private Button goToTankFormButton;
    private Button goToNewCarButton;
    private Spinner autoChooseSpinner;

    private ArrayList<AutoData> autoList;
    private ArrayAdapter<AutoData> arrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);
        initViews();

    }

    private void initViews()
    {
        goToTankFormButton = (Button) findViewById(R.id.go_to_tank_form_button);
        goToNewCarButton = (Button) findViewById(R.id.new_car_button);
        autoChooseSpinner = (Spinner) findViewById(R.id.auto_choose_spinner);

        initAutoList();
        initArrayAdapter();
        autoChooseSpinner.setAdapter(arrayAdapter);

        goToTankFormButton.setOnClickListener(goToTankUpActivity());
        goToNewCarButton.setOnClickListener(goToNewCarActivity());
    }

    @NonNull
    private View.OnClickListener goToTankUpActivity()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainMenuActivity.this, GasTankUpActivity.class);
                intent.putExtra(SPECIAL_DATA, getCurrentAuto());
                startActivity(intent);
            }
        };
    }

    private void initArrayAdapter()
    {
        arrayAdapter = new ArrayAdapter<AutoData>(this, android.R.layout.simple_spinner_item, autoList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    private void initAutoList()
    {
        autoList = new ArrayList<AutoData>();
        autoList.add(new AutoData("Golf 6", "VolksWagen", "Perłowy metalik"));
        autoList.add(new AutoData("Polo 3", "VolksWagen", "Różowy"));
    }

    @NonNull
    private AutoData getCurrentAuto()
    {
        return (AutoData) autoChooseSpinner.getSelectedItem();
    }

    private View.OnClickListener goToNewCarActivity()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainMenuActivity.this, AddCarActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                if (data != null)
                {
                    AutoData newAutoData = (AutoData) data.getExtras().get(AddCarActivity.AUTO_DATA_NEW_CAR);
                    Boolean isNewCarMasterCar = (Boolean) data.getExtras().get(AddCarActivity.IS_NEW_CAR_MASTER_CAR);
                    if (isNewCarMasterCar!=null && isNewCarMasterCar)
                    {
                        autoList.add(0, newAutoData);
                    } else
                    {
                        autoList.add(newAutoData);
                    }
                }
            }
        }
    }
}
