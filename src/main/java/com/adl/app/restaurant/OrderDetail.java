package com.adl.app.restaurant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.WindowDecorActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Queue;


import static android.R.attr.resource;
import static com.adl.app.restaurant.R.id.listView;

public class OrderDetail extends AppCompatActivity  {
    String[] DISH = { "item1", "item2", "item3", "item4", "item5", "item6",
            "item7", "item8", "item9", "item10" };

    String[] SIZE = { "s", "m", "s", "m", "l", "a",
            "b", "c", "d", "e" };

    String[] TOPPING = { "as,ds", "fv,tg", "srvf,gtvrf", "mrvf,gvr", "lvr,vr", "agtb,gtb",
            "bvf,vrg,vrg", "crvg", "dvrg", "evrg" };

    String[] EXTRA = { "as,ds", "fv,tg", "srvf,gtvrf", "mrvf,gvr", "lvr,vr", "agtb,gtb",
            "bvf,vrg,vrg", "crvg", "dvrg", "evrg" };
    TextView tvTotal;
    TextView tvDate;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava)getApplicationContext());


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawNavigation();
        if(!orderDetailJava.getOrder().ORDER_CONFIRM)
        {
            //redirect to browse menu
            Intent intent = new Intent(this,BrowseMenu.class);
            startActivity(intent);
        }


       // CustomNavigation customNavigation =new CustomNavigation();
       // customNavigation.drawNavigation(this,2);


        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date =new Date();
        orderDetailJava.getOrder().DATE= dateFormat.format(date);

        tvDate= (TextView) findViewById(R.id.textViewDateValue);
        tvDate.setText(orderDetailJava.getOrder().DATE);

/*

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
*/


        tvTotal= (TextView) findViewById(R.id.textViewTotalValue);
        tvTotal.setText(String.format( "%.2f", orderDetailJava.getOrder().getTOTAL(this) ));



      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



            }
        });
*/
        ListView listView = (ListView) findViewById(R.id.OrderList);
        CustomOrderListAdapter adapter=new CustomOrderListAdapter(this,getOrderDishInOrderArray());
        listView.setAdapter(adapter);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,DISH);

        //listView.setAdapter(adapter);
        //listView.setAdapter(new CustomListAdapter(this,DISH,SIZE,TOPPING,EXTRA));

        //listView.setOnItemClickListener(getApplicationContext());




    }

    public  void drawNavigation()
    {
        //navigation drawer code


        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.food_header)
                .addProfiles(
                        new ProfileDrawerItem().withName("UserName").withEmail("email address").withIcon(getResources().getDrawable(R.drawable.profile_icon))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();







        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Browse Menu");
        //SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("Menu Browser");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Your Order");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Order History");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Register");   //// TODO: implement before register and after register



        Drawer result= new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        //pass your items here
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new DividerDrawerItem(),
                        item3,
                        new DividerDrawerItem(),
                        item4,
                        new DividerDrawerItem()
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        Log.e("ppos", String.valueOf(position));
                        Log.e("ppos", "ok");


                        if(position==7)
                        {
                            Intent intent =new Intent(OrderDetail.this,RegisterActivity.class);
                            startActivity(intent);
                        }
                        else if(position==5)
                        {
                            Intent intent =new Intent(OrderDetail.this,OrderHistoryDetailsActivity.class);
                            startActivity(intent);
                        }

                        else if(position==1)
                        {
                            Intent intent =new Intent(OrderDetail.this,BrowseMenu.class);
                            startActivity(intent);
                        }

                        //TODO
                        return false;
                    }
                })
                .build();

        result.setSelection(2);

    }


    public OrderDetaiJava.Order.DishInOrder[] getOrderDishInOrderArray()
    {
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());
        // orderDetailJava.RESTAURANTNAME = "CAli";

        OrderDetaiJava.Order order =  new OrderDetaiJava().new Order();

        order= orderDetailJava.getOrder();

        ArrayList<OrderDetaiJava.Order.DishInOrder> dishInOrders = new ArrayList<OrderDetaiJava.Order.DishInOrder>();
        dishInOrders= order.getArrayListDishInOrder();

        OrderDetaiJava.Order.DishInOrder[] dishInOrders1Array= dishInOrders.toArray(new OrderDetaiJava.Order.DishInOrder[dishInOrders.size()]);

        int n= dishInOrders.size();
        return  dishInOrders1Array;

    }



}




class CustomOrderListAdapter extends ArrayAdapter<OrderDetaiJava.Order.DishInOrder>
{

    Context context;
    String[] dish;
    String[] size;
    String[] topping;
    String[] extra;
    OrderDetaiJava.Order.DishInOrder[] dishInOrders;
    public CustomOrderListAdapter(@NonNull Context c, OrderDetaiJava.Order.DishInOrder[] dishInOrders) {
        super(c,R.layout.orderlistlayout,R.id.dishName,dishInOrders);

        this.context=c;

        this.dishInOrders=dishInOrders;
       /* this.dish=DISH;
        this.size=SIZE;
        this.topping=TOPPING;
        this.extra=EXTRA;*/



    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) { //getview called for every row
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.orderlistlayout,parent,false);
        TextView tvdish=(TextView) row.findViewById(R.id.dishName);
        TextView tvsize=(TextView) row.findViewById(R.id.sizeValue);
        TextView tvtopping=(TextView) row.findViewById(R.id.toppingList);
        TextView tvextra=(TextView) row.findViewById(R.id.extraList);

        /*tvdish.setText(dish[position]);
        tvsize.setText(size[position]);
        tvtopping.setText(topping[position]);
        tvextra.setText(extra[position]);*/
        tvdish.setText(dishInOrders[position].DISHNAME);
        tvsize.setText(String.valueOf(dishInOrders[position].QUANTITY));     //change it to  quantity
        tvtopping.setText(Arrays.toString(dishInOrders[position].TOPPING));
        tvextra.setText(Arrays.toString(dishInOrders[position].EXTRA));



        return row;  //super.getView(position, convertView, parent);
    }
}



/*
class CustomOrderListAdapter extends ArrayAdapter<String>
{

    Context context;
    String[] dish;
    String[] size;
    String[] topping;
    String[] extra;
    public CustomOrderListAdapter(@NonNull Context c, String[] DISH, String[] SIZE, String[] TOPPING, String[] EXTRA ) {
        super(c,R.layout.orderlistlayout,R.id.dishName,DISH);

        this.context=c;
        this.dish=DISH;
        this.size=SIZE;
        this.topping=TOPPING;
        this.extra=EXTRA;



    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) { //getview called for every row
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.orderlistlayout,parent,false);
        TextView tvdish=(TextView) row.findViewById(R.id.dishName);
        TextView tvsize=(TextView) row.findViewById(R.id.sizeValue);
        TextView tvtopping=(TextView) row.findViewById(R.id.toppingList);
        TextView tvextra=(TextView) row.findViewById(R.id.extraList);

        tvdish.setText(dish[position]);
        tvsize.setText(size[position]);
        tvtopping.setText(topping[position]);
        tvextra.setText(extra[position]);




        return row;  //super.getView(position, convertView, parent);
    }
}
*/
