package lam.fpoly.adminmanager.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.adminmanager.Adapter.ViewPagerAdapter;
import lam.fpoly.adminmanager.Dao.TbDanhMucDao;
import lam.fpoly.adminmanager.FragmentViewPager.Create_Fragment;
import lam.fpoly.adminmanager.Model.TbDanhMuc;
import lam.fpoly.adminmanager.Model.TbSanPham;
import lam.fpoly.adminmanager.R;

public class QuanLySanPham extends Fragment {

    private TabLayout idTabDanhMuc;
    private ViewPager2 pager_DanhMuc;
    private List<TbSanPham> list;
    String TAG = "zzzzz";
    Toolbar toolbar;
    //LottieAnimationView loading;
    String [] title = {};

    TbDanhMucDao danhMucDao = new TbDanhMucDao();

    List<TbDanhMuc> getList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_quan_ly_san_pham, container, false);
        setHasOptionsMenu(true);

        Log.i(TAG, "onCreateView: ");

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem.OnActionExpandListener onActionExpandListener=new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return false;
            }
        };
        menu.findItem(R.id.search).setOnActionExpandListener(onActionExpandListener);

        SearchView searchView= (SearchView)menu.findItem(R.id.search) .getActionView();
        searchView.setQueryHint("nhập sản phẩm cần tìm nhé....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        super.onCreateOptionsMenu(menu,inflater);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ScrollTab(view);
    }

    public void ScrollTab(View view){
        getList = danhMucDao.getAll();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity());
        idTabDanhMuc = view.findViewById(R.id.idTabDanhMuc);
        pager_DanhMuc = view.findViewById(R.id.idViewDanhMuc);
        pager_DanhMuc.setAdapter(adapter);
        new TabLayoutMediator(idTabDanhMuc, pager_DanhMuc,((tab, position) ->
                tab.setText(getList.get(position).getTen_danhMuc()))).attach();
        idTabDanhMuc.setTabGravity(TabLayout.GRAVITY_CENTER);
        idTabDanhMuc.setTabMode(TabLayout.MODE_SCROLLABLE);
        idTabDanhMuc.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Create_Fragment.ID_DM =tab.getPosition()+1;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}