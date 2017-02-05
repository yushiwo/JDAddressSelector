package chihane.jdaddressselector;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import java.util.Collections;

import chihane.jdaddressselector.listener.OnAddressSelectedListener;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.util.ResUtils;

public class BottomDialog extends Dialog {

    public AddressSelector getSelector() {
        return selector;
    }

    private AddressSelector selector;

    public BottomDialog(Context context) {
        super(context, R.style.bottom_dialog);
        init(context);
    }

    public BottomDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public BottomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        selector = new AddressSelector(context);
        Province province = new Province();
        province.id = 1;
        province.name = "省份";
        selector.setProvinces(Collections.singletonList(province));

        setContentView(selector.getView());

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = ResUtils.dp2px(context, 256);
        window.setAttributes(params);

        window.setGravity(Gravity.BOTTOM);
    }

    public void setOnAddressSelectedListener(OnAddressSelectedListener listener) {
        this.selector.setOnAddressSelectedListener(listener);
    }

    public static BottomDialog show(Context context) {
        return show(context, null);
    }

    public static BottomDialog show(Context context, OnAddressSelectedListener listener) {
        BottomDialog dialog = new BottomDialog(context, R.style.bottom_dialog);
        dialog.selector.setOnAddressSelectedListener(listener);
        dialog.show();

        return dialog;
    }

}
