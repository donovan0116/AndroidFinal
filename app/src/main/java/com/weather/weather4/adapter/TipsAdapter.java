package com.weather.weather4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weather4.R;
import com.weather.weather4.bean.OtherTipsBean;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {

    private Context context;
    private List<OtherTipsBean> otherTipsBeanList;

    public TipsAdapter(Context context, List<OtherTipsBean> otherTipsBeanList) {
        this.context = context;
        this.otherTipsBeanList = otherTipsBeanList;
    }

    @NonNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tips_item_layout, parent, false);
        return new TipsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipsViewHolder holder, int position) {
        OtherTipsBean otherTipsBean = otherTipsBeanList.get(position);
        holder.tvTitle.setText(otherTipsBean.getTitle());
        holder.tvDesc.setText(otherTipsBean.getDesc());
        holder.tvLevel.setText(otherTipsBean.getLevel());
    }

    @Override
    public int getItemCount() {
        if (otherTipsBeanList == null) return 0;
        return otherTipsBeanList.size();
    }

    class TipsViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc, tvLevel;

        public TipsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLevel = itemView.findViewById(R.id.tv_level);
        }
    }
}
