package net.xokvip.app;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();

        getWindow().getDecorView().post(() -> {
            disableFitsSystemWindows(getWindow().getDecorView());
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            makeFullScreen();
        }
    }

    private void makeFullScreen() {
        Window window = getWindow();

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);

        WindowCompat.setDecorFitsSystemWindows(window, false);

        WindowInsetsControllerCompat controller =
            WindowCompat.getInsetsController(window, window.getDecorView());
        controller.hide(WindowInsetsCompat.Type.systemBars());
        controller.setSystemBarsBehavior(
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.getAttributes().layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.getAttributes().layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
    }

    private void disableFitsSystemWindows(View view) {
        view.setFitsSystemWindows(false);
        view.setPadding(0, 0, 0, 0);

        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                disableFitsSystemWindows(group.getChildAt(i));
            }
        }

        view.setOnApplyWindowInsetsListener((v, insets) -> {
            v.setPadding(0, 0, 0, 0);
            return insets;
        });
    }
}
