package com.example.semana12wsrestphp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UsuarioFragment extends Fragment {
    EditText txtCorreoF, txtClaveF, txtfirmaClaveF, txtNombreCompletoF;
    Button btnGrabar;
    ListView lsvUsuario;


    public UsuarioFragment() {


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        txtCorreoF = view.findViewById(R.id.txtCorreoF);
        txtClaveF = view.findViewById(R.id.txtClaveF);
        txtfirmaClaveF = view.findViewById(R.id.txtConfirmarClaveF);
        txtNombreCompletoF = view.findViewById(R.id.txtNombreCompletoF);
        lsvUsuario = view.findViewById(R.id.lsvusuario);
        btnGrabar = view.findViewById(R.id.btnGrabar);


        btnGrabar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                GrabarUsuario(txtCorreoF.getText().toString(), txtClaveF.getText().toString(), txtNombreCompletoF.getText().toString());

                CargarUsuario();


            }
        });

        this.CargarUsuario();
        return view;


    }


    private void GrabarUsuario(String correo, String clave, String nombreCompleto) {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://www.kreapps.biz/esan/grabar.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, "CORREO=" + correo + "&CLAVE=" + clave + "&NOMBRECOMPLETO=" + nombreCompleto,
                new  Response.Listener<JSONArray>() {


                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            Toast.makeText(getActivity().getApplicationContext(), "SE REGISTRO EXITOSAMENTE", Toast.LENGTH_SHORT).show();

                        } catch (Error error) {


                            Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                        }
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override

            public String getBodyContentType() {


                return "application/x.www-form-urlencoded; charset=UTF-8";

            }

        };
        requestQueue.add(request);


    }


///=======================


    private void CargarUsuario() {


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://www.kreapps.biz/esan/usuario.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, "ID=-1", new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                try {


                    List<Usuario> listadoUsuario = new ArrayList<>();


                    for (int i = 0; i < response.length(); i++) {

                        JSONObject jsonChilNode = response.getJSONObject(i);
                        String correo = jsonChilNode.optString("Correo");
                        String nombreCompleto = jsonChilNode.optString("NombreCompleto");

                        listadoUsuario.add(new Usuario("", correo, nombreCompleto));

                    }


                    ListAdapter listAdapter = new ListAdapter(getActivity().getBaseContext(), listadoUsuario);
                    lsvUsuario.setAdapter(listAdapter);


                } catch (JSONException error) {


                    Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override

            public String getBodyContentType() {


                return "application/x-www-form-urlencoded; charset=UTF-8";

            }

        };
        requestQueue.add(request);


    }
}