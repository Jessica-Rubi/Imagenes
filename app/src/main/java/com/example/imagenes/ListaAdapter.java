package com.example.imagenes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class ListaAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    String[] nombre;
    String[] imagenes;
    LayoutInflater inflater;

    public ListaAdapter(Context context, String[] titulos, String[] imagenes) {
        this.context = context;
        this.nombre = titulos;
        this.imagenes = imagenes;
    }

    @Override
    public int getCount() {
        return nombre.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView txtTitle;
        NetworkImageView imgImg;
        ImageLoader mImageLoader;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.lista_formato, viewGroup, false);

        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.tituloLista);
        imgImg = (NetworkImageView) itemView.findViewById(R.id.iconLista);

        mImageLoader = ImageVolley.getInstance(context)
                .getImageLoader();
        //Image URL - This can point to any image file supported by Android
        mImageLoader.get(imagenes[i], ImageLoader.getImageListener(imgImg,
                R.mipmap.ic_launcher, android.R.drawable
                        .ic_dialog_alert));

        // Capture position and set to the TextViews
        txtTitle.setText(nombre[i]);
        imgImg.setImageUrl(imagenes[i], mImageLoader);

        return itemView;
    }
}
