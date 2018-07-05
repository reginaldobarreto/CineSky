package br.com.barrsoft.cinesky.adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import org.greenrobot.eventbus.EventBus;
import java.util.List;
import br.com.barrsoft.cinesky.R;
import br.com.barrsoft.cinesky.events.AdapterEvent;
import br.com.barrsoft.cinesky.model.Models;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Models> list;
    static final String TAG = RecyclerAdapter.class.getName();

    public RecyclerAdapter(List<Models> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Models models = list.get(position);

        holder.textView.setText(models.getTitle());

        Glide.with(holder.itemView.getContext())
                .load(models.getCover_url())
                .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                holder.progressBar.setVisibility(View.GONE);
                Log.i(TAG,"onLoadFailed" + e.getMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                holder.progressBar.setVisibility(View.GONE);
                Log.i(TAG,"onResourceReady");
                return false;
            }
        })
                .error(Glide.with(holder.itemView).load(R.drawable.error))
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG,"Item "+ position + " clicado");
                EventBus.getDefault().post(new AdapterEvent(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public @BindView(R.id.imageView)
        ImageView imageView;
        public @BindView(R.id.textView)
        TextView textView;
        public @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

}

