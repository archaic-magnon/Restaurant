

package com.adl.app.restaurant;

/**
 * Created by merajahmed on 14/07/17.
 */

        import android.widget.BaseAdapter;
        import android.content.Context;
        import android.widget.BaseAdapter;
        import android.content.Context;
        import android.widget.BaseAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
public class CustomListAdapter extends BaseAdapter {
    String [] DISH;
    String [] SIZE;
    String [] TOPPING;
    String [] EXTRA;
    Context context;

    private static LayoutInflater inflater=null;
    public CustomListAdapter(OrderDetail orderDetail, String[] DISH, String[] SIZE, String[] TOPPING, String[] EXTRA ) {
        // TODO Auto-generated constructor stub
        DISH=DISH;
        SIZE=SIZE;
        TOPPING=TOPPING;
        EXTRA=EXTRA;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public class Holder
    {
        TextView dishname;
        TextView sizevalue;
        TextView toppinglist;
        TextView extralist;

        ImageView img;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.orderlistlayout, null);
        holder.dishname=(TextView) rowView.findViewById(R.id.dishName);
        holder.sizevalue=(TextView) rowView.findViewById(R.id.sizeValue);
        holder.toppinglist=(TextView) rowView.findViewById(R.id.toppingList);
        holder.extralist=(TextView) rowView.findViewById(R.id.extraList);


        holder.dishname.setText(DISH[position]);
        holder.sizevalue.setText(SIZE[position]);
        holder.toppinglist.setText(TOPPING[position]);
        holder.extralist.setText(EXTRA[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+DISH[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}
