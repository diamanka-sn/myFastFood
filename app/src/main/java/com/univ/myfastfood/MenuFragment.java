package com.univ.myfastfood;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuFragment extends Fragment {

    private ListView listMenu;
    private String [] tabMenu, tabDetails;
    private String menu, details;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        listMenu = view.findViewById(R.id.listMenu);
        tabMenu = getResources().getStringArray(R.array.tab_menu);
        tabDetails = getResources().getStringArray(R.array.tab_details);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tabMenu);
        listMenu.setAdapter(adapter);//Affichage des données sur la liste.
        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                menu = tabMenu[i];
                details = tabDetails[i];
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setTitle(menu);
                dialog.setMessage(details);
                dialog.setNegativeButton(getString(R.string.back), null);
                dialog.setPositiveButton("Commander", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:778795172"));
                        intent.putExtra("sms_body","Bonjour myFastFood, je veux commander du "+menu);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });
        return view;
    }
}