package com.mewa.langhub.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mewa.langhub.Data;
import com.mewa.langhub.R;
import com.mewa.langhub.interfaces.WordClickHandler;
import com.mewa.langhub.models.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mewa on 5/9/15.
 */
public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private static final int TYPE_HEADER = 0xD00D;
    private static final int TYPE_ITEM = 0xBAD;
    private List<Word> mWords = Collections.synchronizedList(new ArrayList<Word>());
    private final int kHeaderViewsCount = 0;

    public WordAdapter() {
        mWords.addAll(Arrays.asList(Data.word1, Data.word2, Data.word3, Data.word4, Data.word5, Data.word6, Data.word7, Data.word8, Data.word9, Data.word10));
        Collections.sort(mWords, new Comparator<Word>() {
            @Override
            public int compare(Word lhs, Word rhs) {
                return lhs.getWord().compareTo(rhs.getWord());
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh;
        View view = null;
        switch (viewType) {
            case TYPE_HEADER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
                break;
            case TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
                break;
        }
        vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.wordTextView.setText(mWords.get(position).getWord());
        holder.translationTextView.setText(mWords.get(position).getTranslation());
        if (position == 0) {
            ((ViewGroup.MarginLayoutParams) holder.root.getLayoutParams()).topMargin = 0;
        } else {
            ((ViewGroup.MarginLayoutParams) holder.root.getLayoutParams()).topMargin = holder.root.getResources().getDimensionPixelOffset(R.dimen.divider_height);
        }
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WordClickHandler) holder.root.getContext()).onClick(mWords.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWords.size() + kHeaderViewsCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < kHeaderViewsCount)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordTextView;
        public final TextView translationTextView;
        public final View root;

        public ViewHolder(View v) {
            super(v);
            root = v;
            wordTextView = (TextView) v.findViewById(R.id.text_word);
            translationTextView = (TextView) v.findViewById(R.id.text_word_translation);
        }
    }
}
