package che.sai.android.mbds.fr.bipper2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sai on 10/28/16.
 */

public class PersonItemAdapter extends BaseAdapter {
    private Context context;
    public List<Person> person;
    View.OnClickListener listener;

    public PersonItemAdapter(Context context, List<Person> person, View.OnClickListener listener) {
        this.listener = listener;
        this.context = context;
        this.person = person;
    }

    @Override
    public int getCount() {
        return person.size();
    }

    @Override
    public Person getItem(int position) {
        return person.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        PersonViewHolder viewHolder = null;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.line_person, parent, false);


            //v = View.inflate(context, R.layout.line_person, null);
            viewHolder = new PersonViewHolder();
            viewHolder.nom_prenom= (TextView)v.findViewById(R.id.txt_nom_prenom);
            viewHolder.status= (TextView)v.findViewById(R.id.txt_connected);
            viewHolder.buzzBtn= (TextView)v.findViewById(R.id.txt_buzz);
            viewHolder.deleteBtn= (Button) v.findViewById(R.id.deleteBtn);
            v.setTag(viewHolder);
        } else{
            viewHolder = (PersonViewHolder) v.getTag();
        }

        Person person = this.getItem(position);

        if(person.isConnected()){
            viewHolder.status.setText(R.string.status_connected);
            viewHolder.buzzBtn.setBackgroundColor(context.getColor(R.color.color_buzz_connected));

            //ICI A FAIRE CHANGER LA COULEUR
            // viewHolder.buzzBtn.setCompoundDrawables(null,context.getDrawable(R.drawable.buzz_button),null,null);

        }else{
            viewHolder.status.setText(R.string.status_not_connected);
            //viewHolder.buzzBtn.setBackgroundColor(R.color.color_buzz_not_connected);
            //viewHolder.buzzBtn.setBackgroundResource(R.color.color_buzz_not_connected);
            //viewHolder.buzzBtn.setBackgroundColor();
            viewHolder.buzzBtn.setBackgroundColor(context.getColor(R.color.color_buzz_not_connected));
        }
        viewHolder.nom_prenom.setText(person.getNom() +" " + person.getPrenom());
        viewHolder.deleteBtn.setOnClickListener(listener);
        viewHolder.deleteBtn.setTag(person.getId());

        return v;
    }

    class PersonViewHolder{
        TextView nom_prenom;
        TextView status;
        Button deleteBtn;
        TextView buzzBtn;
    }
}
