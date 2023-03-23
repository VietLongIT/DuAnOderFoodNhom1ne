package ph29152.fptpoly.duanoderfoodnhom1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ph29152.fptpoly.duanoderfoodnhom1.Adapter.ProductAdapter;
import ph29152.fptpoly.duanoderfoodnhom1.Adapter.TopSellingAdapter;
import ph29152.fptpoly.duanoderfoodnhom1.Helper.Connection_SQL;
import ph29152.fptpoly.duanoderfoodnhom1.Model.Hamburger;
import ph29152.fptpoly.duanoderfoodnhom1.R;

public class HomeFragment extends Fragment {
    RecyclerView rcvTopSelling,rcvListBurgerHome;
    TopSellingAdapter adapter;
    List<Hamburger> list  = new ArrayList<>();
    Connection_SQL connection_sql;
    ProductAdapter productAdapter;
    List<Hamburger> list2 = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvTopSelling=view.findViewById(R.id.rcvTopSelling);
        rcvListBurgerHome=view.findViewById(R.id.rcvListBurgerHome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        getList();
        adapter= new TopSellingAdapter(list,getContext());
        rcvTopSelling.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        getListBurgerHome();
        productAdapter = new ProductAdapter(list2,getContext());
        rcvListBurgerHome.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();



    }

    private void getListBurgerHome() {
        try{
            connection_sql = new Connection_SQL();
            String sql = "SELECT * FROM HAMBURGER";
            PreparedStatement stm = connection_sql.SQLconnection().prepareStatement(sql);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("IDHAMBURGER");
                String ten = resultSet.getString("TEN");
                String moTaNgan = resultSet.getString("MOTANGAN");
                String moTaChiTiet = resultSet.getString("MOTACHITIET");
                double giaTien = resultSet.getDouble("GIATIEN");
                int soLuong = resultSet.getInt("SOLUONG");
                String hinhAnh = resultSet.getString("HINHANH");
                int daBan = resultSet.getInt("DABAN");
                Hamburger hamburger = new Hamburger(id,ten,moTaNgan,moTaChiTiet,hinhAnh,soLuong,giaTien,daBan);
                list2.add(hamburger);
            }
            resultSet.close();
            stm.close();
            connection_sql.SQLconnection().close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getList() {
        try{
            connection_sql = new Connection_SQL();
            String sql = "SELECT TOP 10 * FROM HAMBURGER ORDER BY DABAN DESC";
            PreparedStatement stm = connection_sql.SQLconnection().prepareStatement(sql);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("IDHAMBURGER");
                String ten = resultSet.getString("TEN");
                String moTaNgan = resultSet.getString("MOTANGAN");
                String moTaChiTiet = resultSet.getString("MOTACHITIET");
                double giaTien = resultSet.getDouble("GIATIEN");
                int soLuong = resultSet.getInt("SOLUONG");
                String hinhAnh = resultSet.getString("HINHANH");
                int daBan = resultSet.getInt("DABAN");
                Hamburger hamburger = new Hamburger(id,ten,moTaNgan,moTaChiTiet,hinhAnh,soLuong,giaTien,daBan);
                list.add(hamburger);
            }
            resultSet.close();
            stm.close();
            connection_sql.SQLconnection().close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
