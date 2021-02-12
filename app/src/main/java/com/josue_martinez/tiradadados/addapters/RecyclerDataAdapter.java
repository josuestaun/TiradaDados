package com.josue_martinez.tiradadados.addapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.josue_martinez.tiradadados.model.LayoutTirada;
import com.josue_martinez.tiradadados.R;

import io.realm.RealmResults;

//Adaptador para generar los layout item_list, el cual relleno con una lista de LayoutTirada
public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.RecyclerDataHolder> {
    private RealmResults<LayoutTirada> listaTiradas;

    public RecyclerDataAdapter(RealmResults<LayoutTirada> listaTiradas){
        this.listaTiradas = listaTiradas;
    }

    @NonNull
    @Override
    public RecyclerDataAdapter.RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new RecyclerDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataAdapter.RecyclerDataHolder holder, int position) {
        holder.assignData(listaTiradas.get(position));
    }

    @Override
    public int getItemCount() {
        return listaTiradas.size();
    }

    public class RecyclerDataHolder extends RecyclerView.ViewHolder {
        TextView tipoDado;
        TextView tirada1;
        TextView tirada2;
        TextView tirada3;
        TextView tirada4;
        TextView totalTirada;
        //Intentaba recoger los botones para poder sumar y restar 1 al total de la tirada
        Button botonSuma;
        Button botonResta;
        int totalActual;
        public RecyclerDataHolder(@NonNull View itemView) {
            super(itemView);
            tipoDado = (TextView)itemView.findViewById(R.id.textViewTipoDado);
            tirada1 = (TextView)itemView.findViewById(R.id.textViewTirada1);
            tirada2 = (TextView)itemView.findViewById(R.id.textViewTirada2);
            tirada3 = (TextView)itemView.findViewById(R.id.textViewTirada3);
            tirada4 = (TextView)itemView.findViewById(R.id.textViewTirada4);
            totalTirada = (TextView)itemView.findViewById(R.id.textViewTotalTirada);
            botonSuma = (Button)itemView.findViewById(R.id.btnSumar);
            botonResta = (Button)itemView.findViewById(R.id.btnRestar);

        //esto no funciona, estos clickListener no deberian ir aqui
            //Botones Sumar/Restar
/*            botonSuma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int totalActual = Integer.parseInt((String) totalTirada.getText());
                    int totalIncremento = totalActual +1;
                    totalTirada.setText(totalIncremento);
                }
            });

            botonResta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int totalActual = Integer.parseInt((String) totalTirada.getText());
                    int totalDecremento = totalActual -1;
                    totalTirada.setText(totalDecremento);
                }
            });
 */
        }

        @SuppressLint("SetTextI18n")
        public void assignData(LayoutTirada tirada) {
            //aqui asigno los datos
            tipoDado.setText(tirada.getTipoDado());
            tirada1.setText(tirada.getTirada1().toString());
            tirada2.setText(tirada.getTirada2().toString());
            tirada3.setText(tirada.getTirada3().toString());
            tirada4.setText(tirada.getTirada4().toString());
            totalTirada.setText(tirada.totalTirada().toString());
        }
    }
}