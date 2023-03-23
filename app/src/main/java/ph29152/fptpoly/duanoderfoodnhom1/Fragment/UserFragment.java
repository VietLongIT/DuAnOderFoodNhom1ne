package ph29152.fptpoly.duanoderfoodnhom1.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import ph29152.fptpoly.duanoderfoodnhom1.Model.UserSession;
import ph29152.fptpoly.duanoderfoodnhom1.Model.Users;
import ph29152.fptpoly.duanoderfoodnhom1.R;


public class UserFragment extends Fragment {
    private TextView tvNameUser,tvPhoneUser,tvPasswordUser,tvEmailUser,tvAddressUser,tvViTien;
    private ImageView imgAvatarUser;
    private Button btnUpdateUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user,container,false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvNameUser=view.findViewById(R.id.tvNameUser);
        tvPhoneUser=view.findViewById(R.id.tvPhoneUser);
        tvPasswordUser=view.findViewById(R.id.tvPasswordUser);
        tvEmailUser=view.findViewById(R.id.tvEmailUser);
        tvAddressUser=view.findViewById(R.id.tvAddressUser);
        tvViTien=view.findViewById(R.id.tvViTien);
        imgAvatarUser=view.findViewById(R.id.imgAvatarUser);
        btnUpdateUser=view.findViewById(R.id.btnUpdateUser);

        Users users = UserSession.getCurrentUser();
        tvNameUser.setText("Tên người dùng: "+users.getTen());
        tvPhoneUser.setText("Số điện thoại: "+users.getSoDienThoai());
        tvPasswordUser.setText("Mật Khẩu: "+users.getMatKhau());
        tvEmailUser.setText("Email: "+users.getEmail());
        tvAddressUser.setText("Địa Chỉ: "+users.getDiaChi());
        tvViTien.setText("Số dư ví: $"+users.getViTien());
        tvPhoneUser.setText(users.getSoDienThoai());
        if(users.getHinhAnh()!=null){
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inPreferredConfig = Bitmap.Config.RGBA_F16;
            opts.inMutable = true;
            byte[] decodedString = Base64.decode(users.getHinhAnh(), Base64.DEFAULT);
            Bitmap myBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length,opts);
            myBitmap.setHasAlpha(true);
            imgAvatarUser.setImageBitmap(myBitmap);
        }else{
            imgAvatarUser.setImageResource(R.drawable.imgadmin);
        }

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



}
