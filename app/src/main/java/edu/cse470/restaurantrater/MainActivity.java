package edu.cse470.restaurantrater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RateDialog.SaveRatingBarListener{

    private Restaurant currentRestaurant = new Restaurant();
    private Dish currentDish = new Dish();
    ArrayList<String> dishNames;
    ArrayList<Dish> dishData;
    DishAdapter dishAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRateMealButton();
        initSaveButton();
        initTextChangedEvents();
        initDeleteButton();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            initDish(extras.getInt("dishID"));
        }
        else{
            currentDish = new Dish();
        }

    }

    @Override
    public void onResume() {
      super.onResume();
        DishDataSource ds = new DishDataSource(this);
        try {
            ds.open();
            dishData = ds.getAllDishes();
            ds.close();
            if (dishData != null && dishData.size() > 0) {
                RecyclerView dishList = findViewById(R.id.rvDishes);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                dishList.setLayoutManager(layoutManager);
                dishAdapter = new DishAdapter(dishData, this); // update the existing dishAdapter instance.
                dishAdapter.setOnItemClickListener(onItemClickListener);
                dishList.setAdapter(dishAdapter);
            }
            else {

            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Error retreiving dishes", Toast.LENGTH_LONG).show();
        }
    }

    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            int dishID = dishData.get(position).getDishID();
            Toast.makeText(MainActivity.this, "Clicked dish ID: " + dishID, Toast.LENGTH_SHORT).show();
            FragmentManager fm = getSupportFragmentManager();
            RateDialog rating = new RateDialog();
//            dishData.get(position).setRating(String.valueOf(rating));
            rating.show(fm, "Rate Meal");
        }
    };

    private void initRateMealButton() {
        // once this button is clicked the rating bar will appear.
        Button buttonRate = findViewById(R.id.rateDishButton);
        buttonRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                RateDialog rating = new RateDialog(); // changed
                rating.show(fm, "Rate Meal");

            }
        });
    }

    @Override
    public void didFinishRatingBarDialog(RatingBar rateTime) {
        // displays the rating the user gave the dish.
        TextView ratingText = findViewById(R.id.displayRatingTextView);
        ratingText.setText(String.valueOf(rateTime.getRating()));
        currentDish.setRating(String.valueOf(rateTime.getRating()));
    }

    private void initTextChangedEvents() {
        final EditText etRestaurantName = (EditText) findViewById(R.id.nameEditText);
        etRestaurantName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentRestaurant.setRestaurantName(etRestaurantName.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //
            }
        });

        final EditText etAddress = (EditText) findViewById(R.id.addressEditText);
        etAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentRestaurant.setRestaurantAddress(etAddress.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etCity = (EditText) findViewById(R.id.cityEditText);
        etCity.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentRestaurant.setCity(etCity.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etDishName = (EditText) findViewById(R.id.dishEditText);
        etDishName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentDish.setDishName(etDishName.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etType = (EditText) findViewById(R.id.typeEditText);
        etType.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentDish.setType(etType.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etState = (EditText) findViewById(R.id.stateEditText);
        etState.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentRestaurant.setState(etState.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etZip = (EditText) findViewById(R.id.zipEditText);
        etZip.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentRestaurant.setZip(etZip.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });



    }

    private void initSaveButton() {
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hideKeyboard();
                boolean wasSuccessful = false;
                boolean wasSucessful_dish = false;
                RestaurantDataSource ds = new RestaurantDataSource(MainActivity.this);
                DishDataSource dish_ds = new DishDataSource(MainActivity.this);
                try {
                    ds.open();
                    dish_ds.open();
                    if (currentRestaurant.getRestaurantId() == -1 && currentDish.getDishID() == -1) {
                        wasSuccessful = ds.insertRestaurant(currentRestaurant);
                        wasSucessful_dish = dish_ds.insertDish(currentDish);
                        int newId = ds.getLastRestaurantID();
                        int newDishId = dish_ds.getLastDishID();
                        currentRestaurant.setRestaurantId(newId);
                        currentDish.setDishID(newDishId);
                        currentDish.setRestaurantId(newId);

                    }
                    else {
                        wasSuccessful = ds.updateRestaurant(currentRestaurant);
                        wasSucessful_dish = dish_ds.updateDish(currentDish);
                    }
                    ds.close();
                    dish_ds.close();
                }
                catch(Exception e) {
                    wasSuccessful = false;
                    wasSucessful_dish = false;
                }
            }
        });

    }

    private void initDish(int id) {
        DishDataSource ds = new DishDataSource(MainActivity.this);
        try{
            ds.open();
            currentDish = ds.getSpecificDish(id);
            ds.close();
        }
        catch (Exception e) {
            Toast.makeText(this, "Load Dish Failed", Toast.LENGTH_LONG).show();
        }
        EditText editName = findViewById(R.id.dishEditText);
        EditText editType = findViewById(R.id.typeEditText);
        TextView rating = findViewById(R.id.displayRatingTextView);

        editName.setText(currentDish.getDishName());
        editType.setText(currentDish.getType());
        rating.setText(currentDish.getRating());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initDeleteButton(){
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch s = findViewById(R.id.deleteSwitch);
        s.setOnCheckedChangeListener((compoundButton, b) -> {
            boolean status = compoundButton.isChecked();
            dishAdapter.setDelete(status);
            dishAdapter.notifyDataSetChanged();
        });
    }

}
