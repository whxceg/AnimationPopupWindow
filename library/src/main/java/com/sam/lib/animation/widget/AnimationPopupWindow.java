package com.sam.lib.animation.widget;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;

public class AnimationPopupWindow extends PopupWindow {

    private Animation mShowAnimation, mFadeAnimation;

    public AnimationPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        setAnimationStyle(0);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        showAnimation();
    }

    public void setShowAndDismissAnimation(Animation showAnimation, Animation dismissAnimation) {
        this.mShowAnimation = showAnimation;
        this.mFadeAnimation = dismissAnimation;
    }


    public void setShowAndDismissAnimation(int showAnimation, int dismissAnimation) {
        if (getContentView() != null) {
            mShowAnimation = AnimationUtils.loadAnimation(getContentView().getContext(), showAnimation);
            mFadeAnimation = AnimationUtils.loadAnimation(getContentView().getContext(), dismissAnimation);
        }
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        super.showAsDropDown(anchor, xoff, yoff, gravity);
        showAnimation();
    }

    private void showAnimation() {
        if (getContentView() != null) {
            getContentView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (getContentView() != null && mShowAnimation != null)
                        getContentView().startAnimation(mShowAnimation);
                }
            }, 1);
        }
    }

    @Override
    public void dismiss() {
        if (getContentView() != null && mFadeAnimation != null) {
            getContentView().startAnimation(mFadeAnimation);
            mFadeAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    onDismissEnd();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else {
            super.dismiss();
        }
    }

    private void onDismissEnd() {
        super.dismiss();
    }

}
