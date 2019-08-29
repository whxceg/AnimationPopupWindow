
#### AnimationPopupWindow
```
PopupWindow添加显示和消失动画
```
###### 使用
* 1、添加 maven { url 'https://www.jitpack.io' } 和  implementation 'com.github.whxceg:AnimationPopupWindow:0.0.2'
```
maven { url 'https://www.jitpack.io' }
implementation 'com.github.whxceg:AnimationPopupWindow:0.0.2'
```
* 2、把项目中的PopupWindow替换成AnimationPopupWindow并使用addAnimation给View添加动画和removeAnimation移除动画
```
 popupWindow = new AnimationPopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

 mPopupWindow.addAnimation(R.id.lv, R.anim.slide_top_to_bottom, R.anim.slide_bottom_to_top);
        
```
* 截图
![image](https://github.com/whxceg/AnimationPopupWindow/blob/master/screenshot/screenshot01.gif)
![image](https://github.com/whxceg/AnimationPopupWindow/blob/master/screenshot/screenshot02.gif)

