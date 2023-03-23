package ph29152.fptpoly.duanoderfoodnhom1.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ph29152.fptpoly.duanoderfoodnhom1.Model.Hamburger;
import ph29152.fptpoly.duanoderfoodnhom1.R;

public class TopSellingAdapter extends RecyclerView.Adapter<TopSellingAdapter.MyViewHolder>{
    List<Hamburger> list = new ArrayList<>();
    Context context;

    public TopSellingAdapter(List<Hamburger> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_burger,parent,false);
        return new MyViewHolder(view);    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Hamburger item = list.get(position);
        holder.tvTenSPact.setText(item.getTen());
        holder.tvGiaTienact.setText(""+item.getGiaTien());
        holder.tvMotaNganact.setText(item.getMoTaNgan());
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGBA_F16;
        opts.inMutable = true;
        byte[] decodedString = Base64.decode(item.getHinhAnh(), Base64.DEFAULT);
        Bitmap myBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length,opts);
        myBitmap.setHasAlpha(true);
        holder.imgBurger.setImageBitmap(myBitmap);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenSPact, tvMotaNganact, tvGiaTienact;
        ImageView imgBurger;
//        CardView btnAddtoCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSPact= itemView.findViewById(R.id.tvTenSPact);
            tvMotaNganact=itemView.findViewById(R.id.tvMotaNganact);
            tvGiaTienact=itemView.findViewById(R.id.tvGiaTienact);
            imgBurger=itemView.findViewById(R.id.imgBurger);


        }

    }
}
