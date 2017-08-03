package com.adl.app.restaurant;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.MediaRouteButton;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ContainerDrawerItem;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import in.mamga.carousalnotification.Carousal;
import in.mamga.carousalnotification.CarousalItem;

import static android.R.attr.backupInForeground;
import static android.R.attr.id;
import static android.R.attr.inAnimation;
import static android.R.attr.order;
import static android.R.attr.theme;
import static android.R.attr.thickness;
import static android.media.CamcorderProfile.get;
import static android.os.Build.VERSION_CODES.O;
import static com.adl.app.restaurant.R.menu.drawer;
import static com.google.android.gms.common.api.Status.sh;
import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;

public class BrowseMenu extends AppCompatActivity implements View.OnClickListener {
    String[] DISH = {"item1", "item2", "item3", "item4", "item5", "item6",
            "item7", "item8", "item9", "item10"};
    Boolean[] TOPPINGFLAG = {true};

    String[] PRICE = {"s", "m", "s", "m", "l", "a",
            "b", "c", "d", "e"};
    Boolean isDialogConfirm;

    TextView showOrder;
    Dialog dialog;
    ArrayList<String> extraList = new ArrayList<String>();
    ArrayList<String> toppingList = new ArrayList<String>();

    ArrayList<OrderDetaiJava.Menu.Dish> dishArrayList = new ArrayList<OrderDetaiJava.Menu.Dish>();
    ArrayList<OrderDetaiJava.Order.DishInOrder> dishInOrderArrayList = new ArrayList<OrderDetaiJava.Order.DishInOrder>();

    JSONObject RESPONSE_USER_INFO;

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    DataClass dataClass = new DataClass();

    Search search = new Search();


    //TODO implement max-min Topping
    //Todo : check change in showdialog for max min topping


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Log.e("abc", "browsemenu");
        sendRequestForUserInfoByMac(this);


        //navigation drawer code
        /*CustomNavigation customNavigation =new CustomNavigation();
        customNavigation.drawNavigation(this,BrowseMenu.this,1);*/
        drawNavigation();


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

        try {
            sendRequestForMenu(BrowseMenu.this);
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//
//
//
//            }
//        });



        /*dummy function call to set menu
         */


        /*setOneDish(new String[]{"s", "m"}, new int[]{50, 100},new String[][]{{"e2", "e1"},{"e3","e1"}},
                new int[][]{{23,12}, {12,34}},new String[][]{{"e1","e2"},{"e3","e1"}},new String[][]{{"t1","t2"},{"t1","t3"}},
                new String[][]{{"t1","t2"},{"t3", "t1"}},true,"d1","Pizza");
        setMenu();*/



        /*try {
            dataClass.sendRequestForMenu(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        //List view of browse menu
        ListView listView = (ListView) findViewById(R.id.menu_list_browse_menu);
*//*
        CustomBrowseMenuListAdapter adapter=new CustomBrowseMenuListAdapter(this,DISH,PRICE);
*//*
        CustomBrowseMenuListAdapter adapter=new CustomBrowseMenuListAdapter(this,getMenuDishArray());



        listView.setAdapter(adapter); //list view end*/


        showOrder = (TextView) findViewById(R.id.showOrderTxt);

        //write code for clickable check
        showOrder.setOnClickListener(this);

        /*Button buttonPlus= new Button(this);
        buttonPlus= (Button) findViewById(R.id.buttonPlus_Browse_menu_list);
        buttonPlus.setOnClickListener(this);

        Button buttonMinus = new Button(this);
        buttonMinus = (Button) findViewById(R.id.buttonMinus_Browse_menu_list);
        buttonMinus.setOnClickListener(this);*/














       /*
      Layout_ExtraToppingDialog l= new Layout_ExtraToppingDialog();
      l.setAdapter();
      Dialog dialog=new Dialog();
      final Dialog dialog = new Dialog(this);
      dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
      dialog.setCancelable(false);
      dialog.setContentView(R.layout.topping_extra_dialog_layout);
      TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
      text.setText(msg);
      Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
      dialogButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
      dialog.dismiss();
      }
      });
      dialog.show();
      */

       /*setMenu();
        getMenu();*/

    }

    @Override
    protected void onStart() {
        super.onStart();

        //Animation
        YoYo.with(Techniques.SlideInUp)
                .duration(700)
                .repeat(1)
                .playOn(findViewById(R.id.showOrderTxt));



        /*try {
            dataClass.sendRequestForMenu(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        /*try {
            sendRequestForMenu(BrowseMenu.this);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        Log.e("loop ", "start");


        Log.e("loop ", "end");
/*
        //List view of browse menu
        ListView listView = (ListView) findViewById(R.id.menu_list_browse_menu);
*//*
        CustomBrowseMenuListAdapter adapter=new CustomBrowseMenuListAdapter(this,DISH,PRICE);
*//*
        CustomBrowseMenuListAdapter adapter=new CustomBrowseMenuListAdapter(this,getMenuDishArray());

        listView.setAdapter(adapter); //list view end*/


        String mac = getMacId(getApplicationContext());

//        TextView mid= (TextView)    findViewById(R.id.macid)  ;
//        mid.setText(mac);
//
//        Toast.makeText(getApplicationContext(), mac,
//                Toast.LENGTH_LONG).show();


        //Intent in = new Intent(getApplicationContext(), SignUp.class);
        Intent in = new Intent(getApplicationContext(), ProfileActivity.class);

        Log.e("abc", "before in");

        //startActivity(in);

        Log.e("abc", "after in");

        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        mac = info.getMacAddress();

        Toast.makeText(getApplicationContext(), mac, Toast.LENGTH_LONG).show();


/*
        Layout_ExtraToppingDialog layout_extraToppingDialog=new Layout_ExtraToppingDialog();
        layout_extraToppingDialog.setAdapter();
*/


        //  showDialog();

        //205.147.103.4/food/menu

        /*// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://205.147.103.4/food/menu";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                        Log.e("jsonRes",response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // mTextView.setText("That didn't work!");
                Log.e("jsonRes","error");

            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);*/

        //String url = "http://192.168.1.19:8000/order/food/menu/app";


       /* JSONStringer jsonString = {
                "name" : "Ronaldo",
                "sport" : "soccer",
                "age" : 25,
                "id" : 121,
                "lastScores" : [ 2, 1, 3, 5, 0, 0, 1, 1 ]
                }*/

        /*JSONObject obj = new JSONObject();
        try {
            obj.put("store",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, obj, new Response.Listener<JSONObject>() {  //chexk get post

                    @Override
                    public void onResponse(JSONObject response) {
                       // mTxtDisplay.setText("Response: " + response.toString());
                        Log.e("response",response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.e("error","error");

                    }
                });

        Log.e("response","b");

        queue. add(jsObjRequest);
        Log.e("response","a");*/


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("onrestart", "OK");
        listLoad(this);
    }

    JSONObject RESPONSE = new JSONObject();
    Context context;
    boolean successResponse;

    public void sendRequestForMenu(final Context context) throws JSONException {
        successResponse = false;

        // ERROR_COUNT=0;
        //String url = "http://192.168.1.19:8000/order/food/menu/app";

        String url = "http://205.147.103.4:8001/food/menu";
        JSONObject obj = new JSONObject();
        try {
            obj.put("store", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //  final ProgressDialog pd =new ProgressDialog(BrowseMenu.this);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {  //chexk get post

                    @Override
                    public void onResponse(JSONObject response) {
                        RESPONSE = response;
                        Log.e("dimiss", "ok");
                        Log.e("response", RESPONSE.toString());
                        try {
                            setMenu(context);
                            successResponse = true;
                            listLoad(BrowseMenu.this);
                            //   pd.dismiss();
                            //  pd.cancel();
                            // pd.hide();


                            //this.notify();
                        } catch (JSONException e) {
                            Log.e("setMenu", "setMenu failed");
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.e("error", "error");
                        try {
                            //if(ERROR_COUNT<6)
                            {
                                // ERROR_COUNT++;
                                sendRequestForMenu(context);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Log.e("response", "b");
        // pd.show(this,"Please Wait...","Please Wait...");

        queue.add(jsObjRequest);
        Log.e("response", "a");


    }


    public void setMenu(Context context) throws JSONException {
        Log.e("setmenu begin", "ok");

        //food
        ArrayList<OrderDetaiJava.Menu.Dish> foodDish = new ArrayList<>();
        final JSONArray foodArray = RESPONSE.getJSONArray("food");


        for (int i = 0; i < foodArray.length(); i++) {
            OrderDetaiJava.Menu.Dish dish = new OrderDetaiJava().new Menu().new Dish();
            JSONObject objFood = foodArray.getJSONObject(i);
            dish.DISH_ID = objFood.getString("id");
            dish.DISHNAME = objFood.getString("name");
            dish.DISH_CATEGORY_ID = objFood.getString("category");
            dish.DISH_DESCRIPTION = objFood.getString("description");
            dish.MAX_TOPPING = (objFood.getString("max_toppings"));
            dish.DISH_DIET = objFood.getString("diet");

            List<String> items = Arrays.asList(objFood.getString("toppings").split("\\s*,\\s*"));
            dish.DISH_TOPPINGS_IDs = items.toArray(new String[items.size()]);
            /// dish.DISH_TOPPINGS_IDs = objFood.getString("toppings");

            List<String> items1 = Arrays.asList(objFood.getString("addons").split("\\s*,\\s*"));
            dish.DISH_EXTRAS_IDs = items1.toArray(new String[items.size()]);
            if (i == 2)
                Log.e("toppongid", String.valueOf((dish.DISH_TOPPINGS_IDs[0].equals(""))));

            // dish.DISH_EXTRAS_IDs = objFood.getString("addons");
            dish.DISH_PRICE = (objFood.getString("price"));
            foodDish.add(dish);
        }

        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) context.getApplicationContext());
        OrderDetaiJava.Menu menu = new OrderDetaiJava().new Menu();
        menu.setArrayListDish(foodDish);

        //category
        ArrayList<OrderDetaiJava.Menu.Category> categories = new ArrayList<>();
        final JSONArray categoryArray = RESPONSE.getJSONArray("category");

        for (int i = 0; i < categoryArray.length(); i++) {
            OrderDetaiJava.Menu.Category category = new OrderDetaiJava().new Menu().new Category();

            JSONObject objCategory = categoryArray.getJSONObject(i);
            category.id = objCategory.getString("id");
            category.Name = objCategory.getString("name");

            categories.add(category);
        }
        menu.categoryArrayList = categories;


        //work here


        //toppings
        ArrayList<OrderDetaiJava.Menu.Topping> toppings = new ArrayList<>();
        //work here
        final JSONArray toppingArray = RESPONSE.getJSONArray("topping");

        //work here
        for (int i = 0; i < toppingArray.length(); i++) {
            OrderDetaiJava.Menu.Topping topping = new OrderDetaiJava().new Menu().new Topping();

            JSONObject objTopping = toppingArray.getJSONObject(i);
            topping.id = objTopping.getString("id");
            topping.Name = objTopping.getString("name");
            topping.Diet = objTopping.getString("diet");
            ;
            topping.Desc = objTopping.getString("description");
            ;

            toppings.add(topping);
        }
        menu.toppingArrayList = toppings;

        //work here


        //extra addon
        ArrayList<OrderDetaiJava.Menu.Extra> extras = new ArrayList<>();
        final JSONArray extraArray = RESPONSE.getJSONArray("addon");
//workhere

        for (int i = 0; i < extraArray.length(); i++) {
            OrderDetaiJava.Menu.Extra extra = new OrderDetaiJava().new Menu().new Extra();

            JSONObject objExtra = extraArray.getJSONObject(i);
            extra.id = objExtra.getString("id");
            extra.Name = objExtra.getString("name");
            extra.Diet = objExtra.getString("diet");
            extra.Desc = objExtra.getString("description");
            extra.Price = objExtra.getString("price");

            extras.add(extra);
        }
        menu.extraArrayList = extras;
        orderDetailJava.setMenu(menu);

        Log.e("setmenu end", menu.arrayListDish.get(1).DISHNAME);

    }

    public void sendRequestForUserInfoByMac(final Context context) {

        successResponse = false;


        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String mac = info.getMacAddress();


        // ERROR_COUNT=0;
        //String url = "http://192.168.1.19:8000/order/food/menu/app";

        String url = "http://205.147.103.4:8001/customer/info";
        JSONObject obj = new JSONObject();
        try {
            obj.put("mac_address", mac);
            Log.e("mac", mac);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // final ProgressDialog pd =new ProgressDialog(BrowseMenu.this);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {  //chexk get post

                    @Override
                    public void onResponse(JSONObject response) {
                        RESPONSE_USER_INFO = response;
                        try {
                            save_user_info(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("dimiss", "ok");
                        Log.e("response", RESPONSE_USER_INFO.toString());
                        successResponse = true;
                        // listLoad(BrowseMenu.this);
                        //  pd.dismiss();
                        // pd.cancel();
                        // pd.hide();


                        //this.notify();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.e("error", "error");
                        //if(ERROR_COUNT<6)
                        {
                            // ERROR_COUNT++;
                            // sendRequestForUserInfoByMac(context);


                        }

                    }
                });

        Log.e("response", "b");
        // pd.show(this,"Please Wait...","Please Wait...");

        queue.add(jsObjRequest);
        Log.e("response", "a");

    }

    public void save_user_info(JSONObject response) throws JSONException {
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());


        orderDetailJava.NAME = response.getJSONObject("info").getString("name");
        orderDetailJava.EMAIL = response.getJSONObject("info").getString("email");
        orderDetailJava.MOBILE = response.getJSONObject("info").getString("mobile");

        SharedPreferences.Editor editor = getSharedPreferences("USER_INFO", MODE_PRIVATE).edit();
        editor.putString("name", orderDetailJava.NAME);
        editor.putString("email", orderDetailJava.EMAIL);
        editor.putString("mobile", orderDetailJava.MOBILE);
        editor.apply();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            //toolbar backbutton implemntation
        }


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    public String getMacId(Context context) {

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getBSSID();

    }


    public void updateQuantity() {
        //TODO minimize dishInorder and set qty


        //topping extra update
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());
        ArrayList<OrderDetaiJava.Order.DishInOrder> DIO = orderDetailJava.getOrder().arrayListDishInOrder;
        String[] tempT;
        String[] tempE;

        for (int i = 0; i < DIO.size(); i++) {
            tempT = new String[20];
            tempE = new String[20];
            for (int j = 0; j < DIO.get(i).TOPPINGID.length; j++) {
                tempT[j] = search.toppingIdTotoppingName(this, DIO.get(i).TOPPINGID[j]);
            }
            for (int j = 0; j < DIO.get(i).EXTRAID.length; j++) {
                tempE[j] = search.extraIdToExtraName(this, DIO.get(i).EXTRAID[j]);
            }
            orderDetailJava.getOrder().arrayListDishInOrder.get(i).TOPPING = Arrays.copyOf(tempT, DIO.get(i).TOPPINGID.length);

            orderDetailJava.getOrder().arrayListDishInOrder.get(i).EXTRA = Arrays.copyOf(tempE, DIO.get(i).EXTRAID.length);

        }


        int count = 0;
        for (int i = 0; i < DIO.size(); i++) {
            for (int j = i + 1; j < DIO.size(); j++) {

//listA.containsAll(listB) && listB.containsAll(listA)                        List<String> wordList = Arrays.asList(words);

                //Todo add condition for no topping

                List<String> a = Arrays.asList(DIO.get(i).TOPPINGID);
                List<String> b = Arrays.asList(DIO.get(j).TOPPINGID);

                List<String> c = Arrays.asList(DIO.get(i).EXTRAID);
                List<String> d = Arrays.asList(DIO.get(j).EXTRAID);


                if ((DIO.get(i).DISH_ID.equals(DIO.get(j).DISH_ID)) &&             //remove redundant condition
                        a.containsAll(b) && b.containsAll(a) && c.containsAll(d) && d.containsAll(c)
                        ) {
                    //i and j are same
                    //remove j and increment qty of i;
                    //TODO jahan add kr rhe h wahan qty++ krna h; ya yahin qty 2 bdhana h

                    orderDetailJava.getOrder().arrayListDishInOrder.remove(j);
                    orderDetailJava.getOrder().arrayListDishInOrder.get(i).QUANTITY++;
                }

            }
        }

    }

    public int countDishQtyTotalInOrder(OrderDetaiJava.Menu.Dish dish) {
        updateQuantity();
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());
        ArrayList<OrderDetaiJava.Order.DishInOrder> DIO = orderDetailJava.getOrder().arrayListDishInOrder;

        int count = 0;
        for (int i = 0; i < DIO.size(); i++) {
            if (dish.DISH_ID.equals(DIO.get(i).DISH_ID)) {
                count = count + DIO.get(i).QUANTITY;
            }
        }


        return count;
    }


    @Override
    public void onClick(View view) {
        if (view == showOrder) {

            OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());
            ArrayList<OrderDetaiJava.Order.DishInOrder> DIO = orderDetailJava.getOrder().arrayListDishInOrder;
            updateQuantity();

            /*






            //TODO minimize dishInorder and set qty




            //topping extra update
            OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());
            ArrayList<OrderDetaiJava.Order.DishInOrder> DIO = orderDetailJava.getOrder().arrayListDishInOrder;
            String[] tempT ;
            String[] tempE ;

            for (int i=0;i<DIO.size();i++)
            {
                tempT= new String[20];
                tempE=new String[20];
                for (int j=0 ;j< DIO.get(i).TOPPINGID.length;j++)
                {
                    tempT[j] = search.toppingIdTotoppingName(this,DIO.get(i).TOPPINGID[j]);
                }
                for (int j=0 ;j< DIO.get(i).EXTRAID.length;j++)
                {
                    tempE[j] = search.toppingIdTotoppingName(this,DIO.get(i).EXTRAID[j]);
                }
                orderDetailJava.getOrder().arrayListDishInOrder.get(i).TOPPING= Arrays.copyOf(tempT,DIO.get(i).TOPPINGID.length);
                orderDetailJava.getOrder().arrayListDishInOrder.get(i).EXTRA=Arrays.copyOf(tempE,DIO.get(i).EXTRAID.length);
            }







            int count=0;
            for(int i=0;i<DIO.size();i++)
            {
                for(int j=i+1;j<DIO.size();j++)
                {

//listA.containsAll(listB) && listB.containsAll(listA)                        List<String> wordList = Arrays.asList(words);

                    //Todo add condition for no topping

                    List<String> a = Arrays.asList(DIO.get(i).TOPPINGID);
                    List<String> b = Arrays.asList(DIO.get(j).TOPPINGID);

                    List<String> c = Arrays.asList(DIO.get(i).EXTRAID);
                    List<String> d = Arrays.asList(DIO.get(j).EXTRAID);





                    if(  (DIO.get(i).DISH_ID.equals(DIO.get(j).DISH_ID)) &&             //remove redundant condition
                            a.containsAll(b) && b.containsAll(a)&&  c.containsAll(d)&&d.containsAll(c)
                           )
                    {
                            //i and j are same
                        //remove j and increment qty of i;
                        //TODO jahan add kr rhe h wahan qty++ krna h; ya yahin qty 2 bdhana h

                        orderDetailJava.getOrder().arrayListDishInOrder.remove(j);
                        orderDetailJava.getOrder().arrayListDishInOrder.get(i).QUANTITY++;
                    }

                }
            }*/




/*            for(int i=0;i<DIO.size();i++)
            {
                if(DIO.get(i).DISH_ID.equals(dishes[position].DISH_ID))
                {
                    Log.e("same",dishinorder.get(i).DISH_ID);
                    index=i;
                    for (int j=i+1;j<dishinorder.size();j++)
                    {
                        if(!Arrays.equals(dishinorder.get(j).TOPPINGID,dishinorder.get(i).TOPPINGID)   *//*!dishinorder.get(j).TOPPINGID.equals(dishinorder.get(i).TOPPINGID)*//*  ) //not  equal;
                        {
                            sameFound=false;
                            break loop;
                        }
                    }

                }
            }
            */


            Intent intent = new Intent(this, ReviewOrder.class);

            //toast
            Toast.makeText(this, String.valueOf(orderDetailJava.getOrder().getArrayListDishInOrder().size()), Toast.LENGTH_SHORT).show();

            Log.e("click", String.valueOf(orderDetailJava.getOrder().getArrayListDishInOrder().size()));

            if (DIO.size() > 0) {
                startActivity(intent);
            } else {
                Snackbar.make(view, "Please select at least one dish", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                //Toast.makeText(this, "Please select at least one dish", Toast.LENGTH_SHORT).show();
                Log.e("size!>0", "retain");
            }
        }


    }


    public void listLoad(Context context) {

        //List view of browse menu
        ListView listView = (ListView) findViewById(R.id.menu_list_browse_menu);
/*
        CustomBrowseMenuListAdapter adapter=new CustomBrowseMenuListAdapter(this,DISH,PRICE);
*/
        CustomBrowseMenuListAdapter adapter = new CustomBrowseMenuListAdapter(context, getMenuDishArray());

        listView.setAdapter(adapter); //list view end

    }


    public void showDialog(final OrderDetaiJava.Menu.Dish dish, final TextView tvpqty, final int qty, final View tvADDbutton, final Button buttonPlus, final Button buttonMinus) {


        isDialogConfirm = false;
        dialog = new Dialog(this);


        View view = getLayoutInflater().inflate(R.layout.topping_extra_dialog_layout, null);  //dialog file


        ListView lv = (ListView) view.findViewById(R.id.topping_list_toppingExtra);  //listview

        // Change MyActivity.this and myListOfItems to your own values
        CustomToppingListDialogAdapter1 clad = new CustomToppingListDialogAdapter1(BrowseMenu.this, dish.DISH_TOPPINGS_IDs, dish.MAX_TOPPING); //by defalt mini
        lv.setAdapter(clad);


        //lv.setOnItemClickListener(........);

        ListView lvextra = (ListView) view.findViewById(R.id.extra_list_toppingextra);  //listview

        // Change MyActivity.this and myListOfItems to your own values
        CustomExtraListDialogAdapter1 cladextra = new CustomExtraListDialogAdapter1(BrowseMenu.this, dish.DISH_EXTRAS_IDs); //by default mini
        lvextra.setAdapter(cladextra);


        //lvextra.setOnItemClickListener(........);

        TextView tvCancel = view.findViewById(R.id.dialog_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                toppingList.clear();
                extraList.clear();
                dialog.cancel();
            }
        });


        TextView tvConfirm = view.findViewById(R.id.dialog_confirm);
        tvConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //</code>
                if (toppingList.size() == 0) {
                    Snackbar.make(view, "Select atleast one topping", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } else if (toppingList.size() > Integer.parseInt(dish.MAX_TOPPING)) {
                    Snackbar.make(view, "Max toppping limit is " + dish.MAX_TOPPING, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    update(dish);
                    dialog.cancel();
                    Log.e("cancel", "cancel");
                    //tvpqty.setText(String.valueOf(qty+1));
                    tvpqty.setText(String.valueOf(countDishQtyTotalInOrder(dish)));

                    tvADDbutton.setVisibility(View.INVISIBLE);
                    buttonPlus.setVisibility(View.VISIBLE);
                    buttonMinus.setVisibility(View.VISIBLE);
                    tvpqty.setVisibility(View.VISIBLE);

                }


            }
        });

        dialog.setContentView(view);

        //dialog.setButton(DialogInterface.BUTTON_POSITIVE,);


        // dialog.set

        // dialog.setCancelable(false);


        dialog.show();

    }

    public void update(OrderDetaiJava.Menu.Dish dish) {
        String[] extrastring = extraList.toArray(new String[extraList.size()]);


        //add extra topping dish to order;
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());
        OrderDetaiJava.Order order = new OrderDetaiJava().new Order();
        OrderDetaiJava.Order.DishInOrder dishInOrder = new OrderDetaiJava().new Order().new DishInOrder();


        dishInOrder.DISH_ID = dish.DISH_ID;


        dishInOrder.TOPPINGID = toppingList.toArray(new String[toppingList.size()]);
        dishInOrder.EXTRAID = extraList.toArray(new String[extraList.size()]);
                /*sizeDetailsInOrder.EXTRA=extrastring;  //future -- relate it to extra id
                sizeDetailsInOrder.TOPPING=toppingList.toArray(new String[toppingList.size()]);*/

/*
                dishInOrder.setSizeDetails(sizeDetailsInOrder);
*/
        dishInOrder.DISHNAME = dish.DISHNAME;
        dishInOrder.PRICE = dish.DISH_PRICE;
        dishInOrder.QUANTITY++;
        dishInOrderArrayList.add(dishInOrder);
        order.arrayListDishInOrder = dishInOrderArrayList;

        orderDetailJava.setOrder(order);

        toppingList.clear();
        extraList.clear();

    }


    public void dialogCancel() {
        TextView tvCancel = (TextView) findViewById(R.id.dialog_cancel);

        tvCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.cancel();

            }
        });

    }


    public void drawNavigation() {
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


        Drawer result = new DrawerBuilder()
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
                        if (position == 3) {
                            Intent intent = new Intent(BrowseMenu.this, OrderDetail.class);
                            startActivity(intent);
                        } else if (position == 5) {
                            Intent intent = new Intent(BrowseMenu.this, OrderHistoryDetailsActivity.class);
                            startActivity(intent);

                        } else if (position == 7) {
                            Intent intent = new Intent(BrowseMenu.this, RegisterActivity.class);
                            startActivity(intent);
                        }

                        //TODO
                        return false;
                    }
                })
                .build();

        result.setSelection(1);

    }


    public boolean checkToppingExtraAny(String DISH) {
        //write </code>
        return true;
    }


/*
      call setMenu funtion for every dish
*/
  /*  public void setOneDish(String[] SIZE,int[] PRICE,String[][] EXTRA,int[][] EXTRA_PRICE,String[][] EXTRAID,String[][] TOPPING, String[][] TOPPINGID, Boolean isToppingExtra,
                        String DISH_ID, String DISHNAME)
    {
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());
        //set menu
        *//*orderDetailJava.menu.arrayListDish.add()
        *//*
        OrderDetaiJava.Menu.Dish.SizeDetails sizeDetails= new OrderDetaiJava().new Menu().new Dish().new SizeDetails();
        sizeDetails.SIZE=SIZE;
        sizeDetails.PRICE=PRICE;
        sizeDetails.EXTRA=EXTRA;
        sizeDetails.EXTRA_PRICE=EXTRA_PRICE;
        sizeDetails.EXTRAID=EXTRAID;
        sizeDetails.TOPPING=TOPPING;
        sizeDetails.TOPPINGID=TOPPINGID;

        OrderDetaiJava.Menu.Dish dish= new OrderDetaiJava().new Menu().new Dish();
        dish.isToppingExtra=isToppingExtra;
        dish.DISH_ID=DISH_ID;
        dish.DISHNAME=DISHNAME;
        dish.setSizeDetails(sizeDetails);

        OrderDetaiJava.Menu menu= new OrderDetaiJava().new Menu();
        menu.arrayListDish.add(dish);
        dishArrayList.add(dish);
    }

    public void setMenu(){
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());
        OrderDetaiJava.Menu menu= new OrderDetaiJava().new Menu();
        menu.arrayListDish=dishArrayList;
        orderDetailJava.setMenu(menu);
    }*/

    public void setRestaurantDetail(String RESTAURANTNAME, String RESTAURANT_ID) {
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());
        orderDetailJava.RESTAURANTNAME = RESTAURANTNAME;
        orderDetailJava.RESTAURANT_ID = RESTAURANT_ID;

    }


/*
    Here start order
*/

   /* public  void setOneOrder(String SIZE,int PRICE,String[] EXTRA,int[] EXTRA_PRICE,String[] EXTRAID,String[] TOPPING, String[] TOPPINGID, Boolean isToppingExtra,
                          String DISH_ID, String DISHNAME)
    {
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());

        OrderDetaiJava.Order.DishInOrder.SizeDetailsInOrder sizeDetailsInOrder=new  OrderDetaiJava().new Order().new DishInOrder().new SizeDetailsInOrder();
        sizeDetailsInOrder.SIZE=SIZE;
        sizeDetailsInOrder.PRICE=PRICE;
        sizeDetailsInOrder.EXTRA=EXTRA;
        sizeDetailsInOrder.EXTRA_PRICE=EXTRA_PRICE;
        sizeDetailsInOrder.EXTRAID=EXTRAID;
        sizeDetailsInOrder.TOPPING=TOPPING;
        sizeDetailsInOrder.TOPPINGID=TOPPINGID;

        OrderDetaiJava.Order.DishInOrder dishInOrder=new  OrderDetaiJava().new Order().new DishInOrder();
        dishInOrder.isToppingExtra=isToppingExtra;
        dishInOrder.DISH_ID=DISH_ID;
        dishInOrder.DISHNAME=DISHNAME;
        dishInOrder.setSizeDetails(sizeDetailsInOrder);

        OrderDetaiJava.Order order=new  OrderDetaiJava().new Order();
        order.calculateSubTotal();
        order.calculateTotal();

        dishInOrderArrayList.add(dishInOrder);
        order.arrayListDishInOrder=dishInOrderArrayList;

    }

    public void  setOrder()
    {
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());

        OrderDetaiJava.Order order= new OrderDetaiJava().new Order();
        order.setArrayListDish(dishInOrderArrayList);

        orderDetailJava.setOrder(order);
    }*/


    /*
    get menu & load menu
    */
    public OrderDetaiJava.Menu.Dish[] getMenuDishArray() {
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());
        // orderDetailJava.RESTAURANTNAME = "CAli";

        TextView tv = (TextView) findViewById(R.id.restaurantNameBrowseMenu);
        tv.setText(orderDetailJava.RESTAURANTNAME);

        OrderDetaiJava.Menu menu = new OrderDetaiJava().new Menu();

        menu = orderDetailJava.getMenu();

        ArrayList<OrderDetaiJava.Menu.Dish> dishes = new ArrayList<OrderDetaiJava.Menu.Dish>();
        dishes = menu.getArrayListDish();

        OrderDetaiJava.Menu.Dish[] dishArray = dishes.toArray(new OrderDetaiJava.Menu.Dish[dishes.size()]);


        int n = dishes.size();
        return dishArray;

       /* for(int i=0;i<n;i++)   *//*extract all details*//*
        {
             *//*String dishname = dishes.get(i).DISHNAME;
            String dish_id = dishes.get(i).DISH_ID;
            int max_topping = dishes.get(i).MAX_TOPPING;
            boolean isToppingExtra = dishes.get(i).isToppingExtra;

            OrderDetaiJava.Menu.Dish.SizeDetails sizeDetails = dishes.get(i).getSizeDetails();
            String[] size = sizeDetails.SIZE;
            int[] price = sizeDetails.PRICE;
            String[][] toppingid = sizeDetails.TOPPINGID;
            String[][] topping = sizeDetails.TOPPING;
            String[][] extraid = sizeDetails.EXTRAID;
            int[][] extra_price = sizeDetails.EXTRA_PRICE;*//*








        }*/


    }


    class CustomToppingListDialogAdapter1 extends ArrayAdapter<String> {


        Context context;
        String[] dish_toppingID;
        String max_topping;

        //    String[] size;
//    String[] topping;
//    String[] extra;
        // String[] price;
        public CustomToppingListDialogAdapter1(@NonNull Context c, String[] DISH_TOPPING, String max_topping) {    //future change to id
            super(c, R.layout.browse_menu_list_layout, R.id.dishName, DISH_TOPPING);   //list template
            this.context = c;
            this.dish_toppingID = DISH_TOPPING;
            this.max_topping = max_topping;
            //this.price=PRICE;
//        this.size=SIZE;
//        this.topping=TOPPING;
//        this.extra=EXTRA;


        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) { //getview called for every row
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.topping_list_layout_dialog, parent, false);   //list template
            final CheckBox chdish = (CheckBox) row.findViewById(R.id.toppping_list_item_dialog);
            //TextView tvprice=(TextView) row.findViewById(R.id.priceValue_browse_menu_list);

//        TextView tvsize=(TextView) row.findViewById(R.id.sizeValue);
//        TextView tvtopping=(TextView) row.findViewById(R.id.toppingList);
//        TextView tvextra=(TextView) row.findViewById(R.id.extraList);
            Toast.makeText(BrowseMenu.this, dish_toppingID[0], Toast.LENGTH_SHORT).show();


            chdish.setText(search.toppingIdTotoppingName(context, dish_toppingID[position]));
            Toast.makeText(BrowseMenu.this, "dishtopping", Toast.LENGTH_SHORT).show();


            //TextView tvprice=(TextView) row.findViewById(R.id.priceValue_browse_menu_list);

//        TextView tvsize=(TextView) row.findViewById(R.id.sizeValue);
//        TextView tvtopping=(TextView) row.findViewById(R.id.toppingList);
//        TextView tvextra=(TextView) row.findViewById(R.id.extraList);


            chdish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        toppingList.add(dish_toppingID[position]);
                    } else {
                        toppingList.remove(dish_toppingID[position]);
                    }

                }
            });


            // tvdish.setText(price[position]);

//        tvsize.setText(size[position]);
//        tvtopping.setText(topping[position]);
//        tvextra.setText(extra[position]);


            return row;  //super.getView(position, convertView, parent);
        }
    }


    class CustomExtraListDialogAdapter1 extends ArrayAdapter<String> {

        Context context;
        String[] dish_extra;

        //    String[] size;
//    String[] topping;
//    String[] extra;
        // String[] price;
        public CustomExtraListDialogAdapter1(@NonNull Context c, String[] DISH_extra) {
            super(c, R.layout.browse_menu_list_layout, R.id.dishName, DISH_extra);   //list template
            Log.e("inside extra", "ok");

            this.context = c;
            this.dish_extra = DISH_extra;
            //this.price=PRICE;
//        this.size=SIZE;
//        this.topping=TOPPING;
//        this.extra=EXTRA;


        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) { //getview called for every row
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.extra_list_layout_dialog, parent, false);   //list template
            final CheckBox chdish = (CheckBox) row.findViewById(R.id.extra_list_item_diaolg);
            TextView tvprice = (TextView) row.findViewById(R.id.extra_price_dialog);
            tvprice.setText(search.extraIdToExtraPrice(getApplicationContext(), dish_extra[position]));

            //TextView tvprice=(TextView) row.findViewById(R.id.priceValue_browse_menu_list);

//        TextView tvsize=(TextView) row.findViewById(R.id.sizeValue);
//        TextView tvtopping=(TextView) row.findViewById(R.id.toppingList);
//        TextView tvextra=(TextView) row.findViewById(R.id.extraList);

            chdish.setText(search.extraIdToExtraName(context, dish_extra[position]));


            chdish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        extraList.add(dish_extra[position]);
                    } else {
                        extraList.remove(dish_extra[position]);
                    }

                }
            });

            // tvdish.setText(price[position]);

//        tvsize.setText(size[position]);
//        tvtopping.setText(topping[position]);
//        tvextra.setText(extra[position]);


            return row;  //super.getView(position, convertView, parent);
        }


    }


    class CustomBrowseMenuListAdapter extends ArrayAdapter<OrderDetaiJava.Menu.Dish> {

        Context context;
        /*String[] dish;
        //    String[] size;
//    String[] topping;
//    String[] extra;
        String[] price;*/

        String[] size;
        int[] price;
        String[][] toppingid;
        String[][] topping;
        String[][] extraid;
        int[][] extra_price;

        String dishname;
        String dish_id;
        int max_topping;
        boolean isToppingExtra;
        OrderDetaiJava.Menu.Dish[] dishes;
        OrderDetaiJava orderDetailJava = ((OrderDetaiJava) getApplicationContext());
        OrderDetaiJava.Order order = new OrderDetaiJava().new Order();
        OrderDetaiJava.Order.DishInOrder dishInOrder = new OrderDetaiJava().new Order().new DishInOrder();


        public CustomBrowseMenuListAdapter(@NonNull Context c, OrderDetaiJava.Menu.Dish[] dishes) {

            super(c, R.layout.browse_menu_list_layout, R.id.dishName, dishes);

            Log.e("list start", "list start");


            this.context = c;
            this.dishes = dishes;




            /*this.dish=DISH;
            this.price=PRICE;*/

//        this.size=SIZE;
//        this.topping=TOPPING;
//        this.extra=EXTRA;


        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) { //getview called for every row

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.browse_menu_list_layout, parent, false);
            final TextView tvdish = (TextView) row.findViewById(R.id.dishName_browse_menu_list);
            TextView tvprice = (TextView) row.findViewById(R.id.priceValue_browse_menu_list);

            final View tvADDbutton;
            tvADDbutton = row.findViewById(R.id.add_button_custom_id);
            final Button buttonPlus = (Button) row.findViewById(R.id.buttonPlus_Browse_menu_list);
            final Button buttonMinus = (Button) row.findViewById(R.id.buttonMinus_Browse_menu_list);


            //tvADDbutton.setOnClickListener(this);
            final TextView tvpqty = (TextView) row.findViewById(R.id.qty_browse_menu_list);
            tvpqty.setText(String.valueOf(countDishQtyTotalInOrder(dishes[position])));

            if (countDishQtyTotalInOrder(dishes[position]) >= 1) {
                tvADDbutton.setVisibility(View.INVISIBLE);
                buttonPlus.setVisibility(View.VISIBLE);
                buttonMinus.setVisibility(View.VISIBLE);
                tvpqty.setVisibility(View.VISIBLE);

            }
            if (countDishQtyTotalInOrder(dishes[position]) == 0) {
                tvADDbutton.setVisibility(View.VISIBLE);
                buttonPlus.setVisibility(View.INVISIBLE);
                buttonMinus.setVisibility(View.INVISIBLE);
                tvpqty.setVisibility(View.INVISIBLE);

            }


//        TextView tvsize=(TextView) row.findViewById(R.id.sizeValue);
//        TextView tvtopping=(TextView) row.findViewById(R.id.toppingList);
//        TextView tvextra=(TextView) row.findViewById(R.id.extraList);
            Toast.makeText(BrowseMenu.this, "start", Toast.LENGTH_SHORT).show();
            Log.d("Start", "Start");

            tvdish.setText(dishes[position].DISHNAME);
            tvprice.setText(String.valueOf(dishes[position].DISH_PRICE));  //by default mini price //this gives error //error recovered
            Toast.makeText(BrowseMenu.this, String.valueOf(dishes[position].DISH_PRICE), Toast.LENGTH_SHORT).show();
            Log.d("end", "end");


//        tvsize.setText(size[position]);
//        tvtopping.setText(topping[position]);
//        tvextra.setText(extra[position]);


            tvADDbutton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //button plus logic

                   /* if(!dishes[position].DISH_TOPPINGS_IDs[0].equals(""))  //if topping
                    {
                        showDialog(dishes[position]);
                        Log.e("dialog","Dialog success");
                        if(isDialogConfirm)
                        {
                            int i= Integer.parseInt(tvpqty.getText().toString()); i++;
                            tvpqty.setText(Integer.toString(i));
                        }

                        //on confirm increment and add to order
                    }
                    else
                    {
                        int i= Integer.parseInt(tvpqty.getText().toString()); i++;
                        tvpqty.setText(Integer.toString(i));
                    }*/

                    if (dishes[position].DISH_TOPPINGS_IDs[0].equals("")) //no toppong
                    {
                        Log.e("topping no", "ok");

                        int i = Integer.parseInt(tvpqty.getText().toString());
                        Log.e("topping no", String.valueOf(i));
                        i++;

                        update(dishes[position]);

                        // tvpqty.setText(String.valueOf(i));

                        tvpqty.setText(String.valueOf(countDishQtyTotalInOrder(dishes[position])));


                        if (i >= 1) {
                            tvADDbutton.setVisibility(View.INVISIBLE);
                            buttonPlus.setVisibility(View.VISIBLE);
                            buttonMinus.setVisibility(View.VISIBLE);
                            tvpqty.setVisibility(View.VISIBLE);

                        }


                    } else //topping
                    {
                        Log.e("topping yes", "ok");

                        int i = Integer.parseInt(tvpqty.getText().toString());
                        showDialog(dishes[position], tvpqty, i, tvADDbutton, buttonPlus, buttonMinus);  //remove redundant parameter
                        Log.e("isd", String.valueOf(isDialogConfirm));
                        i = Integer.parseInt(tvpqty.getText().toString());


                    }

                  /*  if(checkToppingExtraAny(tvpqty.getText().toString()))
                    {
                        showDialog();
                    }*/

                   /* int i= Integer.parseInt(tvpqty.getText().toString()); i++;
                    tvpqty.setText(Integer.toString(i));*/
                    //write code for cart update


                    int i = Integer.parseInt(tvpqty.getText().toString());
                    if (i >= 1) {
                        tvADDbutton.setVisibility(View.INVISIBLE);
                        buttonPlus.setVisibility(View.VISIBLE);
                        buttonMinus.setVisibility(View.VISIBLE);
                        tvpqty.setVisibility(View.VISIBLE);

                    }


                }

            });

            buttonPlus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //button plus logic
                    if (dishes[position].DISH_TOPPINGS_IDs[0].equals(""))   //no topping
                    {
                        int i = Integer.parseInt(tvpqty.getText().toString());
                        i++;
                        update(dishes[position]);

                        //tvpqty.setText(String.valueOf(i));
                        tvpqty.setText(String.valueOf(countDishQtyTotalInOrder(dishes[position])));


                    } else //topping
                    {
                        int i = Integer.parseInt(tvpqty.getText().toString());
                        //show dialog
                        showDialog(dishes[position], tvpqty, i, tvADDbutton, buttonPlus, buttonMinus);  //remove redundant parameter
                    }

                    //write code for cart update


                }
            });


            buttonMinus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //button minus logic

                    //check if all same in arraylist dishinoeder

                    ArrayList<OrderDetaiJava.Order.DishInOrder> dishinorder = orderDetailJava.order.getArrayListDishInOrder();

                    Boolean sameFound = true;
                    int index = 0;
                    loop:
                    for (int i = 0; i < dishinorder.size(); i++) {
                        if (dishinorder.get(i).DISH_ID.equals(dishes[position].DISH_ID)) {
                            Log.e("same", dishinorder.get(i).DISH_ID);
                            index = i;
                            for (int j = i + 1; j < dishinorder.size(); j++) {

                                List<String> a = Arrays.asList(dishinorder.get(i).TOPPINGID);
                                List<String> b = Arrays.asList(dishinorder.get(j).TOPPINGID);

                                List<String> c = Arrays.asList(dishinorder.get(i).EXTRAID);
                                List<String> d = Arrays.asList(dishinorder.get(j).EXTRAID);
                                if (!(a.containsAll(b) && b.containsAll(a) && c.containsAll(d) && d.containsAll(c))) /*!dishinorder.get(j).TOPPINGID.equals(dishinorder.get(i).TOPPINGID)*/  //not  equal;
                                {
                                    sameFound = false;
                                    break loop;
                                }
                            }

                        }
                    }
                    Log.e("same", sameFound + " " + dishinorder.get(index).DISHNAME);


                    if (sameFound) {
                        //delete
                        // dishinorder.remove(index);
                        Log.e("size1", String.valueOf(dishinorder.size()));

                        orderDetailJava.order.getArrayListDishInOrder().get(index).QUANTITY--;
                        if (orderDetailJava.order.getArrayListDishInOrder().get(index).QUANTITY == 0) {
                            orderDetailJava.order.getArrayListDishInOrder().remove(index);


                        }
                        //orderDetailJava.order.getArrayListDishInOrder().remove(index);


                        //dishinorder.remove(index);

                        Log.e("size2", String.valueOf(dishinorder.size()));

                        int i = Integer.parseInt(tvpqty.getText().toString());
                        i--;

                        if (countDishQtyTotalInOrder(dishes[position]) >= 0) {
                            //tvpqty.setText(String.valueOf(i));
                            tvpqty.setText(String.valueOf(countDishQtyTotalInOrder(dishes[position])));


                        }
                        if (countDishQtyTotalInOrder(dishes[position]) == 0) {

                            tvADDbutton.setVisibility(View.VISIBLE);
                            buttonPlus.setVisibility(View.INVISIBLE);
                            buttonMinus.setVisibility(View.INVISIBLE);
                            tvpqty.setVisibility(View.INVISIBLE);


                        }
                    } else {
                        Toast.makeText(BrowseMenu.this, "Different Dish", Toast.LENGTH_SHORT).show();

                    }

                    //write code for cart update


                }
            });

            return row;  //super.getView(position, convertView, parent);
        }

    }


}



