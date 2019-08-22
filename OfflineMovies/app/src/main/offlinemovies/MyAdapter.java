package com.appdeepo.offlinemovies;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Model> profiles;
    private List<Model> exampleListFull;




    public MyAdapter(Context c , ArrayList<Model> p)
    {
        context = c;
        profiles = p;
        exampleListFull = new ArrayList<>(profiles);
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Model profile = profiles.get(position);
        holder.name.setText(profile.getName());
        holder.email.setText(profile.getEmail());
        holder.aurthor.setText(profile.getAurthor());
        holder.keydata.setText(profile.getKeydata());
        holder.plink.setText(profile.getPlink());
        holder.dlink.setText(profile.getDlink());
        holder.subcategory.setText(profile.getCategory());

        Picasso.get().load(profile.getProfilepic()).into(holder.profilepic);


        holder.linearmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,postdetail.class);
                i.putExtra("profilepic", profile.getProfilepic());
                i.putExtra("name",profile.getName());
                i.putExtra("email",profile.getEmail());
                i.putExtra("keydata",profile.getKeydata());
                i.putExtra("aurthor",profile.getAurthor());
                i.putExtra("dlink",profile.getDlink());
                i.putExtra("plink",profile.getPlink());
                i.putExtra("subcategory",profile.getCategory());


                context.startActivity(i);
            }
        });


    }



    @Override
    public int getItemCount() {
        return profiles.size();
    }







    class MyViewHolder extends RecyclerView.ViewHolder {


        LinearLayout linearmain;
        TextView name,email,keydata,dlink,plink,aurthor,subcategory;
        ImageView profilepic;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            profilepic = (ImageView) itemView.findViewById(R.id.profilepic);
            aurthor = (TextView) itemView.findViewById(R.id.aurthor);
            keydata = (TextView) itemView.findViewById(R.id.keydata);
            dlink = (TextView) itemView.findViewById(R.id.dlink);
            plink = (TextView) itemView.findViewById(R.id.plink);
            subcategory = (TextView) itemView.findViewById(R.id.subcategory);




            linearmain = itemView.findViewById(R.id.linearmain);




        }




    }



}
