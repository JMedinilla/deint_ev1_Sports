package com.jmedinilla.medinilladeportes_2;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class Adapter extends ArrayAdapter<Sport> {
    private Context context;

    Adapter(Context context) {
        super(context, R.layout.row_list, new ArrayList<>(Repository.getInstance()));
        this.context = context;
    }

    Adapter(Context context, ArrayList<Parcelable> sports) {
        super(context, R.layout.row_list);
        this.context = context;
        this.clear();
        for (int i = 0; i < sports.size(); i++) {
            this.add((Sport) sports.get(i));
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        SportHolder holder;
        View rootView = convertView;

        if (rootView == null) {
            rootView = LayoutInflater.from(this.context).inflate(R.layout.row_list, parent, false);
            holder = new SportHolder();

            holder.imgIcon = (ImageView) rootView.findViewById(R.id.row_imgIcon);
            holder.txtName = (TextView) rootView.findViewById(R.id.row_txtName);
            holder.chbChecked = (CheckBox) rootView.findViewById(R.id.row_chbChecked);

            rootView.setTag(holder);
        } else {
            holder = (SportHolder) rootView.getTag();
        }

        final Sport sport = getItem(position);
        holder.chbChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (sport != null) {
                    sport.setSport_checked(b);
                }
            }
        });

        if (sport != null) {
            holder.imgIcon.setBackgroundResource(sport.getSport_image());
            holder.txtName.setText(sport.getSport_name());
            holder.chbChecked.setChecked(sport.isSport_checked());
        }

        return rootView;
    }

    boolean[] getCheckedItems() {
        boolean[] tmp = new boolean[getCount()];
        for (int i = 0; i < getCount(); i++) {
            Sport sport = getItem(i);
            if (sport != null) {
                tmp[i] = sport.isSport_checked();
            }
        }
        return tmp;
    }

    void setCheckedItems(boolean[] checkedItems) {
        for (int i = 0; i < getCount(); i++) {
            Sport sport = getItem(i);
            if (sport != null) {
                sport.setSport_checked(checkedItems[i]);
            }
        }
    }

    void loadCharacter(String character) {
        Repository repository = Repository.getInstance();
        this.clear();
        for (int i = 0; i < repository.size(); i++) {
            Sport sport = repository.get(i);
            if (sport.getSport_name().toUpperCase().startsWith(character.toUpperCase())) {
                add(sport);
            }
        }
    }

    void reloadSports() {
        this.clear();
        addAll(Repository.getInstance());
    }

    private class SportHolder {
        ImageView imgIcon;
        TextView txtName;
        CheckBox chbChecked;
    }
}
