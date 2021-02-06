package com.example.semana12wsrestphp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter  extends ArrayAdapter<Usuario> {

List<Usuario> listado;


 public ListAdapter(Context context , List<Usuario> lista)

        {

            super(context,R.layout.item_usuario , lista);


         listado=lista;
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = convertView;
        if(view ==null){


            view = inflater.inflate(R.layout.item_usuario,null);

            TextView lblcorreo = view.findViewById(R.id.txtitemCorreo);
            TextView lblNombre = view.findViewById(R.id.txtitemNombre);
            Usuario usuario=listado.get(position);
            lblcorreo.setText(usuario.getCorreo());
            lblNombre.setText(usuario.getNombre());


        }




        return view;






    }
}
