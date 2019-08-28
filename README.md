
#### AnimationPopupWindow
```
PopupWindow添加显示和消失动画
```
###### 实例代码
* 把项目中的PopupWindow替换成AnimationPopupWindow并使用setShowAndDismissAnimation设置动画
```
popupWindow = new AnimationPopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
popupWindow.setShowAndDismissAnimation(R.anim.show_top_to_bottom_sam_anim, R.anim.dismiss_bottom_to_top_sam_anim);
        
```
* 截图
![image](https://github.com/whxceg/AnimationPopupWindow/blob/master/screenshot/screenshot01.png)

