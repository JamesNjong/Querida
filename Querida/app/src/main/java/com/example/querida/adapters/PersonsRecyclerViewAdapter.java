package com.example.querida.adapters;

import static com.example.querida.firebase.DataStores.GuestList;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.querida.firebase.Person;
import com.example.querida.databinding.FragmentPersonsBinding;

public class PersonsRecyclerViewAdapter extends RecyclerView.Adapter<PersonsRecyclerViewAdapter.ViewHolder> {


    public static  OnItemClickListener onClickListerner;

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.onClickListerner = itemClickListener;
    }

    public interface OnItemClickListener {
        public  void onClick(View view, int position);
    }

    public PersonsRecyclerViewAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentPersonsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = GuestList.get(position);

        holder.mName.setText(holder.mItem.getName());
        holder.mContact.setText(holder.mItem.getTable());
        holder.mCode.setText("CODE:"+holder.mItem.getCode());
    }

    @Override
    public int getItemCount() {
        return GuestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView mName;
        public final TextView mContact;
        public final TextView mCode;
        public Person mItem;

        public ViewHolder(FragmentPersonsBinding binding) {
            super(binding.getRoot());
            mName = binding.personName;
            mContact = binding.peronContact;
            mCode = binding.personCode;
            binding.getRoot().setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (onClickListerner != null) onClickListerner.onClick(view, getAdapterPosition());
        }
    }

}