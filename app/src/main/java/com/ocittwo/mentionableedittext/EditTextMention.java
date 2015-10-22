package com.ocittwo.mentionableedittext;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ar-android on 22/10/2015.
 */
public class EditTextMention extends MultiAutoCompleteTextView implements AdapterView.OnItemClickListener{

    public EditTextMention(Context context) {
        super(context);
        setOnItemClickListener(this);
        addTextChangedListener(watcher);
    }

    public EditTextMention(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnItemClickListener(this);
        addTextChangedListener(watcher);
        init(attrs);
    }

    public EditTextMention(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnItemClickListener(this);
        addTextChangedListener(watcher);
        init(attrs);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setMention();
    }

    private void setMention() {

        SpannableStringBuilder ssb = new SpannableStringBuilder(getText());
        //split string with space
        String chips[] = getText().toString().trim().split(" ");
        int x = 0;
        for (String c  : chips){
            LayoutInflater lf = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            TextView tv = (TextView) lf.inflate(R.layout.item_mention, null);
            tv.setText(c);
            x = x + c.length() + 1;
        }
        // set chips span
        setText(ssb);
        // move cursor to last
        setSelection(getText().length());

    }

    @Override
    public <T extends ListAdapter & Filterable> void setAdapter(T adapter) {

        ArrayAdapter<DataMention> dataMentionArrayAdapter = (ArrayAdapter<DataMention>) adapter;

        List<String> name = new ArrayList<String>();
        for (int i = 0; i < dataMentionArrayAdapter.getCount(); i++){
            name.add("@" + dataMentionArrayAdapter.getItem(i).getName());
        }

        ArrayAdapter<String>  dataAdapter = new ArrayAdapter<String>(
                getContext(), R.layout.support_simple_spinner_dropdown_item, name
        ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView tv = (TextView)v.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);

                return v;
            }
        };

        super.setAdapter(dataAdapter);
    }

    private void init(AttributeSet attrs) {
        setText(getText());
    }

    @Override
    protected void onTextChanged(CharSequence text, int start,
                                 int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
