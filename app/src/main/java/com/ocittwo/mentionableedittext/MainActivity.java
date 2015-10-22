package com.ocittwo.mentionableedittext;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<DataMention> listFriends=new ArrayList<>();
        listFriends.add(new DataMention("ahmad"));
        listFriends.add(new DataMention("arif"));
        listFriends.add(new DataMention("akbar"));
        listFriends.add(new DataMention("bob"));
        listFriends.add(new DataMention("rosid"));
        listFriends.add(new DataMention("zamzam"));
        listFriends.add(new DataMention("abdullah"));

        ArrayAdapter<DataMention> adapter=new ArrayAdapter<DataMention>(this,android.R.layout.simple_list_item_1,listFriends);

        EditTextMention editText= (EditTextMention) findViewById(R.id.edittext);
        editText.setAdapter(adapter);
        editText.setTokenizer(new SpaceTokenizer());

    }
    public class SpaceTokenizer implements MultiAutoCompleteTextView.Tokenizer {

        public int findTokenStart(CharSequence text, int cursor) {
            int i = cursor;

            while (i > 0 && text.charAt(i - 1) != ' ') {
                i--;
            }
            while (i < cursor && text.charAt(i) == ' ') {
                i++;
            }

            return i;
        }

        public int findTokenEnd(CharSequence text, int cursor) {
            int i = cursor;
            int len = text.length();

            while (i < len) {
                if (text.charAt(i) == ' ') {
                    return i;
                } else {
                    i++;
                }
            }

            return len;
        }

        public CharSequence terminateToken(CharSequence text) {
            int i = text.length();

            while (i > 0 && text.charAt(i - 1) == ' ') {
                i--;
            }

            if (i > 0 && text.charAt(i - 1) == ' ') {
                return text;
            } else {
                if (text instanceof Spanned) {
                    SpannableString sp = new SpannableString(text + " ");
                    TextUtils.copySpansFrom((Spanned) text, 0, text.length(),
                            Object.class, sp, 0);
                    return sp;
                } else {
                    return text + " ";
                }
            }
        }
    }


}