package siac.com.shortcut;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class ShortcutUtils {
    private Activity context;
    private ShortcutManager shortcutManager;
    private List<ShortcutInfo> dynamicShortcutInfos;
    private List<String> disabledDynamicShortCutIds;
    private List<String> enabledDynamicShortCutIds;
    private List<String> removedDynamicShortCutIds;

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public ShortcutUtils(Activity context) {
        this.context = context;
        shortcutManager = context.getSystemService(ShortcutManager.class);
        dynamicShortcutInfos = new ArrayList<>();
        disabledDynamicShortCutIds = new ArrayList<>();
        enabledDynamicShortCutIds = new ArrayList<>();
        removedDynamicShortCutIds = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public void addDynamicShortCut(Shortcut shortcut, IReceiveStringExtra iReceiveStringExtra) {
        Intent intent = new Intent(context.getApplicationContext(), context.getClass());
        intent.putExtra(shortcut.getIntentStringExtraKey(), shortcut.getIntentStringExtraValue());
        intent.setAction(shortcut.getIntentAction());
        ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(context, shortcut.getShortcutId())
                .setShortLabel(shortcut.getShortcutShortLabel())
                .setLongLabel(shortcut.getShortcutLongLabel())
                .setIcon(Icon.createWithResource(context, shortcut.getShortcutIcon()))
                .setIntent(intent)
                .build();
        iReceiveStringExtra.onReceiveStringExtra(shortcut.getIntentStringExtraKey(), shortcut.getIntentStringExtraValue());
        dynamicShortcutInfos.add(shortcutInfo);
        if (shortcutManager != null) {
            shortcutManager.setDynamicShortcuts(dynamicShortcutInfos);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public void removeDynamicShortCut(Shortcut shortcut) {
        if (shortcutManager != null) {
            removedDynamicShortCutIds.add(shortcut.getShortcutId());
            shortcutManager.removeDynamicShortcuts(removedDynamicShortCutIds);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public void enableDynamicShortCut(Shortcut shortcut) {
        if (shortcutManager != null) {
            enabledDynamicShortCutIds.add(shortcut.getShortcutId());
            shortcutManager.enableShortcuts(enabledDynamicShortCutIds);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public void disableDynamicShortCut(Shortcut shortcut) {
        if (shortcutManager != null) {
            disabledDynamicShortCutIds.add(shortcut.getShortcutId());
            shortcutManager.disableShortcuts(disabledDynamicShortCutIds);
        }
    }
}

