package com.josue_martinez.tiradadados.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.josue_martinez.tiradadados.model.LayoutTirada;
import com.josue_martinez.tiradadados.R;
import com.josue_martinez.tiradadados.addapters.RecyclerDataAdapter;
import com.josue_martinez.tiradadados.model.Dado;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    //Recycler view del activity_main
    RecyclerView recyclerView;

    //Botones flotantes de añadir y eliminar del actibity_main
    FloatingActionButton addLayout;
    FloatingActionButton delLayout;

    //Realm
    Realm realm;

    //Resultado de los registros de la tabla
    RealmResults<LayoutTirada> realmTiradas;
    RecyclerDataAdapter recyclerDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos la base de datos
        realm = Realm.getDefaultInstance();

        //realmTiradas sera el resultado de la select de la base de datos
        realmTiradas = realm.where(LayoutTirada.class).findAll();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        addLayout = (FloatingActionButton) findViewById(R.id.addLayout);
        delLayout = (FloatingActionButton) findViewById(R.id.delLayout);

        final RecyclerDataAdapter recyclerDataAdapter = new RecyclerDataAdapter(realmTiradas);


        //vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        recyclerView.setAdapter(recyclerDataAdapter);


        //Parte de actualizar realm
        realmTiradas.addChangeListener(new RealmChangeListener<RealmResults<LayoutTirada>>() {
            @Override
            public void onChange(RealmResults<LayoutTirada> boards) {

                recyclerDataAdapter.notifyDataSetChanged();

            }
        });


        //Agregar layout
        addLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertForCreatingLayout("Nueva Tirada","Introduce el numero de dados y el numero de caras");
            }
        });

        //Borrar layout
        delLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                realm.deleteAll();
                realm.commitTransaction();
            }
        });
    }

    //Crear ventana
    private void showAlertForCreatingLayout(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (title != null){
            builder.setTitle(title);
        }
        if (title != null){
            builder.setMessage(message);
        }

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.alert_add_note,null);
        builder.setView(viewInflated);

        //Busca los radioButtons de numDados del alert_add_notes
        final RadioButton radDados1 = (RadioButton) viewInflated.findViewById((R.id.Dados1));
        final RadioButton radDados2 = (RadioButton) viewInflated.findViewById((R.id.Dados2));
        final RadioButton radDados3 = (RadioButton) viewInflated.findViewById((R.id.Dados3));
        final RadioButton radDados4 = (RadioButton) viewInflated.findViewById((R.id.Dados4));

        //Busca los radioButtons de numCaras del alert_add_notes
        final RadioButton radCaras4 = (RadioButton) viewInflated.findViewById(R.id.Caras4);
        final RadioButton radCaras6 = (RadioButton) viewInflated.findViewById(R.id.Caras6);
        final RadioButton radCaras8 = (RadioButton) viewInflated.findViewById(R.id.Caras8);
        final RadioButton radCaras12 = (RadioButton) viewInflated.findViewById(R.id.Caras12);
        final RadioButton radCaras20 = (RadioButton) viewInflated.findViewById(R.id.Caras20);


        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int numDados = 0;
                int numCaras = 0;
                String caras = "";
                //Recojo numero de dados
                if (radDados1.isChecked()){
                    numDados = 1;
                }
                else if(radDados2.isChecked()){
                    numDados = 2;
                }
                else if(radDados3.isChecked()){
                    numDados = 3;
                }
                else if(radDados4.isChecked()){
                    numDados = 4;
                }

                //Recojo numero de caras
                if (radCaras4.isChecked()){
                    numCaras = 4;
                    caras = "4 caras";
                }
                else if(radCaras6.isChecked()){
                    numCaras = 6;
                    caras = "6 caras";
                }
                else  if(radCaras8.isChecked()){
                    numCaras = 8;
                    caras = "8 caras";
                }
                else if(radCaras12.isChecked()){
                    numCaras = 12;
                    caras = "12 caras";
                }
                else if(radCaras20.isChecked()){
                    numCaras = 20;
                    caras = "20 caras";
                }

                if (numDados!=0 && numCaras!=0){
                    realm.beginTransaction();
                    Dado dado = new Dado(numCaras);
                    //Dependiendo del numero de dados que se elijan, creo la tirada con 1 o mas dados
                    LayoutTirada newLayout = new LayoutTirada(caras, 0, 0, 0, 0);
                    switch (numDados){
                        case 1: newLayout.setTirada1(dado.TirarDado());
                            break;
                        case 2: newLayout.setTirada1(dado.TirarDado()); newLayout.setTirada2(dado.TirarDado());
                            break;
                        case 3: newLayout.setTirada1(dado.TirarDado()); newLayout.setTirada2(dado.TirarDado()); newLayout.setTirada3(dado.TirarDado());
                            break;
                        case 4: newLayout.setTirada1(dado.TirarDado()); newLayout.setTirada2(dado.TirarDado()); newLayout.setTirada3(dado.TirarDado()); newLayout.setTirada4(dado.TirarDado());
                            break;
                    }
                    realm.copyToRealm(newLayout);
                    realm.commitTransaction();
                }else{
                    Toast.makeText(getApplicationContext(), "La tirada necesita numero de caras y el numero de dados que se va a usar",Toast.LENGTH_SHORT).show();
                }
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}