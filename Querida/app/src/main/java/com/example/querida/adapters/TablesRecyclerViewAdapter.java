package com.example.querida.adapters;

import static com.example.querida.firebase.DataStores.TableList;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.querida.R;
import com.example.querida.firebase.TableChairs;
import com.example.querida.databinding.FragmentDataBinding;

public class TablesRecyclerViewAdapter extends RecyclerView.Adapter<TablesRecyclerViewAdapter.ViewHolder> {

    public static OnItemClickListener onClickListerner;

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.onClickListerner = itemClickListener;
    }

    public interface OnItemClickListener {
        public  void onClick(View view, int position);
    }



    private Context context;

    public TablesRecyclerViewAdapter( Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentDataBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TableChairs table = TableList.get(position);

        holder.tNumber.setText(table.getNumber());
        holder.tTag.setText(table.getTag());
        holder.tName.setText(table.getOwner());

        setProgress(holder, table);
    }

    private void setProgress(ViewHolder holder, TableChairs table){
        switch (table.getCurrent_capacity()){
            case 0:
                holder.tCapacity.setText("0%\n FULL");
                holder.tCapacity.setBackground(context.getDrawable(R.drawable.progress_zero));
                break;
            case 1:
                holder.tCapacity.setText("10%\n FULL");
                holder.tCapacity.setBackground(context.getDrawable(R.drawable.progress_one_two));
                break;
            case 2:
                holder.tCapacity.setText("20%\n FULL");
                holder.tCapacity.setBackground(context.getDrawable(R.drawable.progress_one_two));
                break;
            case 3:
                holder.tCapacity.setText("30%\n FULL");
                holder.tCapacity.setBackground(context.getDrawable(R.drawable.progress_three));
                break;
            case 4:
                holder.tCapacity.setText("40%\n FULL");
                holder.tCapacity.setBackground(context.getDrawable(R.drawable.progress_four));
                break;
            case 5:
                holder.tCapacity.setText("50%\n FULL");
                holder.tCapacity.setBackground(context.getDrawable(R.drawable.progress_five));
                break;
            case 6:
                holder.tCapacity.setText("60%\n FULL");
                holder.tCapacity.setBackground(context.getDrawable(R.drawable.progress_six));
                break;
            case 7:
                holder.tCapacity.setText("70%\n FULL");
                holder.tCapacity.setBackground(context.getDrawable(R.drawable.progress_seven_eight));
                break;
            case 8:
                holder.tCapacity.setText("80%\n FULL");
                holder.tCapacity.setBackground(context.getDrawable(R.drawable.progress_seven_eight));
                break;
            case 9:
                holder.tCapacity.setText("90%\n FULL");
                holder.tCapacity.setBackground(context.getDrawable(R.drawable.progress_nine));
                break;
            default:
                holder.tCapacity.setText("100%\n FULL");
                holder.tCapacity.setBackground(context.getDrawable(R.drawable.progress_ten));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return TableList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        public final TextView tNumber;
        public final TextView tName;
        public final TextView tTag;
        public final TextView tCapacity;
        public final TextView button;


        public ViewHolder(FragmentDataBinding binding) {
            super(binding.getRoot());
            tNumber = binding.tableNumber;
            tName = binding.tableName;
            tTag = binding.tag;
            tCapacity = binding.capacity;
            button = binding.button;

            button.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (onClickListerner != null) onClickListerner.onClick(view, getAdapterPosition());
        }
    }
}