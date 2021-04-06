package siac.com.androidui.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import siac.com.androidui.MainActivity;
import siac.com.androidui.R;
import siac.com.componentes.CustomDialog;
import siac.com.componentes.RecyclerViewButton;

public class ComponentesTresActivity extends AppCompatActivity {

    private List<Cidade> lCidade = new ArrayList<>();
    private RecyclerViewButton recyclerViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componentes_tres);

        ///

        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    CustomDialog customDialog = new CustomDialog(ComponentesTresActivity.this);
                    customDialog.setContentView(R.layout.cadastro)
                            .setToolbarTitle("Log IN")
                            .setToolbarSubTitle("Enter the system")
                            .setMenuToolbar(R.menu.menu_bar)
                            .setBackgroundResource(CustomDialog.DWindow.ROUND)
                            .setHeight(CustomDialog.DLayoutParams.WRAP_CONTENT)
                            .setCancelable(true)
                            .create();

                    customDialog.menu(R.id.miCompose).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            System.out.println("MENU");
                            return false;
                        }
                    });

                    Dialog dialog = customDialog.dialog();

                    Button button = dialog.findViewById(R.id.button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println("BUTTON");
                        }
                    });

                    customDialog.setNavigationOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println("aquiss");
                        }
                    });

                    customDialog.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////

        recyclerViewButton = findViewById(R.id.recyclerViewButton);

        new Async().execute();

        recyclerViewButton.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Async().execute();
            }
        });

        recyclerViewButton.addOnItemTouchListener(new RecyclerViewButton.ItemClickListener(new RecyclerViewButton.Listener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println("aqui");
            }

            @Override
            public void onLongItemClick(View view, int position) {
                System.out.println("aqui");
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("aqui");
            }
        }));
    }

    public class Async extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            recyclerViewButton.setRefreshing(true);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            contruct();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recyclerViewButton.setRefreshing(false);
        }
    }

    private void contruct(){

        for(int i = 0; i < 50; i++) {
            lCidade.add(new Cidade("GOIANIA", "GO"));
            lCidade.add(new Cidade("BRAZABRANTES", "GO"));
            lCidade.add(new Cidade("INHUMAS", "GO"));
            lCidade.add(new Cidade("ITAU", "GO"));
        }

        recyclerViewButton.setAdapter(this, new Adapter());
    }

    public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private LayoutInflater mInflater;

        Adapter() {
            this.mInflater = LayoutInflater.from(getBaseContext());
        }

        @Override
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.cidade_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            final ViewHolder viewHolder = (ViewHolder) holder;

            Cidade cidade = lCidade.get(position);

            viewHolder.nomeTextView.setText(cidade.getNome());
            viewHolder.ufTextView.setText(cidade.getUf());
        }

        @Override
        public int getItemCount() {
            return lCidade.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView nomeTextView;
            TextView ufTextView;

            ViewHolder(View itemView) {
                super(itemView);
                nomeTextView = itemView.findViewById(R.id.nomeTextView);
                ufTextView = itemView.findViewById(R.id.ufTextView);
            }
        }

        Cidade getItem(int id) {
            return lCidade.get(id);
        }
    }

    public class Cidade {

        private String nome;
        private String uf;

        public Cidade(String nome, String uf) {
            this.nome = nome;
            this.uf = uf;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getUf() {
            return uf;
        }

        public void setUf(String uf) {
            this.uf = uf;
        }
    }
}