package com.dingzhi.miaohui.widget.weixin_keyboard;

/**
 * 自定义接口，用于给密码输入完成添加回掉事件
 */
/**
 * 文件名：OnPasswordInputFinish.
 * 版权所有：SRDZ
 * 创建人：TANXIN
 * 创建日期:2016/10/13 17:54.
 * 功能描述:防微信支付键盘——自定义接口，用于给密码输入完成添加回掉事件
 * 参考资料：https://github.com/bbbcode/KeyboardforChat
 * 函数/方法说明:
 */
public interface OnPasswordInputFinish {
    void inputFinish(String password);
}