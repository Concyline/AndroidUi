package siac.com.componentes;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class RecyclerViewButton extends FrameLayout {

    private SwipeRefreshLayout swipeRefreshLayout;
    private static RecyclerView recyclerView;
    private ImageView goToTopImageView;
    private RelativeLayout painelButtonRelativeLayout;

    private static Context context;
    private int locationButton;
    private int numberOfColumns;
    private boolean horizontalDivider;
    private boolean verticalDivider;
    private boolean refresh;
    private boolean basic;
    private int listitem;
    int dyMaster = 0;

    public RecyclerViewButton(@NonNull Context context) {
        super(context);
        obtainStyledAttributes(context, null, 0);
        init();
    }

    public RecyclerViewButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttributes(context, attrs, 0);
        init();
    }

    public RecyclerViewButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttributes(context, attrs, defStyleAttr);
        init();
    }

    private void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RecyclerViewButton, defStyleAttr, 0);

            locationButton = typedArray.getInteger(R.styleable.RecyclerViewButton_locationButton, 0);
            numberOfColumns = typedArray.getInteger(R.styleable.RecyclerViewButton_numberOfColumns, 1);
            horizontalDivider = typedArray.getBoolean(R.styleable.RecyclerViewButton_horizontalDivider, false);
            verticalDivider = typedArray.getBoolean(R.styleable.RecyclerViewButton_verticalDivider, false);
            refresh = typedArray.getBoolean(R.styleable.RecyclerViewButton_refresh, true);
            basic = typedArray.getBoolean(R.styleable.RecyclerViewButton_basic, false);
            listitem = typedArray.getResourceId(R.styleable.RecyclerViewButton_listitem, 1);
            return;
        }

    }

    private void init() {
        inflate(getContext(), R.layout.view_recyclerview_button_ui, this);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        goToTopImageView = findViewById(R.id.goToTopImageView);
        recyclerView = findViewById(R.id.recyclerView);
        painelButtonRelativeLayout = findViewById(R.id.painelButtonRelativeLayout);

        setup();
    }

    private void setup() {

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) goToTopImageView.getLayoutParams();

        if (locationButton == 1) {
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        } else if (locationButton == 2) {
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        } else if (locationButton == 3) { // NONE BUTTON
            painelButtonRelativeLayout.setVisibility(View.GONE);

        } else {
            params.addRule(RelativeLayout.TEXT_ALIGNMENT_CENTER);
        }

        goToTopImageView.setLayoutParams(params);

        goToTopImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeIn(v);
                if(dyMaster > 0) {
                    recyclerView.smoothScrollToPosition(0);
                }
            }
        });

        recyclerView.addOnScrollListener(onScrollListener);

        swipeRefreshLayout.setEnabled(refresh);

        if(basic){
            swipeRefreshLayout.setEnabled(false);
            painelButtonRelativeLayout.setVisibility(View.GONE);
        }

    }

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            dyMaster = dy;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                Animation in = AnimationUtils.loadAnimation(context, R.anim.fadeout_lento);
                goToTopImageView.startAnimation(in);
                goToTopImageView.setVisibility(View.INVISIBLE);

            } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                goToTopImageView.setVisibility(View.VISIBLE);
            }
        }
    };

    public void setAdapter(final Activity context, @Nullable final RecyclerView.Adapter adapter) {
        this.context = context;

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView.setLayoutManager(new GridLayoutManager(context, numberOfColumns));

                if (horizontalDivider) {
                    recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
                }

                if (verticalDivider) {
                    recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                }

                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    public void addOnItemTouchListener(@NonNull RecyclerView.OnItemTouchListener listener){
        recyclerView.addOnItemTouchListener(listener);
    }

    public void scrollToPosition(int position){
        recyclerView.scrollToPosition(position);
    }

    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);

        if(!refreshing) {
            Animation in = AnimationUtils.loadAnimation(context, R.anim.fadeout_lento);
            goToTopImageView.startAnimation(in);
            goToTopImageView.setVisibility(View.INVISIBLE);
        }
    }

    private void fadeIn(View button) {
        Animation in = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        button.startAnimation(in);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public static class ItemClickListener implements RecyclerView.OnItemTouchListener {

        private Listener mListener;
        GestureDetector mGestureDetector;

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

        public ItemClickListener(Listener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });

        }
    }

    public interface Listener extends AdapterView.OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onLongItemClick(View view, int position);
    }
}

