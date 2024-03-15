package edu.cse470.restaurantrater;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class DishAdapter extends RecyclerView.Adapter{
    private static final String TAG = "DishAdapter";
    private ArrayList<String> dishData;
    private View.OnClickListener myOnItemClickListener;
    private ArrayList<Dish> data;
    private Context parentContext;
    private boolean isDeleting;

    public class DishViewHolder extends RecyclerView.ViewHolder { // declare class as a subclass of RecyclerViews ViewHolder
        public TextView textViewDish;
        public TextView textViewDishName;
        public TextView textViewDishType;
        public TextView textViewDishRating;
        public Button deleteButton;
        public DishViewHolder(@NonNull View itemView) { // hold a reference to item's layout in the widget.
            super(itemView);
            textViewDishName = itemView.findViewById(R.id.listNameTextView);
            textViewDishType = itemView.findViewById(R.id.listTypeTextView);
            textViewDishRating = itemView.findViewById(R.id.listRatingTextView);
            deleteButton = itemView.findViewById(R.id.delButton);
            itemView.setTag(this);
            itemView.setOnClickListener(myOnItemClickListener); // set up the on click listener.
        }
        public TextView getTextViewDish() {
            return textViewDish;
        }
        public TextView getTextViewName() {return textViewDishName;}
        public TextView getTextViewType() {return textViewDishType;}
        public TextView getTextViewRating() {return textViewDishRating;}
        public Button getDeleteButton() {return deleteButton;}

    }
    public DishAdapter(ArrayList<Dish> arrayList, Context context) { // constructor method of the ViewHolder.
        //this.dishData = dishData;
        data = arrayList;
        parentContext = context;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        this.myOnItemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new DishViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DishViewHolder dvh = (DishViewHolder) holder;
        dvh.getTextViewName().setText(data.get(position).getDishName()); //dvh.getTextViewDish().setText(dishData.get(position))
        dvh.getTextViewType().setText(data.get(position).getType());
        dvh.getTextViewRating().setText(data.get(position).getRating());
        if (isDeleting) {
            dvh.getDeleteButton().setVisibility(View.VISIBLE);
            dvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(position);
                }
            });
        }
        else {
            dvh.getDeleteButton().setVisibility(View.INVISIBLE); // Comment this out to test!
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void deleteItem(int position) {
        Dish dish = data.get(position);
        DishDataSource ds = new DishDataSource(parentContext);
        try {
            ds.open();
            boolean didDelete = ds.deleteDish(dish.getDishID()); // added get restaurant id
            ds.close();
            if(didDelete) {
                data.remove(position);
                notifyDataSetChanged();
            }
            else{
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {

        }

    }
    public void setDelete(boolean b) {
        isDeleting = b;
    }

}
