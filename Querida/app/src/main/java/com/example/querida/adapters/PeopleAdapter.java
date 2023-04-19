package com.example.querida.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.querida.R;
import com.example.querida.databinding.PeopleDataBinding;
import com.example.querida.firebase.Person;

import java.util.ArrayList;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {

    private final ArrayList<Person> mValues;

    public PeopleAdapter(ArrayList<Person> items) {
        mValues = items;
    }



    public static  OnItemClickListener onClickListerner;

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.onClickListerner = itemClickListener;
    }

    public interface OnItemClickListener {
        public  void onClick(View view, int position);
    }

    @Override
    public PeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PeopleAdapter.ViewHolder(PeopleDataBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }



    @Override
    public void onBindViewHolder(final PeopleAdapter.ViewHolder holder, int position) {
        Person pers =mValues.get(position);

        holder.mSpot.setText(pers.getCode().substring(20,23));
        holder.mName.setText(pers.getName());
        holder.mContact.setText("CNTT: "+pers.getNumber());
        holder.mCode.setText("CODE: "+pers.getCode().substring(0,30));

        if (!pers.getState().equalsIgnoreCase("WAITING")){
            holder.mSpot.setBackground(holder.context.getDrawable(R.drawable.person_welcomed));
        }
    }






    @Override
    public int getItemCount() {
        return mValues.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener {
        public final TextView mSpot;
        public final TextView mName;
        public final TextView mContact;
        public final TextView mCode;
        public final Context context;


        public ViewHolder(@NonNull PeopleDataBinding binding) {
            super(binding.getRoot());
            mSpot = binding.peopleSpot;
            mName = binding.peopleName;
            mCode = binding.peopleCode;
            mContact = binding.peopleContact;

            binding.getRoot().setOnClickListener(this);

            context = binding.getRoot().getContext();

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContact.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            if (onClickListerner != null) onClickListerner.onClick(view, getAdapterPosition());
        }
    }




}