package siac.com.androidui.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import siac.com.androidui.R;
import siac.com.componentes.SwipeLayout;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

public class SwipLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swip_layout);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new HorizontalAdapter(getItems()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<String> getItems() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            items.add("item " + i);
        }
        return items;
    }


    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ItemHolder> {
        private List<String> items;

        HorizontalAdapter(List<String> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new ItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_swip_layout_item, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {
            itemHolder.dragItem.setText(items.get(position));
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        private void remove(Context context, int position) {
            Toast.makeText(context, "removed item " + position, Toast.LENGTH_SHORT).show();
        }

        private void upload(Context context, int position) {
            Toast.makeText(context, "upload item " + position, Toast.LENGTH_SHORT).show();
        }

        class ItemHolder extends RecyclerView.ViewHolder {
            TextView dragItem;
            ImageView leftView;
            ImageView rightView;
            SwipeLayout swipeLayout;

            ItemHolder(@NonNull final View itemView) {
                super(itemView);
                dragItem = itemView.findViewById(R.id.drag_item);
                swipeLayout = itemView.findViewById(R.id.swipe_layout);
                leftView = itemView.findViewById(R.id.left_view);
                rightView = itemView.findViewById(R.id.right_view);

                rightView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getAdapterPosition() != NO_POSITION) {
                            remove(itemView.getContext(), getAdapterPosition());
                        }
                    }
                });

                leftView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getAdapterPosition() != NO_POSITION) {
                            upload(itemView.getContext(), getAdapterPosition());
                        }
                    }
                });
            }
        }
    }

}