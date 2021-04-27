package br.com.utill;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.FragmentActivity;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BackgroundTask {

    public static final int MESSAGE_FINISH = 415024;
    public static final int MESSAGE_BROKEN = 415025;
    public static final int MESSAGE_STOP = 415026;
    public static final String TAG_HOOK = "HOOK";
    private static final Integer ID_ACTIVITY = 415027;
    private static final Integer ID_FRAGMENT_ACTIVITY = 415028;
    private static final Integer ID_FRAGMENT = 415029;
    private static final Integer ID_SUPPORT_FRAGMENT = 415030;
    private static AtomicInteger count = new AtomicInteger(0);
    private BackgroundTask.Holder holder = null;
    private Map<Integer, TaskDescription> taskMap = new ConcurrentHashMap();
    private Map<Integer, MessageListener> messageMap = new ConcurrentHashMap();
    private Map<Integer, FinishListener> finishMap = new ConcurrentHashMap();
    private Map<Integer, BrokenListener> brokenMap = new ConcurrentHashMap();
    private Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 8);

    private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        public boolean handleMessage(@NonNull Message message) {
            BackgroundTask.Holder result;
            if (message.what == 415024 && message.obj instanceof BackgroundTask.Holder) {
                result = (BackgroundTask.Holder)message.obj;
                BackgroundTask.this.taskMap.remove(result.id);
                BackgroundTask.this.messageMap.remove(result.id);
                BackgroundTask.this.brokenMap.remove(result.id);
                BackgroundTask.FinishListener listener = (BackgroundTask.FinishListener) BackgroundTask.this.finishMap.remove(result.id);
                if (listener != null) {
                    listener.onFinish(result.object);
                }

                BackgroundTask.getInstance().dispatchUnregister();
            } else if (message.what == 415025 && message.obj instanceof BackgroundTask.Holder) {
                result = (BackgroundTask.Holder)message.obj;
                BackgroundTask.this.taskMap.remove(result.id);
                BackgroundTask.this.messageMap.remove(result.id);
                BackgroundTask.this.finishMap.remove(result.id);
                BackgroundTask.BrokenListener listenerxx = (BackgroundTask.BrokenListener) BackgroundTask.this.brokenMap.remove(result.id);
                if (listenerxx != null) {
                    listenerxx.onBroken((Exception)result.object);
                }

                BackgroundTask.getInstance().dispatchUnregister();
            } else if (message.what == 415026) {
                BackgroundTask.this.resetHolder();
                BackgroundTask.this.taskMap.clear();
                BackgroundTask.this.messageMap.clear();
                BackgroundTask.this.finishMap.clear();
                BackgroundTask.this.brokenMap.clear();
            } else {
                Iterator i$ = BackgroundTask.this.messageMap.values().iterator();

                while(i$.hasNext()) {
                    BackgroundTask.MessageListener listenerx = (BackgroundTask.MessageListener)i$.next();
                    listenerx.handleMessage(message);
                }
            }

            return true;
        }
    });

    public BackgroundTask() {
    }

    @MainThread
    public static BackgroundTask.Register with(@NonNull Activity activity) {
        getInstance().registerHookToContext(activity);
        return getInstance().buildRegister(activity);
    }

    @MainThread
    public static BackgroundTask.Register with(@NonNull FragmentActivity activity) {
        getInstance().registerHookToContext(activity);
        return getInstance().buildRegister(activity);
    }

    @MainThread
    public static BackgroundTask.Register with(@NonNull Fragment fragment) {
        getInstance().registerHookToContext(fragment);
        return getInstance().buildRegister(fragment);
    }

    @MainThread
    public static BackgroundTask.Register with(@NonNull androidx.fragment.app.Fragment fragment) {
        getInstance().registerHookToContext(fragment);
        return getInstance().buildRegister(fragment);
    }

    @WorkerThread
    public static void post(@NonNull Message message) {
        getInstance().handler.sendMessage(message);
    }

    private static BackgroundTask getInstance() {
        return BackgroundTask.SugarTaskHolder.INSTANCE;
    }

    private BackgroundTask.Register buildRegister(@NonNull Activity activity) {
        this.holder = new BackgroundTask.Holder(ID_ACTIVITY, activity);
        return new BackgroundTask.Register(count.getAndIncrement());
    }

    private BackgroundTask.Register buildRegister(@NonNull FragmentActivity activity) {
        this.holder = new BackgroundTask.Holder(ID_FRAGMENT_ACTIVITY, activity);
        return new BackgroundTask.Register(count.getAndIncrement());
    }

    private BackgroundTask.Register buildRegister(@NonNull Fragment fragment) {
        this.holder = new BackgroundTask.Holder(ID_FRAGMENT, fragment);
        return new BackgroundTask.Register(count.getAndIncrement());
    }

    private BackgroundTask.Register buildRegister(@NonNull androidx.fragment.app.Fragment fragment) {
        this.holder = new BackgroundTask.Holder(ID_SUPPORT_FRAGMENT, fragment);
        return new BackgroundTask.Register(count.getAndIncrement());
    }

    private Runnable buildRunnable(@NonNull final Integer id) {
        return new Runnable() {
            public void run() {
                Process.setThreadPriority(10);
                if (BackgroundTask.this.taskMap.containsKey(id)) {
                    Message message = Message.obtain();

                    try {
                        message.what = 415024;
                        message.obj = BackgroundTask.this.new Holder(id, ((BackgroundTask.TaskDescription) BackgroundTask.this.taskMap.get(id)).onPreExecute());
                        message.obj = BackgroundTask.this.new Holder(id, ((BackgroundTask.TaskDescription) BackgroundTask.this.taskMap.get(id)).onBackground());
                    } catch (Exception var3) {
                        message.what = 415025;
                        message.obj = BackgroundTask.this.new Holder(id, var3);
                    }

                    BackgroundTask.post(message);
                }

            }
        };
    }

    private void registerHookToContext(@NonNull Activity activity) {
        FragmentManager manager = activity.getFragmentManager();
        BackgroundTask.HookFragment hookFragment = (BackgroundTask.HookFragment)manager.findFragmentByTag("HOOK");
        if (hookFragment == null) {
            hookFragment = new BackgroundTask.HookFragment();
            manager.beginTransaction().add(hookFragment, "HOOK").commitAllowingStateLoss();
        }

    }

    private void registerHookToContext(@NonNull FragmentActivity activity) {
        androidx.fragment.app.FragmentManager manager = activity.getSupportFragmentManager();
        BackgroundTask.HookSupportFragment hookSupportFragment = (BackgroundTask.HookSupportFragment)manager.findFragmentByTag("HOOK");
        if (hookSupportFragment == null) {
            hookSupportFragment = new BackgroundTask.HookSupportFragment();
            manager.beginTransaction().add(hookSupportFragment, "HOOK").commitAllowingStateLoss();
        }

    }

    @TargetApi(17)
    private void registerHookToContext(@NonNull Fragment fragment) {
        FragmentManager manager = fragment.getChildFragmentManager();
        BackgroundTask.HookFragment hookFragment = (BackgroundTask.HookFragment)manager.findFragmentByTag("HOOK");
        if (hookFragment == null) {
            hookFragment = new BackgroundTask.HookFragment();
            manager.beginTransaction().add(hookFragment, "HOOK").commitAllowingStateLoss();
        }

    }

    private void registerHookToContext(@NonNull androidx.fragment.app.Fragment fragment) {
        androidx.fragment.app.FragmentManager manager = fragment.getChildFragmentManager();
        BackgroundTask.HookSupportFragment hookSupportFragment = (BackgroundTask.HookSupportFragment)manager.findFragmentByTag("HOOK");
        if (hookSupportFragment == null) {
            hookSupportFragment = new BackgroundTask.HookSupportFragment();
            manager.beginTransaction().add(hookSupportFragment, "HOOK").commitAllowingStateLoss();
        }

    }

    private void dispatchUnregister() {
        if (this.holder != null && this.taskMap.size() <= 0) {
            if (this.holder.id.equals(ID_ACTIVITY) && this.holder.object instanceof Activity) {
                this.unregisterHookToContext((Activity)this.holder.object);
            } else if (this.holder.id.equals(ID_FRAGMENT_ACTIVITY) && this.holder.object instanceof FragmentActivity) {
                this.unregisterHookToContext((FragmentActivity)this.holder.object);
            } else if (this.holder.id.equals(ID_FRAGMENT) && this.holder.object instanceof Fragment) {
                this.unregisterHookToContext((Fragment)this.holder.object);
            } else if (this.holder.id.equals(ID_SUPPORT_FRAGMENT) && this.holder.object instanceof androidx.fragment.app.Fragment) {
                this.unregisterHookToContext((androidx.fragment.app.Fragment)this.holder.object);
            }

            this.resetHolder();
        }
    }

    private void unregisterHookToContext(@NonNull Activity activity) {
        FragmentManager manager = activity.getFragmentManager();
        BackgroundTask.HookFragment hookFragment = (BackgroundTask.HookFragment)manager.findFragmentByTag("HOOK");
        if (hookFragment != null) {
            hookFragment.postEnable = false;
            manager.beginTransaction().remove(hookFragment).commitAllowingStateLoss();
        }

    }

    private void unregisterHookToContext(@NonNull FragmentActivity activity) {
        androidx.fragment.app.FragmentManager manager = activity.getSupportFragmentManager();
        BackgroundTask.HookSupportFragment hookSupportFragment = (BackgroundTask.HookSupportFragment)manager.findFragmentByTag("HOOK");
        if (hookSupportFragment != null) {
            hookSupportFragment.postEnable = false;
            manager.beginTransaction().remove(hookSupportFragment).commitAllowingStateLoss();
        }

    }

    @TargetApi(17)
    private void unregisterHookToContext(@NonNull Fragment fragment) {
        FragmentManager manager = fragment.getChildFragmentManager();
        BackgroundTask.HookFragment hookFragment = (BackgroundTask.HookFragment)manager.findFragmentByTag("HOOK");
        if (hookFragment != null) {
            hookFragment.postEnable = false;
            manager.beginTransaction().remove(hookFragment).commitAllowingStateLoss();
        }

    }

    private void unregisterHookToContext(@NonNull androidx.fragment.app.Fragment fragment) {
        androidx.fragment.app.FragmentManager manager = fragment.getChildFragmentManager();
        BackgroundTask.HookSupportFragment hookSupportFragment = (BackgroundTask.HookSupportFragment)manager.findFragmentByTag("HOOK");
        if (hookSupportFragment != null) {
            hookSupportFragment.postEnable = false;
            manager.beginTransaction().remove(hookSupportFragment).commitAllowingStateLoss();
        }

    }

    private void resetHolder() {
        if (this.holder != null) {
            this.holder.id = 0;
            this.holder.object = null;
            this.holder = null;
        }
    }

    private static class SugarTaskHolder {
        public static final BackgroundTask INSTANCE = new BackgroundTask();

        private SugarTaskHolder() {
        }
    }

    public static class HookSupportFragment extends androidx.fragment.app.Fragment {
        protected boolean postEnable = true;

        public HookSupportFragment() {
        }

        public void onStop() {
            super.onStop();
            if (this.postEnable) {
                Message message = new Message();
                message.what = 415026;
                BackgroundTask.post(message);
            }

        }
    }

    public static class HookFragment extends Fragment {
        protected boolean postEnable = true;

        public HookFragment() {
        }

        public void onStop() {
            super.onStop();
            if (this.postEnable) {
                Message message = new Message();
                message.what = 415026;
                BackgroundTask.post(message);
            }

        }
    }

    private class Holder {
        private Integer id;
        private Object object;

        private Holder(@NonNull Integer id, @Nullable Object object) {
            this.id = id;
            this.object = object;
        }
    }

    public class Builder {
        private Integer id;

        private Builder(@NonNull Integer id) {
            this.id = id;
        }

        @MainThread
        public BackgroundTask.Builder handle(@NonNull BackgroundTask.MessageListener listener) {
            BackgroundTask.this.messageMap.put(this.id, listener);
            return this;
        }

        @MainThread
        public BackgroundTask.Builder finish(@NonNull BackgroundTask.FinishListener listener) {
            BackgroundTask.this.finishMap.put(this.id, listener);
            return this;
        }

        @MainThread
        public BackgroundTask.Builder broken(@NonNull BackgroundTask.BrokenListener listener) {
            BackgroundTask.this.brokenMap.put(this.id, listener);
            return this;
        }

        @MainThread
        public void execute() {
            BackgroundTask.this.executor.execute(BackgroundTask.this.buildRunnable(this.id));
        }
    }

    public class Register {
        private Integer id;

        private Register(@NonNull Integer id) {
            this.id = id;
        }

        @MainThread
        public BackgroundTask.Builder assign(@NonNull BackgroundTask.TaskDescription description) {
            BackgroundTask.this.taskMap.put(this.id, description);
            return BackgroundTask.this.new Builder(this.id);
        }
    }

    public interface BrokenListener {
        void onBroken(@NonNull Exception var1);
    }

    public interface FinishListener {
        void onFinish(@Nullable Object var1);
    }

    public interface MessageListener {
        void handleMessage(@NonNull Message var1);
    }

    public interface TaskDescription {
        Void onPreExecute();
        Object onBackground();
    }
}

