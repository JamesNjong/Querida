package com.example.querida;

import static com.example.querida.firebase.DataStores.SearchList;
import static com.example.querida.firebase.DataStores.seachData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.querida.adapters.PeopleAdapter;
import com.example.querida.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String characters = binding.searchInput.getText().toString().trim();
                    if (performSearch(characters)){
                        hoistData();
                        showNA(false);
                    }else{
                        showNA(true);
                    }
                    return true;
                }
                return false;
            }
        });
    }


    private void hoistData(){
        RecyclerView rv = binding.listViewSearch;
        rv.setLayoutManager(new LinearLayoutManager(this));
        PeopleAdapter pap = new PeopleAdapter(SearchList);
        rv.setAdapter(pap);
        pap.setClickListener(new PeopleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(SearchActivity.this, Scan.class);
                intent.putExtra("Position",position);
                intent.putExtra("fromList", 1);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showNA(boolean status){
        binding.invalidSearch.setVisibility(status? View.VISIBLE: View.GONE);
    }


    private boolean performSearch(String searchee) {
        boolean status = seachData(searchee);
        binding.searchInput.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(binding.searchInput.getWindowToken(), 0);

        return status;
    }
}