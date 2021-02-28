package com.example.wasterra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_add;
    EditText et_item, et_qty;
    ListView lv_inv;

    ArrayAdapter itemArrayAdapter;
    DatabaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        et_item = findViewById(R.id.et_item);
        et_qty = findViewById(R.id.et_qty);
        lv_inv = findViewById(R.id.lv_inv);

        dataBaseHelper = new DatabaseHelper(MainActivity.this);

        itemArrayAdapter = new ArrayAdapter<ItemModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEverything());
        lv_inv.setAdapter(itemArrayAdapter);

        //DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ItemModel itemModel;

                try{
                    itemModel = new ItemModel(-1, et_item.getText().toString(), Integer.parseInt(et_qty.getText().toString()));
                    Toast.makeText(MainActivity.this, itemModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error adding item", Toast.LENGTH_SHORT).show();
                    itemModel = new ItemModel(-1, "error", 0);
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                boolean success = databaseHelper.addOne(itemModel);

                Toast.makeText(MainActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
                itemArrayAdapter = new ArrayAdapter<ItemModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEverything());
                lv_inv.setAdapter(itemArrayAdapter);
            }
        });

        lv_inv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemModel clickedItem = (ItemModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedItem);
                itemArrayAdapter = new ArrayAdapter<ItemModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEverything());
                lv_inv.setAdapter(itemArrayAdapter);
                Toast.makeText(MainActivity.this, "Deleted " + clickedItem.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}