package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.heqianqian20190226.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bean.HotBean;
import bean.ShowBean;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {
    private List<ShowBean.ResultBean> list;
    private Context context;

    public ShowAdapter(List<ShowBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context=viewGroup.getContext();
        View view=LayoutInflater.from(context).inflate(R.layout.hot_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAdapter.ViewHolder viewHolder, int i) {
        viewHolder.hotText.setText(list.get(i).getName());
        Uri uri=Uri.parse(list.get(i).getImageUrl());
        viewHolder.hotSdv.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView hotSdv;
        private final TextView hotText;
      /*  private final TextView hotPrice;*/

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotSdv = itemView.findViewById(R.id.hot_sdv);
            hotText = itemView.findViewById(R.id.hot_text);
           /* hotPrice = itemView.findViewById(R.id.hot_price);*/
        }
    }
}
