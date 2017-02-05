package chihane.jdaddressselector.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Collections;

import chihane.jdaddressselector.BottomDialog;
import chihane.jdaddressselector.listener.OnAddressSelectedListener;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;
import chihane.jdaddressselector.util.ToastUtils;

public class MainActivity extends AppCompatActivity implements OnAddressSelectedListener {

//    AddressSelector selector;

    BottomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
//
//        selector = new AddressSelector(this);
//        selector.setOnAddressSelectedListener(this);
//
//        Province province = new Province();
//        province.id = 1;
//        province.name = "测试用省份";
//        selector.setProvinces(Collections.singletonList(province));
//
//        assert frameLayout != null;
//        frameLayout.addView(selector.getView());

        Button buttonBottomDialog = (Button) findViewById(R.id.buttonBottomDialog);
        assert buttonBottomDialog != null;
        buttonBottomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new BottomDialog(MainActivity.this);
                dialog.setOnAddressSelectedListener(MainActivity.this);
                dialog.show();
            }
        });
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        String s =
                (province == null ? "" : province.name) +
                (city == null ? "" : "\n" + city.name) +
                (county == null ? "" : "\n" + county.name) +
                (street == null ? "" : "\n" + street.name);

        ToastUtils.showShort(this, s);
    }

    @Override
    public void onProvinceSelected(Province province) {

        // TODO: 2017/2/5 请求城市数据
        City city = new City();
        city.province_id = province.id;
        city.id = 2;
        city.name = "城市";
        dialog.getSelector().setCities(Collections.singletonList(city));
    }

    @Override
    public void onCitySelected(City city) {

        // TODO: 2017/2/5 请求县乡数据
        County county = new County();
        county.city_id = city.id;
        county.id = 3;
        county.name = "乡镇";
        dialog.getSelector().setCountries(Collections.singletonList(county));
    }

    @Override
    public void onCountySelected(County county) {
        Street street = new Street();
        street.id = 4;
        street.county_id = county.id;
        street.name = "街道";

        dialog.getSelector().setStreets(Collections.singletonList(street));
    }

}
