package chihane.jdaddressselector.listener;

import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;

public interface OnAddressSelectedListener {
    void onAddressSelected(Province province, City city, County county, Street street);
    void onProvinceSelected(Province province);
    void onCitySelected(City city);
    void onCountySelected(County county);
}
