

package com.adl.app.restaurant;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

public class Lst extends Activity implements OnItemClickListener {
    String[] ITEMS = { "item1", "item2", "item3", "item4", "item5", "item6",
            "item7", "item8", "item9", "item10" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst);

        ListView listView = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, ITEMS);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View arg1,
                            int position, long arg3) {
        ListView listview = (ListView) adapterView;

        if (listview.isItemChecked(position)) {
            Toast.makeText(getApplicationContext(),
                    adapterView.getItemAtPosition(position) + " Checked",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    adapterView.getItemAtPosition(position) + " Un Checked",
                    Toast.LENGTH_LONG).show();
        }
    }

}

