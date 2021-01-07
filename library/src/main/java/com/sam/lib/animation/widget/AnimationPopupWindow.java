package com.sam.lib.animation.widget;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class AnimationPopupWindow extends PopupWindow {

    private List<AnimationHolder> mAnimationHolders = new ArrayList<>();

    private OnPopupWindowAnimationEndListener mAnimationEndListener;

    public void setOnPopupWindowAnimationEndListener(OnPopupWindowAnimationEndListener listener) {
        this.mAnimationEndListener = listener;
    }

    public AnimationPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        setAnimationStyle(0);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        showAnimation();
    }

    public void addAnimation(Animation showAnimation, Animation dismissAnimation) {
        addAnimation(getContentView(), showAnimation, dismissAnimation);
    }

    public void addAnimation(int showAnimationId, int dismissAnimationId) {
        addAnimation(getContentView(), showAnimationId, dismissAnimationId);
    }

    public void addAnimation(View view, int showAnimationId, int dismissAnimationId) {
        if (view != null) {
            Animation showAnimation = AnimationUtils.loadAnimation(view.getContext(), showAnimationId);
            Animation fadeAnimation = AnimationUtils.loadAnimation(view.getContext(), dismissAnimationId);
            addAnimation(view, showAnimation, fadeAnimation);
        }
    }

    public void addAnimation(int viewId, int showAnimationId, int dismissAnimationId) {
        if (getContentView() != null) {
            addAnimation(getContentView().findViewById(viewId), showAnimationId, dismissAnimationId);
        }
    }

    public void addAnimation(View view, Animation showAnimation, Animation dismissAnimation) {
        if (view != null && showAnimation != null && dismissAnimation != null)
            mAnimationHolders.add(new AnimationHolder(view, showAnimation, dismissAnimation));
    }

    public void removeAnimation(View view) {
        if (view != null && mAnimationHolders != null && mAnimationHolders.size() > 0) {

            List<AnimationHolder> removeList = new ArrayList<>();

            int size = mAnimationHolders.size();
            for (int i = 0; i < size; i++) {
                AnimationHolder holder = mAnimationHolders.get(i);
                if (holder.mView == view) {
                    removeList.add(holder);
                }
            }

            int length = removeList.size();
            for (int i = 0; i < length; i++) {
                mAnimationHolders.remove(removeList.get(i));
            }
        }
    }

    public void removeAnimation(int viewId) {
        if (getContentView() != null) {
            removeAnimation(getContentView().findViewById(viewId));
        }
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        super.showAsDropDown(anchor, xoff, yoff, gravity);
        showAnimation();
    }

    private void showAnimation() {
        if (getContentView() != null) {
            startShowAnimation();
        }
    }

    private void startShowAnimation() {
        int size = mAnimationHolders.size();
        for (int i = 0; i < size; i++) {
            final AnimationHolder holder = mAnimationHolders.get(i);
            holder.mView.startAnimation(holder.mShowAnimation);
            if (i == size - 1) {
                holder.mShowAnimation.setAnimationListener(new SimpleAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        holder.mShowAnimation.setAnimationListener(null);
                        if (mAnimationEndListener != null)
                            mAnimationEndListener.onShowAnimationEnd();
                    }
                });
            }
        }
    }

    @Override
    public void dismiss() {
        if (mAnimationHolders != null && mAnimationHolders.size() > 0) {
            startDismissAnimation();
        } else {
            super.dismiss();
        }
    }

    private void startDismissAnimation() {
        int size = mAnimationHolders.size();
        for (int i = 0; i < size; i++) {
            final AnimationHolder holder = mAnimationHolders.get(i);
            holder.mView.startAnimation(holder.mFadeAnimation);
            if (i == size - 1) {
                holder.mFadeAnimation.setAnimationListener(new SimpleAnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        holder.mFadeAnimation.setAnimationListener(null);
                        if (mAnimationEndListener != null)
                            mAnimationEndListener.onDismissAnimationEnd();
                        onDismissEnd();
                    }
                });
            }
        }
    }

    private void onDismissEnd() {
        super.dismiss();
    }

    static class AnimationHolder {
        private View mView;
        private Animation mShowAnimation, mFadeAnimation;

        public AnimationHolder(View view, Animation mShowAnimation, Animation mFadeAnimation) {
            this.mView = view;
            this.mShowAnimation = mShowAnimation;
            this.mFadeAnimation = mFadeAnimation;
        }
    }

    public static class SimpleAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    public interface OnPopupWindowAnimationEndListener {
        void onShowAnimationEnd();

        void onDismissAnimationEnd();
    }

}
