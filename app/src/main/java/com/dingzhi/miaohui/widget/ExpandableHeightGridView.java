package com.dingzhi.miaohui.widget;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * 文件名：ExpandableHeightGridView.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 18:00.
 * 功能描述: 技能选择多选
 * 函数/方法说明:
 */
public class ExpandableHeightGridView extends GridView {

	private boolean expanded = false;

	public ExpandableHeightGridView(Context context) {
		super(context);
	}

	public ExpandableHeightGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ExpandableHeightGridView(Context context, AttributeSet attrs,
									int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isExpanded() {
		return expanded;
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// HACK! TAKE THAT ANDROID!
		if (isExpanded()) {
			// Calculate entire height by providing a very large height hint.
			// View.MEASURED_SIZE_MASK represents the largest height possible.
			int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST);
			super.onMeasure(widthMeasureSpec, expandSpec);

			ViewGroup.LayoutParams params = getLayoutParams();
			params.height = getMeasuredHeight();
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

}