package com.zero.wolf.greenroad.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;
import com.zero.wolf.greenroad.R;
import com.zero.wolf.greenroad.adapter.ItemTouchHelperCallback;
import com.zero.wolf.greenroad.adapter.RecycleViewDivider;
import com.zero.wolf.greenroad.adapter.SwipeDeleteAdapter;
import com.zero.wolf.greenroad.helper.DeleteHelper;
import com.zero.wolf.greenroad.helper.SortTime;
import com.zero.wolf.greenroad.litepalbean.SupportDraftOrSubmit;
import com.zero.wolf.greenroad.manager.GlobalManager;
import com.zero.wolf.greenroad.tools.ActionBarTool;
import com.zero.wolf.greenroad.tools.SPUtils;
import com.zero.wolf.greenroad.tools.ToastUtils;

import org.litepal.crud.DataSupport;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DraftActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.toolbar_draft)
    Toolbar mToolbarPreview;
    @BindView(R.id.recycler_view_preview)
    RecyclerView mRecyclerViewPreview;

    private DraftActivity mActivity;
    private Context mContext;

    private List<SupportDraftOrSubmit> mDraftList;
    public ItemTouchHelperExtension mItemTouchHelper;
    public ItemTouchHelperExtension.Callback mCallback;
    private SwipeDeleteAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);
        ButterKnife.bind(this);

        mActivity = this;
        mContext = this;
        initToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        initView();
    }

    private void initView() {

        LinearLayoutManager manager = new LinearLayoutManager(mActivity,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerViewPreview.setLayoutManager(manager);
        mRecyclerViewPreview.addItemDecoration(new RecycleViewDivider(mContext,
                LinearLayoutManager.HORIZONTAL, 10, Color.WHITE));
        mRecyclerAdapter = new SwipeDeleteAdapter(this, support -> {
            PreviewDetailActivity.actionStart(mContext, support, PreviewDetailActivity.ACTION_DRAFT_ITEM);
        });
        mRecyclerViewPreview.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.updateData(mDraftList);
        mCallback = new ItemTouchHelperCallback();
        mItemTouchHelper = new ItemTouchHelperExtension(mCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerViewPreview);
        mRecyclerAdapter.setItemTouchHelperExtension(mItemTouchHelper);

    }


    private void initData() {
        mDraftList = DataSupport.where("lite_type=?", GlobalManager.TYPE_DRAFT_LITE).find(SupportDraftOrSubmit.class);
        SortTime sortDraftTime = new SortTime();
        Collections.sort(mDraftList, sortDraftTime);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbarPreview);
        TextView title_text_view = ActionBarTool.getInstance(mActivity, 992).getTitle_text_view();
        title_text_view.setText("草稿列表");
        mToolbarPreview.setNavigationIcon(R.drawable.back_up_logo);
        mToolbarPreview.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_preview_7:
                ToastUtils.singleToast("清除七天前记录");
                DeleteHelper.getInstance().deleteInfos(this, GlobalManager.TYPE_DRAFT_LITE, SPUtils.MATH_DRAFT_LITE, 7, supportList -> {
                    mRecyclerAdapter.updateListView(supportList);
                });
                break;
            case R.id.delete_preview_15:
                ToastUtils.singleToast("清除15天前记录");
                DeleteHelper.getInstance().deleteInfos(this, GlobalManager.TYPE_DRAFT_LITE, SPUtils.MATH_DRAFT_LITE, 15, supportList -> {
                    mRecyclerAdapter.updateListView(supportList);
                });
                break;
            case R.id.delete_preview_30:
                ToastUtils.singleToast("清除30天前记录");
                DeleteHelper.getInstance().deleteInfos(this, GlobalManager.TYPE_DRAFT_LITE, SPUtils.MATH_DRAFT_LITE, 30, supportList -> {
                    mRecyclerAdapter.updateListView(supportList);
                });
                break;
            case R.id.delete_preview_all:
                ToastUtils.singleToast("清空所有记录");
                DeleteHelper.getInstance().deleteAllInfos(mContext, GlobalManager.TYPE_DRAFT_LITE, SPUtils.MATH_DRAFT_LITE, supportList -> {
                    mRecyclerAdapter.updateListView(supportList);
                });

                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preview, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.preview_item_car_number:

                break;

            default:
                break;
        }
    }
}
