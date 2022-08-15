package com.univ.myfastfood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecetteFragment extends Fragment {

    private Button btnAjouter;
    private EditText nom_plat, txtprix;
    private String nom,prix;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_recette, container, false);

        btnAjouter = view.findViewById(R.id.btnAjouter);
        nom_plat = view.findViewById(R.id.txtPlat);
        txtprix = view.findViewById(R.id.txtPrix);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nom = nom_plat.getText().toString().trim();
                prix = txtprix.getText().toString().trim();

                if(nom.isEmpty() || prix.isEmpty()){
                    String message = getString(R.string.error_fields);
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Vous avez ajouter \n "+nom+" "+prix+" F cfa", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;
    }
}