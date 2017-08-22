package com.zero.wolf.greenroad.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zero.wolf.greenroad.R;
import com.zero.wolf.greenroad.litepalbean.SupportDraft;
import com.zero.wolf.greenroad.manager.CarColorManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/28.
 */

public class PreviewDraftAdapter extends RecyclerView.Adapter<PreviewDraftAdapter.PreviewPhotoHolder> {


    private final Context mContext;


    private ArrayList<SupportDraft> mPreviewList;
    private final AppCompatActivity mActivity;
    private final onPreviewItemClick mItemClick;
    // private final onItemClick mItemClick;


    public PreviewDraftAdapter(Context context, AppCompatActivity activity,
                               ArrayList<SupportDraft> previewList,
                               onPreviewItemClick onPreviewItemClick) {
        mContext = context;
        mPreviewList = previewList;
        mActivity = activity;
        //   mItemClick = onItemClick;
        mItemClick = onPreviewItemClick;
    }


    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<SupportDraft> list) {
        if (list == null) {
            this.mPreviewList = new ArrayList<SupportDraft>();
        } else {
            this.mPreviewList = (ArrayList<SupportDraft>) list;
        }
        notifyDataSetChanged();
    }

    @Override
    public PreviewPhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new PreviewPhotoHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view_preview, parent, false));

    }

    @Override
    public void onBindViewHolder(PreviewPhotoHolder holder, int position) {
//        holder.bindHolder(mPreviewList.get(position));
        //
        holder.bindHolder(mPreviewList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return mPreviewList.size();
    }


    /**
     * 设置预览信息的颜色图标
     *
     * @param imageView
     * @param color
     */
    private void setColor(ImageView imageView, String color) {
        if (imageView != null && color != null) {
            if (CarColorManager.COLOR_BLACK.equals(color)) {
                imageView.setImageDrawable(mContext.getResources()
                        .getDrawable(R.drawable.preview_item_color_black));
            }
            if (CarColorManager.COLOR_BLUE.equals(color)) {
                imageView.setImageDrawable(mContext.getResources()
                        .getDrawable(R.drawable.preview_item_color_blue));
            }
            if (CarColorManager.COLOR_YELLOW.equals(color)) {
                imageView.setImageDrawable(mContext.getResources()
                        .getDrawable(R.drawable.preview_item_color_yellow));
            }
            if (CarColorManager.COLOR_GREEN.equals(color)) {
                imageView.setImageDrawable(mContext.getResources()
                        .getDrawable(R.drawable.preview_item_color_green));
            }
            if (CarColorManager.COLOR_WHITE.equals(color)) {
                imageView.setImageDrawable(mContext.getResources()
                        .getDrawable(R.drawable.preview_item_color_white));
            }
        }
    }

    public class PreviewPhotoHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.preview_text_car_number)
        TextView mPreviewTextCarNumber;
        @BindView(R.id.preview_text_check)
        TextView mPreviewTextCheck;
        @BindView(R.id.preview_text_login)
        TextView mPreviewTextLogin;
        @BindView(R.id.preview_text_isFree)
        TextView mPreviewTextIsFree;

        @BindView(R.id.preview_text_shutTime)
        TextView mPreviewTextShutTime;


        public PreviewPhotoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindHolder(SupportDraft supportDraft, int position) {
            String check = supportDraft.getSiteCheck();
            String login = supportDraft.getSiteLogin();
            String goods = supportDraft.getGoods();
            String car_number = supportDraft.getNumber();
            String shutTime = supportDraft.getCurrent_time();
            int isFree = supportDraft.getIsFree();
            String station = supportDraft.getStation();
            String color = supportDraft.getColor();
            String scan_code = supportDraft.getScan_code();

        /*    String[] split = check.split("()");
            for (int i = 0; i < split.length; i++) {
                Logger.i(split[i]);
            }*/

            if (position % 2 == 0) {
                itemView.setBackgroundColor(Color.WHITE);
            }

            if (check != null) {
                String[] checks = check.split("/");
                mPreviewTextCheck.setText(checks[0]);
            }
            if ( login!= null) {
                String[] logins = login.split("/");
                mPreviewTextLogin.setText(logins[0]);
            }

            mPreviewTextCarNumber.setText(car_number);
            // mPreviewTextGoods.setText(goods);
            mPreviewTextLogin.setText(login);
            mPreviewTextShutTime.setText(shutTime);
            mPreviewTextIsFree.setText(isFree == 0 ? "否" : "是");
            //mPreviewTextStation.setText(scan_code);

            //setColor(mPreviewItemColorImg, color);

            itemView.setOnClickListener(v -> {
                mItemClick.itemClick(supportDraft);

            });
        }
    }


    public interface onPreviewItemClick {
        void itemClick(SupportDraft supportDraft);
    }
}
