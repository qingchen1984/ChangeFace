package com.changeface.swb.changeface.view.paginggridview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;


import in.srain.cube.views.GridViewWithHeaderAndFooter;


public class PagingGridView extends GridViewWithHeaderAndFooter {

	public interface Pagingable {
		void onLoadMoreItems();
        void onScrollStateChanged(int scrollState,int firstVisibleItem);
	}

	private boolean isLoading;
	private boolean hasMoreItems;
	private Pagingable pagingableListener;
	private LoadingView loadingView;
    private int mFirstVisibleItem = 0;

    public LoadingView getLoadingView() {
        return loadingView;
    }

    public PagingGridView(Context context) {
		super(context);
		init();
	}

	public PagingGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PagingGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public boolean isLoading() {
		return this.isLoading;
	}

	public void setIsLoading(boolean isLoading) {
		this.isLoading = isLoading;
	}

	public void setPagingableListener(Pagingable pagingableListener) {
		this.pagingableListener = pagingableListener;
	}

	public void setHasMoreItems(boolean hasMoreItems) {
		this.hasMoreItems = hasMoreItems;
		if(!this.hasMoreItems) {
			removeFooterView(loadingView);
        }
    }

	public boolean hasMoreItems() {
		return this.hasMoreItems;
	}

	private void init() {
		isLoading = false;
		loadingView = new LoadingView(getContext());
		addFooterView(loadingView);
		setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				//DO NOTHING...
                pagingableListener.onScrollStateChanged(scrollState,mFirstVisibleItem);
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                mFirstVisibleItem = firstVisibleItem;
				if (totalItemCount > 0) {
					int lastVisibleItem = firstVisibleItem + visibleItemCount;
                    if (hasMoreItems && (lastVisibleItem == totalItemCount)) {
                        if (pagingableListener != null) {
                            hasMoreItems = false;
                            pagingableListener.onLoadMoreItems();
						}

					}
				}

			}
		});
	}


}
