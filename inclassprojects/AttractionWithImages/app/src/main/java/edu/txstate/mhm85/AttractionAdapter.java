package edu.txstate.mhm85;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AttractionAdapter extends ArrayAdapter<Attraction> {
    private Context context;
    private int resource;

    // Override constructor.
    // Store context and resource in object because not stored internally.
    public AttractionAdapter(@NonNull Context context, int resource, @NonNull List<Attraction> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    // A view holder class to be used as a buffer inside the getView method.
    // Used to increase efficiency of getView method.
    // Has a default constructor. No need for properties since only used in this class.
    private class ViewHolder {
        TextView textView;
        ImageView imageView;
    }

    // Display view for each attraction.
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Create a view holder to reuse views.
        ViewHolder holder = null;

        // Get current object iterated over in list.
        Attraction bean = getItem(position);

        // Create layout inflater from context system service.
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // Check if convert view has a view inflated already. Corresponds to first row.
        if (convertView == null) {
            // Inflate the inflater to get a view.
            convertView = mInflater.inflate(resource, null);

            // Create new view holder to hold components of view.
            holder = new ViewHolder();

            // Store components from view (text view and image view) in holder.
            holder.textView = convertView.findViewById(R.id.txtName);
            holder.imageView = convertView.findViewById(R.id.ivAttraction);

            // Remember holder used for the view
            convertView.setTag(holder);

        } else { // Reuse components already stored in holder by getting view holder from view.
            // Get holder stored in view
            holder = (ViewHolder) convertView.getTag();
        }

        // Set the text of the text view based on the name from the attraction.
        holder.textView.setText(bean.getName());

        // Set the image for the image view object based on the image id stored in the attraction.
        holder.imageView.setImageResource(bean.getImage());

        return convertView;
    }
}
