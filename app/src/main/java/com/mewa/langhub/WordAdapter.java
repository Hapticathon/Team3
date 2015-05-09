package com.mewa.langhub;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mewa on 5/9/15.
 */
public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private List<Word> mWords = Collections.synchronizedList(new ArrayList<Word>());

    public WordAdapter() {
        
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.wordTextView.setText(mWords.get(position).getWord());
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordTextView;

        public ViewHolder(View v) {
            super(v);
            wordTextView = (TextView) v.findViewById(R.id.text_word);
        }
    }
}
