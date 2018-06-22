package in.example.rahul.vegcartpro;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import in.example.rahul.vegcartpro.Interface.ItemClickListener;
import in.example.rahul.vegcartpro.Model.AllFood;
import in.example.rahul.vegcartpro.Model.Cart;
import in.example.rahul.vegcartpro.ViewHolder.FoodViewHolder;
import in.example.rahul.vegcartpro.ViewHolder.MenuViewHolder;

public class MyCart extends AppCompatActivity {
    RecyclerView recyclerView;
    //RecyclerView.LayoutManager layoutManager;
    LinearLayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference allfoodData;
    String name,nameHindi,mName,ImageUrl, det,det2,det3,det4,det5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        database= FirebaseDatabase.getInstance();
        allfoodData= database.getReference("Cart");

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);

        loadImages();
    }
    private void loadImages() {
        FirebaseRecyclerAdapter<Cart, FoodViewHolder> adapter= new FirebaseRecyclerAdapter<Cart, FoodViewHolder>(Cart.class, R.layout.foodcart, FoodViewHolder.class,allfoodData) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Cart model, int position) {
                //Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                viewHolder.tv1.setText(model.getName());
                viewHolder.tv2.setText(model.getPrice());
                viewHolder.tv3.setText(model.getQuantity());
                viewHolder.tv4.setText(model.getAddress());
                //Typeface face2=Typeface.createFromAsset(getAssets(),"fonts/K11.TTF");

               // viewHolder.txtnameHindi.setTypeface(face2);
                // final AllFood clickItem= model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                   /*     name=clickItem.getName();
                        nameHindi=clickItem.getNameHindi();
                        ImageUrl=clickItem.getImage();
                        det=clickItem.getAdvt();
                        det2=clickItem.getVam();
                        det3=clickItem.getDis();
                        det4=clickItem.getDat();
                        det5=clickItem.getPrice();

                        mName= name+"\n"+nameHindi+"\n"+det5;
                        Toast.makeText(MyCart.this,mName,Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(MyCart.this, Detail.class);
                        Bundle bundle= new Bundle();
                        bundle.putString("imageurl",ImageUrl);
                        bundle.putString("deta",det);
                        bundle.putString("deta2",det2);
                        bundle.putString("deta3",det3);
                        bundle.putString("deta4",det4);
                        bundle.putString("deta5",det5);

                        intent.putExtras(bundle);
                        startActivity(intent);
                        */
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}