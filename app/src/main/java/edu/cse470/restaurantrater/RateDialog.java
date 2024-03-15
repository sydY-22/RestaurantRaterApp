package edu.cse470.restaurantrater;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.fragment.app.DialogFragment;

public class RateDialog extends DialogFragment {
    RatingBar rateMeal;
    public interface SaveRatingBarListener {
        void didFinishRatingBarDialog(RatingBar rateTime);
    }


    public RateDialog() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.rate_meal, container);

        getDialog().setTitle("Rate Meal");

        final RatingBar rate_id = view.findViewById(R.id.ratingBar);
        rateMeal = rate_id;
        initSaveButton(view);

        return view;

    }
    private void initSaveButton(View view) {
        // saves the rating data when the save button is clicked.
        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItem(rateMeal);
                Dish dish = new Dish();
                dish.getDishID();
            }
        });

    }
    private void saveItem(RatingBar selectedRate) {
        // saves the data then dismisses the rating bar dialog.
        SaveRatingBarListener activity = (SaveRatingBarListener) getActivity();
        activity.didFinishRatingBarDialog(selectedRate);
        getDialog().dismiss();
    }

}
